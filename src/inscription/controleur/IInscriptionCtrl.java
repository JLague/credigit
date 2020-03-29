package inscription.controleur;

import commun.exception.ExceptionCreationCompte;
import inscription.modele.DataClient;
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
	public boolean envoyerDataClient(DataClient data) throws ExceptionCreationCompte;

	/**
	 * Méthode qui demande au modèle de supprimer un compte
	 * 
	 * @param empreinte l'empreinte associée au compte
	 * @return Vrai si le compte est supprimé, faux sinon
	 */
	public boolean supprimerCompte(byte[] empreinte);

	/**
	 * Méthode qui vérifie l'empreinte passée en paramètre avec le modèle
	 * 
	 * @param empreinteScanne l'empreinte scannée à vérifier
	 * @return l'empreinte si elle est trouvée dans la base de données, null sinon
	 */
	byte[] verifierEmpreinte(byte[] empreinteScanne);

}
