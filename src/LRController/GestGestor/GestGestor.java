package LRController.GestGestor;

import LRModel.*;

import java.time.LocalTime;
import java.util.*;
import static java.time.temporal.ChronoUnit.SECONDS;

public class GestGestor implements IGestGestor {
    ILojaReparacoesModel model;



    public GestGestor(ILojaReparacoesModel model){
        this.model = model;
    }


    public boolean loginGestor(String username, String password) {
        if(!this.model.containsGestor(username)) return false;

        Gestor g = this.model.getGestor(username);
        String gPass = g.getPassword();

        return password.equals(gPass);
    }

    public Gestor getGestor(String id) {
        return this.model.getGestor(id);
    }

    public boolean existeGestor(String idGestor) {
        if (this.model.containsGestor(idGestor)) return true;
        else return false;
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
    Para cada técnico devolver:
    -número de reparações programadas/expresso realizadas
    -a duração média das reparações programadas realizadas
    -média dos desvio em relação às durações previstas
     */
    public List<String> getListagem1 () {
        Map<String, AbstractMap.SimpleEntry<Integer, Integer>> numReparacoesPorTecnico = new HashMap<>();
        Map<String, Float> duracaoMediaReparacoesProgramadas = new HashMap<>();
        Map<String, Float> desvio = new HashMap<>();

        //lista dos passos de reparacao
        for(PedidoOrcamento po : this.model.getListaPedidosOrcamento()) {
            if (numReparacoesPorTecnico.get(po.getIdTecnico()) != null) {
                AbstractMap.SimpleEntry<Integer, Integer> pair = numReparacoesPorTecnico.get(po.getIdTecnico());  //ir buscar o map que temos atualmente como value
                int oldOrcamento = pair.getKey();
                int Expresso = pair.getValue();
                AbstractMap.SimpleEntry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<>(oldOrcamento+1, Expresso);
                numReparacoesPorTecnico.put(po.getIdTecnico(), newPair);    //colocar no map das intervencoes por tecnico
            }
            else {
                AbstractMap.SimpleEntry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<>(1, 0);
                numReparacoesPorTecnico.put(po.getIdTecnico(), newPair);    //colocar no map das intervencoes por tecnico
            }

        }
        //reparacoes expresso
        for(PedidoExpresso pe : this.model.getListaPedidosExpresso()){
            if (numReparacoesPorTecnico.get(pe.getIdTecnico()) != null) {
                AbstractMap.SimpleEntry<Integer, Integer> pair = numReparacoesPorTecnico.get(pe.getIdTecnico());  //ir buscar o map que temos atualmente como value
                int orcamento = pair.getKey();
                int oldExpresso = pair.getValue();   //atualizar a lista que esta como key
                AbstractMap.SimpleEntry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<>(orcamento, oldExpresso+1);
                numReparacoesPorTecnico.put(pe.getIdTecnico(), newPair);    //colocar no map das intervencoes por tecnico
            }
            else {
                AbstractMap.SimpleEntry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<>(0, 1);
                numReparacoesPorTecnico.put(pe.getIdTecnico(), newPair);    //colocar no map das intervencoes por tecnico
            }
        }


        //calcular duracao media das reparacoes programadas
        Map<String, LocalTime> duracaoTotalReparacoesProgramadas = new HashMap<>();
        for(PedidoOrcamento po : this.model.getListaPedidosOrcamento()) {
            if (duracaoTotalReparacoesProgramadas.get(po.getIdTecnico()) != null) {
                LocalTime t = po.getTempoTotalPrevisto();
                LocalTime novoValor = duracaoTotalReparacoesProgramadas.get(po.getIdTecnico()).plusHours(t.getHour()).plusMinutes(t.getMinute()).plusSeconds(t.getSecond());
                duracaoTotalReparacoesProgramadas.put(po.getIdTecnico(), novoValor);
            }
            else {
                LocalTime t = po.getTempoTotalPrevisto();
                duracaoTotalReparacoesProgramadas.put(po.getIdTecnico(), t);
            }
        }


        for(var entry : duracaoTotalReparacoesProgramadas.entrySet()) {
            LocalTime duracaoTotal = entry.getValue();
            float tempoTotalEmSegundos = duracaoTotal.getHour()*60 + duracaoTotal.getMinute()*60 + duracaoTotal.getSecond();    //ESTE TEMPO É EM SEGUNDOS
            int totalReparacoes = numReparacoesPorTecnico.get(entry.getKey()).getKey();
            duracaoMediaReparacoesProgramadas.put(entry.getKey(), tempoTotalEmSegundos/totalReparacoes);
        }


        //média dos desvio em relação às durações previstas
        Map<String, Long> totalDesvio = new HashMap<>();
        for(PedidoOrcamento po : this.model.getListaPedidosOrcamento()) {
            if (duracaoTotalReparacoesProgramadas.get(po.getIdTecnico()) != null) {
                LocalTime tempoTotalPrevisto = po.getTempoTotalPrevisto();
                LocalTime tempoTotalGasto = po.getTempoTotalGasto();
                long diff = SECONDS.between(tempoTotalGasto, tempoTotalPrevisto);       //ESTE TEMPO É EM SEGUNDOS
                long novoDesvio = totalDesvio.get(po.getIdTecnico()) + diff;
                totalDesvio.put(po.getIdTecnico(), novoDesvio);
            }
            else {
                LocalTime tempoTotalPrevisto = po.getTempoTotalPrevisto();
                LocalTime tempoTotalGasto = po.getTempoTotalGasto();
                long diff = SECONDS.between(tempoTotalGasto, tempoTotalPrevisto);       //ESTE TEMPO É EM SEGUNDOS
                totalDesvio.put(po.getIdTecnico(), diff);
            }
        }

        for(var entry : totalDesvio.entrySet()) {
            long desvioTotal = entry.getValue();
            int totalReparacoes = numReparacoesPorTecnico.get(entry.getKey()).getKey();
            desvio.put(entry.getKey(), (float) (desvioTotal/totalReparacoes));
        }
        return parseToDisplayListagem1(numReparacoesPorTecnico, duracaoMediaReparacoesProgramadas, desvio);
    }




    public List<String> parseToDisplayListagem1 (Map<String, AbstractMap.SimpleEntry<Integer, Integer>> numReparacoesPorTecnico, Map<String, Float> duracaoMediaReparacoesProgramadas, Map<String, Float> desvio) {
        List<String> res = new ArrayList<>();
        for (Tecnico tecnico : this.model.getTecnicos()) {
            res.add("Tecnico ID: " + tecnico.getUsername() + "\n" +
                    "\t tem " + numReparacoesPorTecnico.get(tecnico.getUsername()).getKey() + " reparações programadas e " + numReparacoesPorTecnico.get(tecnico.getUsername()).getValue() + " reparações expresso realizadas\n" +
                    "\t a duração média das suas reparações programadas realizadas é de " + duracaoMediaReparacoesProgramadas.get(tecnico.getUsername())+ " segundos\n" +
                    "\t e a média dos desvios em relação às durações previstas é de " + desvio.get(tecnico.getUsername()) + "\n");
        }
        return res;
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
        return parseToDisplayListagem3(intervencoesPorTecnico);
    }

    public List<String> parseToDisplayListagem3 (Map<String, AbstractMap.SimpleEntry<List<List<Passo>>, List<String>>> listaIntervencoes) {
        List<String> res = new ArrayList<>();
        for(var entry : listaIntervencoes.entrySet()) {
            res.add("Tecnico ID: " + entry.getKey() + "\n" +
                    "Lista dos passos de reparação: " + entry.getValue().getKey() + "\n" +
                    "Lista das reparações expresso: " + entry.getValue().getValue() + "\n\n");
        }
        return res;
    }



    public void saveFiles(){
        this.model.saveData("saves");
    }












}
