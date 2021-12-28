package LRView;

import LRController.*;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;


/**
 * Interface do projeto.
 */
public class UserInterface {

    // O model tem a 'lógica de negócio'
    private LojaReparacoesFacade model;

    // Scanner para leitura
    private Scanner scin;

    /*
    private IGestFuncionarioBalcao funcBalc;
    private IGestGestor gest;
    private IGestAutenticacao aut;
    private IGestTecnico tec;
     */

    public UserInterface() {
        this.model = new LojaReparacoesFacade();
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        System.out.println("Bem vindo ao Sistema de Gestão de Turmas!");
        int user_type = efetuarLogin();
        this.menuPrincipal();
        System.out.println("Até breve...");
    }

    // Métodos auxiliares - Estados da UI

    /**
     * Estado - Menu Principal
     * <p>
     * Transições para:
     * Operações sobre Gestor
     * Operações sobre Tecnicos de Reparação
     * Operações sobre Funcionario de Balcao
     */
    private void menuPrincipal() {
        Menu menu = new Menu(new String[]{
                "Operações sobre Gestor",
                "Operações sobre Tecnicos de Reparação",
                "Operações sobre Funcionário de Balcão",
        });


        // Registar pré-condições das transições
        //menu.setPreCondition()
        // mais pré-condições?
        // Registar os handlers das transições
        menu.setHandler(1, () -> gestaoAutenticacao());

        // Executar o menu
        menu.run();
    }

    /**
     *  Estado - Operações sobre Autenticação
     *
     *  Transições possíveis:
     *      Efetuar login
     */
    private void gestaoAutenticacao() {
        Menu menuAutenticacao = new Menu(new String[]{
                "Efetuar Login"
        });

        menuAutenticacao.setHandler(1, () -> efetuarLogin());

        menuAutenticacao.run();
    }

    /**
     * Estado - Efetuar login
     */
    private int efetuarLogin() {
        System.out.println("Prima 1 para Gestor. Prima 2 para Funcionário de Balcão. Prima 3 para Técnico de Reparação");
        String num = scin.nextLine();
        int user_type = Integer.parseInt(num);
        if(user_type == 1) {
            
        } else if(user_type == 2) {

        } else if(user_type == 3) {
        }
    }

    /**
     * Estado - Operações sobre Gestor
     *
     * Transições possíveis:
     *       Adicionar Gestor
     *       Remover Gestor
     *       Listar Gestores
     *       Consultar Listagem
     */
    private void gestaoGestor() {
        Menu menuGestor = new Menu(new String[]{
                "Adicionar Gestor",
                "Remover Gestor",
                "Listar Gestores",
                "Get Gestor",
                "Consultar Listagem"
        });

        menuGestor.setHandler(1, () -> adicionarGestor());
        menuGestor.setHandler(2, () -> removerGestor());
        menuGestor.setHandler(3, () -> listarGestores());
        menuGestor.setHandler(4, () -> getGestor();
        menuGestor.setHandler(5, () -> consultarListagem());

        menuGestor.run();
    }

    /**
     *
     */
    private void getGestor() {
        System.out.println("Id do Gestor: ");
        String idGestor = scin.nextLine();
        if(this.model.existeGestor(idGestor)) {
            System.out.println(this.model.getGestor(idGestor).toString());
        }
    }

    /**
     *  Estado - Adicionar Gestor
     */
    private void adicionarGestor() {
    try {
        System.out.println("Id do novo Gestor: ");
        String idGestor = scin.nextLine();
        if (!this.model.existeGestor(idGestor)) { // Função booleana pra averiguar se o Gestor com este ID exist
          this.model.adicionaGestor(new Gestor(idGestor));
          System.out.println("Gestor adicionado.");
        } else {
          System.out.println("Gestor já existe!");
        }
       } catch (NullPointerException e) {
        System.out.println(e.getMessage());
     }
    }

    /**
     *  Estado - Remover Gestor
     */
    private void removerGestor(){
    try{
        System.out.println("Id do Gestor: ");
        String idGestor = scin.nextLine();
        if(this.model.existeGestor(idGestor)) {
            this.model.removeGestor(idGestor);
            System.out.println("Gestor removido!");
        } else {
            System.out.println("Gestor não existe!");
        }
    } catch (NullPointerException e) {
        System.out.println(e.getMessage());
     }
    }

    /**
     *  Estado - Listar Gestores
     */
    private void listarGestores() {
        try {
            System.out.println(this.model.getGestores().toString());
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Estado - Consultar Listagem
     */
    private void consultarListagem() {
        try {
            System.out.println("Número da listagem a consultar: ");
            String num = scin.nextLine();
            int listNumber = Integer.parseInt(num);
            if(listNumber == 1) {
                System.out.println(this.model.getListagem1().toString());
            } else if (listNumber == 2) {
                System.out.println(this.model.getListagem2().toString());
            } else {
                System.out.println(this.model.getListagem3().toString());
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Operações sobre o Tecnico de Reparacao
     *
     *  Transições para:
     *       Registar plano de trabalho reparação
     *       Assinalar execução de passo
     *       Determina equipamento mais urgente
     *       Registar plano de trabalho para a reparação
     *       Assinalar a execução de um passo
     *       Determina equipamento mais urgente
     *
     */
    private void gestaoTecnicoReparacao() {
        Menu menuTecnico = new Menu(new String[]{
                "Adicionar Técnico de Reparação",
                "Remover Técnico de Reparação"
                "Listar Técnicos de Reparação",
                "Registar plano de trabalho reparação",
                "Assinalar execução de passo",
                "Determina equipamento mais urgente"
        });

        menuTecnico.setHandler(1, () -> adicionarTecnico());
        menuTecnico.setHandler(2, () -> removerTecnico());
        menuTecnico.setHandler(3, () -> listarTecnicos());
        menuTecnico.setHandler(4, () -> registaPlanoTrabRep());
        menuTecnico.setHandler(5, () -> assinalaExecucaoPasso());
        menuTecnico.setHandler(6, () -> determinaEquipamentoMaisUrgente());

        menuTecnico.run();
    }

    private void adicionarTecnico() {}
    private void removerTecnico() {}
    private void listarTecnicos() {}
    private void registaPlanoTrabRep() {}
    private void assinalaExecucaoPasso() {}
    private void determinaEquipamentoMaisUrgente() {}

    /**
     *  Estado - Operações sobre o Funcionario de Balcao
     *
     *  Transições possíveis:
     *      Adicionar Funcionário de Balcão
     *      Remover Funcionário de Balcão
     *      Listar Funcionários de Balcão
     *      Registar pedido
     *      Registar serviço expresso
     *      Registar conclusão de um pedido
     *      Registar confirmação da reparação
     *
     */
    private void gestaoFuncionarioBalcao() {
        Menu menuFuncionario = new Menu(new String[]{
                "Adicionar Funcionário de Balcão",
                "Remover Funcionário de Balcão",
                "Listar Funcionários de Balcão",
                "Registar pedido",
                "Registar serviço expresso",
                "Registar conclusão de um pedido",
                "Registar confirmação da reparação"
        });

        menuFuncionario.setHandler(1, () -> adicionarFuncionario());
        menuFuncionario.setHandler(2, () -> removerFuncionario());
        menuFuncionario.setHandler(3, () -> listarFuncionarios());
        menuFuncionario.setHandler(4, () -> registarPedido());
        menuFuncionario.setHandler(5, () -> registaServicoExpresso());
        menuFuncionario.setHandler(6, () -> registarConclusaoPedido());
        menuFuncionario.setHandler(7, () -> registarConfirmacaoDaReparacao());

        menuFuncionario.run();
    }

    private void adicionarFuncionario() {}
    private void removerFuncionario() {}
    private void listarFuncionarios() {}
    private void registarPedido() {}
    private void registaServicoExpresso() {}
    private void registarConclusaoPedido() {}
    private void registarConfirmacaoDaReparacao() {}

}