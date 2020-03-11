package inscription.controleur;

import connexion.Connexion;
import connexion.CourrielConfirmation;
import exception.ExceptionCreationCompte;
import inscription.modele.Client;
import inscription.modele.DataTransition;
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
		return connexion.ajouterCompteClient(new Client(data));
	}

	@Override
	public boolean supprimerCompte(String nom, String prenom, String email, String nas) {
		return connexion.supprimerCompteClient(nom, prenom, email, nas);
	}

}
