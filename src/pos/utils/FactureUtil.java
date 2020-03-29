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

import commun.DataProduit;
import commun.Produit;
import commun.Transaction;
import commun.exception.ExceptionProduitEtablissement;


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
	private static void connexionCourriel() {

					System.out.println("Création de la connection au courriel...");
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
	

	
	private static boolean envoyerCourriel(String path, String prenom, String courriel, String nomEtablissement, String heure) {

		boolean envoye = true;

		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USER));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(courriel));
			message.setSubject("Facture de votre achat - " + nomEtablissement + " - " 
			+ heure);

			// 3) Crée le corps du message
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Bonjour, \n\n" + "Voici en pièce jointe la facture de"
			+ " votre achat réalisé à " + nomEtablissement + ".\n\nMerci de faire confiance à la grande famille de Crédigit,"
					+ "\n\nCredigit\nUne filiale de Bank-era\n\n\n");

			// Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart1);

	         // Part two is attachment
	         messageBodyPart1 = new MimeBodyPart();
	         DataSource source = new FileDataSource(path);    
	         messageBodyPart1.setDataHandler(new DataHandler(source));
	         messageBodyPart1.setFileName("Facture " + nomEtablissement + "/" + heure);
	         multipart.addBodyPart(messageBodyPart1);

	         // Send the complete message parts
	         message.setContent(multipart );

			// 5) Envoir le message
			Transport.send(message);
			
			System.out.println("Courriel envoyé");
			
			

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}

		return envoye;
	}

	
	public static PDDocument envoyerFacture(String prenomClient, String courrielClient, Transaction transaction) throws IOException
	{
		connexionCourriel();
    	
		PDDocument retour = null;
    	String filename = "facture" + prenomClient + ".pdf";
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
                String line1 = "";
                cont.showText(line1);
                cont.newLine();

                cont.endText();
            }
            
            doc.save(absoluteFilePath);
            retour = doc;
            
            System.out.println("Document enregistré");

            envoyerCourriel(absoluteFilePath, prenomClient, courrielClient, transaction.getNomEtablissement(), transaction.getHeure());

            try{
        		
        		File file = new File(absoluteFilePath);
            	
        		if(file.delete()){
        			System.out.println("Document supprimé");
        		}else{
        			System.out.println("Document non supprimé");
        		}
        	   
        	}catch(Exception e){
        		
        		e.printStackTrace();
        		
        	}
        }
        
        return retour;
    }
	

	    public static void main(String[] args) throws IOException, ExceptionProduitEtablissement {
	    	
	    	Produit p1, p2, p3;

	    	Transaction tr1 = new Transaction();

	    		DataProduit d1 = new DataProduit();

	    		byte[] array1 = { 0, 1 };

	    		d1.setCoutant(12.34f);
	    		d1.setDescription("Une banane d'Asie");
	    		d1.setFournisseur("China");
	    		d1.setImage(array1);
	    		d1.setNom("Banane");
	    		d1.setPrix(13.45f);
	    		d1.setQuantite(45);
	    		d1.setSku(12);

	    		DataProduit d2 = new DataProduit();

	    		byte[] array2 = { 1, 1 };

	    		d2.setCoutant(2.3f);
	    		d2.setDescription("Un chocolat");
	    		d2.setFournisseur("Leclerc");
	    		d2.setImage(array2);
	    		d2.setNom("Chocolat");
	    		d2.setPrix(3.5f);
	    		d2.setQuantite(234);
	    		d2.setSku(564);

	    		DataProduit d3 = new DataProduit();

	    		byte[] array3 = { 1, 1 };

	    		d3.setCoutant(2.3f);
	    		d3.setDescription("Un gateau");
	    		d3.setFournisseur("Vachon");
	    		d3.setImage(array3);
	    		d3.setNom("Gateau");
	    		d3.setPrix(3.5f);
	    		d3.setQuantite(234);
	    		d3.setSku(565);

	    		p1 = new Produit(d1);
	    		p2 = new Produit(d2);
	    		p3 = new Produit(d3);
	    
	    		tr1.addProduit(p1);
	    		tr1.addProduit(p1);
	    		tr1.addProduit(p2);
	    		tr1.addProduit(p3);
	    		
	    		envoyerFacture("Étienne", "eticlo24@gmail.com", tr1);
	    	
	    }

	    	
	}

