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

	public void envoyerDataClient(DataTransition data) {
		Client client = null;
		try {
			client = new Client(new DataTransition("Duchesne", "Christophe", "youremail@here.com", null, null,
					"111 111 111", null, null, "5555555555", null));
			connexion.ajouterCompte(client);
		} catch (ExceptionCreationCompte e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
