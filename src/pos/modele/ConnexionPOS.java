package pos.modele;

import java.math.BigInteger;
import java.util.ArrayList;
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

import commun.exception.ExceptionCreationCompte;
import commun.exception.ExceptionProduitEtablissement;
import encryption.CleRSA;
import encryption.RSA;
import commun.*;
import commun.codecs.DateCodec;
import commun.codecs.EnumCodec;
import commun.codecs.FloatCodec;
import commun.codecs.IntegerCodec;
import commun.codecs.LongCodec;
import commun.codecs.StringCodec;

/**
 * Classe permettant d'effectuer la connection avec la base de données
 * 
 * @author Bank-era Corp.
 *
 */
public class ConnexionPOS {

	/**
	 * Clé du RSA
	 */
	public static final CleRSA CLE_RSA = new CleRSA(new BigInteger("32244774284211042705171103939999050641"),
			new BigInteger("65537"), new BigInteger("166671328359045595559284971252973341809"));

	/**
	 * String représentant le nom de la base de données sur le serveur
	 */
	private final static String DB = "credigit_etablissements";

	/**
	 * String représentant le nom de la base de données des images sur le serveur
	 */
	private final static String DB_IMAGES = "images_database";

	/**
	 * String représentant le nom de la collection contenant les établissements dans
	 * la base de données
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
	 * Objet base de données
	 */
	private MongoDatabase database;

	/**
	 * Client ayant accès à la base de données
	 */
	private MongoClient mongoClient;

	private Etablissement etablissement;

	private List<CryptableCodec<?>> customCodecs;

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

		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(this.createCustomCodecRegistry(),
				MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
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
	 * @throws ExceptionProduitEtablissement
	 */
	public void setEtablissementFromDatabase(String nom) throws ExceptionProduitEtablissement {
		MongoCollection<Etablissement> collection = database.getCollection(ETABLISSEMENTS, Etablissement.class);
		BasicDBObject object = new BasicDBObject();
		object.put("nom", nom);

		etablissement = collection.find(object).first();

		if (etablissement == null) {
			throw new ExceptionProduitEtablissement("L'établissement n'a pas été trouvé");
		}

		loadImages();
	}

	/**
	 * Permet d'avoir le numéro d'établissement
	 * 
	 * @param nom - L'établissement
	 * @return
	 * @throws ExceptionProduitEtablissement
	 */
	public long getNumeroEtablissement(String nom) throws ExceptionProduitEtablissement {
		setEtablissementFromDatabase(nom);
		return etablissement.getNumero();
	}

	/**
	 * Permet d'obtenir l'établissement courant
	 * 
	 * @return l'établissement courant
	 */
	public Etablissement getEtablissement() {
		return etablissement;
	}

	/**
	 * Mets à jour l'établissement dans la base de données
	 * 
	 * @return vrai si l'établissement est bel et bien mis à jour
	 */
	public boolean updateEtablissement() {
		// TODO Encrypter
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom", etablissement.getNom());
			MongoCollection<Etablissement> collection = database.getCollection(ETABLISSEMENTS, Etablissement.class);
			Etablissement clone = (Etablissement) etablissement.clone();
			for (Produit produit : clone.getInventaire()) {
				produit.setImage(null);
			}
			collection.replaceOne(searchQuery, clone);

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
		produit.setImagePathDB(uploadImageToDatabase(produit));
		etablissement.ajouterProduitInventaire(produit);
		updateEtablissement();

		return true;
	}

	/**
	 * Ajoute l'image du produit dans la base d'images
	 * 
	 * @param produit - Le produit ajouté
	 * @return la path de l'image dans la base de donnée
	 */
	public String uploadImageToDatabase(Produit produit) {
		System.out.println("uploading image");
		MongoDatabase imageDb = mongoClient.getDatabase(DB_IMAGES);
		MongoCollection<Document> collectionImages = imageDb.getCollection("produits");

		String path = etablissement.getNom() + "_" + produit.getSku();
		Document image = new Document();
		image.put("path", path);
		image.put("image", produit.getImage());

		collectionImages.insertOne(image);

		System.out.println("Image ajoutée");

		return path;

	}

	/**
	 * Méthode permettant de supprimer une image dans la base de données
	 * 
	 * @param produit - Le produit dont l'image est à supprimer
	 */
	public void removeImageFromDatabase(Produit produit) {
		MongoDatabase imageDb = mongoClient.getDatabase(DB_IMAGES);
		MongoCollection<Document> collectionImages = imageDb.getCollection("produits");
		Document object = new Document();
		object.put("path", produit.getImagePathDB());

		System.out.println(collectionImages.deleteOne(object));
	}

	/**
	 * Load les arrays de bytes de chaque image
	 */
	public void loadImages() {
		MongoDatabase imageDb = mongoClient.getDatabase(DB_IMAGES);
		MongoCollection<Document> collectionImages = imageDb.getCollection("produits");

		if (etablissement.getInventaire() != null && etablissement.getInventaire().size() >= 1) {
			System.out.println("here");
			for (Produit produit : etablissement.getInventaire()) {
				BasicDBObject object = new BasicDBObject();
				object.put("path", produit.getImagePathDB());

				try {
					Binary binary = (Binary) collectionImages.find(object).first().get("image");
					produit.setImage(binary.getData());
				} catch (NullPointerException e) {

				}

			}

			System.out.println("Images chargées");
		} else {
			System.out.println("Inventaire vide");
		}

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
	 * Instancie et retourne un CodecRegistry contenant tous les codecs customs
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
