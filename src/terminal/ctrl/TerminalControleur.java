package terminal.ctrl;

import javafx.scene.Scene;
import terminal.modele.Connexion;
import terminal.modele.TableauDeBord;
import terminal.application.TerminalApplication;
import terminal.modele.Transaction;
import terminal.vue.TerminalControleurVue;

public class TerminalControleur {

	/**
	 * Tableau de bord de l'application
	 */
	private TableauDeBord tb;

	/**
	 * Vue associé à ce controleur
	 */
	private TerminalControleurVue vue;
	
	public TerminalControleur(TerminalApplication terminalApplication) {
		vue = new TerminalControleurVue(this);
		tb = new TableauDeBord();
		tb.setTransaction(Transaction.deserialize());
	}

	public Scene getScene() {
		return vue.getScene();
	}
}
