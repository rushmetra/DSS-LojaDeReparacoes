package lojareparacoes.business;

public class Cliente extends Utilizador {
    private int id;

    public Cliente(String username, String password, int id) {
        super(username, password);
        this.id = id;
    }

    public int getId () {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
