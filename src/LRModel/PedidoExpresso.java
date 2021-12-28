package LRModel;

public class PedidoExpresso {
    private String contacto;
    private String nif;
    private String idTecnico;

    public PedidoExpresso(String nif,String contacto){
        this.contacto = contacto;
        this.nif = nif;
    }


    // getters e setters
    public String getContacto(){
        return this.contacto;
    }

    public String getNif() {
        return this.nif;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
