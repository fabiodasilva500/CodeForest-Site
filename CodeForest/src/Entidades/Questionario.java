package Entidades;

public class Questionario {
private int idQuestao;
private String titulo;
private String resposta;


public int getIdQuestao(){
	return idQuestao;
}

public void setIdQuestao(int idQuestao){
this.idQuestao= idQuestao;
}


public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}


public String getResposta() {
	return resposta;
}

public void setResposta(String resposta) {
	this.resposta = resposta;
}


}
