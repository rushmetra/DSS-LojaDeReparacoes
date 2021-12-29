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

    public List<String> getNomeTecnicos();

    public void registarConclusaoReparacao(String nif);

    public void registarConclusaoExpresso(String nif);

    public List<Tecnico> getTecnicos();

    public void saveFiles();

    public String getEmailOrcamento(String nif);

    public String getNomeOrcamento(String nif);

    public Float getCustoTotalPrevisto(String nif);

    public LocalTime getTempoPrevistoOrcamento(String nif);



}