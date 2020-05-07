package statistiques.modele;

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

import commun.Etablissement;
import commun.Vendeur;
import commun.exception.ExceptionProduitEtablissement;

public class Connexion {

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
	 * Objet base de données
	 */
	private MongoDatabase database;

	/**
	 * Client ayant accès à la base de données
	 */
	private MongoClient mongoClient;

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
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		mongoClient = MongoClients.create(clientSettings);

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
}
