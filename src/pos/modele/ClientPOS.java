package pos.modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import commun.TableauDeBord;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class ClientPOS extends Task<Void> {

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
			call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Void call() throws Exception {
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					socketClient = new Socket("192.168.0.153", 47800);
					oos = new ObjectOutputStream(socketClient.getOutputStream());
					ois = new ObjectInputStream(socketClient.getInputStream());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		return null;
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
