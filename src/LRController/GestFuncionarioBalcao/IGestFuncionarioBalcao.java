package LRController.GestFuncionarioBalcao;

import LRModel.Entrega;
import LRModel.PedidoExpresso;
import LRModel.PedidoOrcamento;

public interface IGestFuncionarioBalcao {
    public boolean loginFuncionarioBalcao(String username, String password);

    public void registarPedidoOrcamento(String nomeCliente, String contacto, String nif, String email);

    public String registarServicoExpresso(String nif,String contacto);

    public void registarConclusaoPedido(String nif);

    public void registarConfirmacaoReparacao(String nif);

    public void registarEntregaEquipamentoPeloCliente(String nif,String idFuncionarioBalcao);

    public void registarEntregaEquipamentoePagamento(String nif);

}
