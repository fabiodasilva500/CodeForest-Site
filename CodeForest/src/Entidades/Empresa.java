package Entidades;

import java.io.Serializable;

import Conversor.BaseEntity;

public class Empresa implements BaseEntity, Serializable {  
	  
    private static final long serialVersionUID = 1L;  
  
    private Integer codigo;  
    private String nome;  
  
    public Empresa(Integer codigo, String nome) {  
        this.codigo = codigo;  
        this.nome = nome;  
    }  
  
    public Long getId() {  
        return new Long(codigo);  
    }

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}  
  
    // Métodos getters e setters  
    // Não esquecer os métodos equals e hashCode  
}  
