package memory.Politicas;
import java.util.List;
import memory.Segmento;

public class CircularFit extends Policy {
    public CircularFit() {}
    private static int ponteiroCircular = 0;

    @Override
    public int alocarNovoProcesso(List<Segmento> disponiveis, int tamanho) {
        int n = disponiveis.size();
        int count = 0;

        while (count < n) {
            Segmento s = disponiveis.get(ponteiroCircular);

            if (s.livre && s.tamanho >= tamanho) {
                int indiceEncontrado = ponteiroCircular;
                ponteiroCircular = (ponteiroCircular + 1) % n; // atualiza o ponteiro circular
                return indiceEncontrado;
            }

            ponteiroCircular = (ponteiroCircular + 1) % n;
            count++;
        }

        return -1;
    }
}
