package terminal.modele;

import java.io.*;
import java.net.*;
import terminal.ctrl.*;

public class ServeurTerminal {

	private TerminalControleur ctrl;

	private PrintWriter outgoing;
	private BufferedReader incoming;
	private ServerSocket socketServer;
	private Socket socketClient;

	String entrant;
	
	InetAddress ip;

	public ServeurTerminal(TerminalControleur pCtrl) {
		ctrl = pCtrl;
	}

	public void start() throws IOException {
		ip = InetAddress.getLocalHost();
		System.out.println("Adresse ip du terminal (à enter dans le POS) : " + ip.getHostAddress());
		
		socketServer = new ServerSocket(47800);
		socketClient = socketServer.accept();

		outgoing = new PrintWriter(socketClient.getOutputStream(), true);
		incoming = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

		while ((entrant = incoming.readLine()) != null) {
			if ("finConnection".equals(entrant)) {
				break;
			} else {
				writeFile(entrant);
				ctrl.updateTransaction();
			}

		}

	}

	public void stop() throws IOException {
		incoming.close();
		outgoing.close();

		socketClient.close();
		socketServer.close();
	}

	private void writeFile(String entrant) {
		String fichier = "transaction.ser";

		try {
			byte[] buffer = entrant.getBytes();
			FileOutputStream os = new FileOutputStream(fichier);
			os.write(buffer);
			os.flush();
			os.close();
		} catch (Exception e) {
			System.out.println("Fuck pas capable d'écrire");
			e.printStackTrace();
		}

	}

}
