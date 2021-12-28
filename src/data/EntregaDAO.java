package data;

import business.Entrega;

import java.util.HashMap;
import java.util.Map;

public class EntregaDAO {

    private Map<String, Entrega> entregas;


    public EntregaDAO(){
        this.entregas = new HashMap<>();
    }


}
