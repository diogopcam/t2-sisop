package memory.Politicas;
import java.util.List;
import memory.*;

public class FirstFit extends Policy {
    public FirstFit() {}

    @Override
    public int alocarNovoProcesso(List<Segmento> disponiveis, int tamanho) {
        for (int i = 0; i < disponiveis.size(); i++) {
            Segmento s = disponiveis.get(i);

            if (s.livre && s.tamanho >= tamanho) return i;
        }
        return -1;
    }
}
