package LRController.GestGestor;

import LRModel.*;

import java.util.*;

public interface IGestGestor {
    public boolean loginGestor(String username, String password);

    public Gestor getGestor(String id);

    public boolean existeGestor(String idGestor);

    public List<String> getListagem1 ();

    public List<String> parseToDisplayListagem1 (Map<String, AbstractMap.SimpleEntry<Integer, Integer>> numReparacoesPorTecnico, Map<String, Float> duracaoMediaReparacoesProgramadas, Map<String, Float> desvio);

    public List<String> getListagem2();

    public List<String> getListagem3();

    public List<String> parseToDisplayListagem3 (Map<String, AbstractMap.SimpleEntry<List<List<Passo>>, List<String>>> listaIntervencoes);
}
