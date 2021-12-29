package LRController.GestGestor;

import LRModel.*;

import java.util.*;

public interface IGestGestor {
    public boolean loginGestor(String username, String password);

    public Gestor getGestor(String id);

    public boolean adicionarGestor(String username, String password);


    public List<String> getListagem1 ();

    public List<String> getListagem2();

    public List<String> getListagem3();

    public List<String> parseToDisplay (Map<String, AbstractMap.SimpleEntry<List<List<Passo>>, List<String>>> listaIntervencoes);
}
