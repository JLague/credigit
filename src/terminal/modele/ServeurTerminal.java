package terminal.modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import commun.Transaction;
import javafx.application.Platform;
import terminal.ctrl.TerminalControleur;

public class ServeurTerminal implements Runnable {

	private TerminalControleur ctrl;

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ServerSocket socketServer;
	private Socket socketClient;

	byte entrant[];

	InetAddress ip;

	public ServeurTerminal(TerminalControleur pCtrl) {
		ctrl = pCtrl;
	}

	@Override
	public void run() {
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("Adresse ip du terminal (à enter dans le POS) : " + ip.getHostAddress());

			socketServer = new ServerSocket(47800);
			Platform.runLater(() -> {

				try {
					socketClient = socketServer.accept();
					oos = new ObjectOutputStream(socketClient.getOutputStream());
					ois = new ObjectInputStream(socketClient.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Transaction t = null;

				try {
					while ((t = (Transaction) ois.readObject()) != null) {
						ctrl.updateTransaction(ois);
					}
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stop() throws IOException {
		ois.close();
		oos.close();

		socketClient.close();
		socketServer.close();
	}

}
