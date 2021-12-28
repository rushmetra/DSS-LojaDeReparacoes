package business.GestTecnico;

import business.PedidoOrcamento;

public interface IGestTecnico {
    public void registarPlanoTrabRep(String nif);

    public void assinalaExecucaoPasso(String nif);

    public PedidoOrcamento determinaEquipamentoMaisUrgente();
}