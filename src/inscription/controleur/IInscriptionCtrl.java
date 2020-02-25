package inscription.controleur;

import inscription.modele.DataTransition;
import inscription.modele.ExceptionCreationCompte;
import javafx.scene.Scene;

/**
 * Interface définissant les comportements obligatoires de InscriptionCtrl
 * 
 * @author Bank-era Corp.
 *
 */
public interface IInscriptionCtrl {

	/**
	 * Méthode qui retourne la scène de la vue
	 * 
	 * @return - La scène
	 */
	public Scene getScene();

	/**
	 * Méthode qui demande au modèle d'ajouter un nouvel utilisateur à la base de
	 * données
	 * 
	 * @return - Vrai si l'utilisateur est ajouté avec succès, faux sinon
	 */
	public boolean envoyerDataClient(DataTransition data) throws ExceptionCreationCompte;

}
