package business;

import business.GestTecnico.FuncionarioBalcao;

import java.util.HashMap;
import java.util.Map;

public class LojaReparacoesFacade {

    Map<String,Gestor> gestores;
    Map<String, FuncionarioBalcao> funcionariosDoBalcao;
    Map<String, Tecnico> tecnicos;
    Map<String,PedidoOrcamento> pedidos;
    Map<String,PedidoExpresso> pedidosExpressos;



    public LojaReparacoesFacade(){
        this.gestores = new HashMap<>();
        this.funcionariosDoBalcao = new HashMap<>();
        this.tecnicos = new HashMap<>();
    }


    //gestores

    public Boolean containsGestor(String username){
        return this.gestores.containsKey(username);
    }


    public Gestor getGestor(String username){
        return this.gestores.get(username);
    }


    //funcionariosBalcao

    public Boolean containsFuncionario(String username){
        return this.funcionariosDoBalcao.containsKey(username);
    }


    public FuncionarioBalcao getFuncionarioBalcao(String username){
        return this.funcionariosDoBalcao.get(username);
    }


    //tecnicos


    public Boolean containsTecnico(String username){
        return this.tecnicos.containsKey(username);
    }

    public Tecnico getTecnicos(String username){
        return this.tecnicos.get(username);
    }














}
