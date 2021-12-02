package lojareparacoes.business;

public class Tecnico extends Utilizador{
    private int id;

    public Tecnico(String username, String password, int id) {
        super(username, password);
        this.id = id;
    }
}
