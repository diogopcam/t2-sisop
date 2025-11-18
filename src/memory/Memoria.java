package memory;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private List<Segmento> segmentos;
    private int tamanhoTotal;

    public Memoria(int tamanhoTotal) {
        this.tamanhoTotal = tamanhoTotal;
        this.segmentos = new ArrayList<>();

        // Inicialmente toda a memória está livre
        segmentos.add(new Segmento(0, tamanhoTotal, true, null));
    }

    public List<Segmento> getSegmentos() {
        return segmentos;
    }

    public int getTamanhoTotal() {
        return tamanhoTotal;
    }

    /**
     * Função auxiliar para compactar blocos livres adjacentes.
     */
    public void mergeLivres() {
        for (int i = 0; i < segmentos.size() - 1; i++) {
            Segmento atual = segmentos.get(i);
            Segmento prox = segmentos.get(i + 1);

            if (atual.livre && prox.livre) {
                // Mesclar
                atual.tamanho += prox.tamanho;
                segmentos.remove(i + 1);
                i--; // voltar para conferir novos merges
            }
        }
    }

    /**
     * Inserir um novo segmento na lista na posição especificada
     * (será usado pelo módulo de políticas).
     */
    public void substituirSegmento(int index, Segmento... novos) {
        segmentos.remove(index);
        for (int i = novos.length - 1; i >= 0; i--) {
            if (novos[i] != null) {
                segmentos.add(index, novos[i]);
            }
        }
    }

    public void liberarProcesso(String id) {

        for (int i = 0; i < segmentos.size(); i++) {
            Segmento s = segmentos.get(i);

            if (!s.livre && s.processoID.equals(id)) {
                // Marca como livre
                s.livre = true;
                s.processoID = null;

                // Faz merge com vizinhos
                mergeLivres();
                return;
            }
        }
    }
}
