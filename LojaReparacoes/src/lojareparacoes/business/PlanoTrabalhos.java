package lojareparacoes.business;

import java.util.List;
import java.util.Map;

public class PlanoTrabalhos {
    private double custoTotalPecas;
    private double totalHorasTrabalho;
    private List<Passo> passosReparacao; // lista de passos de reparação


    public double getCustoTotalPecas() {
        return custoTotalPecas;
    }

    public void setCustoTotalPecas(double custoTotalPecas) {
        this.custoTotalPecas = custoTotalPecas;
    }

    public double getTotalHorasTrabalho() {
        return totalHorasTrabalho;
    }

    public void setTotalHorasTrabalho(double totalHorasTrabalho) {
        this.totalHorasTrabalho = totalHorasTrabalho;
    }
}
