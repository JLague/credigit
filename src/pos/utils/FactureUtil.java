package pos.utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
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
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.settings.HorizontalAlignment;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.Row.RowBuilder;
import org.vandeseer.easytable.structure.Table.TableBuilder;
import org.vandeseer.easytable.structure.cell.TextCell;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import commun.DataProduit;
import commun.Etablissement;
import commun.LigneFacture;
import commun.Produit;
import commun.Transaction;
import commun.exception.ExceptionProduitEtablissement;
import javafx.collections.ObservableList;


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

	
	public static PDDocument envoyerFacture(String prenomClient, String nomClient, String courrielClient, Transaction transaction) throws IOException
	{
		//connexionCourriel();
    	
		PDDocument retour = null;
    	String filename = "facture" + prenomClient + ".pdf";
		String workingDirectory = System.getProperty("java.io.tmpdir");			
		String absoluteFilePath = workingDirectory + File.separator + filename;
    	
        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

                try (PDPageContentStream contTitre = new PDPageContentStream(doc, myPage, AppendMode.APPEND, false))
                {
                	contTitre.beginText();
                	
                	contTitre.setFont(PDType1Font.TIMES_ROMAN, 25);
                	contTitre.setLeading(14.5f);

                	contTitre.newLineAtOffset(250, 700);
                    
                    String line0 = "FACTURE" ;
                    contTitre.showText(line0);
                    contTitre.newLine();
                    
                    contTitre.endText();
                    
                }
                
               try (PDPageContentStream cont = new PDPageContentStream(doc, myPage, AppendMode.APPEND, false)) {   
                cont.beginText();
                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(25, 650);
                String line1 = "Client : " + prenomClient + " "+ nomClient ;
                cont.showText(line1);
                cont.newLine();
                
                String line2 = "Endroit de la transaction : " + transaction.getNomEtablissement() + ", " + transaction.getEtablissement().getAdresse();
                cont.showText(line2);
                cont.newLine();
                
                String line3 = "Heure de la transaction : " + transaction.getHeure();
                cont.showText(line3);
                cont.newLine();
                
                String line4 = "Numéro de la transaction : " + transaction.getNumero();
                cont.showText(line4);
                cont.newLine();
                
                String line5 = "Courriel de l'établissment : " + transaction.getEtablissement().getCourriel();
                cont.showText(line5);
                cont.newLine();
                cont.newLine();

                cont.endText();
            }
               
               try (PDPageContentStream contentStream = new PDPageContentStream(doc, myPage, AppendMode.APPEND, false)) {

                   // Build the table
                   TableBuilder myTable = Table.builder();
                          myTable.addColumnsOfWidth(200, 150, 100, 100 );                      
             
                          RowBuilder row = Row.builder();
                          row.add(TextCell.builder().text(String.valueOf("Produit")).borderWidth(1).backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build())
                          .add(TextCell.builder().text(String.valueOf("Quantité")).borderWidth(1).backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build())
                          .add(TextCell.builder().text(String.valueOf("Prix unitaire")).borderWidth(1).backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build())
                          .add(TextCell.builder().text(String.valueOf("Prix")).borderWidth(1).borderWidth(1).backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build());
          
                   myTable.addRow(row.build());
                   
                   NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));
                   
                   for(int i = 0; i < transaction.getLignesFacture().size(); i++)
                   {
                	   RowBuilder row1 = Row.builder();
                	   
                	   LigneFacture lignesFacture = transaction.getLignesFacture().get(i);
                	   
                	   
                	   row1.add(TextCell.builder().text(lignesFacture.getNom()).borderWidth(1).backgroundColor(Color.WHITE).build())
						.add(TextCell.builder().text(String.valueOf(lignesFacture.getQuantite())).borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text(String.valueOf(cf.format(lignesFacture.getPrixUnitaire()))).borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text(lignesFacture.getPrixString()).borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build());
						
						
                       myTable.addRow(row1.build());
                   }
                   
                   
                   RowBuilder rowTemp = Row.builder();
                   rowTemp.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text("").borderWidth(1).borderWidth(1).backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build());
   
                   myTable.addRow(rowTemp.build());
                   
                   RowBuilder row2 = Row.builder();
                   row2.add(TextCell.builder().text(String.valueOf("Sous-Total")).borderWidth(1).backgroundColor(Color.WHITE).build())
                   .add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text(String.valueOf(cf.format(transaction.getSousTotal()))).borderWidth(1).borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build());
   
                   myTable.addRow(row2.build());
                   
                   
                   RowBuilder row3 = Row.builder();
                   row3.add(TextCell.builder().text(String.valueOf("Taxe")).borderWidth(1).backgroundColor(Color.WHITE).build())
                   .add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text(String.valueOf(cf.format(transaction.getMontantTaxes()))).borderWidth(1).borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build());
   
                   myTable.addRow(row3.build());
                   
                   RowBuilder row4 = Row.builder();
                   row4.add(TextCell.builder().text(String.valueOf("Montant Total")).borderWidth(1).backgroundColor(Color.WHITE).build())
                   .add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).horizontalAlignment(HorizontalAlignment.CENTER).build())
                   .add(TextCell.builder().text(String.valueOf(cf.format(transaction.getMontantTotal()))).borderWidth(1).borderWidth(1).backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER).build());
   
                   myTable.addRow(row4.build());
                   //Sous-Total, Taxes et Total
                   //Mettre pour rejoindre Credigit

              
                   // Set up the drawer
                   TableDrawer tableDrawer = TableDrawer.builder()
                           .contentStream(contentStream)
                           .startX(25f)
                           .startY(570f)
                           .table(myTable.build())
                           .build();
               

                   // And go for it!
                   tableDrawer.draw();
               }
            
           // doc.save(absoluteFilePath);
            doc.save("/Users/etiennecloutier/Desktop/Test/test2.pdf");
            retour = doc;
            
            System.out.println("Document enregistré");

            //envoyerCourriel(absoluteFilePath, prenomClient, courrielClient, transaction.getNomEtablissement(), transaction.getHeure());

//            try{
//        		
//        		File file = new File(absoluteFilePath);
//            	
//        		if(file.delete()){
//        			System.out.println("Document supprimé");
//        		}else{
//        			System.out.println("Document non supprimé");
//        		}
//        	   
//        	}catch(Exception e){
//        		
//        		e.printStackTrace();
//        		
//        	}
        }
        
        return retour;
    }
	
	
	

	    public static void main(String[] args) throws IOException, ExceptionProduitEtablissement {
	    	
	    	/**
	    	 * String représentant le nom de la base de données sur le serveur
	    	 */
	    	final String DB = "credigit_etablissements";

	    	/**
	    	 * String représentant le nom de la collection contenant les établissements dans
	    	 * la base de données
	    	 */
	    	final String ETABLISSEMENTS = "etablissements";

	    	/**
	    	 * Objet base de données
	    	 */
	    	MongoDatabase database;

	    	/**
	    	 * Client ayant accès à la base de données
	    	 */
	    	MongoClient mongoClient;

	    	Etablissement etablissement;

	    		// Connection à la base de donnée
	    		ConnectionString connectionString = new ConnectionString(
	    				"mongodb+srv://pos:yZYjTYVicPxBdgx6@projetprog-oi2e4.gcp.mongodb.net/test?retryWrites=true&w=majority");
	    		CodecRegistry pojoCodecRegistry = CodecRegistries
	    				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
	    		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
	    				pojoCodecRegistry);
	    		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
	    				.codecRegistry(codecRegistry).build();

	    		mongoClient = MongoClients.create(clientSettings);

	    		database = mongoClient.getDatabase(DB);



	    		MongoCollection<Etablissement> collection = database.getCollection(ETABLISSEMENTS, Etablissement.class);
	    		BasicDBObject object = new BasicDBObject();
	    		object.put("nom", "Credigit");

	    		etablissement = collection.find(object).first();


	    	
	    	Produit p1, p2, p3;

	    	Transaction tr1 = new Transaction(etablissement);

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
	    		
	    		envoyerFacture("Étienne", "Cloutier", "eticlo24@gmail.com", tr1);
	    	
	    }

	    	
	}

