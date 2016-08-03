package ManagedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import Dao.OpcaoDao;
import Dao.OpcaoDaoImplementation;
import Dao.QuestionarioDao;
import Dao.QuestionarioDaoImplementation;
import Dao.RespostaDao;
import Dao.RespostaDaoImplementation;
import Entidades.Opcao;
import Entidades.Questionario;
import Entidades.Resposta;


@ManagedBean(name="questionarioMB")
@ViewScoped
public class QuestionarioMB{
private String questao;
private int idQuestao;
private List<Questionario> listaQuestoes;
private List<Opcao> listaOpcoes;
private List<Resposta> listaRespostas;
private QuestionarioDao qDao;
private OpcaoDao oDao;
private RespostaDao rDao;

private Opcao opcaoAtual;


public QuestionarioMB(){
qDao = new QuestionarioDaoImplementation();
oDao = new OpcaoDaoImplementation();
rDao = new RespostaDaoImplementation();
setListaQuestoes(new ArrayList<Questionario>());
setListaRespostas(new ArrayList<Resposta>());
setListaQuestoes(qDao.localizaQuestoes());
opcaoAtual = new Opcao();
setListaRespostas(new ArrayList<Resposta>());
idQuestao = 0;
}


public String questaoAtual(){
if(idQuestao>=11){
idQuestao = 0;
}
else{
//System.out.println("ID Questão:"+idQuestao+" Tamanho:"+listaQuestoes.size()+" Indice:"+idQuestao);
questao = listaQuestoes.get(idQuestao).getTitulo();
idQuestao = idQuestao +1;
setListaOpcoes(oDao.localizaOpcoes(idQuestao));

//System.out.println("Lista de opções:"+listaOpcoes.size()+" Questão:"+idQuestao);
}
return questao;
}

public void setQuestao(String questao){
this.questao = questao;
}

public String getQuestao() {
	return questao;
}

public void setQuestoes(String questao) {
	this.questao = questao;
}

public List<Questionario> getListaQuestoes() {
	return listaQuestoes;
}

public void setListaQuestoes(List<Questionario> listaQuestoes) {
	this.listaQuestoes = listaQuestoes;
}


  public void limpaCampo(){
  listaQuestoes.clear();  
  listaOpcoes.clear();
  setListaQuestoes(new ArrayList<Questionario>());
  setListaOpcoes(new ArrayList<Opcao>());
  setListaQuestoes(qDao.localizaQuestoes());
  setListaRespostas(new ArrayList<Resposta>());
  setOpcaoAtual(new Opcao());
  idQuestao = 0;
  }


public List<Opcao> getListaOpcoes() {
	return listaOpcoes;
}


public void setListaOpcoes(List<Opcao> listaOpcoes) {
	this.listaOpcoes = listaOpcoes;
}


public List<Opcao> listaOpcoes(){
System.out.println("Listando opções do ID:"+idQuestao);
setListaOpcoes(oDao.localizaOpcoes(idQuestao));
return listaOpcoes;
}


public Opcao getOpcaoAtual() {
	return opcaoAtual;
}


public void setOpcaoAtual(Opcao opcaoAtual) {
	this.opcaoAtual = opcaoAtual;
}


public List<Opcao> insereResposta(){
boolean encontrado = false;
int idLocalizado = 0;
Resposta r = new Resposta();
r.setIdQuestao(opcaoAtual.getOpcao().getIdQuestao());
r.setIdResposta(opcaoAtual.getOpcao().getIdOpcao());

for (int i=0;i<listaRespostas.size();i++){
if(listaRespostas.get(i).getIdQuestao()==r.getIdQuestao()){
encontrado = true;
idLocalizado = i;
}
}

if(encontrado){
listaRespostas.set(idLocalizado, r);
}
else{
listaRespostas.add(r);
}

System.out.println("Adicionando:"+r.getIdQuestao()+ " "+r.getIdResposta()+" Tamanho:"+listaRespostas.size());
return listaOpcoes;
}

public String enviar(){
FacesContext context = FacesContext.getCurrentInstance();

boolean efetuado = false;
if(listaRespostas.size()==11){
for (int i=0;i<listaRespostas.size();i++){
efetuado = rDao.inserir(listaRespostas.get(i).getIdQuestao(), listaRespostas.get(i).getIdResposta());
}

if(efetuado){
context.addMessage("paginaPrincipal:mensagem", new FacesMessage (FacesMessage.SEVERITY_INFO, "Caro(a) usuário(a) o questionário foi respondido com sucesso.",""+""));
limpaCampo();
return "./index.jsf";
}
else{
context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_ERROR, "Caro(a) usuário(a) o questionário não foi preenchido",""+""));
limpaCampo();
}
}
else{
context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_ERROR, "Caro(a) usuário(a), por favor preencha todas as questões.",""+""));
limpaCampo();
}
return "";
}


public List<Resposta> getListaRespostas() {
	return listaRespostas;
}


public void setListaRespostas(List<Resposta> listaRespostas) {
	this.listaRespostas = listaRespostas;
}

}




