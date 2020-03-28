package inscription.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.Binary;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import commun.exception.ExceptionCreationCompte;

/**
 * Classe permettant d'effectuer la connection avec la base de données
 * 
 * @author Bank-era Corp.
 *
 */
public class ConnexionInscription {

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
	 * String représentant le nom de la collection contenant les empreintes dans la
	 * base de données
	 */
	private final static String EMPREINTES = "empreintes";

	/**
	 * Objet base de données
	 */
	private MongoDatabase database;

	/**
	 * Client ayant accès à la base de données
	 */
	private MongoClient mongoClient;

	/**
	 * Collection contenant les comptes client
	 */
	private MongoCollection<Client> clientsCollection;

	/**
	 * Collection contenant les empreintes
	 */
	private MongoCollection<Document> empreintesCollection;

	/**
	 * Connexion au serveur d'envoi de courriels
	 */
	private CourrielConfirmation courriel;

	/**
	 * Constructeur par défaut utilisé lorsqu'on a pas besoin d'envoyer un courriel
	 * de confirmation. De cette manière, l'attribut courriel reste à null.
	 */
	public ConnexionInscription(CourrielConfirmation courriel) {
		// Utilise la connexion existente
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

		// Instantiation des collections
		clientsCollection = database.getCollection(COMPTES_CLIENTS, Client.class);
		empreintesCollection = database.getCollection(EMPREINTES);
	}

	/**
	 * Méthode permettant d'ajouter un client dans la base de données
	 * 
	 * @param client - Le client à ajouter
	 * @return Vrai si le client est ajouté avec succès, faux sinon
	 * @throws ExceptionCreationCompte
	 */
	public boolean ajouterCompteClient(Client client) throws ExceptionCreationCompte {
		try {
			// On insère le compte
			clientsCollection.insertOne(client);

			// On insère l'empreinte
			Document doc = new Document("empreinte", client.getEmpreinte());
			empreintesCollection.insertOne(doc);

			// On envoie un courriel
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
			Client result = clientsCollection.findOneAndDelete(object);
			System.out.println("Client supprimé: " + result.toString());
		} catch (NullPointerException e) {
			return false;
		}

		return true;
	}
	
	/**
	 * Méthode qui va chercher toutes les empreintes dans la base de donnée et les
	 * retourne dans une liste
	 * 
	 * @return les empreintes
	 */
	public List<byte[]> getEmpreintes() {
		List<byte[]> listeEmpreintes = new ArrayList<>();
		MongoCollection<Document> collection = database.getCollection(EMPREINTES);
		Iterator<Document> it = collection.find().cursor();

		while (it.hasNext()) {
			Binary binaryData = (Binary) it.next().get("empreinte");
			listeEmpreintes.add(binaryData.getData());
		}

		return listeEmpreintes;
	}
}
