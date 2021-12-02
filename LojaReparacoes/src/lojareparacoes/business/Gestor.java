package lojareparacoes.business;

public class Gestor extends Utilizador{
    private int id;

    public Gestor(String username, String password, int id) {
        super(username, password);
        this.id = id;
    }

}
