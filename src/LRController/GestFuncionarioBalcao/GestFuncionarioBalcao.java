package LRController.GestFuncionarioBalcao;

import LRModel.FuncionarioBalcao;
import LRModel.LojaReparacoesModel;

public class GestFuncionarioBalcao {
    LojaReparacoesModel model;


    public boolean loginFuncionarioBalcao(String username, String password){

        if(!this.model.containsFuncionario(username)) return false;

        FuncionarioBalcao f = this.model.getFuncionarioBalcao(username);
        String fPass = f.getPassword();

        return password.equals(fPass);
    }

}
