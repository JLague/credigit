package statistiques.modele;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import commun.CryptableCodec;
import commun.Etablissement;
import commun.Vendeur;
import commun.codecs.DateCodec;
import commun.codecs.EnumCodec;
import commun.codecs.FloatCodec;
import commun.codecs.IntegerCodec;
import commun.codecs.LongCodec;
import commun.codecs.StringCodec;
import commun.exception.ExceptionProduitEtablissement;
import encryption.CleRSA;
import encryption.RSA;

public class Connexion {

	/**
	 * Clé du RSA
	 */
	public static final CleRSA CLE_RSA = new CleRSA(new BigInteger("32244774284211042705171103939999050641"),
			new BigInteger("65537"), new BigInteger("166671328359045595559284971252973341809"));
	
	/**
	 * String représentant le nom de la collection contenant les établissements dans
	 * la base de données
	 */
	private final static String ETABLISSEMENTS = "etablissements";
	
	/**
	 * String représentant le nom de la base de données sur le serveur
	 */
	private final static String DB = "credigit_etablissements";

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
	 * Méthode permettant d'aller rechercher l'établissement dans la base de données
	 * 
	 * @param nom - Le nom de l'établissement
	 * @return L'établissement trouvé
	 * @throws ExceptionProduitEtablissement
	 */
	private Etablissement getEtablissementFromDatabase(String nom) {
		MongoCollection<Etablissement> collection = database.getCollection(ETABLISSEMENTS, Etablissement.class);
		BasicDBObject object = new BasicDBObject();
		object.put("nom", nom);
		
		return collection.find(object).first();
	}

	/**
	 * Permet d'aller chercher les informations du vendeur si les informations
	 * passées en paramètre sont valides
	 * 
	 * @param username         le nom d'utilisateur
	 * @param password         le mot de passe
	 * @param nomEtablissement
	 * @return l'établissement si la connection est réussi
	 * @throws ExceptionProduitEtablissement
	 */
	public Etablissement connecter(String username, String password, String nomEtablissement) {
		Vendeur vendeur = null;
		Etablissement etablissement = getEtablissementFromDatabase(nomEtablissement);

		password = pos.utils.SHAUtil.hashPassword(password);

		for (Vendeur utilisateur : etablissement.getUtilisateurs()) {
			if (utilisateur.getPassword().equals(password) && utilisateur.getUsername().equals(username)) {
				vendeur = utilisateur;
			}
		}

		return vendeur != null ? etablissement : null;
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

		for (CryptableCodec<?> codec : this.customCodecs) {
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
