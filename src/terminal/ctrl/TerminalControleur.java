package terminal.ctrl;

import java.util.List;

import commun.EtatTransaction;
import commun.Transaction;
import commun.exception.ExceptionTransaction;
import commun.utils.EmpreinteUtil;
import javafx.application.Platform;
import javafx.scene.Scene;
import terminal.application.TerminalApplication;
import terminal.modele.Connexion;
import terminal.modele.ServeurTerminal;
import terminal.vue.TerminalControleurVue;

/**
 * Classe du contrôleur du terminal de paiement
 * 
 * @author Bank-era Corp.
 *
 */
public class TerminalControleur implements ITerminalControleur {

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

	@Override
	public void updateTransaction(Transaction newTransaction) {
		trans = newTransaction;
		Platform.runLater(() -> {
			vue.actualiser(trans);
		});

		if (trans.getEtat() == EtatTransaction.EMPREINTE || trans.getEtat() == EtatTransaction.ATTENTE) {
			effectuerTransaction(connexion.getEmpreintes());
		}
	}

	@Override
	public Scene getScene() {
		return vue.getScene();
	}

	@Override
	public void effectuerTransaction(List<byte[]> empreintes) {
		byte[] empreinteClient = EmpreinteUtil.matchEmpreinte(EmpreinteUtil.getEmpreinte(), empreintes);

		if (!connexion.effectuerTransaction(empreinteClient, trans)) {
			trans.setEtat(EtatTransaction.ERREUR);
		}

		serveur.send(trans);
		vue.actualiser(trans);
	}
}
