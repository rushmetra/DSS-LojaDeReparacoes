package business;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoOrcamento {
    String id;
    String nomeCliente;
    String contacto;
    String email;
    LocalDateTime dataPedido;
    String descricao;
    List<Passo> planoTrabalho;


    public PedidoOrcamento(String id,String nomeCliente,String contacto,String email,LocalDateTime data, String descricao,List<Passo> plano){
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.contacto = contacto;
        this.email = email;
        this.dataPedido = data;
        this.descricao = descricao;
        this.planoTrabalho = plano;
    }


    public String getIdPedido() { return this.id;}
    public LocalDateTime getDataPedido() { return this.dataPedido;}
    public String getDescricaoPedido() { return this.descricao;}

    public void adicionaPasso(String nif,float custo,float tempoPrevisto,String descricao, float tempo,boolean concluido){
        Passo p = new Passo(custo,tempo,tempoPrevisto,descricao,concluido);
        this.planoTrabalho.add(p);
    }

}