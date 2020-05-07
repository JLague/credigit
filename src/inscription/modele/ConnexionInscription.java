package inscription.modele;

import java.math.BigInteger;
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

import commun.CryptableCodec;
import commun.codecs.DateCodec;
import commun.codecs.EnumCodec;
import commun.codecs.FloatCodec;
import commun.codecs.IntegerCodec;
import commun.codecs.LongCodec;
import commun.codecs.StringCodec;
import commun.exception.ExceptionCreationCompte;
import encryption.CleRSA;
import encryption.RSA;

/**
 * Classe permettant d'effectuer la connection avec la base de données
 * 
 * @author Bank-era Corp.
 *
 */
public class ConnexionInscription {

	/**
	 * Clé du RSA
	 */
	public static final CleRSA CLE_RSA = new CleRSA(new BigInteger("32244774284211042705171103939999050641"),
			new BigInteger("65537"), new BigInteger("166671328359045595559284971252973341809"));

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
	 * Liste contenant les codecs custom
	 */
	private List<CryptableCodec<?>> customCodecs;

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
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(this.createCustomCodecRegistry(), MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		mongoClient = MongoClients.create(clientSettings);

		database = mongoClient.getDatabase(DB);

		this.loadCleFromDB();
		
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
	 * @param empreinte l'empreinte à supprimer
	 * @return true si la suppression est effectuée
	 */
	public boolean supprimerCompteClient(byte[] empreinte) {

		BasicDBObject object = new BasicDBObject("empreinte", empreinte);
		Document result = database.getCollection(COMPTES_CLIENTS).findOneAndDelete(object);
		byte[] empreinteSupprime = ((Binary) (empreintesCollection.findOneAndDelete(object).get("empreinte")))
				.getData();

		if (result == null || empreinteSupprime == null) {
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
