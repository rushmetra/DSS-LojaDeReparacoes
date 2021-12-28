package LRModel;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoOrcamento {
    private String id;
    private String nomeCliente;
    private String contacto;
    private String email;
    private LocalDateTime dataPedido;
    private String descricao;
    private List<Passo> planoTrabalho;
    private String idTecnico;
    private boolean confirmacaoReparacao;
    private boolean conlusaoPedido;


    public PedidoOrcamento(String id,String nomeCliente,String contacto,String email,LocalDateTime data, String descricao,List<Passo> plano){
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.contacto = contacto;
        this.email = email;
        this.dataPedido = data;
        this.descricao = descricao;
        this.planoTrabalho = plano;
        this.idTecnico = "";
        this.confirmacaoReparacao = false;
        this.conlusaoPedido = false;
    }

    public PedidoOrcamento(String nif,String nomeCliente,String contacto,String email){
        this.nomeCliente = nomeCliente;
        this.contacto = contacto;
        this.email = email;
        this.id = nif;
        this.descricao = "";
        this.planoTrabalho = new ArrayList<>();
        this.idTecnico = "";
        this.confirmacaoReparacao = false;
        this.conlusaoPedido = false;
    }

    // getters e setters

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public String getContacto(){
        return this.contacto;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() { return this.id;}

    public List<Passo> getPlanoTrabalho() {
        List<Passo> res = new ArrayList<>();
        for(Passo p : this.planoTrabalho) {
            res.add(p);
        }
        return res;
    }

    public void setPlanoTrabalho(List<Passo> planoTrabalho) {
        for(Passo p : planoTrabalho) {
            this.planoTrabalho.add(p);
        }
    }

    public boolean isConfirmacaoReparacao() {
        return confirmacaoReparacao;
    }

    public boolean isConlusaoPedido() {
        return conlusaoPedido;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public LocalDateTime getDataPedido() { return this.dataPedido;}

    public String getDescricaoPedido() { return this.descricao;}

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setConfirmacaoReparacao(boolean confirmacaoReparacao) {
        this.confirmacaoReparacao = confirmacaoReparacao;
    }

    public void setConlusaoPedido(boolean conlusaoPedido) {
        this.conlusaoPedido = conlusaoPedido;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }


    public void adicionaPasso(String nif, float custo, LocalTime tempoPrevisto, String descricao, boolean concluido){
        Passo p = new Passo(custo,tempoPrevisto,descricao,concluido);
        this.planoTrabalho.add(p);
    }

}