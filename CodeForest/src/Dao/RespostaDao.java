package Dao;

import Entidades.TotalizaRespostas;

public interface RespostaDao {

	boolean inserir (int idQuestao, int idResposta);
	TotalizaRespostas graficoRespostas(int idOpcao, int idQuestao);


}
