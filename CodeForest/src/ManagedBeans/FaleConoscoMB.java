package ManagedBeans;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean (name="faleConoscoMB")
@SessionScoped
public class FaleConoscoMB  implements Serializable{
	
	private boolean habilitaGridErro;
        
    private String assunto;
    private String conteudo;
    private String emailRemetente;
    
    private UploadedFile arquivo;  
    private String caminho;
    private boolean habilitaGridArquivo;
    private FileUploadEvent arquivoSelecionado;
   
    
  	public FaleConoscoMB(){
    limpaCampo();
	}
			
public void enviarEmail() throws AddressException, MessagingException, IOException {
	
	if(habilitaGridErro && habilitaGridArquivo==false){
	enviarUsuarioUnicoSemAnexo();
	}
	else
	if(habilitaGridErro == false && habilitaGridArquivo==false){
	enviarParaTodosSemAnexo();
	}
	else
	if(habilitaGridErro && habilitaGridArquivo==true){
		enviarUsuarioUnicoComAnexo();
	}
	else
	if(habilitaGridErro==false && habilitaGridArquivo==true){
	enviaParaTodosComAnexo();
	}
	
}

public void enviarUsuarioUnicoSemAnexo() throws AddressException, MessagingException {
	boolean enviado = false;

	String destinatario = "fabiodasilva600@gmail.com";
	FacesContext context = FacesContext.getCurrentInstance();
	
	
	String host = null;
	String port = null;
	final String email = "fabiodasilva600@gmail.com";
	final String senha = "aparecida1962";


	String[] attachFiles = new String[1];

	Properties dados = new Properties();


	host = "smtp.gmail.com";
	port = "587";
	dados.put("mail.smtp.host", host);
	dados.put("mail.smtp.port", port);
	dados.put("mail.smtp.auth", "true");
	dados.put("mail.smtp.starttls.enable", "true");
	dados.put("mail.user", email);
	dados.put("mail.password", senha);



	Authenticator auth = new Authenticator() {
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(email, senha);
	}
	};
	
	try{
	Session session = Session.getInstance(dados, auth);
	MimeMessage message = new MimeMessage(session);
	message.setSubject(assunto);
	message.setContent(conteudo, "text/plain");
	message.setFrom(new InternetAddress(email));
	message.addRecipients(Message.RecipientType.TO, destinatario);
	Transport.send(message);

	enviado= true;
	}
	catch(Exception e){
	e.printStackTrace();
	}
	
	if(enviado){
	context.addMessage("formQuestionario:mensagem", new FacesMessage (FacesMessage.SEVERITY_INFO, "Email enviado com sucesso.",""+""));
	if(!emailRemetente.equals("") && !emailRemetente.equals(null)){
    enviarCopiaSemAnexo();
    }
	limpaCampo();
	}
	else{
	context.addMessage("formQuestionario:mensagem", new FacesMessage (FacesMessage.SEVERITY_ERROR, "Email não enviado.",""+""));
	}
	}

	
	
public void enviarParaTodosSemAnexo() throws AddressException, MessagingException {
boolean enviado=false;

    System.out.println("Enviando email");

	String destinatario = "fabiodasilva600@gmail.com";

		

	String host = null;
	String port = null;
	final String email = "fabiodasilva600@gmail.com";
	final String senha = "aparecida1962";


	String[] attachFiles = new String[1];

	Properties dados = new Properties();


	host = "smtp.gmail.com";
	port = "587";
	dados.put("mail.smtp.host", host);
	dados.put("mail.smtp.port", port);
	dados.put("mail.smtp.auth", "true");
	dados.put("mail.smtp.starttls.enable", "true");
	dados.put("mail.user", email);
	dados.put("mail.password", senha);



	Authenticator auth = new Authenticator() {
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(email, senha);
	}
	};
	try{
	Session session = Session.getInstance(dados, auth);

	
	MimeMessage message = new MimeMessage(session);
	message.setSubject(assunto);
	message.setContent(conteudo, "text/plain");
	message.setFrom(new InternetAddress(email));
	message.addRecipients(Message.RecipientType.TO, destinatario);
	Transport.send(message);

	enviado=true;
	}
	catch(Exception e){
	e.printStackTrace();
	}
	
   FacesContext context = FacesContext.getCurrentInstance();

    if(enviado){
    context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_INFO, "Email enviado com sucesso.",""+""));
	if(!emailRemetente.equals("") && !emailRemetente.equals(null)){
    enviarCopiaSemAnexo();
    }
	limpaCampo();
    }
    else{
    context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_ERROR, "Email não enviado.",""+""));
    }

}


public void enviarCopiaSemAnexo() throws AddressException, MessagingException {
	FacesContext context = FacesContext.getCurrentInstance();  

    String emailUsuario= emailRemetente;
    		
    System.out.println("Email Professor:"+emailUsuario);
    
	String destinatario = emailUsuario;			
	
	String host = null;
	String port = null;
	final String email = "fabiodasilva600@gmail.com";
	final String senha = "aparecida1962";


	String[] attachFiles = new String[1];

	Properties dados = new Properties();


	host = "smtp.gmail.com";
	port = "587";
	dados.put("mail.smtp.host", host);
	dados.put("mail.smtp.port", port);
	dados.put("mail.smtp.auth", "true");
	dados.put("mail.smtp.starttls.enable", "true");
	dados.put("mail.user", email);
	dados.put("mail.password", senha);



	Authenticator auth = new Authenticator() {
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(email, senha);
	}
	};
	
	try{
	Session session = Session.getInstance(dados, auth);
	MimeMessage message = new MimeMessage(session);
	message.setSubject(assunto);
	message.setContent(conteudo, "text/plain");
	message.setFrom(new InternetAddress(email));
	message.addRecipients(Message.RecipientType.TO, destinatario);
	Transport.send(message);
	
    //context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_INFO, "Email enviado com sucesso.",""+""));
    
    limpaCampo();
	}
	catch(Exception e){
	e.printStackTrace();
	}
}


    public void enviarUsuarioUnicoComAnexo() throws AddressException, MessagingException, IOException{
    	FacesContext context = FacesContext.getCurrentInstance();  

    	String host = null;
		String port = null;
		boolean enviado=false;
	

		final String email = "fabiodasilva600@gmail.com";
		final String senha = "aparecida1962";
	    
		String destinatario = "fabiodasilva600@gmail.com";
		String[] attachFiles = new String[1];
		attachFiles[0] = caminho;

		Properties dados = new Properties();

			host = "smtp.gmail.com";
			port = "587";
			dados.put("mail.smtp.host", host);
			dados.put("mail.smtp.port", port);
			dados.put("mail.smtp.auth", "true");
			dados.put("mail.smtp.starttls.enable", "true");
			dados.put("mail.user", email);
			dados.put("mail.password", senha);
		

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, senha);
			}
		};
		Session session = Session.getInstance(dados, auth);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email));
		InternetAddress[] toAddresses = { new InternetAddress(destinatario) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(assunto);
		msg.setSentDate(new Date());
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(conteudo, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
	
		MimeMessage message = new MimeMessage(session);
		message.setSubject(assunto);
		message.setFrom(new InternetAddress(email));
		message.addRecipients(Message.RecipientType.TO, destinatario);


		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
					enviado=true;

		
				} catch (IOException ex) {
					enviado=false;
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}


			msg.setContent(multipart);
			Transport.send(msg);
			enviado = true;
		}
		
		
		if(enviado){
		if(!emailRemetente.equals("") && !emailRemetente.equals(null)){
			enviarCopiaComAnexo();
		}
	    limpaCampo();
		removeArquivo();
		context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_INFO, "Email enviado com sucesso.",""+""));
		}

		else{
		context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_ERROR, "Email não enviado, todos os campos são de preenchimento obrigatório.",""+""));	
		}
		}
   
    
    
    public void enviaParaTodosComAnexo() throws AddressException, MessagingException{
FacesContext context = FacesContext.getCurrentInstance();  
		
    	String host = null;
		String port = null;
		boolean enviado=false;
	

		final String email = "fabiodasilva600@gmail.com";
		final String senha = "aparecida1962";
	    
		String destinatario = "fabiodasilva600@gmail.com";
		String[] attachFiles = new String[1];
		attachFiles[0] = caminho;

		Properties dados = new Properties();

			host = "smtp.gmail.com";
			port = "587";
			dados.put("mail.smtp.host", host);
			dados.put("mail.smtp.port", port);
			dados.put("mail.smtp.auth", "true");
			dados.put("mail.smtp.starttls.enable", "true");
			dados.put("mail.user", email);
			dados.put("mail.password", senha);
		

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, senha);
			}
		};
		Session session = Session.getInstance(dados, auth);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email));
		InternetAddress[] toAddresses = { new InternetAddress(destinatario) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(assunto);
		msg.setSentDate(new Date());
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(conteudo, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
	
		MimeMessage message = new MimeMessage(session);
		message.setSubject(assunto);
		message.setFrom(new InternetAddress(email));
		message.addRecipients(Message.RecipientType.TO, destinatario);


		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
					enviado=true;

		
				} catch (IOException ex) {
					enviado=false;
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}


			msg.setContent(multipart);
			Transport.send(msg);
			enviado = true;
		}
		
		
		if(enviado){
		if(!emailRemetente.equals("") && !emailRemetente.equals(null)){
			enviarCopiaComAnexo();
		}
	    limpaCampo();
		removeArquivo();
		context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_INFO, "Email enviado com sucesso.",""+""));
		}

		else{
		context.addMessage("formFaleConosco:mensagem", new FacesMessage (FacesMessage.SEVERITY_ERROR, "Email não enviado, todos os campos são de preenchimento obrigatório.",""+""));	
		}
		}
   
    
  
    public void enviarCopiaComAnexo () throws MessagingException{
    	String host = null;
		String port = null;

		final String email = "fabiodasilva600@gmail.com";
		final String senha = "aparecida1962";
		
		String destinatario = emailRemetente;	

		String[] attachFiles = new String[1];
		attachFiles[0] = caminho;

		Properties dados = new Properties();

			host = "smtp.gmail.com";
			port = "587";
			dados.put("mail.smtp.host", host);
			dados.put("mail.smtp.port", port);
			dados.put("mail.smtp.auth", "true");
			dados.put("mail.smtp.starttls.enable", "true");
			dados.put("mail.user", email);
			dados.put("mail.password", senha);
		

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, senha);
			}
		};
		Session session = Session.getInstance(dados, auth);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email));
		InternetAddress[] toAddresses = { new InternetAddress(destinatario) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(assunto);
		msg.setSentDate(new Date());
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(conteudo, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
	
		MimeMessage message = new MimeMessage(session);
		message.setSubject(assunto);
		message.setFrom(new InternetAddress(email));
		message.addRecipients(Message.RecipientType.TO, destinatario);


		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
			

				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}


			msg.setContent(multipart);
			Transport.send(msg);

		}
    }

	

    
    
    public void uploadListener(FileUploadEvent evento) throws IOException {  
        this.arquivo = evento.getFile();  
  
        FacesContext ctx = FacesContext.getCurrentInstance();  
        FacesMessage msg = new FacesMessage();  
  
        msg.setSummary("Arquivo anexado com sucesso.");  
        msg.setSeverity(FacesMessage.SEVERITY_INFO);  
  
        ctx.addMessage("mensagens", msg);  
          
        String nomeArquivo = arquivo.getFileName(); //Nome do arquivo enviado  
        byte[] conteudo = evento.getFile().getContents(); //Conteudo a ser gravado no arquivo  
        
        System.out.println(conteudo);
        
   
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
        
       
       FacesContext fc = FacesContext.getCurrentInstance();
       ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
       caminho = sc.getRealPath(File.separator+"/WEB-INF/LIB/EnviaAnexo/"+nomeArquivo);
       System.out.println("Caminho:"+caminho);

  
        FileWriter writer = new FileWriter(caminho);
		writer.write(caminho.toString());
		writer.flush();
		writer.close();               
        arquivoSelecionado = evento;
       }

    
    
    
     public void limpaCampo(){
     assunto="";
     conteudo="";
     emailRemetente="";
      }
     
     public void removeArquivo(){
  		File f = new File(caminho); 
  		f.delete();
     }
   

public boolean isHabilitaGridErro() {
	return habilitaGridErro;
}


public void setHabilitaGridErro(boolean habilitaGridErro) {
	this.habilitaGridErro = habilitaGridErro;
}


public String getAssunto() {
	return assunto;
}


public void setAssunto(String assunto) {
	this.assunto = assunto;
}


public String getConteudo() {
	return conteudo;
}


public void setConteudo(String conteudo) {
	this.conteudo = conteudo;
}





public UploadedFile getArquivo() {
	return arquivo;
}


public void setArquivo(UploadedFile arquivo) {
	this.arquivo = arquivo;
}


public String getCaminho() {
	return caminho;
}


public void setCaminho(String caminho) {
	this.caminho = caminho;
}


public boolean ishabilitaGridArquivo() {
	return habilitaGridArquivo;
}


public void sethabilitaGridArquivo(boolean habilitaGridArquivo) {
	this.habilitaGridArquivo = habilitaGridArquivo;
}


public FileUploadEvent getArquivoSelecionado() {
	return arquivoSelecionado;
}


public void setArquivoSelecionado(FileUploadEvent arquivoSelecionado) {
	this.arquivoSelecionado = arquivoSelecionado;
}

public String getEmailRemetente() {
	return emailRemetente;
}

public void setEmailRemetente(String emailRemetente) {
	this.emailRemetente = emailRemetente;
}

}
