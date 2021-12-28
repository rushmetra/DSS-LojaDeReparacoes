package LRController.GestFuncionarioBalcao;

import LRModel.*;

public class GestFuncionarioBalcao {
    ILojaReparacoesModel model;

    public void registarPedido(String nomeCliente, String contacto, String nif, String email){
        PedidoOrcamento po = new PedidoOrcamento(nomeCliente,contacto,email,nif);
        model.adicionaPedidoOrcamento(po);
    }

    public void registarServicoExpresso(String nif,String contacto){
        PedidoExpresso pe = new PedidoExpresso(nif,contacto);
        model.adicionaPedidoExpresso(pe);
    }

    public void registarConclusaoPedido(String nif){
        PedidoOrcamento po = this.model.getPedidoOrcamento(nif);
        po.setConlusaoPedido(true);
    }

    public void registarConfirmacaoReparacao(String nif){
        PedidoOrcamento po = this.model.getPedidoOrcamento(nif);
        po.setConfirmacaoReparacao(true);
    }

    public void registarEntregaEquipamentoPeloCliente(String nif,String idFuncionarioBalcao){
        Entrega e = new Entrega(nif,idFuncionarioBalcao,false,false);
        model.adicionaEntregaPeloCliente(e);
    }

    public void registarEntregaEquipamentoePagamento(String nif){
        Entrega e = this.model.getEntrega(nif);
        e.setEntregue(true);
        e.setPago(true);
    }



}
