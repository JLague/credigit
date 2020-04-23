package pos.modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import commun.Transaction;

/**
 * Classe permettant d'envoyer des objets Transaction au serveur du Terminal
 * 
 * @author Bank-era Corp.
 *
 */
public class ClientPOS {

	/**
	 * L'addresse IP du server
	 */
	private static final String REMOTE_IP = "24.203.30.174";

	/**
	 * Le port du serveur
	 */
	private static final int REMOTE_PORT = 47800;

	/**
	 * Socket du client
	 */
	private Socket socketClient;

	/**
	 * Expédition du POS ves le terminal
	 */
	private ObjectOutputStream oos;

	/**
	 * Réception par le Pos venant du terminal
	 */
	private ObjectInputStream ois;

	/**
	 * Constructeur qui permet de se connecter au serveur et d'ouvrir un output
	 * stream
	 */
	public ClientPOS() {

		// Ferme le socket et l'output stream lorsque l'application ferme
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if (oos != null || socketClient != null) {
						System.out.println("[CLIENT] Déconnexion du serveur!");
						oos.close();
						socketClient.close();
					}
				} catch (IOException e) {
					/* failed */ }
			}
		});

		try {
			// Se connecte et crée l'output stream
			socketClient = new Socket(REMOTE_IP, REMOTE_PORT);
			oos = new ObjectOutputStream(socketClient.getOutputStream());
			ois = new ObjectInputStream(socketClient.getInputStream());
			System.out.println("[CLIENT] Connecté au serveur à " + REMOTE_IP + ":" + REMOTE_PORT);
		} catch (ConnectException ce) {
			System.err.println("[CLIENT] Vous devez ouvrir le serveur avant le client!");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sérialise la transaction et l'envoie
	 * 
	 * @param transaction la transaction à envoyer
	 */
	public void send(Transaction transaction) {
		transaction.serialize(oos);
	}

	public Transaction retourTrans() throws ClassNotFoundException, IOException {
		return (Transaction) ois.readObject();
	}
}
