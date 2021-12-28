package LRController.GestTecnico;


import LRModel.PedidoOrcamento;

import java.time.LocalTime;

public interface IGestTecnico {
    public void registarPasso(String nif, float custo, LocalTime tempoPrevisto, String descricao, boolean concluido);

    public void assinalarExecucaoPasso(String nif, LocalTime tempo, float custo);

    public PedidoOrcamento determinaEquipamentoMaisUrgente();
}