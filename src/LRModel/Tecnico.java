package LRModel;

public class Tecnico {
    private String username;
    private String password;

    /* Construtores */
    public Tecnico(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Tecnico(Tecnico t){
        this.username = t.getUsername();
        this.password = t.getPassword();
    }

    /* Getters e Setters */
    public String getUsername() { return this.username;}
    public String getPassword() { return this.password;}

    public void setUsername(String username) { this.username = username;}
    public void setPassword(String password) { this.password = password;}
}
