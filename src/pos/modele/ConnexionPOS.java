package pos.modele;

import java.util.ArrayList;
import java.util.List;

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

import commun.exception.ExceptionCreationCompte;
import commun.exception.ExceptionProduitEtablissement;
import inscription.modele.Client;
import commun.*;

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
	private final static String DB = "credigit_etablissements";

	/**
	 * String représentant le nom de la collection contenant les établissements dans
	 * la base de données
	 */
	private final static String ETABLISSEMENTS = "etablissements";

	/**
	 * Objet base de données
	 */
	private MongoDatabase database;

	/**
	 * Client ayant accès à la base de données
	 */
	private MongoClient mongoClient;

	private Etablissement etablissement;

	/**
	 * Constructeur permettant de se connecter à la base de données et qui peuple
	 * l'objet database et mongoClient
	 */
	public ConnexionPOS() {
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

	public void setEtablissementFromDatabase(String nom) throws ExceptionProduitEtablissement {
		MongoCollection<Etablissement> collection = database.getCollection(ETABLISSEMENTS, Etablissement.class);
		BasicDBObject object = new BasicDBObject();
		object.put("nom", nom);

		etablissement = collection.find(object).first();

		if (etablissement == null)
			throw new ExceptionProduitEtablissement("L'établissement n'a pas été trouvé");
	}

	public long getNumeroEtablissement(String nom) throws ExceptionProduitEtablissement {
		setEtablissementFromDatabase(nom);
		return etablissement.getNumero();
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public boolean updateEtablissement() {
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom", etablissement.getNom());
			MongoCollection<Etablissement> collection = database.getCollection(ETABLISSEMENTS, Etablissement.class);
			collection.replaceOne(searchQuery, etablissement);

		} catch (NullPointerException e) {
			return false;
		}

		return true;
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
				etablissement.ajouterVendeur(vendeur);
				updateEtablissement();
				compteCree = true;
			} catch (ExceptionProduitEtablissement e) {
				e.printStackTrace();
			}
		} else
			throw new ExceptionCreationCompte("Le nom d'utilisateur choisi est déjà utilisé.");

		return compteCree;
	}

	/**
	 * Permet d'aller chercher les informations du vendeur si les informations
	 * passées en paramètre sont valides
	 * 
	 * @param username         le nom d'utilisateur
	 * @param password         le mot de passe
	 * @param nomEtablissement
	 * @return les informations du vendeur
	 * @throws ExceptionProduitEtablissement
	 */
	public Vendeur connecter(String username, String password, String nomEtablissement)
			throws ExceptionProduitEtablissement {

		setEtablissementFromDatabase(nomEtablissement);

		Vendeur vendeur = null;

		if (validerPassword(password) && validerUsername(username)) {
			password = pos.utils.SHAUtil.hashPassword(password);

			for (Vendeur utilisateur : etablissement.getUtilisateurs()) {
				if (utilisateur.getPassword().equals(password) && utilisateur.getUsername().equals(username)) {
					vendeur = utilisateur;
				}
			}
		}

		if (vendeur == null)
			throw new ExceptionProduitEtablissement("Le nom d'utilisateur ou le mot de passe est invalide.");

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

		etablissement.ajouterProduitInventaire(produit);
		updateEtablissement();

		return true;
	}

	/**
	 * Permet d'aller chercher les produits contenus dans la base de données
	 * 
	 * @return une liste contenant tous les produits dans la base de données
	 */
	public List<Produit> getProduits() {
		return etablissement.getInventaire();
	}

	/**
	 * Permet de vérifier si le nom d'utilisateur choisi n'est pas déjà utilisé
	 * 
	 * @param nomUtilisateur le nom d'utilisateur à valider
	 * @return true si le nom d'utilisateur est utilisé
	 */
	private boolean isUsernameUsed(String nomUtilisateur) {
		boolean estUtilise = false;

		if (etablissement.getUtilisateurs() != null) {
			for (Vendeur utilisateur : etablissement.getUtilisateurs()) {
				if (utilisateur.getUsername().equals(nomUtilisateur)) {
					estUtilise = true;
				}
			}
		} else {
			estUtilise = true;
		}

		return estUtilise;
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
