package LRController.GestFuncionarioBalcao;

import LRModel.*;

import java.util.List;

public interface IGestFuncionarioBalcao {



    public boolean loginFuncionarioBalcao(String username, String password);

    public void registarPedidoOrcamento(String nomeCliente, String contacto, String nif, String email);

    public String registarServicoExpresso(String nif,String contacto,String descricao);

    public void registarConfirmacaoOrcamento(String nif,boolean confirm);

    public void registarEntregaEquipamentoPeloCliente(String nif,String idFuncionarioBalcao);

    public void registarEntregaEquipamentoePagamento(String nif,String idFuncionarioBalcao);

    public String verificaDisponiblidadeExpresso();

    public List<FuncionarioBalcao> getFuncionarios();

    public void saveFiles();

    public List<String> getNomeFuncionarios();


}
