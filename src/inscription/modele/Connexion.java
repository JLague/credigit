package inscription.modele;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connexion {

	private final static String DB = "credigit_data";

	private final static String COMPTES_COLLECTION = "comptes";

	private MongoDatabase database;

	private MongoClient mongoClient;

	private CourrielConfirmation courriel;

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

	public void ajouterCompte(Client client) {
		MongoCollection<Client> collection = database.getCollection(COMPTES_COLLECTION, Client.class);
		collection.insertOne(client);
		courriel.envoyerCourriel(client.getEmail(), client.getPrenom());

	}

}
