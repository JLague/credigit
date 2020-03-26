package terminal.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.Binary;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * Classe permettant au Terminal de se connecter avec la base de données
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
	 * String représentant le nom de la collection contenant les établissements dans
	 * la base de données
	 */
	private final static String COMPTES_CLIENT = "comptes";

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
	 * Méthode qui va chercher toutes les empreintes dans la base de donnée et les
	 * retourne dans une liste
	 * 
	 * @return les empreintes
	 */
	public List<byte[]> getAllEmpreintes() {
		List<byte[]> listeEmpreintes = new ArrayList<>();
		MongoCollection<Document> collection = database.getCollection(COMPTES_CLIENT);
		Iterator<Document> it = collection.find().cursor();

		while (it.hasNext()) {
			Binary binaryData = (Binary) it.next().get("empreinte");
			listeEmpreintes.add(binaryData.getData());
		}

		return listeEmpreintes;
	}
}