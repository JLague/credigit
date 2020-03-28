package terminal.modele;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import com.pi4j.platform.Platform;

import commun.Transaction;
import terminal.ctrl.*;

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
			socketClient = socketServer.accept();

			oos = new ObjectOutputStream(socketClient.getOutputStream());
			ois = new ObjectInputStream(socketClient.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Transaction t;

		try {
			while ((t = (Transaction) ois.readObject()) != null) {
				ctrl.updateTransaction(ois);
				
			}
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
