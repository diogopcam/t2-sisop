package memory.Politicas;
import java.util.List;
import memory.*;

public class BestFit extends Policy {
    public BestFit() {}

    @Override
    public int alocarNovoProcesso(List<Segmento> disponiveis, int tamanho) {
        int melhorIndex = -1;
        int menorFolga = Integer.MAX_VALUE;

        for (int i = 0; i < disponiveis.size(); i++) {
            Segmento s = disponiveis.get(i);

            if (s.livre && s.tamanho >= tamanho) {
                int folga = s.tamanho - tamanho;

                if (folga < menorFolga) {
                    menorFolga = folga;
                    melhorIndex = i;
                }
            }
        }

        return melhorIndex;
    }
}
