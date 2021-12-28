package LRController.GestTecnico;


import LRModel.PedidoOrcamento;

import java.time.LocalTime;

public interface IGestTecnico {
    public void registarPlanoTrabRep(String nif);

    public void assinalarExecucaoPasso(String nif, LocalTime tempo, float custo);

    public PedidoOrcamento determinaEquipamentoMaisUrgente();
}