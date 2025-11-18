package memory;

public class Segmento {
    public int inicio;
    public int tamanho;
    public boolean livre;
    public String processoID;

    public Segmento(int inicio, int tamanho, boolean livre, String processoID) {
        this.inicio = inicio;
        this.tamanho = tamanho;
        this.livre = livre;
        this.processoID = processoID;
    }

    @Override
    public String toString() {
        if (livre) {
            return "[LIVRE: inicio=" + inicio + ", tam=" + tamanho + "]";
        } else {
            return "[" + processoID + ": inicio=" + inicio + ", tam=" + tamanho + "]";
        }
    }
}
