package LRController.Autenticacao;


import LRModel.FuncionarioBalcao;
import LRModel.Gestor;
import LRModel.LojaReparacoesModel;



public class GestAutenticacao {

    LojaReparacoesModel model;


    public boolean loginGestor(String username, String password) {


        if(!this.model.containsGestor(username)) return false;

        Gestor g = this.model.getGestor(username);
        String gPass = g.getPassword();

        return password.equals(gPass);
    }

    public boolean loginFuncionarioBalcao(String username, String password){

        if(!this.model.containsFuncionario(username)) return false;

        FuncionarioBalcao f = this.model.getFuncionarioBalcao(username);
        String fPass = f.getPassword();

        return password.equals(fPass);
    }

    public boolean loginTecnico(String username, String password){

        if(!this.model.containsTecnico(username)) return false;

        FuncionarioBalcao t = this.model.getFuncionarioBalcao(username);
        String tPass = t.getPassword();

        return password.equals(tPass);
    }




}
