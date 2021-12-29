package LRView;

import LRController.GestFuncionarioBalcao.IGestFuncionarioBalcao;
import LRController.GestGestor.IGestGestor;
import LRController.GestTecnico.IGestTecnico;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;


/**
 * Interface do projeto.
 */
public class UserInterface {
    private IGestFuncionarioBalcao gestFuncionarioBalcao;
    private IGestGestor gestGestor;
    private IGestTecnico gestTecnico;

    private String username;

    // Scanner para leitura
    private Scanner scin;

    public UserInterface(IGestFuncionarioBalcao f, IGestGestor g, IGestTecnico t, String u) {
        this.gestFuncionarioBalcao = f;
        this.gestGestor = g;
        this.gestTecnico = t;
        this.username = u;
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        System.out.println("Bem vindo ao Sistema de Gestão de Reparações!");
        efetuarLogin();
        System.out.println("Até breve...");
    }

    /**
     * Menu Principal - Efetuar login
     *
     * Transições possíveis:
     *      Operações sobre Gestor
     *      Operações sobre Técnicos de Reparação
     *      Operações sobre Funcionário de Balcão
     *
     */
    private void efetuarLogin() {
        System.out.println("Prima 1 para Gestor. Prima 2 para Funcionário de Balcão. Prima 3 para Técnico de Reparação");
        String num = scin.nextLine();
        int user_type = Integer.parseInt(num);
        boolean correct_password = false;
        while(!correct_password) {
            if (user_type == 1) {
                System.out.println("Insira o seu username: ");
                username = scin.nextLine();
                System.out.println("Insira a sua password: ");
                String password = scin.nextLine();
                correct_password = this.gestGestor.loginGestor(username, password);
                gestaoGestor();
            } else if (user_type == 2) {
                System.out.println("Insira o seu username: ");
                username = scin.nextLine();
                System.out.println("Insira a sua password: ");
                String password = scin.nextLine();
                correct_password = this.gestFuncionarioBalcao.loginFuncionarioBalcao(username, password);
                gestaoFuncionarioBalcao();
            } else if (user_type == 3) {
                System.out.println("Insira o seu username: ");
                username = scin.nextLine();
                System.out.println("Insira a sua password: ");
                String password = scin.nextLine();
                correct_password = this.gestTecnico.loginTecnico(username, password);
                gestaoTecnicoReparacao();
            }
        }
    }

    // Métodos auxiliares - Estados da UI

    /**
     * Estado - Operações sobre Gestor
     *
     * Transições possíveis:
     *       Adicionar Gestor
     *       Remover Gestor
     *       Adicionar Funcionário de Balcão
     *       Remover Funcionário de Balcão
     *       Adicionar Técnico de Reparação
     *       Remover Técnico de Reparação
     *       Listar Gestores
     *       Listar Funcionários de Balcão
     *       Listar Técnicos de Reparação
     *       Consultar Listagem
     */
    private void gestaoGestor() {
        Menu menuGestor = new Menu(new String[]{
                "Adicionar Gestor",
                "Remover Gestor",
                "Adicionar Funcionário de Balcão",
                "Remover Funcionário de Balcão",
                "Adicionar Técnico de Reparação",
                "Remover Técnico de Reparação",
                "Listar Gestores",
                "Listar Funcionários de Balcão",
                "Listar Técnicos de Reparação",
                "Consultar Listagem"
        });

        menuGestor.setHandler(1, () -> adicionarGestor());
        menuGestor.setHandler(2, () -> removerGestor());
        menuGestor.setHandler(3, () -> adicionarFuncionario());
        menuGestor.setHandler(4, () -> removerFuncionario());
        menuGestor.setHandler(5, () -> adicionarTecnico());
        menuGestor.setHandler(6, () -> removerTecnico());
        menuGestor.setHandler(7, () -> listarGestores());
        menuGestor.setHandler(8, ()-> listarFuncionarios());
        menuGestor.setHandler(9, ()-> listarTecnicos());
        menuGestor.setHandler(10, () -> consultarListagem());

        menuGestor.run();
    }

    /**
     *  Estado - Adicionar Gestor
     */
    private void adicionarGestor() {
    try {
        System.out.println("Id do novo Gestor: ");
        String idGestor = scin.nextLine();
        System.out.println("Passaword do novo Gestor: ");
        String pw = scin.nextLine();
        if ((this.gestGestor.adicionarGestor(idGestor, pw))) { // Função booleana pra averiguar se o Gestor com este ID exist
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
        if((this.gestGestor.removerGestor(idGestor))) {
            System.out.println("Gestor removido!");
        } else {
            System.out.println("Gestor não existe!");
        }
    } catch (NullPointerException e) {
        System.out.println(e.getMessage());
     }
    }

    /**
     *  Estado - Adicionar Funcionário de Balcõa
     */
    private void adicionarFuncionario() {
        try {
            System.out.println("Id do novo Funcionário de Balcão: ");
            String idFuncionario = scin.nextLine();
            System.out.println("Passaword do novo Funcionario de Balcão: ");
            String pw = scin.nextLine();
            if ((this.gestGestor.adicionarFuncionarioBalcao(idFuncionario, pw))) {
                System.out.println("Funcionário de Balcão adicionado.");
            } else {
                System.out.println("Funcionário de Balcão já existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Remover Funcionário de Balcão
     */
    private void removerFuncionario() {
        try{
            System.out.println("Id do Funcionário de Balcão: ");
            String idFuncionario = scin.nextLine();
            if((this.gestGestor.removerFuncionarioBalcao(idFuncionario))) {
                System.out.println("Funcionário de Balcão removido!");
            } else {
                System.out.println("Funcionário de Balcão não existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Adicionar Técnico de Reparação
     */
    private void adicionarTecnico() {
        try {
            System.out.println("Id do novo Técnico de Reparações: ");
            String idTecnico = scin.nextLine();
            System.out.println("Passaword do novo Técnico de Reparações: ");
            String pw = scin.nextLine();
            if (this.gestGestor.adicionarTecnico(idTecnico, pw)) {
                System.out.println("Técnico de Reparações adicionado.");
            } else {
                System.out.println("Técnico de Reparações já existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Remover Técnico de Reparação
     */
    private void removerTecnico() {
        try{
            System.out.println("Id do Técnico de Reparações: ");
            String idTecnico = scin.nextLine();
            if((this.gestGestor.removerTecnico(idTecnico))) {
                System.out.println("Funcionário de Balcão removido!");
            } else {
                System.out.println("Funcionário de Balcão não existe!");
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
            System.out.println(this.gestGestor.getGestores().toString()); // Função que lista os Gestores
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Listar Funcionários de Balcão
     */
    private void listarFuncionarios() {
        try {
            System.out.println(this.gestFuncionarioBalcao.getFuncionarios().toString()); // Função que lista os Funcionarios
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Listar Técnicos de Reparação
     */
    private void listarTecnicos() {
        try {
            System.out.println(this.gestTecnico.getTecnicos().toString()); // Função que lista os Tecnicos
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
                System.out.println(gestGestor.getListagem1().toString());
            } else if (listNumber == 2) {
                System.out.println(gestGestor.getListagem2().toString());
            } else if (listNumber == 3){
                System.out.println(gestGestor.getListagem3().toString());
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Operações sobre o Funcionario de Balcao
     *
     *  Transições possíveis:
     *      Registar pedido de orçamento
     *      Registar entrega do equipamento pelo cliente
     *      Registar serviço expresso
     *      Registar conclusão de um pedido
     *      Registar confirmação da reparação
     *
     */
    private void gestaoFuncionarioBalcao() {
        Menu menuFuncionario = new Menu(new String[]{
                "Registar pedido de orçamento",
                "Registar entrega do equipamento pelo cliente",
                "Registar serviço expresso",
                "Registar conclusão de um pedido",
                "Registar confirmação da reparação"
        });

        menuFuncionario.setHandler(1, () -> registarPedidoOrcamento());
        menuFuncionario.setHandler(2, () -> registarEntregaEquipamentoPeloCliente());
        menuFuncionario.setHandler(3, () -> registaServicoExpresso());
        menuFuncionario.setHandler(4, () -> registarConclusaoPedido());
        menuFuncionario.setHandler(5, () -> registarConfirmacaoDaReparacao());

        menuFuncionario.run();
    }

    /**
     *  Estado - Registar pedido de orçamento
     */
    private void registarPedidoOrcamento() {
        try {
            System.out.println("Insira nome do Cliente: ");
            String nomeCliente = scin.nextLine();
            System.out.println("Insira o contacto: ");
            String contacto = scin.nextLine();
            System.out.println("Insira o NIF: ");
            String nif = scin.nextLine();
            System.out.println("Insira o email: ");
            String email = scin.nextLine();
            this.gestFuncionarioBalcao.registarPedidoOrcamento(nomeCliente, contacto, nif, email);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Estado - Registar entrega do equipamento pelo cliente
     */
    private void registarEntregaEquipamentoPeloCliente() {
        try {
            System.out.println("Insira o NIF: ");
            String nif = scin.nextLine();
            this.gestFuncionarioBalcao.registarEntregaEquipamentoPeloCliente(nif, username);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Registar serviço expresso
     */
    private void registaServicoExpresso() {
        try {
            System.out.println("Insira o NIF:");
            String nif = scin.nextLine();
            System.out.println("Insira o contacto: ");
            String contacto = scin.nextLine();
            this.gestFuncionarioBalcao.registarServicoExpresso(nif, contacto);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Registar conclusão dum pedido
     */
    private void registarConclusaoPedido() {
        try {
            System.out.println("Insira o NIF:");
            String nif = scin.nextLine();
            this.gestFuncionarioBalcao.registarConclusaoPedido(nif);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Registar confirmação da reparação
     */
    private void registarConfirmacaoDaReparacao() {
        try {
            System.out.println("Insira o NIF:");
            String nif = scin.nextLine();
            this.gestFuncionarioBalcao.registarConfirmacaoReparacao(nif);
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
                "Registar plano de trabalho reparação",
                "Assinalar execução de passo",
                "Determina equipamento mais urgente"
        });

        menuTecnico.setHandler(1, () -> registaPlanoTrabRep());
        menuTecnico.setHandler(2, () -> assinalaExecucaoPasso());
        menuTecnico.setHandler(3, () -> determinaEquipamentoMaisUrgente());

        menuTecnico.run();
    }

    /**
     *  Estado - Registar de trabalho reparação
     */
    private void registaPlanoTrabRep() {
        try {
            System.out.println("Registe os passos de trabalho: ");
            System.out.println("Para sair escreva quit.");
            String line;
            while(scin.hasNextLine() && !( line = scin.nextLine() ).equals("quit")) {
                System.out.println("Insira o NIF:");
                String nif = line;
                System.out.println("Insira o custo: ");
                String custo_string = scin.nextLine();
                float custo = Float.parseFloat(custo_string);
                System.out.println("Agora insira o tempo previsto ->");
                System.out.println("Insira a hora: ");
                String hora_string = scin.nextLine();
                int hora = Integer.parseInt(hora_string);
                System.out.println("Insira o minuto: ");
                String min_string = scin.nextLine();
                int min = Integer.parseInt(min_string);
                System.out.println("Insira os segundos: ");
                String seg_string = scin.nextLine();
                int seg = Integer.parseInt(seg_string);
                LocalTime lt = LocalTime.of(hora, min, seg, 0);
                System.out.println("Insira a descrição do passo: ");
                String descricao = scin.nextLine();
                System.out.println("Insira o estado de conclusão (V ou F): ");
                String conclusao_string = scin.nextLine();
                boolean conclusao = Boolean.parseBoolean(conclusao_string);
                this.gestTecnico.registarPasso(nif, custo, lt, descricao, conclusao);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Assinalar execução de passo
     */
    private void assinalaExecucaoPasso() {
        try {
            System.out.println("Insira o NIF:");
            String nif = scin.nextLine();
            System.out.println("Agora insira o tempo previsto ->");
            System.out.println("Insira a hora: ");
            String hora_string = scin.nextLine();
            int hora = Integer.parseInt(hora_string);
            System.out.println("Insira o minuto: ");
            String min_string = scin.nextLine();
            int min = Integer.parseInt(min_string);
            System.out.println("Insira os segundos: ");
            String seg_string = scin.nextLine();
            int seg = Integer.parseInt(seg_string);
            LocalTime lt = LocalTime.of(hora, min, seg, 0);
            System.out.println("Insira o custo: ");
            String custo_string = scin.nextLine();
            float custo = Float.parseFloat(custo_string);
            this.gestTecnico.assinalarExecucaoPasso(nif, lt, custo);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Determina equipamento mais urgente
     */
    private void determinaEquipamentoMaisUrgente() {
        try {
            this.gestTecnico.determinaEquipamentoMaisUrgente();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}