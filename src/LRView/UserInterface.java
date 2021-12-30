package LRView;

import LRController.GestFuncionarioBalcao.IGestFuncionarioBalcao;
import LRController.GestGestor.IGestGestor;
import LRController.GestTecnico.IGestTecnico;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public UserInterface(IGestFuncionarioBalcao f, IGestGestor g, IGestTecnico t) {
        this.gestFuncionarioBalcao = f;
        this.gestGestor = g;
        this.gestTecnico = t;
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        System.out.println("Bem vindo ao Sistema de Gestão de Reparações!");
        System.out.println("A remover pedidos não aprovados com mais de 30 dias");
        this.gestFuncionarioBalcao.arquivarPedidosNaoAprovados();
        System.out.println("A remover equipamentos não levantados há mais de 90 dias");
        this.gestFuncionarioBalcao.darBaixaEquipamentosNaoRecolhidos();
        menuPrincipal();
        System.out.println("Até breve...");
        this.gestGestor.saveFiles();
    }

    /**
     * Menu Principal - Efetuar login
     *
     * Transições possíveis:
     *      Operações sobre Gestores
     *      Operações sobre Técnicos de Reparação
     *      Operações sobre Funcionários de Balcão
     *
     */
    private void menuPrincipal() {
        Menu menuPrincipal = new Menu(new String[]{
                "Operações sobre Gestores",
                "Operações sobre Funcionários de Balcão",
                "Operações sobre Técnicos de Reparação"

        });

        menuPrincipal.setHandler(1, ()-> gestaoGestor());
        menuPrincipal.setHandler(2, ()-> gestaoFuncionarioBalcao());
        menuPrincipal.setHandler(3, ()-> gestaoTecnicoReparacao());

        menuPrincipal.run();
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

        boolean correct_password = false;
        while(!correct_password) {
            System.out.println("Insira o seu username: ");
            username = scin.nextLine();
            System.out.println("Insira a sua password: ");
            String password = scin.nextLine();
            correct_password = this.gestGestor.loginGestor(username, password);
            if(!correct_password) System.out.println("Dados Log in inválidos");

            this.username = username;
        }
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

                menuGestor.setHandler(1, this::adicionarGestor);
                menuGestor.setHandler(2, this::removerGestor);
                menuGestor.setHandler(3, this::adicionarFuncionario);
                menuGestor.setHandler(4, this::removerFuncionario);
                menuGestor.setHandler(5, this::adicionarTecnico);
                menuGestor.setHandler(6, this::removerTecnico);
                menuGestor.setHandler(7, this::listarGestores);
                menuGestor.setHandler(8, this::listarFuncionarios);
                menuGestor.setHandler(9, this::listarTecnicos);
                menuGestor.setHandler(10, this::consultarListagem);

                menuGestor.run();

    }

    /**
     *  Estado - Adicionar Gestor
     */
    private void adicionarGestor() {
    try {
        System.out.println("Id do novo Gestor: ");
        String idGestor = scin.nextLine();
        System.out.println("Password do novo Gestor: ");
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
            System.out.println("Password do novo Funcionario de Balcão: ");
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
            System.out.println(this.gestGestor.getNomeGestores().toString());
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
            System.out.println(this.gestFuncionarioBalcao.getNomeFuncionarios().toString());
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
            System.out.println(this.gestTecnico.getNomeTecnicos().toString());
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
            System.out.println("1 - Por técnico: \n" +
                                  "                   - número de reparações programadas/expresso realizadas \n" +
                                  "                   _ a duração média das reparações programadas realizadas\n" +
                                  "                   _ média dos desvio em relação às durações previstas \n");

            System.out.println("2 - Recepções e entregas de equipamentos realizadas por funcionário balcão\n");
            System.out.println("3 - Todas as intervenções (passos de reparação e reparações expresso) realizadas por técnico.\n");

            int listNumber = readOptionInt(3);

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
        boolean correct_password = false;
        while(!correct_password) {
            System.out.println("Insira o seu username: ");
            username = scin.nextLine();
            System.out.println("Insira a sua password: ");
            String password = scin.nextLine();
            correct_password = this.gestFuncionarioBalcao.loginFuncionarioBalcao(username, password);
            if(!correct_password) System.out.println("Dados Log in inválidos");

        }
        this.username = username;
            Menu menuFuncionario = new Menu(new String[]{
                    "Registar pedido de orçamento",
                    "Registar entrega do equipamento pelo cliente",
                    "Registar serviço expresso",
                    "Registar confirmação do Orçamento",
                    "Registar recolha do equipamento por parte do cliente"
            });

            menuFuncionario.setHandler(1, this::registarPedidoOrcamento);
            menuFuncionario.setHandler(2, this::registarEntregaEquipamentoPeloCliente);
            menuFuncionario.setHandler(3, this::registaServicoExpresso);
            menuFuncionario.setHandler(4, this::registarConfirmacaoDoOrcamento);
            menuFuncionario.setHandler(5,this::registarRecolhaEquipamento);

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
            System.out.println("Insira a descricao do Pedido");
            String descricao = scin.nextLine();
            LocalDate ldt = LocalDate.now();
            this.gestFuncionarioBalcao.registarPedidoOrcamento(nomeCliente, contacto, nif, email,descricao,ldt);
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
            System.out.println("Insira a descricao");
            String descricao = scin.nextLine();

            String tecnico = this.gestFuncionarioBalcao.registarServicoExpresso(nif, contacto,descricao);

            if(tecnico == null) System.out.println("Não existe disponibilidade para realizar o serviço expresso");
            else{
                System.out.println("O Serviço expresso foi atribuido ao tecnico  " + tecnico);
            }


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    /**
     *  Estado - Registar confirmação da reparação
     */
    private void registarConfirmacaoDoOrcamento() {
        try {
            System.out.println("Insira o NIF:");
            String nif = scin.nextLine();
            System.out.println("Insira '1' se aceitou ou '2' se rejeitou "); // ver no fim se isto de ler o boolean funciona
            int op = readOptionInt(2);

            boolean b ;
            b = op == 1;
            this.gestFuncionarioBalcao.registarConfirmacaoOrcamento(nif,b);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }


    public void registarRecolhaEquipamento(){

        System.out.println("Insira o NIF:");
        String nif = scin.nextLine();

        if(!this.gestFuncionarioBalcao.registarRecolhaEquipamentoePagamento(nif,this.username))
            System.out.println("O equipamento não está pronto para ser recolhido");

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
        boolean correct_password = false;
        while(!correct_password) {
            System.out.println("Insira o seu username: ");
            username = scin.nextLine();
            System.out.println("Insira a sua password: ");
            String password = scin.nextLine();
            correct_password = this.gestTecnico.loginTecnico(username, password);
            if(!correct_password) System.out.println("Dados Log in inválidos");

        }

            Menu menuTecnico = new Menu(new String[]{
                    "Registar plano de trabalho reparação",
                    "Assinalar execução dos passos",
                    "Determina equipamento mais urgente",
                    "Registar conclusão da Reparação"
            });

            menuTecnico.setHandler(1, this::registaPlanoTrabRep);
            menuTecnico.setHandler(2, this::assinalaExecucaoPassos);
            menuTecnico.setHandler(3, this::determinaEquipamentoMaisUrgente);
            menuTecnico.setHandler(4, this::registarConclusaoDaReparacao);

            menuTecnico.run();
        }


    /**
     *  Estado - Registar de trabalho reparação
     */
    private void registaPlanoTrabRep() {
        try {
            String nif = this.gestTecnico.determinaEquipamentoMaisAntigo();
            if (nif == null){
                System.out.println("Não existe nenhum equipamento para efectuar o plano de trabalhos");
            } else {
                System.out.println("O NIF do equipamento mais antigo é:" + nif);
                System.out.println("Descrição do Pedido: " +  this.gestTecnico.getInfoPedido(nif));

                System.out.println("Quando tiver analisado a descrição do pedido e o equipamento, registe os passos de trabalho: ");
                System.out.println("Para sair escreva quit.");

                String line = "";
                while (!line.equals("quit")) {
                    System.out.println("Insira o custo: ");
                    float custo = readOptionFloat(1000);
                    System.out.println("Agora insira o tempo previsto ->");
                    System.out.println("Insira a hora: ");
                    int hora = readOptionInt(168);
                    System.out.println("Insira o minuto: ");
                    int min = readOptionInt(59);
                    System.out.println("Insira os segundos: ");
                    int seg = readOptionInt(59);
                    LocalTime lt = LocalTime.of(hora, min, seg, 0);
                    System.out.println("Insira a descrição do passo: ");
                    String descricao = scin.nextLine();
                    this.gestTecnico.registarPasso(nif, custo, lt, descricao, false);
                    System.out.println("Se desejar terminar escreva 'quit' ou se desejar continuar prima ENTER");
                    line = scin.nextLine();

                }
            }

            String email = this.gestTecnico.getEmailOrcamento(nif);
            String nome = this.gestTecnico.getNomeOrcamento(nif);
            LocalTime tempoPrevisto = this.gestTecnico.getTempoPrevistoOrcamento(nif);
            LocalTime prazoMaximo = this.gestTecnico.getPrazoMaximo(nif);
            Float custo = this.gestTecnico.getCustoTotalPrevisto(nif);



            System.out.println("O seguinte orçamento deve ser enviado ao cliente: ");
            System.out.println("Tempo previsto : " + tempoPrevisto.toString());
            System.out.println("Prazo máximo : " + prazoMaximo.toString());
            System.out.println("Custo total : " + custo);
            System.out.println("Nome -> "+ nome);
            System.out.println("Email -> "+ email);



        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Assinalar execução de passo
     */
    private void assinalaExecucaoPassos() {
        try {

            boolean pausa = false;
            boolean valorSuperior = false;


            System.out.println("Insira o NIF associado à reparação:");
            String nif = scin.nextLine();

            float custoPrevisto = this.gestTecnico.getCustoTotalPrevisto(nif);

           while (!pausa && !valorSuperior) {

                String descricao = this.gestTecnico.proximoPassoExecutarString(nif);
                System.out.println(descricao);

               System.out.println("Insira o custo: ");
               float custo = readOptionFloat(1000);
               System.out.println("Agora insira o tempo gasto ->");
               System.out.println("Insira a hora: ");
               int hora = readOptionInt(170);
               System.out.println("Insira o minuto: ");
               int min = readOptionInt(59);
               System.out.println("Insira os segundos: ");
               int seg = readOptionInt(59);
               LocalTime lt = LocalTime.of(hora, min, seg, 0);
               float novoCusto = this.gestTecnico.assinalarExecucaoPasso(nif, lt, custo);

               if(novoCusto > 1.2 * custoPrevisto) valorSuperior = true;

               System.out.println("Deseja colocar a reparação em pausa? (digite y)");
               String pausaS = scin.nextLine();

               if(pausaS.equals("y")) pausa = true;

           }

           if(valorSuperior){
               this.gestTecnico.colocarReparacaoAEsperaDeAprovacao(nif);

               String email = this.gestTecnico.getEmailOrcamento(nif);
               String nome = this.gestTecnico.getNomeOrcamento(nif);
               LocalTime tempoPrevisto = this.gestTecnico.getTempoPrevistoOrcamento(nif);
               LocalTime prazoMaximo = this.gestTecnico.getPrazoMaximo(nif);
               Float custo = this.gestTecnico.getCustoTotalPrevisto(nif);


               System.out.println("A reparação ultrapassou em 120% o valor previsto inicialmente é necessário informar o cliente  : " +nif);
               System.out.println("Nome -> "+ nome);
               System.out.println("Email -> "+ email);
           }



           } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Determina equipamento mais urgente
     */
    private void determinaEquipamentoMaisUrgente() {
        try {
            String nif = this.gestTecnico.determinaEquipamentoMaisUrgente();
            if (nif != null)
                System.out.println("O NIF do equipamento mais urgente é: " + nif);
            else
                System.out.println("Não existem equipamentos para serem reparados.");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Determina equipamento mais urgente
     */
    private void determinaEquipamentoMaisAntigo() {
        try {
            System.out.println(this.gestTecnico.determinaEquipamentoMaisAntigo());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registarConclusaoDaReparacao() {
        try {
            System.out.println("Insira 1 para serviço normal e 2 para serviço expresso");

            int op = readOptionInt(2);

            System.out.println("Insira o NIF:");
            String nif = scin.nextLine();


            if(op == 1) this.gestTecnico.registarConclusaoReparacao(nif);
            else this.gestTecnico.registarConclusaoExpresso(nif);


            this.gestTecnico.colocarProntoParaRecolha(nif);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }


    /** Ler uma opção válida */

    private int readOptionInt(int opcoes) {
        int op = -1;

        while(op == -1){

            System.out.print("Opção: ");
            try {
                String line = this.scin.nextLine();
                op = Integer.parseInt(line);
            }
            catch (NumberFormatException e) { // Não foi inscrito um int
                op = -1;
            }
            if (op<0 || op> opcoes) {
                System.out.println("Opção Inválida!!!");
                op = -1;
            }
        }

        return op;

    }


    private float readOptionFloat(float opcoes) {
        float op = -1;

        while(op == -1){

            System.out.print("Opção: ");
            try {
                String line = this.scin.nextLine();
                op = Float.parseFloat(line);
            }
            catch (NumberFormatException e) { // Não foi inscrito um int
                op = -1;
            }
            if (op<0 || op> opcoes) {
                System.out.println("Opção Inválida!!!");
                op = -1;
            }
        }

        return op;

    }

}