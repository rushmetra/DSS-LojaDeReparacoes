package LRController.GestTecnico;

import LRController.GestGestor.GestGestor;
import LRModel.*;

import java.time.LocalTime;
import java.util.List;

public class GestTecnico implements IGestTecnico{
    ILojaReparacoesModel model;


    public GestTecnico(ILojaReparacoesModel model){
        this.model = model;
    }


    // Registar Passo no Plano de Trabalhos
    public void registarPasso(String nif, float custo, LocalTime tempoPrevisto, String descricao, boolean concluido) {
        model.getPedidoOrcamento(nif).adicionaPasso(custo, tempoPrevisto, descricao, concluido);
    }

    public void assinalarExecucaoPasso(String nif, LocalTime tempo, float custo){
        PedidoOrcamento p = model.getPedidoOrcamento(nif);
        List<Passo> passos = p.getPlanoTrabalho();

        Passo proximo = proximoPassoExecutar(passos);
        if(proximo != null){
            proximo.setConcluido(true);
            proximo.setTempo(tempo);
            proximo.setCustoFinal(custo);
        }
    }

    public Passo proximoPassoExecutar(List<Passo> passos){
        Passo prox = null;
        for(Passo p : passos){
            if (p.getConcluido() == false){
                prox = p;
                break;
            }
        }
        return prox;
    }


    public PedidoOrcamento determinaEquipamentoMaisUrgente(){
        // lista ordenada de pedidos (mais antigo no fim da lista)
        List<PedidoOrcamento> pedidoOrcamentoList = model.getListaPedidosOrcamento();
        PedidoOrcamento p = pedidoOrcamentoList.get(pedidoOrcamentoList.size() - 1);
        return p;
    }

    public void registarConclusaoReparacao(String nif){
        PedidoOrcamento po = model.getPedidoOrcamento(nif);
        po.setConlusaoReparacao(true);
    }

    public void registarConclusaoExpresso(String nif){
        PedidoExpresso pe = model.getPedidoExpresso(nif);
        pe.setConcluido(true);
    }


    public boolean loginTecnico(String username, String password){

        if(!this.model.containsTecnico(username)) return false;

        Tecnico t = this.model.getTecnico(username);
        String tPass = t.getPassword();

        return password.equals(tPass);
    }

    public List<String> getNomeTecnicos() { return this.model.getNomeTecnicos(); }

    public List<Tecnico> getTecnicos() {
        return this.model.getTecnicos();
    }


    public void saveFiles(){
        this.model.saveData("saves");
    }

}
