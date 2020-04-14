package terminal.vue;

import commun.Transaction;
import javafx.scene.Scene;

/**
 * Interface représentant les comportements obligatoires de
 * TerminalControleurVue
 * 
 * @author Bank-era Corp.
 *
 */
public interface ITerminalControleurVue {

	/**
	 * Getter de la scène
	 * 
	 * @return la scène
	 */
	public Scene getScene();

	/**
	 * Méthode permettant de mettre à jour la vue du terminal
	 * 
	 * @param trans - La transaction actuelle
	 */
	public void actualiser(Transaction trans);

}
