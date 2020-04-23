package inscription.controleur;

import java.util.List;

import commun.exception.ExceptionCreationCompte;
import inscription.modele.Client;
import inscription.modele.ConnexionInscription;
import inscription.modele.CourrielConfirmation;
import inscription.modele.DataClient;
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
	private ConnexionInscription connexion;

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
		connexion = new ConnexionInscription(courriel);
	}

	@Override
	public Scene getScene() {
		return vue.getScene();
	}

	@Override
	public boolean envoyerDataClient(DataClient data) throws ExceptionCreationCompte {
		return connexion.ajouterCompteClient(new Client(data));
	}

	@Override
	public boolean supprimerCompte(byte[] empreinte) {
		return connexion.supprimerCompteClient(empreinte);
	}

	@Override
	public byte[] verifierEmpreinte(byte[] empreinteScanne) {
		List<byte[]> empreintes = connexion.getEmpreintes();
		return commun.utils.EmpreinteUtil.matchEmpreinte(empreinteScanne, empreintes);
	}

}
