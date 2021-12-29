package LRModel;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LojaReparacoesModel implements ILojaReparacoesModel {

    Map<String, Gestor> gestores;
    Map<String, FuncionarioBalcao> funcionariosDoBalcao;
    Map<String, Tecnico> tecnicos;
    Map<String, PedidoOrcamento> pedidos;
    Map<String, PedidoExpresso> pedidosExpressos;
    Map<String, Entrega> entregas;



    public LojaReparacoesModel() {

        this.gestores = new HashMap<>();
        this.funcionariosDoBalcao = new HashMap<>();
        this.tecnicos = new HashMap<>();
        this.pedidos = new HashMap<>();
        this.pedidosExpressos = new HashMap<>();
        this.entregas = new HashMap<>();

        File file = new File("saves"); //alterar pasta possivelmente
        if (!file.exists()) {
            this.gestores = new HashMap<>();
            this.funcionariosDoBalcao = new HashMap<>();
            this.tecnicos = new HashMap<>();
        } else {

            try {
                loadData("saves");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //gestores

    public Boolean containsGestor(String username){
        return this.gestores.containsKey(username);
    }


    public Gestor getGestor(String username){
        return this.gestores.get(username);
    }

    public boolean adicionarGestor(Gestor g){

        if(this.gestores.containsKey(g.getUsername())) return false;

        this.gestores.put(g.getUsername(),g);

        return true;
    }

    public boolean removeGestor(String username){

        if(!this.gestores.containsKey(username)) return false;

        this.gestores.remove(username);

        return true;

    }

    public List<String> getNomeGestores() {
        return this.getListaDeGestores().stream().map(Gestor::getUsername).collect(Collectors.toList());
    }

    public List<Gestor> getListaDeGestores() {
        return this.gestores.values().stream().collect(Collectors.toList());
    }

    //funcionariosBalcao

    public Boolean containsFuncionario(String username){
        return this.funcionariosDoBalcao.containsKey(username);
    }


    public FuncionarioBalcao getFuncionarioBalcao(String username){
        return this.funcionariosDoBalcao.get(username);
    }

    public List<String> getNomeFuncionarios() {
        return this.getListaDeFuncionarios().stream().map(FuncionarioBalcao::getUsername).collect(Collectors.toList());
    }

    public List<FuncionarioBalcao> getListaDeFuncionarios() {
        return this.funcionariosDoBalcao.values().stream().collect(Collectors.toList());
    }

    public boolean adicionarFuncionario(FuncionarioBalcao f){

        if(this.funcionariosDoBalcao.containsKey(f.getUsername())) return false;

        this.funcionariosDoBalcao.put(f.getUsername(),f);

        return true;
    }

    public boolean removeFuncionario(String username){

        if(!this.funcionariosDoBalcao.containsKey(username)) return false;

        this.funcionariosDoBalcao.remove(username);

        return true;

    }



    //tecnicos


    public Boolean containsTecnico(String username){
        return this.tecnicos.containsKey(username);
    }

    public Tecnico getTecnico(String username){
        return this.tecnicos.get(username);
    }

    public List<String> getNomeTecnicos() {
        return this.getTecnicos().stream().map(Tecnico::getUsername).collect(Collectors.toList());
    }

    public List<Tecnico> getTecnicos(){
        return  this.tecnicos.values().stream().collect(Collectors.toList());
    }

    public boolean adicionarTecnico(Tecnico t){

        if(this.tecnicos.containsKey(t.getUsername())) return false;

        this.tecnicos.put(t.getUsername(),t);

        return true;
    }

    public boolean removeTecnico(String username){

        if(!this.tecnicos.containsKey(username)) return false;

        this.tecnicos.remove(username);

        return true;

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

    public Boolean containsPedidoExpresso(String nif) {
        return this.pedidosExpressos.containsKey(nif);
    }

    public PedidoExpresso getPedidoExpresso(String nif) {
        return this.pedidosExpressos.get(nif);
    }

    public List<PedidoExpresso> getListaPedidosExpresso() {
        List<PedidoExpresso> pedidosExpresso = new ArrayList<>();
        for(PedidoExpresso pe : this.pedidosExpressos.values()) {
            pedidosExpresso.add(pe);
        }
        return pedidosExpresso;
    }

    //Funções para persisntência dos dados

    public void loadGestores(String pasta) throws IOException, ClassNotFoundException {

        File toRead = new File(pasta + "/gestores.txt");
        FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);

        this.gestores = (HashMap<String,Gestor>) ois.readObject();


        ois.close();
        fis.close();

    }

    public void loadFuncionarios(String pasta) throws IOException, ClassNotFoundException {

        File toRead = new File(pasta + "/funcionarios.txt");
        FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);

        this.funcionariosDoBalcao = (HashMap<String,FuncionarioBalcao>) ois.readObject();


        ois.close();
        fis.close();

    }

    public void loadTecnicos(String pasta) throws IOException, ClassNotFoundException {

        File toRead = new File(pasta + "/tecnicos.txt");
        FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);

        this.tecnicos = (HashMap<String,Tecnico>) ois.readObject();


        ois.close();
        fis.close();

    }

    public void loadPedidos(String pasta) throws IOException, ClassNotFoundException {

        File toRead = new File(pasta + "/pedidos.txt");
        FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);

        this.gestores = (HashMap<String,Gestor>) ois.readObject();


        ois.close();
        fis.close();

    }

    public void loadPedidosExpresso(String pasta) throws IOException, ClassNotFoundException {

        File toRead = new File(pasta + "/pedidos-expresso.txt");
        FileInputStream fis = new FileInputStream(toRead);
        ObjectInputStream ois = new ObjectInputStream(fis);

        this.pedidosExpressos = (HashMap<String,PedidoExpresso>) ois.readObject();


        ois.close();
        fis.close();

    }


    public void loadData(String pasta) throws IOException, ClassNotFoundException {

        loadTecnicos(pasta);
        loadFuncionarios(pasta);
        loadGestores(pasta);
        loadPedidos(pasta);
        loadPedidosExpresso(pasta);
    }

    public void saveGestores(String pasta) throws IOException {

        File file = new File(pasta + "/gestores.txt");
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(this.gestores);
        oos.flush();
        oos.close();
        fos.close();


    }

    public void saveFuncionarios(String pasta) throws java.io.IOException {

        File file = new File(pasta + "/funcionarios.txt");
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(this.funcionariosDoBalcao);
        oos.flush();
        oos.close();
        fos.close();


    }

    public void saveTecnicos(String pasta) throws IOException {

        File file = new File(pasta + "/tecnicos.txt");
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(this.tecnicos);
        oos.flush();
        oos.close();
        fos.close();


    }


    public void savePedidos(String pasta) throws IOException {

        File file = new File(pasta + "/pedidos.txt");
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(this.gestores);
        oos.flush();
        oos.close();
        fos.close();


    }

    public void savePedidosExpresso(String pasta) throws IOException {

        File file = new File(pasta + "/pedidos-expresso.txt");
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(this.pedidosExpressos);
        oos.flush();
        oos.close();
        fos.close();

    }


    public void saveData(String pasta){


            File folder = new File(pasta);
            if(!folder.exists()) folder.mkdir();

        try {
            saveFuncionarios(pasta);
            saveGestores(pasta);
            saveTecnicos(pasta);
            savePedidos(pasta);
            savePedidosExpresso(pasta);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void adicionaPedidoOrcamento(PedidoOrcamento po){
        this.pedidos.put(po.getId(),po);
    }

    public void adicionaPedidoExpresso(PedidoExpresso pe) {
        this.pedidosExpressos.put(pe.getNif(),pe);
    }

    public void adicionaEntregaPeloCliente(Entrega e) {
        this.entregas.put(e.getNif(),e);
    }

    public Entrega getEntrega(String nif) {
        return this.entregas.get(nif);
    }
}
