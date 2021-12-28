package LRController.GestGestor;

import LRModel.Gestor;
import LRModel.LojaReparacoesModel;

public class GestGestor implements IGestGestor {
    LojaReparacoesModel model;


    public boolean loginGestor(String username, String password) {


        if(!this.model.containsGestor(username)) return false;

        Gestor g = this.model.getGestor(username);
        String gPass = g.getPassword();

        return password.equals(gPass);
    }
}
