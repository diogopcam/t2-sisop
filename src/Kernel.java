import java.util.ArrayList;
import memory.MemoryManager;

public class Kernel {

    private Scheduler scheduler;
    private MemoryManager memoryManager;
    private ArrayList<PCB> prontos = new ArrayList<>();

    public Kernel(MemoryManager memoryManager) {
        System.out.println("\n(Kernel) INFO: Kernel iniciado.");

        this.scheduler = new Scheduler();
        this.memoryManager = memoryManager;
    }

    // public void verificarFilaProntos() {
    //     while (temProcessos()) {
    //         if (temProcessos()) {
    //             PCB p = scheduler.selecionarProximo(prontos);

    //             if (p != null) {
    //                 System.out.println("(Kernel) INFO: Processo selecionado: " + p.id + ".\n");
    //                 memoryManager.alocarProcesso(p.id, p.tamanho);
    //                 removerProcessoFila(p.id);
    //             }
    //         }
    //     }
    // }

    public boolean criarProcesso(String id, int tamanho) {
        if (hasProcessoID(id)) {
            System.out.println("(Kernel) ERRO: Processo " + id + " já existe.\n");
            return false;
        }

        prontos.add(new PCB(id, tamanho));

        // estamos fazendo de forma direta aqui, sem rotinas de interrupção
        PCB p = scheduler.selecionarProximo(prontos);
        if (p != null) {
            System.out.println("(Kernel) INFO: Processo selecionado: " + p.id + ".\n");
            memoryManager.alocarProcesso(p.id, p.tamanho);
            removerProcessoFila(p.id);
        }  
        return true;
    }

    public boolean hasProcessoID(String id) {
        for (PCB p : prontos) {
            if (p.id.equals(id)) return true;
        }
        return false;
    }

    public void removerProcessoMemoria(String id) {
        if (hasProcessoID(id)) removerProcessoFila(id);
        else memoryManager.desalocarProcesso(id); 
    }

    public void removerProcessoFila(String id) { prontos.removeIf(p -> p.id.equals(id)); }
    public boolean temProcessos() { return !prontos.isEmpty(); }
}
