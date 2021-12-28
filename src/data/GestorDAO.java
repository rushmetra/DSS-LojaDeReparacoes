package data;

import business.Entrega;

import java.util.HashMap;
import java.util.Map;

public class GestorDAO {

    private Map<String, Entrega> gestores;


    public GestorDAO(){
        this.gestores = new HashMap<>();
    }
}
