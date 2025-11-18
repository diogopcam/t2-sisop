package memory;
import java.util.List;

public class Visualizacao {

    /**
     * Exibir estado completo da memória:
     * Ex.: [A: inicio=0, tam=3] [LIVRE: inicio=3, tam=13]
     */
    public static void imprimirEstadoMemoria(List<Segmento> segmentos) {
        System.out.print("| ");

        for (Segmento s : segmentos) {
            if (s.livre) {
                System.out.print(s.tamanho + " | ");
            } else {
                System.out.print(s.processoID + ":" + s.tamanho + " | ");
            }
        }

        System.out.println();
    }

    /**
     * Exibe a quantidade total de blocos contíguos livres
     * igual ao estilo dos exemplos do PDF.
     */
    public static void imprimirBlocosLivres(List<Segmento> segmentos) {
        int total = 0;

        for (Segmento s : segmentos) {
            if (s.livre) total++;
        }

        System.out.println("Blocos contíguos livres: " + total);
    }
}

