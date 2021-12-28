package LRModel;

public class Passo {
    private String descricao;
    private float tempo;
    private float tempoPrevisto;
    private float custo;
    private boolean concluido;


    public Passo(float custo,float tempo,float tempoPrevisto,String descricao,boolean concluido){
        this.custo = custo;
        this.tempo = tempo;
        this.tempoPrevisto = tempoPrevisto;
        this.descricao = descricao;
        this.concluido = concluido;
    }

    public String getDescricao() { return this.descricao;}
    public float getTempo() { return this.tempo;}
    public float getTempoPrevisto() { return this.tempoPrevisto; }
    public float getCusto() { return this.custo;}
    public boolean getConcluido() { return this.concluido; }
}