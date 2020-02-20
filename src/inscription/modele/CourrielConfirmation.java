package inscription.modele;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class CourrielConfirmation {
	
	static public boolean envoyerCourriel(String adresse, String Prenom)
    {    
        
		boolean envoye = true;
		
		String to= adresse;   
        final String user="credigit.bankera@gmail.com";   
        final String password="78f6ENKUgp2HHtr33Da4q";   

        //1) Ouvre la session pour envoyer un courriel   
        Properties properties = System.getProperties();  
        properties.put("mail.smtp.host", "smtp.gmail.com");  
        properties.put("mail.smtp.port", "587"); 
        properties.put("mail.smtp.auth", "true");   
        properties.put("mail.smtp.starttls.enable", "true"); 

        Session session = Session.getDefaultInstance(properties,   
                new javax.mail.Authenticator() {   
            protected PasswordAuthentication getPasswordAuthentication() {   
                return new PasswordAuthentication(user,password);    }   });       

        //2) Composition du message      
        try{    
            MimeMessage message = new MimeMessage(session);    
            message.setFrom(new InternetAddress(user));     
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
            message.setSubject("Bienvenue dans la famille!");         

            //3) Crée le corps du message        
            BodyPart messageBodyPart1 = new MimeBodyPart();     
            messageBodyPart1.setText("Bonjour " + Prenom + ",\n\nLa grande famille de Credigit est heureuse de vous"
            		+ " compter comme un nouveau membre! Avec Credigit, vous rendez vos transactions bancaires plus sécuritaires.\n\n"
            		+ "Si vous rencontrez des difficultés, n'hésitez pas à contacter notre service à la clientèle au 418-803-5698.\n\n"
            		+ "Continuez à avoir l'argent au bout de votre doigt,\n\n"
            		+ "L'équipe de Crédigit\n"
            		+ "Une filiale de Bank-era Corp");                     
  
            //4) Set le contenu du message   
            message.setContent(new MimeMultipart(messageBodyPart1));        

            //5) Envoir le message 
            Transport.send(message);       

        }catch (MessagingException ex) 
        {
        	envoye = false;
        }  
        
        return envoye;
    }

}
