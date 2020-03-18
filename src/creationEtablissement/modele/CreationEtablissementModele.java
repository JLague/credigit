package creationEtablissement.modele;

import java.util.Iterator;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.application.Platform;
import javafx.concurrent.Task;
import pos.exception.ExceptionCreationCompte;
import pos.modele.DataVendeur;
import pos.modele.Etablissement;
import pos.modele.Vendeur;

public class CreationEtablissementModele {

	/**
	 * String représentant le nom de la base de données sur le serveur
	 */
	private final static String DB = "credigit_etablissements";

	/**
	 * String représentant le nom de la collection contenant les comptes dans la
	 * base de données
	 */
	private final static String ETABLISSEMENTS = "etablissements";

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
	private Session session;

	/**
	 * Objet base de données
	 */
	private MongoDatabase database;

	/**
	 * Client ayant accès à la base de données
	 */
	private MongoClient mongoClient;

	/**
	 * Constructeur de la connection du serveur d'envoi de courriels
	 */
	public CreationEtablissementModele() {
		Task<Void> connexion = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Platform.runLater(() -> {
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

					ConnectionString connectionString = new ConnectionString(
							"mongodb+srv://pos:yZYjTYVicPxBdgx6@projetprog-oi2e4.gcp.mongodb.net/test?retryWrites=true&w=majority");
					CodecRegistry pojoCodecRegistry = CodecRegistries
							.fromProviders(PojoCodecProvider.builder().automatic(true).build());
					CodecRegistry codecRegistry = CodecRegistries
							.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
					MongoClientSettings clientSettings = MongoClientSettings.builder()
							.applyConnectionString(connectionString).codecRegistry(codecRegistry).build();

					mongoClient = MongoClients.create(clientSettings);

					database = mongoClient.getDatabase(DB);
				});

				return null;
			}

		};

		new Thread(connexion).start();
	}

	/**
	 * Méthode permettant d'envoyer un courriel de bienvenue
	 * 
	 * @param adresse - L'adresse du correspondant
	 * @param Prenom  - Le prénom du correspondant
	 * @return Vrai si le courriel est envoyé avec succès, faux sinon
	 */
	public boolean envoyerCourriel(Etablissement etablissement) {

		boolean envoye = true;

		String to = etablissement.getCourriel();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USER));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Bienvenue dans la famille!");

			// 3) Crée le corps du message
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Bonjour " + etablissement.getNom()
					+ ",\n\nLa grande famille de Credigit est heureuse de vous"
					+ " compter comme un nouveau membre! Avec Credigit, vous pouvez d'un service de point de vente réinventé.\n\n"
					+ "Si vous rencontrez des difficultés, n'hésitez pas à contacter notre service à la clientèle au 418-XXX-XXXX.\n\n"
					+ "Voici votre numéro d'établissement qui vous permettra de créer des comptes pour vos vendeurs: "
					+ etablissement.getNumero() + "\n\nContinuez à avoir l'argent au bout de votre doigt,\n\n"
					+ "L'équipe de Crédigit\n" + "Une filiale de Bank-era Corp");

			// 4) Set le contenu du message
			message.setContent(new MimeMultipart(messageBodyPart1));

			// 5) Envoir le message
			Transport.send(message);

		} catch (MessagingException ex) {
			envoye = false;
		}

		return envoye;
	}

	/**
	 * Ajoute un établissement à la base de données
	 * 
	 * @param etablissement - L'établissement à ajouter
	 * @throws ExceptionCreationCompte
	 */
	public boolean ajouterCompteVendeur(Etablissement etablissement) throws ExceptionCreationCompte {

		boolean compteCree = false;

		// Vérifie si le nom d'utilisateur n'est pas déjà utilisé
		if (!isUsernameUsed(etablissement.getNom())) {
			try {
				// Ajoute le vendeur à la bonne collection
				MongoCollection<Etablissement> collection = database.getCollection(ETABLISSEMENTS, Etablissement.class);
				collection.insertOne(etablissement);
				compteCree = true;
			} catch (MongoWriteException e) {
			}
		} else
			throw new ExceptionCreationCompte("Le nom d'utilisateur choisi est déjà utilisé.");

		return compteCree;

	}

	/**
	 * Permet de vérifier si le nom d'utilisateur choisi n'est pas déjà utilisé
	 * 
	 * @param nomUtilisateur le nom d'utilisateur à valider
	 * @return true si le nom d'utilisateur est utilisé
	 */
	private boolean isUsernameUsed(String nomUtilisateur) {
		BasicDBObject object = new BasicDBObject();
		object.put("nom", nomUtilisateur);
		FindIterable<Document> result = database.getCollection("credigit_etablissement").find(object);
		Iterator<Document> it = result.iterator();
		return it.hasNext();
	}
}
