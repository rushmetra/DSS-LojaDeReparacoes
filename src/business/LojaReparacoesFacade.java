package business;

import business.GestTecnico.FuncionarioBalcao;

import java.util.*;
import java.util.stream.Collectors;

public class LojaReparacoesFacade {

    Map<String,Gestor> gestores;
    Map<String, FuncionarioBalcao> funcionariosDoBalcao;
    Map<String, Tecnico> tecnicos;
    Map<String,PedidoOrcamento> pedidos;
    Map<String,PedidoExpresso> pedidosExpresso;



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

    public Tecnico getTecnico(String username){
        return this.tecnicos.get(username);
    }


    //pedidos

    public Boolean containsPedido(String id) { return this.pedidos.containsKey(id); }

    public PedidoOrcamento getPedidoOrcamento(String id) { return this.pedidos.get(id); }

    public List<PedidoOrcamento> getListaPedidosOrcamento() { // mais recente fica à cabeça
        List<PedidoOrcamento> pedidos = new ArrayList<>();
        for (PedidoOrcamento p : this.pedidos.values()) {
            pedidos.add(p);
        }
        return pedidos.stream().sorted(Comparator.comparing(PedidoOrcamento::getDataPedido).reversed())
                .collect(Collectors.toList());
    }

    //pedidos-expresso

    public Boolean containsPedidoExpresso(String id) { return this.pedidosExpresso.containsKey(id); }

    public PedidoExpresso getPedidoExpresso(String id) { return this.pedidosExpresso.get(id); }
}
