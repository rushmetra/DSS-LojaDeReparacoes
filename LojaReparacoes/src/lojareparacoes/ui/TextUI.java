package lojareparacoes.ui;

import java.util.Scanner;

public class TextUI {
    private Scanner scanner;

    public TextUI () {
        scanner = new Scanner(System.in);
    }

    private void menuPrincipal() {
        Menu menu = new Menu(new String[]{
                "Registar reparação",
                "Registar passos",
                "Consultar clientes",
                "Consultar equipamentos a reparar",
                "Listagens de estatisticas"
        });
        menu.setHandler(1, ()->registarReparacao());
        menu.setHandler(2, ()->registarPassos());
        menu.setHandler(3, ()->consutarCliente());
        menu.setHandler(4, ()->consultarEquipamentos());
        menu.setHandler(5, ()->listagensEstatisticas());

        menu.run();
    }

    public void run() {
        System.out.println("Bem vindo ao Sistema de Gestão de Turmas!");
        this.menuPrincipal();
        System.out.println("Até breve...");
    }

    public void registarReparacao() {
        System.out.println("Opção de registar reparação");
    }
    public void registarPassos() {
        System.out.println("Opçao de registar passos de reparacao");
    }
    public void consutarCliente() {
        System.out.println("Opção de consultar cliente");
    }
    public void consultarEquipamentos() {
        System.out.println("Opçãod e consultar equipamentos em reparacao");
    }
    public void listagensEstatisticas() {
        System.out.println("Opçãode consultar listagens de estatisticas");
    }
}
