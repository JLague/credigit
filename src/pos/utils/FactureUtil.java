package pos.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


/**
 * Cette classe permet de créer et d'envoyer des factures 
 * 
 * @author Bank-era
 *
 */
public class FactureUtil {
	

	/**
	 * String représentant le compte d'envoi
	 */
	private static final String USER = "credigit.bankera@gmail.com";
	/**
	 * String représentant le mot de passe de connection du compte d'envoi
	 */
	private static final String PASSWORD = "78f6ENKUgp2HHtr33Da4q";

	/**
	 * Session de communication avec le serveur
	 */
	private  static Session session;


	/**
	 * Constructeur de la connection du serveur d'envoi de courriels
	 */
	public static void connexionCourriel() {

					System.out.println("Création de la connection...");
					Properties properties = System.getProperties();
					properties.put("mail.smtp.host", "smtp.gmail.com");
					properties.put("mail.smtp.port", "587");
					properties.put("mail.smtp.auth", "true");
					properties.put("mail.smtp.starttls.enable", "true");

					session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(USER, PASSWORD);
						}
					});

	}
			

	
	public static boolean envoyerCourriel(String path) {

		boolean envoye = true;

		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USER));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("eticlo24@gmail.com"));
			message.setSubject("Bienvenue dans la famille!");

			// 3) Crée le corps du message
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Bonjour " );

			// Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart1);

	         // Part two is attachment
	         messageBodyPart1 = new MimeBodyPart();
	         DataSource source = new FileDataSource(path);    
	         messageBodyPart1.setDataHandler(new DataHandler(source));
	         messageBodyPart1.setFileName("Facture Test");
	         multipart.addBodyPart(messageBodyPart1);

	         // Send the complete message parts
	         message.setContent(multipart );

			// 5) Envoir le message
			Transport.send(message);
			
			System.out.println("Envoye");
			
			

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}

		return envoye;
	}


	    public static void main(String[] args) throws IOException {

	    	connexionCourriel();
	    	
	    	String filename = "newFile.pdf";
			String workingDirectory = System.getProperty("java.io.tmpdir");			
			String absoluteFilePath = workingDirectory + File.separator + filename;
	    	
	        try (PDDocument doc = new PDDocument()) {

	            PDPage myPage = new PDPage();
	            doc.addPage(myPage);

	            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

	                cont.beginText();

	                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
	                cont.setLeading(14.5f);

	                cont.newLineAtOffset(25, 700);
	                String line1 = "World War II (often abbreviated to WWII or WW2), "
	                        + "also known as the Second World War,";
	                cont.showText(line1);

	                cont.newLine();

	                String line2 = "was a global war that lasted from 1939 to 1945, "
	                        + "although related conflicts began earlier.";
	                cont.showText(line2);
	                cont.newLine();

	                String line3 = "It involved the vast majority of the world's "
	                        + "countries—including all of the great powers—";
	                cont.showText(line3);
	                cont.newLine();

	                String line4 = "eventually forming two opposing military "
	                        + "alliances: the Allies and the Axis.";
	                cont.showText(line4);
	                cont.newLine();

	                cont.endText();
	            }
	            
	            doc.save(absoluteFilePath);
	            
	            System.out.println("Document enregistré");

	            envoyerCourriel(absoluteFilePath);
	            
	            //Enresgistré la facture sur la base de données
	            
	            try{
	        		
	        		File file = new File(absoluteFilePath);
	            	
	        		if(file.delete()){
	        			System.out.println(file.getName() + " is deleted!");
	        		}else{
	        			System.out.println("Delete operation is failed.");
	        		}
	        	   
	        	}catch(Exception e){
	        		
	        		e.printStackTrace();
	        		
	        	}
	        }
	    }
	}

