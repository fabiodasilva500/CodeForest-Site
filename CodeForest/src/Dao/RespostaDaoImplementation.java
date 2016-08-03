package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entidades.TotalizaRespostas;

public class RespostaDaoImplementation implements RespostaDao{

public boolean inserir(int idQuestao, int idResposta){
boolean efetuado=false;
System.out.println("Inserindo");
try{
Connection con = JDBCUtil.getConnection();
String sql = "Insert into Resposta values (?,?)";
PreparedStatement stmt=con.prepareStatement(sql);
stmt.setInt(1, idQuestao);
stmt.setInt(2, idResposta);
stmt.executeUpdate();
efetuado=true;
}
catch(SQLException e){
e.printStackTrace();
}

System.out.println("Efetuado:"+efetuado);
return efetuado;
}


public TotalizaRespostas graficoRespostas(int idOpcao, int idQuestao) {
TotalizaRespostas totais = null;
try{
String sql = "select Count(idResposta) as 'respostas', opcao.descricao as 'descricao' "+
"from resposta "+
"inner join opcao "+
"on resposta.idOpcao = opcao.idOpcao "+
"inner join questao "+
"on opcao.idQuestao = questao.idQuestao "+
"where questao.idQuestao = ? and opcao.idOpcao= ? "+
"group by opcao.descricao ";
Connection con = JDBCUtil.getConnection();

PreparedStatement stmt=con.prepareStatement(sql);
stmt.setInt(1, idQuestao);
stmt.setInt(2, idOpcao);
ResultSet rs = stmt.executeQuery();
if(rs.next()){
totais=new TotalizaRespostas();

totais.setIdResposta(rs.getInt("respostas"));
totais.setDescricao(rs.getString("descricao"));
}
}
catch(SQLException e){
e.printStackTrace();
}
return totais;
}

}