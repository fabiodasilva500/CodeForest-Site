// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 25/04/2016 09:22:01
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   GraficoQuestionarioMB.java

package ManagedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import Dao.OpcaoDao;
import Dao.OpcaoDaoImplementation;
import Dao.QuestionarioDao;
import Dao.QuestionarioDaoImplementation;
import Dao.RespostaDao;
import Dao.RespostaDaoImplementation;
import Entidades.Opcao;
import Entidades.Questionario;
import Entidades.TotalizaRespostas;

@ManagedBean(name="graficoQuestionarioMB")
@ViewScoped
public class GraficoQuestionarioMB
{

    public GraficoQuestionarioMB()
    {
        qDao = new QuestionarioDaoImplementation();
        oDao = new OpcaoDaoImplementation();
        rDao = new RespostaDaoImplementation();
        setListaQuestoes(new ArrayList());
        setListaRespostas(new ArrayList());
        setListaQuestoes(qDao.localizaQuestoes());
        setListaRespostas(new ArrayList());
        numeroQuestao = 0;
        bloqueiaRequisicao = true;
        setgraficoQuestionario(new PieChartModel());
    }

    public String questaoAtual()
    {
        if(bloqueiaRequisicao)
            bloqueiaRequisicao = false;
        else
        if(numeroQuestao >= 11)
        {
            numeroQuestao = 0;
        } else
        {
            questao = ((Questionario)listaQuestoes.get(numeroQuestao)).getTitulo();
            numeroQuestao++;
            setListaOpcoes(oDao.localizaOpcoes(numeroQuestao));
            bloqueiaRequisicao = true;
        }
        return questao;
    }

    public boolean habilitaGrafico(int i)
    {
        indice = i;
        graficoQuestoes();
        return habilitaQuestao;
    }

    public PieChartModel graficoQuestoes()
    {
        graficoQuestionario.clear();
        setListaOpcoes(oDao.localizaOpcoes(((Questionario)listaQuestoes.get(indice)).getIdQuestao()));
        List dadosLocalizados = new ArrayList();
        for(int i = 0; i < listaOpcoes.size(); i++)
        {
            int id = indice + 1;
            TotalizaRespostas t = rDao.graficoRespostas(((Opcao)listaOpcoes.get(i)).getIdOpcao(), id);
            if(t == null)
            {
                t = new TotalizaRespostas();
                t.setIdResposta(0);
                t.setDescricao("");
                dadosLocalizados.add(t);
            } else
            {
                dadosLocalizados.add(t);
            }
            graficoQuestionario.set((new StringBuilder(String.valueOf(((Opcao)listaOpcoes.get(i)).getDescricao()))).append(" :").append(((TotalizaRespostas)dadosLocalizados.get(i)).getIdResposta()).toString(), Integer.valueOf(((TotalizaRespostas)dadosLocalizados.get(i)).getIdResposta()));
        }

        return graficoQuestionario;
    }

    public PieChartModel getGraficoQuestionario()
    {
        return graficoQuestionario;
    }

    public void setgraficoQuestionario(PieChartModel graficoQuestionario)
    {
        this.graficoQuestionario = graficoQuestionario;
    }

    public String getQuestao()
    {
        return questao;
    }

    public void setQuestao(String questao)
    {
        this.questao = questao;
    }

    public List getListaQuestoes()
    {
        return listaQuestoes;
    }

    public void setListaQuestoes(List listaQuestoes)
    {
        this.listaQuestoes = listaQuestoes;
    }

    public List getListaOpcoes()
    {
        return listaOpcoes;
    }

    public void setListaOpcoes(List listaOpcoes)
    {
        this.listaOpcoes = listaOpcoes;
    }

    public List getListaRespostas()
    {
        return listaRespostas;
    }

    public void setListaRespostas(List listaRespostas)
    {
        this.listaRespostas = listaRespostas;
    }

    public QuestionarioDao getqDao()
    {
        return qDao;
    }

    public void setqDao(QuestionarioDao qDao)
    {
        this.qDao = qDao;
    }

    public OpcaoDao getoDao()
    {
        return oDao;
    }

    public void setoDao(OpcaoDao oDao)
    {
        this.oDao = oDao;
    }

    public RespostaDao getrDao()
    {
        return rDao;
    }

    public void setrDao(RespostaDao rDao)
    {
        this.rDao = rDao;
    }

    public boolean isHabilitaQuestao()
    {
        return habilitaQuestao;
    }

    public void setHabilitaQuestao(boolean habilitaQuestao)
    {
        this.habilitaQuestao = habilitaQuestao;
    }

    private String questao;
    private int numeroQuestao;
    private int indice;
    private boolean bloqueiaRequisicao;
    private boolean habilitaQuestao;
    private List listaQuestoes;
    private List listaOpcoes;
    private List listaRespostas;
    private QuestionarioDao qDao;
    private OpcaoDao oDao;
    private RespostaDao rDao;
    private PieChartModel graficoQuestionario;
}
