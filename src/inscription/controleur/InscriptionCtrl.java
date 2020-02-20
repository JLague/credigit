package inscription.controleur;

import java.time.LocalDate;
import java.util.ArrayList;

import inscription.modele.Client;
import inscription.modele.Connexion;
import inscription.modele.DataTransition;
import inscription.modele.ExceptionCreationCompte;
import inscription.modele.LocalAdresse;
import inscription.modele.Questions;
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
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			client = new Client(new DataTransition("Duchesne", "Christophe", "youremail@here.com", localDate,
					new LocalAdresse("555 rue Yolo", 12, "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "5555555555", null));
			connexion.ajouterCompte(client);
		} catch (ExceptionCreationCompte e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
