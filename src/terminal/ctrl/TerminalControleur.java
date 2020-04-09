package terminal.ctrl;

import commun.EtatTransaction;
import commun.Transaction;
import javafx.application.Platform;
import javafx.scene.Scene;
import terminal.application.TerminalApplication;
import terminal.modele.Connexion;
import terminal.modele.ServeurTerminal;
import terminal.utils.EmpreinteUtil;
import terminal.vue.TerminalControleurVue;

public class TerminalControleur {

	/**
	 * 
	 * Tableau de bord de l'application
	 */
	private Transaction trans;

	/**
	 * Vue associé à ce controleur
	 */
	private TerminalControleurVue vue;

	/**
	 * S'occupe d'établir et d'intéragir avec le terminal
	 */
	private ServeurTerminal serveur;

	private Connexion connexion;

	public TerminalControleur(TerminalApplication terminalApplication) {
		vue = new TerminalControleurVue(this);
		connexion = new Connexion();

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
		trans = newTransaction;
		Platform.runLater(() -> {
			vue.actualiser(trans);
			if (trans.getEtat() == EtatTransaction.EMPREINTE || trans.getEtat() == EtatTransaction.ATTENTE) {
				effectuerTransaction();
			}
		});
	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void effectuerTransaction() {

		byte[] empreinteClient = EmpreinteUtil.matchEmpreinte(EmpreinteUtil.getEmpreinte(), connexion.getEmpreintes());

		if (connexion.effectuerTransaction(empreinteClient, trans)) {
			trans.setEtat(EtatTransaction.CONFIRMATION);
		} else {
			trans.setEtat(EtatTransaction.ERREUR);
		}
		serveur.send(trans);
		vue.actualiser(trans);
	}
}
