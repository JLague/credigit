package terminal.ctrl;

import java.util.List;

import commun.EtatTransaction;
import commun.Transaction;
import commun.utils.EmpreinteUtil;
import javafx.application.Platform;
import javafx.scene.Scene;
import terminal.application.TerminalApplication;
import terminal.modele.Connexion;
import terminal.modele.ServeurTerminal;
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
		});
		
		if (trans.getEtat() == EtatTransaction.EMPREINTE || trans.getEtat() == EtatTransaction.ATTENTE) {
			effectuerTransaction(connexion.getEmpreintes());
		}
	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void effectuerTransaction(List<byte[]> empreintes) {
		byte[] empreinteClient = EmpreinteUtil.matchEmpreinte(EmpreinteUtil.getEmpreinte(), empreintes);

		if (!connexion.effectuerTransaction(empreinteClient, trans)) {
			trans.setEtat(EtatTransaction.ERREUR);
		}
		
		serveur.send(trans);
		vue.actualiser(trans);
	}
}
