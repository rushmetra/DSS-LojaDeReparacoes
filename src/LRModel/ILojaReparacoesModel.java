package LRModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public interface ILojaReparacoesModel {


    //gestor

    public Boolean containsGestor(String username);

    public Gestor getGestor(String username);

    public boolean adicionarGestor(Gestor g);

    public boolean removeGestor(String username);

    public List<Gestor> getListaDeGestores();



    //funcionariosBalcao

    public Boolean containsFuncionario(String username);

    public FuncionarioBalcao getFuncionarioBalcao(String username);

    public List<FuncionarioBalcao> getListaDeFuncionarios();

    public boolean adicionarFuncionario(FuncionarioBalcao f);
    public boolean removeFuncionario(String username);


    //tecnicos


    public Boolean containsTecnico(String username);

    public Tecnico getTecnico(String username);

    public List<Tecnico> getTecnicos();

    public boolean adicionarTecnico(Tecnico t);

    public boolean removeTecnico(String username);

    //pedidos

    public Boolean containsPedido(String id);

    public PedidoOrcamento getPedidoOrcamento(String id) ;

    public List<PedidoOrcamento> getListaPedidosOrcamento() ;




    //pedidos-expresso

    public Boolean containsPedidoExpresso(String nif);

    public PedidoExpresso getPedidoExpresso(String nif);

    public List<PedidoExpresso> getListaPedidosExpresso();


    //Funções para persisntência dos dados

    public void loadGestores(String pasta) throws IOException, ClassNotFoundException;

    public void loadFuncionarios(String pasta) throws IOException, ClassNotFoundException;

    public void loadTecnicos(String pasta) throws IOException, ClassNotFoundException ;

    public void loadPedidos(String pasta)  throws IOException, ClassNotFoundException;

    public void loadPedidosExpresso(String pasta) throws IOException, ClassNotFoundException ;

    public void loadData(String pasta) throws IOException, ClassNotFoundException;

    public void saveGestores(String pasta) throws IOException ;

    public void saveFuncionarios(String pasta) throws IOException ;

    public void saveTecnicos(String pasta) throws IOException;

    public void savePedidos(String pasta) throws IOException;

    public void savePedidosExpresso(String pasta) throws IOException ;

    public void adicionaPedidoOrcamento(PedidoOrcamento po);

    public void adicionaPedidoExpresso(PedidoExpresso pe) ;

    public void adicionaEntregaPeloCliente(Entrega e) ;

    public Entrega getEntrega(String nif) ;



}
