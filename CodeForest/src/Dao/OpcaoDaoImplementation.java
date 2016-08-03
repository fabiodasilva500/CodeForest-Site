package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Opcao;

public class OpcaoDaoImplementation implements OpcaoDao{


public int localizaIDResposta(int idQuestao, String opcao) {
int idOpcao = 0;
try{
Connection con = JDBCUtil.getConnection();
String sql = "select idOpcao from Opcao where idQuestao = ? and descricao like ? ";
PreparedStatement stmt = con.prepareStatement (sql);
stmt.setInt(1, idQuestao);
stmt.setString(2, opcao);
ResultSet rs = stmt.executeQuery();
	while(rs.next())
	{
	idOpcao = rs.getInt("idOpcao");
	}

}
catch(SQLException e){
e.printStackTrace();
}
return idOpcao;
}




public List<Opcao> localizaOpcoes(int idQuestao) {
List<Opcao> opcoes= new ArrayList<Opcao>();

try{
Connection con = JDBCUtil.getConnection();
String sql = "select idOpcao, idQuestao, descricao from Opcao where idQuestao = ?";
PreparedStatement stmt = con.prepareStatement (sql);
stmt.setInt(1, idQuestao);
ResultSet rs = stmt.executeQuery();
	while(rs.next())
	{
	Opcao op = new Opcao();
	op.setIdOpcao(rs.getInt("idOpcao"));
	op.setIdQuestao(rs.getInt("idQuestao"));
	op.setDescricao(rs.getString("descricao"));
	
	op.setIdConvertido(String.valueOf(op.getIdOpcao()));
	op.setDescricaoConvertida(String.valueOf(op.getDescricao()));
	opcoes.add(op);
	}

}
catch(SQLException e){
e.printStackTrace();
}
return opcoes;
}


}
