package Dao;

import java.util.List;

import Entidades.Questionario;

public interface QuestionarioDao {


      List<Questionario> localizaQuestoes(int idQuestao);
      List<Questionario> localizaQuestoes();



}
