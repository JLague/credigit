package creationEtablissement.controleur;

import commun.Etablissement;
import commun.exception.ExceptionCreationCompte;
import commun.exception.ExceptionProduitEtablissement;
import creationEtablissement.modele.CreationEtablissementModele;
import creationEtablissement.vue.CreationEtablissementVueCtrl;
import javafx.scene.Scene;

/**
 * Contrôleur de l'application permettant de créer un établissement.
 * 
 * @author Bank-era Corp.
 *
 */
public class CreationEtablissementCtrl implements ICreationEtablissementCtrl {

	/**
	 * Le contrôleur de la vue de l'application
	 */
	CreationEtablissementVueCtrl vue;

	/**
	 * Le modèle de l'application
	 */
	CreationEtablissementModele modele;

	/**
	 * L'établissement
	 */
	Etablissement etablissement;

	/**
	 * Constructeur du contrôleur
	 */
	public CreationEtablissementCtrl() {
		vue = new CreationEtablissementVueCtrl(this);

		modele = new CreationEtablissementModele();

	}

	@Override
	public Scene getScene() {
		return vue.getScene();
	}

	@Override
	public void creerEtablissement(String nom, String adresse, float balance, String courriel)
			throws ExceptionProduitEtablissement, ExceptionCreationCompte {
		etablissement = new Etablissement(nom, adresse, balance, courriel);

		modele.ajouterEtablissement(etablissement);

		modele.envoyerCourriel(etablissement);
	}

}
