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

	/**
	 * Méthode qui demande au modèle de supprimer un compte
	 * 
	 * @param nom    - Le nom du client
	 * @param prenom - Le prénom du client
	 * @param email  - L'email du client
	 * @param nas    - Le NAS du client
	 * @return Vrai si le compte est supprimé, faux sinon
	 */
	public boolean supprimerCompte(String nom, String prenom, String email, String nas);

}
