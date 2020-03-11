package connexion;

import java.util.Iterator;

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

import inscription.modele.Client;
import pos.modele.DataVendeur;
import pos.modele.Vendeur;

/**
 * Classe permettant d'effectuer la connection avec la base de données
 * 
 * @author Bank-era Corp.
 *
 */
public class Connexion {

	/**
	 * String représentant le nom de la base de données sur le serveur
	 */
	private final static String DB = "credigit_data";

	/**
	 * String représentant le nom de la collection contenant les comptes dans la
	 * base de données
	 */
	private final static String COMPTES_CLIENTS = "comptes";

	/**
	 * String représentant le nom de la collection contenant les comptes des
	 * vendeurs dans la base de données
	 */
	private final static String COMPTES_VENDEURS = "comptes_vendeurs";

	/**
	 * Objet base de données
	 */
	private MongoDatabase database;

	/**
	 * Client ayant accès à la base de données
	 */
	private MongoClient mongoClient;

	/**
	 * Connexion au serveur d'envoi de courriels
	 */
	private CourrielConfirmation courriel;

	/**
	 * Constructeur par défaut utilisé lorsqu'on a pas besoin d'envoyer un courriel
	 * de confirmation. De cette manière, l'attribut courriel reste à null.
	 */
	public Connexion() {
		// Connection à la base de donnée
		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://inscription:4NhaE8c8SxH0LgWE@projetprog-oi2e4.gcp.mongodb.net/test?retryWrites=true&w=majority");
		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		mongoClient = MongoClients.create(clientSettings);

		database = mongoClient.getDatabase(DB);
	}

	/**
	 * Constructeur de la connexion
	 * 
	 * @param courriel - La connexion au serveru d'envoi de courriels
	 */
	public Connexion(CourrielConfirmation courriel) {
		this();

		// Va chercher la connexion déjà établie
		this.courriel = courriel;

	}

	/**
	 * Méthode permettant d'ajouter un client dans la base de données
	 * 
	 * @param client - Le client à ajouter
	 * @return Vrai si le client est ajouté avec succès, faux sinon
	 */
	public boolean ajouterCompteClient(Client client) {

		try {
			MongoCollection<Client> collection = database.getCollection(COMPTES_CLIENTS, Client.class);
			collection.insertOne(client);
			courriel.envoyerCourriel(client.getEmail(), client.getPrenom());
		} catch (MongoWriteException e) {
			return false;
		}

		return true;

	}

	/**
	 * Méthode permettant de supprimer un compte selon les informations reçues
	 * 
	 * @param nom    - Le nom du client
	 * @param prenom - Le prénom du client
	 * @param email  - L'email du client
	 * @param nas    - Le NAS du client
	 * @return Vrai si la suppression est effectuée, faux sinon
	 */
	public boolean supprimerCompteClient(String nom, String prenom, String email, String nas) {
		try {
			BasicDBObject object = new BasicDBObject();
			object.put("nom", nom);
			object.put("prenom", prenom);
			object.put("email", email);
			object.put("nas", nas);
			Document result = database.getCollection(COMPTES_CLIENTS).findOneAndDelete(object);
			System.out.println("Document supprimé: " + result.toString());
		} catch (NullPointerException e) {
			return false;
		}

		return true;
	}

	/**
	 * Ajoute un vendeur à la base de donnée
	 * 
	 * @param vendeur le vendeur à ajouter
	 * @return true si le compte a été créé
	 */
	public boolean ajouterCompteVendeur(Vendeur vendeur) {
		try {
			MongoCollection<Vendeur> collection = database.getCollection(COMPTES_VENDEURS, Vendeur.class);
			collection.insertOne(vendeur);
		} catch (MongoWriteException e) {
			return false;
		}

		return true;
	}

	/**
	 * Permet de vérifier si le nom d'utilisateur choisi n'est pas déjà utilisé
	 * 
	 * @param nomUtilisateur le nom d'utilisateur à valider
	 * @return true si le nom d'utilisateur n'est pas déjà utilisé
	 */
	public boolean validerNomUtilisateur(String nomUtilisateur) {
		BasicDBObject object = new BasicDBObject();
		object.put("username", nomUtilisateur);
		FindIterable<Document> result = database.getCollection(COMPTES_VENDEURS).find(object);
		Iterator<Document> it = result.iterator();
		return !it.hasNext();
	}

	/**
	 * Permet d'aller chercher les informations du vendeur si les informations
	 * passées en paramètre sont valides
	 * 
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 * @return les informations du vendeur
	 */
	public DataVendeur connecter(String username, String password) {
		BasicDBObject object = new BasicDBObject();
		object.put("username", username);
		object.put("password", password);

		FindIterable<Document> result = database.getCollection(COMPTES_VENDEURS).find(object);
		Iterator<Document> it = result.iterator();

		if (it.hasNext()) {
			Document doc = it.next();
			DataVendeur data = new DataVendeur();
			data.setPrenom(doc.getString("prenom"));
			data.setNom(doc.getString("nom"));
			data.setPassword(doc.getString("password"));
			data.setUsername(doc.getString("username"));
			data.setCourriel(doc.getString("courriel"));

			return data;
		}

		return null;
	}

}
