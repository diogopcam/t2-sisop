//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import memory.*;

import memory.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("TESTE 1 — FIRST-FIT");
        System.out.println("=======================================");
        testarFirstFit();

        System.out.println("\n=======================================");
        System.out.println("TESTE 2 — BEST-FIT");
        System.out.println("=======================================");
        testarBestFit();

        System.out.println("\n=======================================");
        System.out.println("TESTE 3 — WORST-FIT");
        System.out.println("=======================================");
        testarWorstFit();

        System.out.println("\n=======================================");
        System.out.println("TESTE 4 — CIRCULAR-FIT");
        System.out.println("=======================================");
        testarCircularFit();
    }

    // --------------------- TESTES --------------------------
    private static void testarFirstFit() {
        Memoria memoria = new Memoria(16);

        System.out.println("Inicial:");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(A,3)");
        Politicas.firstFit(memoria, "A", 3);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(B,5)");
        Politicas.firstFit(memoria, "B", 5);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("OUT(A)");
        memoria.liberarProcesso("A");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(C,4)");
        Politicas.firstFit(memoria, "C", 4);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());
    }

    private static void testarBestFit() {
        Memoria memoria = new Memoria(30);

        System.out.println("IN(A,4)");
        Politicas.bestFit(memoria, "A", 4);  // Ocupa 4 → sobra 26
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(B,6)");
        Politicas.bestFit(memoria, "B", 6);  // Ocupa 6 → sobra 20
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(C,10)");
        Politicas.bestFit(memoria, "C", 10); // Ocupa 10 → sobra 10
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(D,6)");
        Politicas.bestFit(memoria, "D", 6);  // Ocupa 6 → sobra 4
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("OUT(B)");
        memoria.liberarProcesso("B"); // bloco LIVRE 6
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("OUT(D)");
        memoria.liberarProcesso("D"); // bloco LIVRE 6
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(X,5) -- BEST-FIT deve escolher o bloco LIVRE de tamanho 6");
        Politicas.bestFit(memoria, "X", 5);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());
    }

    private static void testarWorstFit() {
        Memoria memoria = new Memoria(30);

        System.out.println("IN(A,3)");
        Politicas.worstFit(memoria, "A", 3);  // ocupa 3 → sobra 27
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(B,4)");
        Politicas.worstFit(memoria, "B", 4);  // ocupa 4 → sobra 23
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(C,7)");
        Politicas.worstFit(memoria, "C", 7);  // ocupa 7 → sobra 16
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(D,2)");
        Politicas.worstFit(memoria, "D", 2);  // ocupa 2 → sobra 14
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("OUT(A)  // libera bloco LIVRE de 3");
        memoria.liberarProcesso("A");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("OUT(C)  // libera bloco LIVRE de 7");
        memoria.liberarProcesso("C");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(X,7) -- Deve alocar no MAIOR bloco livre (Worst-Fit)");
        Politicas.worstFit(memoria, "X", 7);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());
    }

    private static void testarCircularFit() {
        System.out.println("=======================================");
        System.out.println("TESTE 1 — Circular Fit Básico");
        System.out.println("=======================================");

        Memoria memoria = new Memoria(20);

        System.out.println("IN(A,5)");
        Politicas.circularFit(memoria, "A", 5);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(B,4)");
        Politicas.circularFit(memoria, "B", 4);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(C,3)");
        Politicas.circularFit(memoria, "C", 3);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("OUT(B)  // cria buraco no meio");
        memoria.liberarProcesso("B");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(D,2) — deve alocar NO BURACO após ponteiro");
        Politicas.circularFit(memoria, "D", 2);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("\n=======================================");
        System.out.println("TESTE 2 — Circularidade (ponteiro dá a volta)");
        System.out.println("=======================================");

        System.out.println("IN(E,7)");
        Politicas.circularFit(memoria, "E", 7);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("OUT(A)  // libera primeiro bloco");
        memoria.liberarProcesso("A");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(F,4) — ponteiro está no fim → deve VOLTAR PARA O INÍCIO");
        Politicas.circularFit(memoria, "F", 4);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("\n=======================================");
        System.out.println("TESTE 3 — Teste de divisão pós circularidade");
        System.out.println("=======================================");

        System.out.println("OUT(C)");
        memoria.liberarProcesso("C");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(G,2) — deve dividir bloco e apontar corretamente");
        Politicas.circularFit(memoria, "G", 2);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("\n=======================================");
        System.out.println("TESTE 4 — Caso extremo (múltiplos buracos)");
        System.out.println("=======================================");

        System.out.println("OUT(E)");
        memoria.liberarProcesso("E");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("OUT(D)");
        memoria.liberarProcesso("D");
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());

        System.out.println("IN(H,3) — deve escolher o primeiro bloco APÓS o ponteiro");
        Politicas.circularFit(memoria, "H", 3);
        Visualizacao.imprimirEstadoMemoria(memoria.getSegmentos());
    }
}