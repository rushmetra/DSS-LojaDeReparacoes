package LRController.GestTecnico;


import LRModel.PedidoOrcamento;
import LRModel.Tecnico;

import java.time.LocalTime;
import java.util.List;

public interface IGestTecnico {
    public void registarPasso(String nif, float custo, LocalTime tempoPrevisto, String descricao, boolean concluido);

    public void assinalarExecucaoPasso(String nif, LocalTime tempo, float custo);

    public String determinaEquipamentoMaisUrgente();

    public String determinaEquipamentoMaisAntigo() ;

    public String getInfoPedido(String nif);

    public boolean loginTecnico(String username, String password);

    public List<String> getNomeTecnicos();

    public void registarConclusaoReparacao(String nif);

    public void registarConclusaoExpresso(String nif);

    public List<Tecnico> getTecnicos();

    public void saveFiles();
}