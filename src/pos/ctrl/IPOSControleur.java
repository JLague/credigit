package pos.ctrl;

import javafx.scene.Scene;
import pos.modele.DataVue;

public interface IPOSControleur {

	/**
	 * Permet d'annuler
	 */
	public void annulerItem();

	public void annulerCommande();

	public void produitSelectionne(int ligne, int colonne);

	public void inputEntree(String input);

	public void creerProduit(DataVue data);

	public boolean connexion(String username, String password);

	public void paiementEmpreinte();

	public Scene getScene();
}
