package LRController.GestGestor;

import LRModel.*;

import java.util.*;

public class GestGestor implements IGestGestor {
    LojaReparacoesModel model;


    public boolean loginGestor(String username, String password) {
        if(!this.model.containsGestor(username)) return false;

        Gestor g = this.model.getGestor(username);
        String gPass = g.getPassword();

        return password.equals(gPass);
    }

    public Gestor getGestor(String id) {
        return this.model.getGestor(id);
    }



    public boolean adicionarGestor(String username, String password){

        Gestor g = new Gestor(username,password);
        return this.model.adicionarGestor(g);
    }

    public List<Gestor> getGestores() {
        return this.model.getListaDeGestores();
    }

    public boolean adicionarFuncionarioBalcao(String username, String password){

        FuncionarioBalcao fb = new FuncionarioBalcao(username,password, 0,0);
        return this.model.adicionarFuncionario(fb);
    }


    public boolean adicionarTecnico(String username, String password){

        Tecnico t = new Tecnico(username,password,false);
        return this.model.adicionarTecnico(t);
    }

    public boolean removerGestor(String username){
        return this.model.removeGestor(username);
    }

    public boolean removerFuncionarioBalcao(String username){
        return this.model.removeFuncionario(username);
    }

    public boolean removerTecnico(String username){
        return this.model.removeTecnico(username);
    }



    /*
    uma listagem em que para cada técnico de reparações é indicado o
    número de reparações programadas/expresso realizadas, a duração média
    das reparações programadas realizadas e a média dos desvio em relação
    às durações previstas
     */
    public List<String> getListagem1 () {

    }

    /*
    uma listagem que indica, para cada funcionário de balcão, quantas
     recepções e entregas de equipamentos realizou;
     */
    public List<String> getListagem2() {
        List<String> res = new ArrayList<>();
        for (FuncionarioBalcao f : this.model.getListaDeFuncionarios()) {
            String s = "O funcionário " + f.getUsername() + " tem " + f.getRececoesEq() + " receções de equipamentos e " + f.getEntregasEq() + " entregas de equipamentos.";
            res.add(s);
        }
        return res;
    }

    /*
    uma listagem exaustiva, para cada técnico, de todas as intervenções
    (passos de reparação e reparações expresso) realizas.
     */
    public List<String> getListagem3() {
        Map<String, AbstractMap.SimpleEntry<List<List<Passo>>, List<String>>> intervencoesPorTecnico = new HashMap<>();
        //lista dos passos de reparacao
        for(PedidoOrcamento po : this.model.getListaPedidosOrcamento()) {
            if (intervencoesPorTecnico.get(po.getIdTecnico()) != null) {
                AbstractMap.SimpleEntry<List<List<Passo>>, List<String>> pair = intervencoesPorTecnico.get(po.getIdTecnico());  //ir buscar o map que temos atualmente como value
                pair.getKey().add(po.getPlanoTrabalho());   //atualizar a lista que esta como key
                intervencoesPorTecnico.put(po.getIdTecnico(), pair);    //colocar no map das intervencoes por tecnico
            }
            else {
                List<List<Passo>> novo = new ArrayList<>();
                novo.add(po.getPlanoTrabalho());        //adicionar o novo plano de trabalho à lista vazia
                AbstractMap.SimpleEntry<List<List<Passo>>, List<String>> pair = new AbstractMap.SimpleEntry<>(novo, new ArrayList<>());  //formar uma SimpleEntry
                intervencoesPorTecnico.put(po.getIdTecnico(), pair);    //adicionar ao map
            }

        }
        //reparacoes expresso
        for(PedidoExpresso pe : this.model.getListaPedidosExpresso()){
            if (intervencoesPorTecnico.get(pe.getIdTecnico()) != null) {
                AbstractMap.SimpleEntry<List<List<Passo>>, List<String>> pair = intervencoesPorTecnico.get(pe.getIdTecnico());  //ir buscar o map que temos atualmente como value
                pair.getValue().add(pe.getDescricao());   //atualizar a lista que esta como key
                intervencoesPorTecnico.put(pe.getIdTecnico(), pair);    //colocar no map das intervencoes por tecnico
            }
            else {
                List<String> novo = new ArrayList<>();
                novo.add(pe.getDescricao());        //adicionar o novo plano de trabalho à lista vazia
                AbstractMap.SimpleEntry<List<List<Passo>>, List<String>> pair = new AbstractMap.SimpleEntry<>(new ArrayList<>(), novo);  //formar uma SimpleEntry
                intervencoesPorTecnico.put(pe.getIdTecnico(), pair);    //adicionar ao map
            }
        }
        return parseToDisplay(intervencoesPorTecnico);
    }

    public List<String> parseToDisplay (Map<String, AbstractMap.SimpleEntry<List<List<Passo>>, List<String>>> listaIntervencoes) {
        List<String> res = new ArrayList<>();
        for(var entry : listaIntervencoes.entrySet()) {
            res.add("Tecnico ID: " + entry.getKey() + "\n" +
                    "Lista dos passos de reparação: " + entry.getValue().getKey() + "\n" +
                    "Lista das reparações expresso: " + entry.getValue().getValue() + "\n\n");
        }
        return res;
    }
}
