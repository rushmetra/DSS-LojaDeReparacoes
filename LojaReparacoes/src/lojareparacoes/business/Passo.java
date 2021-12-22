package lojareparacoes.business;

public class Passo {
    private double custo;
    private double tempo;

    public Passo(double custo, double tempo) {
        this.custo = custo;
        this.tempo = tempo;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
}
