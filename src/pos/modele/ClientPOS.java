package pos.modele;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import pos.ctrl.POSControleur;

public class ClientPOS implements Runnable {

	/**
	 * Controleur du POS
	 */
	private POSControleur ctrl;

	/**
	 * Socket du client
	 */
	private Socket socketClient;

	/**
	 * Expédition du POS ves le terminal
	 */
	private PrintWriter outgoing;

	/**
	 * Réception des messages du terminal
	 */
	private BufferedReader incoming;

	public ClientPOS(POSControleur pCtrl) {
		ctrl = pCtrl;
	}

	@Override
	public void run() {
		try {
			socketClient = new Socket("24.201.124.201", 6666);
			outgoing = new PrintWriter(socketClient.getOutputStream(), true);
			incoming = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void send() {
		String fichier = "transaction.ser";
		String fichierEnvoi ="";
		try {
			byte[] buffer = new byte[5000];
			FileInputStream is = new FileInputStream(fichier);
			int nb = 0;
			while ((nb = is.read(buffer)) != -1) {
				fichierEnvoi += new String(buffer);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		outgoing.println(fichierEnvoi);
	}

	public void stop() throws IOException {
		incoming.close();
		outgoing.close();
		socketClient.close();
	}

}
