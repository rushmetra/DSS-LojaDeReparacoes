package lojareparacoes.business;

import java.util.Date;
import java.util.Map;

public class LojaReparacoesFacade {
    private Map<String, PedidoReparacao> listaDePedidos;    //key é o id do pedido; value é o proprio pedido

    public Map<String, PedidoReparacao> getListaDePedidos() {
        return listaDePedidos;
    }

    public void setListaDePedidos(Map<String, PedidoReparacao> listaDePedidos) {
        this.listaDePedidos = listaDePedidos;
    }

    /**
     * Método para obter o pedido mais urgente da lista de pedidos
     * @return O pedido mais urgente
     */
    public PedidoReparacao getPedidoMaisUrgente(){
        PedidoReparacao maisUrgente = null;
        Date maisUrgenteData = new Date(Long.MIN_VALUE);
        for (PedidoReparacao p : listaDePedidos.values()) {
            if (p.getDataEmissao().before(maisUrgenteData)) {
                maisUrgenteData = p.getDataEmissao();
                maisUrgente = p;
            }
        }
        if (maisUrgente == null) {
            System.out.println("Não há pedidos na lista");
        }
        return maisUrgente;
    }


}
