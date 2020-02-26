package inscription.controleur;

import inscription.modele.Client;
import inscription.modele.Connexion;
import inscription.modele.CourrielConfirmation;
import inscription.modele.DataTransition;
import inscription.modele.ExceptionCreationCompte;
import inscription.vue.InscriptionVueCtrl;
import javafx.scene.Scene;

/**
 * Contrôleur principal de l'application
 * 
 * @author Bank-era Corp.
 *
 */
public class InscriptionCtrl implements IInscriptionCtrl {

	/**
	 * Contôleur de la vue
	 */
	private InscriptionVueCtrl vue;

	/**
	 * Connexion avec la base de données
	 */
	private Connexion connexion;

	/**
	 * Connexion avec le serveur d'envoi de courriels
	 */
	private CourrielConfirmation courriel;

	/**
	 * Constructeur du contrôleur principal de l'application
	 */
	public InscriptionCtrl() {
		vue = new InscriptionVueCtrl(this);
		courriel = new CourrielConfirmation();
		connexion = new Connexion(courriel);
	}

	@Override
	public Scene getScene() {
		return vue.getScene();
	}

	@Override
	public boolean envoyerDataClient(DataTransition data) throws ExceptionCreationCompte {
		return connexion.ajouterCompte(new Client(data));
	}

	@Override
	public boolean supprimerCompte(String nom, String prenom, String email, String nas) {
		return connexion.supprimerCompte(nom, prenom, email, nas);
	}

}
