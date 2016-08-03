package Dao;

import java.util.List;

import Entidades.Opcao;

public interface OpcaoDao{


int localizaIDResposta(int idQuestao, String opcao);
 List<Opcao> localizaOpcoes(int idQuestao);

}