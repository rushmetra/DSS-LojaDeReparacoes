package LRController.GestTecnico;


import LRModel.PedidoOrcamento;
import LRModel.Tecnico;

import java.time.LocalTime;
import java.util.List;

public interface IGestTecnico {
    public void registarPasso(String nif, float custo, LocalTime tempoPrevisto, String descricao, boolean concluido);

    public void assinalarExecucaoPasso(String nif, LocalTime tempo, float custo);

    public PedidoOrcamento determinaEquipamentoMaisUrgente();

    public boolean loginTecnico(String username, String password);

    public List<Tecnico> getTecnicos();

    public void saveFiles();
}