package terminal.modele;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import commun.Transaction;
import terminal.ctrl.TerminalControleur;

/**
 * Classe servant de serveur pour communiquer des objets Transaction avec le POS
 * 
 * @author Bank-era Corp.
 *
 */
public class ServeurTerminal implements Runnable, Closeable {

	/**
	 * Le port où le serveur écoute
	 */
	private static final int LOCAL_PORT = 47800;

	/**
	 * Le contrôleur du terminal
	 */
	private TerminalControleur ctrl;

	/**
	 * L'input stream servant à lire les objets Transaction
	 */
	private ObjectInputStream ois;

	/**
	 * Le socket servant de serveur
	 */
	private ServerSocket socketServer;

	/**
	 * Le socket créant la connexion
	 */
	private Socket socketClient;

	/**
	 * L'IP du client connecté
	 */
	private String clientIp;

	/**
	 * Contrôleur permettant d'ouvrir une seule connexion avec un client
	 * 
	 * @param pCtrl
	 */
	public ServeurTerminal(TerminalControleur pCtrl) {
		ctrl = pCtrl;

		// Ferme les sockets et l'input stream lorsque l'application ferme
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					close();
					System.out.println("[SERVEUR] Le serveur est fermé!");
				} catch (IOException e) {
					/* failed */ }
			}
		});
	}

	/**
	 * Méthode permettant de se connecter à un seul client
	 */
	public void connect() {
		try {
			System.out.println("[SERVEUR] Adresse ip du terminal (à enter dans le POS) : " + InetAddress.getLocalHost().getHostAddress());

			// Initiate connection
			System.out.println("[SERVEUR] Attente de la connexion...");
			socketServer = new ServerSocket(LOCAL_PORT);
			socketClient = socketServer.accept();
			clientIp = socketClient.getInetAddress().getHostAddress();
			System.out.println(
					"[SERVEUR] Connexion avec " + clientIp + " acceptée");

			// Open input stream
			ois = new ObjectInputStream(socketClient.getInputStream());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			connect();

			try {
				// Read from input stream
				while (true) {
					ctrl.updateTransaction((Transaction) ois.readObject());
				}
			} catch (EOFException eof) {
				System.out.println("[SERVEUR] Connexion avec " + clientIp + " perdue");
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			try {
				this.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void close() throws IOException {
		ois.close();
		socketClient.close();
		socketServer.close();
	}

}
