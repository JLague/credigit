package terminal.ctrl;

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

	/**
	 * Update la transaction du TableauDeBord et update la vue avec la nouvelle
	 * transaction reçue
	 * 
	 * @param newTransaction
	 */
	public void updateTransaction(Transaction newTransaction) {
		tb.setTransaction(newTransaction);
		Platform.runLater(() -> vue.actualiser(newTransaction));
	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void effectuerTransaction() {
		// byte[] empreinte = EmpreinteUtil.getEmpreinte();
	}
}
