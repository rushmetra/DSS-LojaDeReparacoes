package LRModel;

import java.io.Serializable;

public class Entrega implements Serializable {
    private String nif;
    private String idfuncionarioBalcao;
    private boolean entregue;
    private boolean pago;

    public Entrega (String nif, String idfuncionarioBalcao){
        this.nif = nif;
        this.idfuncionarioBalcao = idfuncionarioBalcao;
        this.entregue = false;
        this.pago = false;
    }

    public String getNif() {
        return this.nif;
    }

    public String getIdfuncionarioBalcao() {
        return this.idfuncionarioBalcao;
    }

    public boolean isEntregue() {
        return this.entregue;
    }

    public boolean isPago(){
        return this.pago;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setIdfuncionarioBalcao(String idfuncionarioBalcao) {
        this.idfuncionarioBalcao = idfuncionarioBalcao;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
