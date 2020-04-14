package creationEtablissement.modele;

import commun.Etablissement;
import commun.exception.ExceptionCreationCompte;

/**
 * Interface définissant les comportements obligatoires de
 * CreationEtablissementModele
 * 
 * @author Bank-era Corp.
 *
 */
public interface ICreationEtablissementModele {

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
}
