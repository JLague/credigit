package terminal.modele;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import terminal.ctrl.*;

public class ServeurTerminal {

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

	public void start() throws IOException {
		ip = InetAddress.getLocalHost();
		System.out.println("Adresse ip du terminal (à enter dans le POS) : " + ip.getHostAddress());

		socketServer = new ServerSocket(47800);
		socketClient = socketServer.accept();

		oos = new ObjectOutputStream(socketClient.getOutputStream());
		ois = new ObjectInputStream(socketClient.getInputStream());

		while (true) {
			ctrl.updateTransaction(ois);
		}

	}

	public void stop() throws IOException {
		ois.close();
		oos.close();

		socketClient.close();
		socketServer.close();
	}

}
