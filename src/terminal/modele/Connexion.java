package terminal.modele;

import java.io.IOException;
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
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import commun.CryptableCodec;
import commun.EtatTransaction;
import commun.Transaction;
import commun.codecs.DateCodec;
import commun.codecs.FloatCodec;
import commun.codecs.IntegerCodec;
import commun.codecs.LongCodec;
import commun.codecs.StringCodec;
import commun.exception.ExceptionTransaction;
import encryption.CleRSA;
import encryption.RSA;
import inscription.modele.Client;
import terminal.utils.FactureUtil;

/**
 * 
 * Classe permettant au Terminal de se connecter avec la base de données
 * 
 * @author Bank-era Corp.
 *
 */
public class Connexion {

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
	 * String représentant le nom de la collection contenant les établissements dans
	 * la base de données
	 */
	private final static String COMPTES_CLIENT = "comptes";

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
	 * Liste contenant les codecs custom
	 */
	private List<CryptableCodec<?>> customCodecs;

	/**
	 * Constructeur permettant de se connecter à la base de données et qui peuple
	 * l'objet database et mongoClient
	 */
	public Connexion() {
		// Connection à la base de donnée
		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://pos:yZYjTYVicPxBdgx6@projetprog-oi2e4.gcp.mongodb.net/test?retryWrites=true&w=majority");
		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(this.createCustomCodecRegistry(), MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		mongoClient = MongoClients.create(clientSettings);
		this.loadCleFromDB();

		database = mongoClient.getDatabase(DB);
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
	 * Méthode permettant d'effectuer la transaction sur la base de données
	 * 
	 * @param empreinte   - L'empreinte scannée
	 * @param transaction - La transaction à ajouter
	 * @return vrai si la transaction est bel et bien réalisée
	 * @throws ExceptionTransaction
	 */
	public boolean effectuerTransaction(byte[] empreinte, Transaction transaction) {
		try {
			// Recherche le client à modifier
			MongoCollection<Client> collection = database.getCollection(COMPTES_CLIENT, Client.class);
			BasicDBObject object = new BasicDBObject();
			object.put("empreinte", empreinte);
			Client clientAModifier = collection.find(object).first();

			// Prépare la transaction pour la stocker dans la base de données
			Transaction transReduite = Transaction.reduireTransactionClient(transaction);

			// Mets à jour le client
			ArrayList<Transaction> transactions = clientAModifier.getTransaction();
			transactions.add(transReduite);
			clientAModifier.setTransaction(transactions);
			clientAModifier.setSolde(clientAModifier.getSolde() + transaction.getMontantTotal());

			// Vérifie si la limite est dépassée et update client dans la base de données
			if (clientAModifier.getSolde() <= clientAModifier.getLimiteCredit()) {
				transaction.setEtat(EtatTransaction.CONFIRMATION);
				transReduite.setEtat(EtatTransaction.CONFIRMATION);
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("empreinte", empreinte);
				// TODO Encrypter
				collection.replaceOne(searchQuery, clientAModifier);

				// Envoie la facture au client
				FactureUtil.envoyerFacture(clientAModifier.getPrenom(), clientAModifier.getNom(),
						clientAModifier.getEmail(), transaction);
			} else {
				return false;
			}

		} catch (NullPointerException e) {
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
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
		
		return CodecRegistries.fromCodecs(customCodecs);
	}

}
