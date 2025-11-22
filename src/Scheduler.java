import java.util.ArrayList;

// FCFS por padrao
public class Scheduler {

   public Scheduler() {} 

   public PCB selecionarProximo(ArrayList<PCB> prontos) {
       if (prontos.isEmpty()) return null;

       PCB p = prontos.get(0);
       System.out.println("\n(Scheduler) INFO: Proximo processo da lista: " + p.id);

       return p;
   }
}

