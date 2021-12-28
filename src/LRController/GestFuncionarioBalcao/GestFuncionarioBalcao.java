package LRController.GestFuncionarioBalcao;

import LRModel.LojaReparacoesModel;
import LRModel.PedidoExpresso;
import LRModel.PedidoOrcamento;

public class GestFuncionarioBalcao {
    LojaReparacoesModel model;

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

    // falta pensar o que vai ter as entregas
    public void registarEntregaEquipamento(String nif){

    }

}
