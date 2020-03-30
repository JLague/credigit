package terminal.ctrl;

import java.io.ObjectInputStream;

import commun.TableauDeBord;
import commun.Transaction;
import javafx.application.Platform;
import javafx.scene.Scene;
import terminal.application.TerminalApplication;
import terminal.modele.ServeurTerminal;
import terminal.vue.TerminalControleurVue;

public class TerminalControleur {

	/**
	 * 
	 * Tableau de bord de l'application
	 */
	private TableauDeBord tb;

	/**
	 * Vue associé à ce controleur
	 */
	private TerminalControleurVue vue;

	/**
	 * S'occupe d'établir et d'intéragir avec le terminal
	 */
	private ServeurTerminal serveur;

	public TerminalControleur(TerminalApplication terminalApplication) {
		vue = new TerminalControleurVue(this);

		tb = new TableauDeBord();

		serveur = new ServeurTerminal(this);
		new Thread(serveur).start();

	}

	public void updateTransaction(Transaction t) {
		tb.setTransaction(t);
		Platform.runLater(() -> vue.actualiser(t));
	}
	
	public void updateTransaction(ObjectInputStream ois) {
		tb.setTransaction(Transaction.deserialize(ois));
		Platform.runLater(() -> vue.actualiser(tb.getTransaction()));
	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void effectuerTransaction() {
		// byte[] empreinte = EmpreinteUtil.getEmpreinte();
	}
}
