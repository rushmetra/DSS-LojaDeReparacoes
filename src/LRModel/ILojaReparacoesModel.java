package LRModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public interface ILojaReparacoesModel {
    public Boolean containsGestor(String username);


    public Gestor getGestor(String username);



    //funcionariosBalcao

    public Boolean containsFuncionario(String username);


    public FuncionarioBalcao getFuncionarioBalcao(String username);


    //tecnicos


    public Boolean containsTecnico(String username);

    public Tecnico getTecnico(String username);

    //pedidos

    public Boolean containsPedido(String id);

    public PedidoOrcamento getPedidoOrcamento(String id) ;

    public List<PedidoOrcamento> getListaPedidosOrcamento() ;

    //pedidos-expresso

    public Boolean containsPedidoExpresso(String nif);

    public PedidoExpresso getPedidoExpresso(String nif);


    //Funções para persisntência dos dados

    public void loadGestores(String pasta) ;
    public void loadFuncionarios(String pasta) ;

    public void loadTecnicos(String pasta) ;

    public void loadPedidos(String pasta) ;

    public void loadPedidosExpresso(String pasta) ;


    public void loadData(String pasta) ;

    public void saveGestores(String pasta) ;

    public void saveFuncionarios(String pasta) ;

    public void saveTecnicos(String pasta) ;

    public void savePedidos(String pasta) ;

    public void savePedidosExpresso(String pasta) ;





    public void adicionaPedidoOrcamento(PedidoOrcamento po);


    public void adicionaPedidoExpresso(PedidoExpresso pe) ;

    public void adicionaEntregaPeloCliente(Entrega e) ;

    public Entrega getEntrega(String nif) ;



}
