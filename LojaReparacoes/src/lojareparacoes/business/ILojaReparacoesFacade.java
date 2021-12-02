package lojareparacoes.business;


import java.util.Collection;

/**
 * API da Facade da lógica de negócio.
 *
 * @author Grupo59
 * @version 20210212
 */
public interface ILojaReparacoesFacade {

    /**
     * Método que adiciona um cliente.
     *
     * @param c cliente a adicionar
     */
    void adicionaCliente(Cliente c);
    /**
     * Método que adiciona um tecnico.
     *
     * @param t tecnico a adicionar
     */
    void adicionaTecnico(Tecnico t);
    /**
     * Método que adiciona um funcionario.
     *
     * @param f funcionario a adicionar
     */
    void adicionaFuncionario(FuncionarioBalcao f);
    /**
     * Método que adiciona um pedido.
     *
     * @param p pedido a adicionar
     */
    void adicionaPedidoReparacao(PedidoReparacao p);

    /**
     * Método que procura um pedido
     *
     * @param num número do pedido a procurar
     * @return true se o pedido existe
     */
    PedidoReparacao procuraPedido(String num);
    /**
     * Método que altera a sala da turma.
     *
     * @param pid id do pedido que vai mudar de tecnico
     * @param t novo tecnico do pedido
     */
    void alteraTecnicoPedido(PedidoReparacao pid, Tecnico t);
    /**
     * Método que verifica se um aluno existe
     *
     * @param num número do pedido a procurar
     * @return true se o pedido existe
     */
    boolean existePedido(String num);
    /**
     * Método que verifica se um aluno existe
     *
     * @param num número do cliente a procurar
     * @return true se o cliente existe
     */
    boolean existeCliente(String num);
    /**
     * Método que verifica se há pedidos no sistema
     * @return true se há pedidos registados
     */
    boolean haPedidos();
    /**
     * Método que verifica se há tecnicos no sistema
     * @return true se há tecnicos registados
     */
    boolean haTecnicos();

    /**
     * Método que devolve todos os pedidos registados.
     *
     * @return todos os pedidos registados
     */
    Collection<PedidoReparacao> getPedidosReparacao();
    /**
     * Método que devolve todos os clientes registados.
     *
     * @return todos os clientes registados
     */
    Collection<Cliente> getClientes();
    /**
     * Método que devolve todos os tecnicos registados.
     *
     * @return todos os tecnicos registados
     */
    Collection<Tecnico> getTecnicos();
}
