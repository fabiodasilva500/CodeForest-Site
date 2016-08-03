package Entidades;

import java.io.Serializable;


import Conversor.BaseEntity;

public class Opcao  implements  Serializable, BaseEntity{
private int idOpcao;
private int idQuestao;
private String descricao;

private Opcao opcao;

private String idConvertido;
private String descricaoConvertida;

public int getIdQuestao() {
	return idQuestao;
}
public void setIdQuestao(int idQuestao) {
	this.idQuestao = idQuestao;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public int getIdOpcao() {
	return idOpcao;
}
public void setIdOpcao(int idOpcao) {
	this.idOpcao = idOpcao;
}
public String getIdConvertido() {
	return idConvertido;
}
public void setIdConvertido(String idConvertido) {
	this.idConvertido = idConvertido;
}
public String getDescricaoConvertida() {
	return descricaoConvertida;
}
public void setDescricaoConvertida(String descricaoConvertida) {
	this.descricaoConvertida = descricaoConvertida;
}


@Override
public boolean equals(Object obj) {
    if (obj == null) {
        return false;
    }
   
    
    return true;
}

@Override
public int hashCode() {
    int hash = 7;
    hash = 47 * hash + (int) (this.idOpcao ^ (this.idOpcao >>> 32));
    hash = 47 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
    hash = 47 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
    return hash;
}
public Opcao getOpcao() {
	return opcao;
}
public void setOpcao(Opcao opcao) {
	this.opcao = opcao;
}


@Override
public Long getId() {
	// TODO Auto-generated method stub
    return new Long(this.idOpcao); 
}



}
