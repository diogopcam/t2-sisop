package memory.Politicas;
import java.util.List;
import memory.Segmento;

public class CircularFit extends Policy {
    public CircularFit() {}
    private static int ultimoEnderecoFim = 0;

    @Override
    public int alocarNovoProcesso(List<Segmento> disponiveis, int tamanho) {
        int n = disponiveis.size();
        
        int startIndex = 0;
        boolean foundStart = false;
        
        for (int i = 0; i < n; i++) {
            if (disponiveis.get(i).inicio >= ultimoEnderecoFim) {
                startIndex = i;
                foundStart = true;
                break;
            }
        }
        
        if (!foundStart) {
            startIndex = 0;
        }

        int count = 0;
        int ponteiro = startIndex;

        while (count < n) {
            Segmento s = disponiveis.get(ponteiro);

            if (s.livre && s.tamanho >= tamanho) {
                ultimoEnderecoFim = s.inicio + tamanho;
                
                return ponteiro;
            }

            ponteiro = (ponteiro + 1) % n;
            count++;
        }

        return -1;
    }
}