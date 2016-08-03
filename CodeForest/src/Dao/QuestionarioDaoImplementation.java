package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Questionario;

public class QuestionarioDaoImplementation implements QuestionarioDao{



public List<Questionario> localizaQuestoes(int idQuestao) {
List<Questionario> questoes = new ArrayList<Questionario>();
try{
Connection con = JDBCUtil.getConnection();
String sql = "select questao.idQuestao, questao.titulo, opcao.descricao "+
"from questao "+
"inner join opcao "+
"on questao.idQuestao = opcao.idQuestao "+
"Where questao.idQuestao = ?";
PreparedStatement stmt = con.prepareStatement (sql);
stmt.setInt(1, idQuestao);
ResultSet rs = stmt.executeQuery();
	while(rs.next())
	{
	Questionario q = new Questionario();
	q.setIdQuestao (rs.getInt("idQuestao"));
	q.setTitulo(rs.getString("titulo"));
	q.setResposta(rs.getString("descricao"));
	questoes.add(q);
	}

}
catch(SQLException e){
e.printStackTrace();
}
return questoes;
}

@Override
public List<Questionario> localizaQuestoes() {
	List<Questionario> questoes = new ArrayList<Questionario>();
	try{
	Connection con = JDBCUtil.getConnection();
	String sql = "select distinct idQuestao, titulo from questao order by idQuestao";
	PreparedStatement stmt = con.prepareStatement (sql);
	ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
		Questionario q = new Questionario();
		q.setIdQuestao (rs.getInt("idQuestao"));
		q.setTitulo(rs.getString("titulo"));
		questoes.add(q);
		}

	}
	catch(SQLException e){
	e.printStackTrace();
	}
	return questoes;
	}

}
