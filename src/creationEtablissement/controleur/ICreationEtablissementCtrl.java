package creationEtablissement.controleur;

import java.math.BigInteger;

import commun.exception.ExceptionCreationCompte;
import commun.exception.ExceptionProduitEtablissement;
import encryption.CleRSA;
import javafx.scene.Scene;

/**
 * Interface dictant les comportements obligatoires de CreationEtablissementCtrl
 * 
 * @author Bank-era Corp.
 *
 */
public interface ICreationEtablissementCtrl {

	/**
	 * Getter de la scène active.
	 * 
	 * @return la scène
	 */
	public Scene getScene();

	/**
	 * Méthode permettant de créer un nouvel établissement.
	 * 
	 * @param nom      - Le nom de l'établissement
	 * @param adresse  - L'adresse de l'établissement
	 * @param balance  - La balance de l'établissement
	 * @param courriel - Le courriel de l'établissement
	 * @throws ExceptionProduitEtablissement
	 * @throws ExceptionCreationCompte
	 */
	public void creerEtablissement(String nom, String adresse, float balance, String courriel)
			throws ExceptionProduitEtablissement, ExceptionCreationCompte;

}
