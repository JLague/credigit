package inscription.controleur;

import inscription.modele.Client;
import inscription.modele.Connexion;
import inscription.modele.DataTransition;
import inscription.modele.ExceptionCreationCompte;
import inscription.vue.InscriptionVueCtrl;
import javafx.scene.Scene;

public class InscriptionCtrl {

	private InscriptionVueCtrl vue;
	private Connexion connexion;

	public InscriptionCtrl() {
		vue = new InscriptionVueCtrl(this);
		connexion = new Connexion();
	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void envoyerDataClient(DataTransition data) throws ExceptionCreationCompte {
		connexion.ajouterCompte(new Client(data));
	}
}
