package memory.Politicas;
import java.util.List;
import memory.Segmento;

public abstract class Policy {
    public abstract int alocarNovoProcesso(List<Segmento> disponiveis, int tamanho);
}