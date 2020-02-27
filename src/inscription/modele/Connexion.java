package inscription.modele;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
	private final static String COMPTES_COLLECTION = "comptes";

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
	 * Constructeur de la connexion
	 * 
	 * @param courriel - La connexion au serveru d'envoi de courriels
	 */
	public Connexion(CourrielConfirmation courriel) {
		// Va chercher la connexion déjà établie
		this.courriel = courriel;

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
	 * Méthode permettant d'ajouter un client dans la base de données
	 * 
	 * @param client - Le client à ajouter
	 * @return Vrai si le client est ajouté avec succès, faux sinon
	 */
	public boolean ajouterCompte(Client client) {

		try {
			MongoCollection<Client> collection = database.getCollection(COMPTES_COLLECTION, Client.class);
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
	public boolean supprimerCompte(String nom, String prenom, String email, String nas) {
		try {
			BasicDBObject object = new BasicDBObject();
			object.put("nom", nom);
			object.put("prenom", prenom);
			object.put("email", email);
			object.put("nas", nas);
			Document result = database.getCollection(COMPTES_COLLECTION).findOneAndDelete(object);
			System.out.println("Document supprimé: " + result.toString());
		} catch (NullPointerException e) {
			return false;
		}

		return true;
	}

}
