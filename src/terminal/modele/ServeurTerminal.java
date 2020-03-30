package terminal.modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import commun.Transaction;
import terminal.ctrl.TerminalControleur;

public class ServeurTerminal implements Runnable {

	private TerminalControleur ctrl;

	private ObjectInputStream ois;
	private ServerSocket socketServer;
	private Socket socketClient;

	byte entrant[];

	InetAddress ip;

	public ServeurTerminal(TerminalControleur pCtrl) {
		ctrl = pCtrl;

		// Ferme le socket lorsque l'application ferme
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					socketServer.close();
					System.out.println("The server is shut down!");
				} catch (IOException e) {
					/* failed */ }
			}
		});

		try {
			ip = InetAddress.getLocalHost();
			System.out.println("Adresse ip du terminal (à enter dans le POS) : " + ip.getHostAddress());
			socketServer = new ServerSocket(47800);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			socketClient = socketServer.accept();
			ois = new ObjectInputStream(socketClient.getInputStream());
			
			Transaction t = null;
			
			while ((t = (Transaction) ois.readObject()) != null) {
				System.out.println("here");
				ctrl.updateTransaction(t);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void stop() throws IOException {
		ois.close();

		socketClient.close();
		socketServer.close();
	}

}
