package memory.Politicas;
import java.util.List;
import memory.*;

public class WorstFit extends Policy {
    public WorstFit() {}

    @Override
    public int alocarNovoProcesso(List<Segmento> disponiveis, int tamanho) {
        int piorIndex = -1;
        int maiorFolga = -1;

        for (int i = 0; i < disponiveis.size(); i++) {
            Segmento s = disponiveis.get(i);

            if (s.livre && s.tamanho >= tamanho) {
                int folga = s.tamanho - tamanho;

                if (folga > maiorFolga) {
                    maiorFolga = folga;
                    piorIndex = i;
                }
            }
        }
        return piorIndex;
    }
}
