package ManagedBeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean(name="instalacaoMB")
@RequestScoped
public class InstalacaoMB {
private String diretorioArquivo; 

public String instalacaoAutomatica(){
	boolean copiado = false;
	try{
  File file = new File("C:/CodeForest");
   file.mkdir();
   
    FacesContext facesContext = FacesContext.getCurrentInstance();  
    ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
    diretorioArquivo = scontext.getRealPath("/WEB-INF/instalacaoAutomatica/");
    System.out.println("Diret�rio:"+diretorioArquivo);
	File arquivoOrigem = new File(visualizarArquivos());
	FileReader fis = new FileReader(arquivoOrigem);
	BufferedReader bufferedReader = new BufferedReader(fis);
	StringBuilder buffer = new StringBuilder();
	String line = "";
	while ((line = bufferedReader.readLine()) != null) {
		buffer.append(line).append("\n");			
	}
	fis.close();
	bufferedReader.close();
	File arquivoDestino = new File("C:/CodeForest/br.usp.each.saeg.code.forest.ui_1.0.0.201605081809.jar");	
	FileWriter writer = new FileWriter(arquivoDestino);
	writer.write(buffer.toString());
	writer.flush();
	writer.close();
	copiado = true;
}
	catch(IOException e){
	e.printStackTrace();
	}
	
	if(copiado){		
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("formIndex:mensagem", new FacesMessage (FacesMessage.SEVERITY_INFO, "O arquivo Jar da ferramenta foi baixado no diret�rio C:/CodeForest. Para o t�rmino da instala��o, copie-o para o diret�rio \\plugins do Eclipse.",""+""));
	}
	else{
    FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("formIndex:mensagem", new FacesMessage (FacesMessage.SEVERITY_ERROR, "N�o foi poss�vel realizar a c�pia do arquivo Jar. Por favor, verifique se o mesmo j� n�o foi copiado para o diret�rio C:/CodeForest.",""+""));		
	}
 return "";
}


public String visualizarArquivos() throws IOException {
	String caminhoCompleto="";
	File file = new File(diretorioArquivo);
	File afile[] = file.listFiles();
	for (int i=0;i<afile.length;i++) {
	  File arquivo = afile[i];
	  if(arquivo.getName().endsWith(".jar") && arquivo.getName().contains("code.forest.ui")){
	  caminhoCompleto=diretorioArquivo+"\\"+arquivo.getName();	  
	  System.out.println("Arquivo jar");
	  }
	}
	
	System.out.println("Caminho completo:"+caminhoCompleto);

return caminhoCompleto;
}
}
