package memory;
import memory.Politicas.*;
import java.util.ArrayList;
import java.util.List;

public class MemoryManager {

    private Memory memory;
    private Policy politica;
    private List<String> processosAlocados = new ArrayList<>();

    public MemoryManager(int tamanhoMemoria, String politicaNome) {
        this.memory = new Memory(tamanhoMemoria);
        this.processosAlocados = new ArrayList<>();
        
        String regex = "[^a-zA-Z0-9]";
        politicaNome = politicaNome.replaceAll(regex, "").toLowerCase();

        if (politicaNome.equalsIgnoreCase("firstfit")) {
            this.politica = new FirstFit();
        } else if (politicaNome.equalsIgnoreCase("bestfit")) {
            this.politica = new BestFit();
        } else if (politicaNome.equalsIgnoreCase("worstfit")) {
            this.politica = new WorstFit();
        } else if (politicaNome.equalsIgnoreCase("circularfit")) {
            this.politica = new CircularFit();
        } else {
            throw new IllegalArgumentException("\n(MM) ERRO: Política de alocação desconhecida: " + politicaNome);
        }
    }

    public void alocarProcesso(String id, int tamanho) {
        List<Segmento> disponiveis = getSegmentosDisponiveis();
        int index = politica.alocarNovoProcesso(disponiveis, tamanho);
        
        if (index == -1) 
            System.out.println("\n(MM) ERRO: ESPAÇO INSUFICIENTE DE MEMORIA");
        else if (index >= 0) {
            Segmento s = disponiveis.get(index);

            if (s == null) {
                System.out.println("\n(MM) ERRO: Segmento nulo retornado pela política de alocação.");
                return;
            }

            if (s.tamanho == tamanho) {
                memory.substituirSegmento(index, new Segmento(s.inicio, tamanho, false, id));
                System.out.println("\n(MM) INFO: Processo " + id + " alocado com sucesso na memória.");
            } else {
                Segmento ocupado = new Segmento(s.inicio, tamanho, false, id);
                Segmento livreRestante = new Segmento(s.inicio + tamanho, s.tamanho - tamanho, true, null);
                System.out.println("\n(MM) INFO: Processo " + id + " alocado com sucesso na memória com divisão de segmento. Sobra de " + livreRestante.tamanho + " unidades.");

                memory.substituirSegmento(index, ocupado, livreRestante);
            }
            processosAlocados.add(id);
        }
        else {
            System.out.println("\n(MM) ERRO: Índice inválido retornado pela política de alocação.");
        }
    }

    public List<Segmento> getSegmentosDisponiveis() {
        List<Segmento> disponiveis = new ArrayList<>();
        
        for (Segmento s : memory.getSegmentos()) {
            if (s.livre) disponiveis.add(s);
        }

        return disponiveis;
    }

    public void desalocarProcesso(String id) {
        if (!processosAlocados.contains(id)) {
            System.out.println("\n(MM) ERRO: Processo " + id + " não está alocado na memória.");
            return;
        }

        memory.liberarProcesso(id);
        processosAlocados.remove(id);
        System.out.println("\n(MM) INFO: Processo " + id + " desalocado com sucesso da memória.");
    }
}
