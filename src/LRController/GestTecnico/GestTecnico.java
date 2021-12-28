package LRController.GestTecnico;

import LRModel.FuncionarioBalcao;
import LRModel.LojaReparacoesModel;
import LRModel.PedidoOrcamento;

public class GestTecnico implements IGestTecnico{
    LojaReparacoesModel model;

    public void registarPlanoTrabRep(String nif){}
    public void assinalaExecucaoPasso(String nif){}
    public PedidoOrcamento determinaEquipamentoMaisUrgente(){ return null;}




    public boolean loginTecnico(String username, String password){

        if(!this.model.containsTecnico(username)) return false;

        FuncionarioBalcao t = this.model.getFuncionarioBalcao(username);
        String tPass = t.getPassword();

        return password.equals(tPass);
    }

}
