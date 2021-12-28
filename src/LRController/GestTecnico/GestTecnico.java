package LRController.GestTecnico;

import LRModel.FuncionarioBalcao;
import LRModel.LojaReparacoesModel;
import LRModel.Passo;
import LRModel.PedidoOrcamento;

import java.time.LocalTime;
import java.util.List;

public class GestTecnico implements IGestTecnico{
    LojaReparacoesModel model;

    public void registarPlanoTrabRep(String nif){}


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


    public boolean loginTecnico(String username, String password){

        if(!this.model.containsTecnico(username)) return false;

        FuncionarioBalcao t = this.model.getFuncionarioBalcao(username);
        String tPass = t.getPassword();

        return password.equals(tPass);
    }

}
