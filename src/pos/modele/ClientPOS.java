package pos.modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import commun.Transaction;

public class ClientPOS {

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


	public ClientPOS() {
			try {
				socketClient = new Socket("127.0.1.1", 47800);
				oos = new ObjectOutputStream(socketClient.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void send(Transaction trans) {
		trans.serialize(oos);
	}

	public void stop() throws IOException {
		ois.close();
		oos.close();
		socketClient.close();
	}

}
