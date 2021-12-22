package lojareparacoes.business;

public class Tecnico extends Utilizador{
    private int id;

    public Tecnico(String username, String password, int id) {
        super(username, password);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método em que o técnico seleciona o pedido mais urgente
     * @param loja A loja á qual acende à lista de pedidos
     * @return O pedido mais urgente
     */
    public PedidoReparacao selecionaMaisUrgente (LojaReparacoesFacade loja) {
        PedidoReparacao pedidoReparacaoMaisUrgente = loja.getPedidoMaisUrgente();
        return pedidoReparacaoMaisUrgente;
    }

    /**
     * Método em que o técnico regista um pedido como concluido
     * @param loja A loja na qual a reparação é efetuada
     * @param pedidoReparacao O pedido que se quer dar como concluido
     * @return Caso a operacao tenha ocorrido com sucesso retorna verdade, caso contrário retorna falso
     */
    public boolean registarConclusao (LojaReparacoesFacade loja, PedidoReparacao pedidoReparacao) {
        if (!(loja.getListaDePedidos().get(pedidoReparacao.getId()).isConcluido())) {
            loja.getListaDePedidos().get(pedidoReparacao.getId()).setConcluido(true);
        }
        else {
            System.out.println("O pedido já se encontra concluido");
            return false;
        }
        return true;
    }
}
