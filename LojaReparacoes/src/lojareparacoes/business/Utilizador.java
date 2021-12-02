package lojareparacoes.business;

public class Utilizador{
    private String username;
    private String password;

    // Construtores

    // Parametrizado
    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Por Cópia
    public Utilizador(Utilizador u) {
        username = u.getUsername();
        password = u.getPassword();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
