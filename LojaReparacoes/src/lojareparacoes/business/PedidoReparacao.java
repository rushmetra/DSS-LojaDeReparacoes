package lojareparacoes.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PedidoReparacao {
    private String id;  // id do Pedido
    private String info; // informações
    private String tipo; // tipo de serviço
    private boolean concluido;
    private Date dataEmissao;       //data de pedido de reparacao
    private Date dataPagamento;     //data de pagamento da reparacao
    private int horasGastas;
    private Map<String, Double> pecasGastas;    //String é o ID da peça; double é o preco da peça


    /* Construtores */


    //Getters & setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public int getHorasGastas() {
        return horasGastas;
    }

    public void setHorasGastas(int horasGastas) {
        this.horasGastas = horasGastas;
    }

    public Map<String, Double> getPecasGastas() {
        return pecasGastas;
    }

    public void setPecasGastas(Map<String, Double> pecasGastas) {
        this.pecasGastas = pecasGastas;
    }


    /**
     * Método que permite obter o custo total das peças
     * @return O custo total das peças gastas num pedido
     */
    public double getCustoTotalPecas () {
        double total = 0;
        for (double valorPeca : this.pecasGastas.values()) {
            total += valorPeca;
        }
        return total;
    }
}
