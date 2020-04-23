package creationEtablissement.modele;

import java.math.BigInteger;

import commun.Etablissement;
import commun.exception.ExceptionCreationCompte;
import encryption.CleRSA;

/**
 * Interface définissant les comportements obligatoires de
 * CreationEtablissementModele
 * 
 * @author Bank-era Corp.
 *
 */
public interface ICreationEtablissementModele {

	/**
	 * Clé du RSA
	 */
	public static final CleRSA CLE_RSA = new CleRSA(new BigInteger("32244774284211042705171103939999050641"),
			new BigInteger("65537"), new BigInteger("166671328359045595559284971252973341809"));

	/**
	 * Ajoute un établissement à la base de données
	 * 
	 * @param etablissement - L'établissement à ajouter
	 * @throws ExceptionCreationCompte
	 */
	public boolean ajouterEtablissement(Etablissement etablissement) throws ExceptionCreationCompte;

	/**
	 * Méthode permettant d'envoyer un courriel de bienvenue
	 * 
	 * @param adresse - L'adresse du correspondant
	 * @param Prenom  - Le prénom du correspondant
	 * @return Vrai si le courriel est envoyé avec succès, faux sinon
	 */
	public boolean envoyerCourriel(Etablissement etablissement);

	/**
	 * Méthode permettant d'aller chercher la clé dans la base de données.
	 * 
	 * @return la clé de AES encryptée en RSA
	 */
	public String getCleFromDatabase();
}
