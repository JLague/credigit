package pos.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import exception.ExceptionCreationCompte;
import exception.ExceptionProduitEtablissement;

/**
 * Classe permettant d'effectuer la connection avec la base de données
 * 
 * @author Bank-era Corp.
 *
 */
public class ConnexionPOS {

	/**
	 * String représentant le nom de la base de données sur le serveur
	 */
	private final static String DB = "credigit_pos";

	/**
	 * String représentant le nom de la collection contenant les comptes dans la
	 * base de données
	 */
	private final static String PRODUITS = "produits";

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
	 * Constructeur permettant de se connecter à la base de données et qui peuple
	 * l'objet database et mongoClient
	 */
	public ConnexionPOS() {
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
	 * Ajoute un vendeur à la base de données
	 * 
	 * @param vendeur le vendeur à ajouter
	 * @return true si le vendeur a été ajouté
	 * @throws ExceptionCreationCompte
	 */
	public boolean ajouterCompteVendeur(DataVendeur data) throws ExceptionCreationCompte {
		Vendeur vendeur = new Vendeur(data);
		boolean compteCree = false;

		// Vérifie si le nom d'utilisateur n'est pas déjà utilisé
		if (!isUsernameUsed(vendeur.getUsername())) {
			try {
				// Ajoute le vendeur à la bonne collection
				MongoCollection<Vendeur> collection = database.getCollection(COMPTES_VENDEURS, Vendeur.class);
				collection.insertOne(vendeur);
				compteCree = true;
			} catch (MongoWriteException e) {
			}
		} else
			throw new ExceptionCreationCompte("Le nom d'utilisateur choisi est déjà utilisé.");

		return compteCree;
	}

	/**
	 * Permet d'aller chercher les informations du vendeur si les informations
	 * passées en paramètre sont valides
	 * 
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 * @return les informations du vendeur
	 */
	public Vendeur connecter(String username, String password) {
		Vendeur vendeur = null;

		if (validerPassword(password) && validerUsername(username)) {
			password = encryption.SHAUtil.hashPassword(password);

			// On crée le query
			BasicDBObject object = new BasicDBObject();
			object.put("username", username);
			object.put("password", password);

			// On cherche dans la base de données
			FindIterable<Vendeur> result = database.getCollection(COMPTES_VENDEURS, Vendeur.class).find(object);
			Iterator<Vendeur> it = result.iterator();

			if (it.hasNext()) {
				vendeur = it.next();
			}
		}

		return vendeur;
	}

	/**
	 * Ajoute un produit à la base de données
	 * 
	 * @param data le produit à ajouter
	 * @return true si le produit a été ajouté avec succès
	 * @throws ExceptionProduitEtablissement
	 */
	public boolean ajouterProduit(DataProduit data) throws ExceptionProduitEtablissement {
		Produit produit = new Produit(data);

		try {
			MongoCollection<Produit> collection = database.getCollection(PRODUITS, Produit.class);
			collection.insertOne(produit);
		} catch (MongoWriteException e) {
			return false;
		}

		return true;
	}

	/**
	 * Permet d'aller chercher les produits contenus dans la base de données
	 * 
	 * @return une liste contenant tous les produits dans la base de données
	 */
	public List<Produit> getProduits() {
		FindIterable<Produit> result = database.getCollection(PRODUITS, Produit.class).find();
		Iterator<Produit> it = result.iterator();
		ArrayList<Produit> produits = new ArrayList<>();

		while (it.hasNext()) {
			produits.add(it.next());
		}

		return produits;
	}

	/**
	 * Permet de vérifier si le nom d'utilisateur choisi n'est pas déjà utilisé
	 * 
	 * @param nomUtilisateur le nom d'utilisateur à valider
	 * @return true si le nom d'utilisateur est utilisé
	 */
	private boolean isUsernameUsed(String nomUtilisateur) {
		BasicDBObject object = new BasicDBObject();
		object.put("username", nomUtilisateur);
		FindIterable<Document> result = database.getCollection(COMPTES_VENDEURS).find(object);
		Iterator<Document> it = result.iterator();
		return it.hasNext();
	}
	
	/**
	 * Vérifie si le nom d'utilisateur est valide
	 * 
	 * @param nomUtilisateur le nom d'utilisateur à vérifier
	 * @return true s'il est valide
	 */
	private boolean validerUsername(String nomUtilisateur) {
		return nomUtilisateur != null && nomUtilisateur.length() > 0;
	}
	
	/**
	 * Valide le mot de passe
	 * 
	 * @param password le mot de passe à valider
	 * @return true s'il est valide
	 */
	private boolean validerPassword(String password) {
		return password != null && password.length() != 0;
	}

}
