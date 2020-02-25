package inscription.controleur;

import inscription.modele.Client;
import inscription.modele.Connexion;
import inscription.modele.CourrielConfirmation;
import inscription.modele.DataTransition;
import inscription.modele.ExceptionCreationCompte;
import inscription.vue.InscriptionVueCtrl;
import javafx.scene.Scene;

public class InscriptionCtrl {

	private InscriptionVueCtrl vue;
	private Connexion connexion;
	private CourrielConfirmation courriel;

	public InscriptionCtrl() {
		vue = new InscriptionVueCtrl(this);
		courriel = new CourrielConfirmation();
		connexion = new Connexion(courriel);
	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void envoyerDataClient(DataTransition data) throws ExceptionCreationCompte {
		connexion.ajouterCompte(new Client(data));
	}
}
