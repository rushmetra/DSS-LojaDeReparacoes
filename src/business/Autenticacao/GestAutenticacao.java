package business.Autenticacao;


import business.GestTecnico.FuncionarioBalcao;
import business.Gestor;
import business.LojaReparacoesFacade;
import data.GestorDAO;


public class GestAutenticacao {


    public boolean loginGestor(String username, String password, LojaReparacoesFacade lj) {


        if(!lj.containsGestor(username)) return false;

        Gestor g = lj.getGestor(username);
        String gPass = g.getPassword();

        return password.equals(gPass);
    }

    public boolean loginFuncionarioBalcao(String username, String password, LojaReparacoesFacade lj){

        if(!lj.containsFuncionario(username)) return false;

        FuncionarioBalcao f = lj.getFuncionarioBalcao(username);
        String fPass = f.getPassword();

        return password.equals(fPass);
    }

    public boolean loginTecnico(String username, String password, LojaReparacoesFacade lj){

        if(!lj.containsTecnico(username)) return false;

        FuncionarioBalcao t = lj.getFuncionarioBalcao(username);
        String tPass = t.getPassword();

        return password.equals(tPass);
    }




}
