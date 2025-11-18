package memory;
import java.util.List;

public class Politicas {

    // Usado apenas pelo Circular-Fit
    private static int ponteiroCircular = 0;

    /**
     * FIRST-FIT
     * Aloca no primeiro bloco livre que caiba.
     */
    public static boolean firstFit(Memoria memoria, String id, int tamanho) {

        List<Segmento> segmentos = memoria.getSegmentos();

        for (int i = 0; i < segmentos.size(); i++) {
            Segmento s = segmentos.get(i);

            if (s.livre && s.tamanho >= tamanho) {

                // Caso 1: bloco é exatamente igual ao tamanho solicitado
                if (s.tamanho == tamanho) {
                    segmentos.set(i, new Segmento(s.inicio, tamanho, false, id));
                }
                // Caso 2: bloco é maior — divide
                else {
                    Segmento ocupado = new Segmento(s.inicio, tamanho, false, id);
                    Segmento livreRestante = new Segmento(s.inicio + tamanho, s.tamanho - tamanho, true, null);

                    memoria.substituirSegmento(i, ocupado, livreRestante);
                }

                return true;
            }
        }

        System.out.println("ESPAÇO INSUFICIENTE DE MEMORIA");
        return false;
    }

    /**
     * BEST-FIT
     * Encontra o menor bloco livre que comporte o processo.
     */
    public static boolean bestFit(Memoria memoria, String id, int tamanho) {

        List<Segmento> segmentos = memoria.getSegmentos();
        int melhorIndex = -1;
        int menorFolga = Integer.MAX_VALUE;

        for (int i = 0; i < segmentos.size(); i++) {
            Segmento s = segmentos.get(i);

            if (s.livre && s.tamanho >= tamanho) {
                int folga = s.tamanho - tamanho;

                if (folga < menorFolga) {
                    menorFolga = folga;
                    melhorIndex = i;
                }
            }
        }

        if (melhorIndex == -1) {
            System.out.println("ESPAÇO INSUFICIENTE DE MEMORIA");
            return false;
        }

        Segmento escolhido = segmentos.get(melhorIndex);

        if (escolhido.tamanho == tamanho) {
            segmentos.set(melhorIndex, new Segmento(escolhido.inicio, tamanho, false, id));
        } else {
            Segmento ocupado = new Segmento(escolhido.inicio, tamanho, false, id);
            Segmento resto = new Segmento(escolhido.inicio + tamanho, escolhido.tamanho - tamanho, true, null);

            memoria.substituirSegmento(melhorIndex, ocupado, resto);
        }

        return true;
    }

    /**
     * WORST-FIT
     * Encontra o maior bloco livre.
     */
    public static boolean worstFit(Memoria memoria, String id, int tamanho) {

        List<Segmento> segmentos = memoria.getSegmentos();
        int piorIndex = -1;
        int maiorTamanho = -1;

        for (int i = 0; i < segmentos.size(); i++) {
            Segmento s = segmentos.get(i);

            if (s.livre && s.tamanho >= tamanho) {
                if (s.tamanho > maiorTamanho) {
                    maiorTamanho = s.tamanho;
                    piorIndex = i;
                }
            }
        }

        if (piorIndex == -1) {
            System.out.println("ESPAÇO INSUFICIENTE DE MEMORIA");
            return false;
        }

        Segmento escolhido = segmentos.get(piorIndex);

        if (escolhido.tamanho == tamanho) {
            segmentos.set(piorIndex, new Segmento(escolhido.inicio, tamanho, false, id));
        } else {
            Segmento ocupado = new Segmento(escolhido.inicio, tamanho, false, id);
            Segmento resto = new Segmento(escolhido.inicio + tamanho, escolhido.tamanho - tamanho, true, null);

            memoria.substituirSegmento(piorIndex, ocupado, resto);
        }

        return true;
    }

    /**
     * CIRCULAR-FIT
     * Busca circular a partir do ponteiro atual.
     */
    public static boolean circularFit(Memoria memoria, String id, int tamanho) {

        List<Segmento> segmentos = memoria.getSegmentos();

        if (segmentos.isEmpty()) {
            System.out.println("ESPAÇO INSUFICIENTE DE MEMORIA");
            return false;
        }

        int inicioBusca = ponteiroCircular; // salva ponto inicial

        while (true) {

            // Atualiza tamanho da lista dinamicamente
            int n = segmentos.size();

            // Garante que o ponteiro nunca aponte para fora da lista
            if (ponteiroCircular >= n) {
                ponteiroCircular = 0;
            }

            Segmento s = segmentos.get(ponteiroCircular);

            // Bloco encontrado!
            if (s.livre && s.tamanho >= tamanho) {

                int index = ponteiroCircular;

                // Caso tamanho exato
                if (s.tamanho == tamanho) {
                    segmentos.set(index, new Segmento(s.inicio, tamanho, false, id));
                }
                // Caso maior → dividir
                else {
                    Segmento ocupado = new Segmento(s.inicio, tamanho, false, id);
                    Segmento resto = new Segmento(s.inicio + tamanho, s.tamanho - tamanho, true, null);

                    memoria.substituirSegmento(index, ocupado, resto);
                }

                // Recalcular tamanho da lista após divisão
                int novoN = memoria.getSegmentos().size();

                // Avança ponteiro corretamente
                ponteiroCircular = (index + 1) % novoN;

                return true;
            }

            // Avançar ponteiro circularmente
            ponteiroCircular++;
            if (ponteiroCircular >= segmentos.size()) {
                ponteiroCircular = 0;
            }

            // Se voltou ao ponto inicial → não encontrou nada
            if (ponteiroCircular == inicioBusca) break;
        }

        System.out.println("ESPAÇO INSUFICIENTE DE MEMORIA");
        return false;
    }
}