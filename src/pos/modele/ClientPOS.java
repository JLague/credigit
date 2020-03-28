package pos.modele;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import commun.TableauDeBord;
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
	private ObjectOutputStream oos;

	/**
	 * Réception des messages du terminal
	 */
	private ObjectInputStream ois;

	public ClientPOS(POSControleur pCtrl) {
		ctrl = pCtrl;
	}

	@Override
	public void run() {
		try {
			socketClient = new Socket("192.168.1.120", 47800);
			oos = new ObjectOutputStream(socketClient.getOutputStream());
			ois = new ObjectInputStream(socketClient.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void send(TableauDeBord tb) {
		tb.getTransaction().serialize(oos);
	}

	public void stop() throws IOException {
		ois.close();
		oos.close();
		socketClient.close();
	}

}
