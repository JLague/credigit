package creationEtablissement.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import commun.exception.ExceptionCreationCompte;
import encryption.RSA;
import commun.CryptableCodec;
import commun.Etablissement;
import commun.codecs.DateCodec;
import commun.codecs.EnumCodec;
import commun.codecs.FloatCodec;
import commun.codecs.IntegerCodec;
import commun.codecs.LongCodec;
import commun.codecs.StringCodec;

public class CreationEtablissementModele implements ICreationEtablissementModele {

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
	 * String représentant le nom de la base de données contenant les clés sur le
	 * serveur
	 */
	private final static String DB_KEYS = "keys_database";

	/**
	 * String représentant le nom de la collection contenant les clés dans la base
	 * de données
	 */
	private final static String KEYS = "keys";

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
	 * Liste contenant les codecs custom
	 */
	private List<CryptableCodec<?>> customCodecs;

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
							.fromRegistries(createCustomCodecRegistry(), MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
					MongoClientSettings clientSettings = MongoClientSettings.builder()
							.applyConnectionString(connectionString).codecRegistry(codecRegistry).build();

					mongoClient = MongoClients.create(clientSettings);
					loadCleFromDB();

					database = mongoClient.getDatabase(DB);
				});

				return null;
			}

		};

		new Thread(connexion).start();
	}

	@Override
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

	@Override
	public boolean ajouterEtablissement(Etablissement etablissement) throws ExceptionCreationCompte {

		//TODO Encrypter
		boolean compteCree = false;

		// Vérifie si le nom d'établissement n'est pas déjà utilisé
		if (!isNameUsed(etablissement.getNom())) {
			try {
				// Ajoute l'établissement à la bonne collection
				MongoCollection<Etablissement> collection = database.getCollection(ETABLISSEMENTS, Etablissement.class);
				collection.insertOne(etablissement);
				compteCree = true;
			} catch (MongoWriteException e) {
			}
		} else
			throw new ExceptionCreationCompte("Le nom d'établissement est déjà utilisé.");

		return compteCree;

	}

	/**
	 * Permet de vérifier si le nom d'établissement choisi n'est pas déjà utilisé
	 * 
	 * @param nomEtablissement le nom d'utilisateur à valider
	 * @return true si le nom d'utilisateur est utilisé
	 */
	private boolean isNameUsed(String nomEtablissement) {
		BasicDBObject object = new BasicDBObject();
		object.put("nom", nomEtablissement);
		FindIterable<Document> result = database.getCollection(ETABLISSEMENTS).find(object);
		Iterator<Document> it = result.iterator();
		return it.hasNext();
	}

	/**
	 * Méthode permettant d'aller chercher la clé d'encryption dans la base de
	 * données
	 * 
	 * @return la clé encryptée selon RSA
	 */
	private void loadCleFromDB() {
		// Cherche la clé dans la DB
		MongoDatabase data = mongoClient.getDatabase(DB_KEYS);
		MongoCollection<Document> collection = data.getCollection(KEYS);
		Document doc = collection.find().first();
		String cle = doc.getString("key");

		// Décrypte la clé
		cle = RSA.integerToString(RSA.decrypter(RSA.stringToInteger(cle), CLE_RSA.getClePrivee(), CLE_RSA.getModule()));
		
		for(CryptableCodec<?> codec : this.customCodecs) {
			codec.setCle(cle);
		}
	}
	
	/**
	 * Instantie et retourne un CodecRegistry contenant tous les codecs customs
	 * 
	 * @return le CodecRegistry contenant tous les codecs customs
	 */
	private CodecRegistry createCustomCodecRegistry() {
		customCodecs = new ArrayList<>();
		
		// Instantiate custom codecs
		customCodecs.add(new IntegerCodec());
		customCodecs.add(new FloatCodec());
		customCodecs.add(new LongCodec());
		customCodecs.add(new StringCodec());
		customCodecs.add(new DateCodec());
		customCodecs.add(new EnumCodec());
		
		return CodecRegistries.fromCodecs(customCodecs);
	}
}
