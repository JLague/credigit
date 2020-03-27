package creationEtablissement.controleur;

import creationEtablissement.modele.CreationEtablissementModele;
import creationEtablissement.vue.CreationEtablissementVueCtrl;
import javafx.scene.Scene;
import commun.exception.ExceptionCreationCompte;
import commun.exception.ExceptionProduitEtablissement;
import commun.Etablissement;

public class CreationEtablissementCtrl {

	CreationEtablissementVueCtrl vue;
	
	CreationEtablissementModele modele;

	Etablissement etablissement;
	
	public CreationEtablissementCtrl()
	{
		vue = new CreationEtablissementVueCtrl(this);
		
		modele = new CreationEtablissementModele();
	
	}
	
	public Scene getScene()
	{
		return vue.getScene();
	}
	
	public void creerEtablissement(String nom, String adresse, float balance, String courriel) throws ExceptionProduitEtablissement, ExceptionCreationCompte
	{
		etablissement = new Etablissement(nom,adresse,balance, courriel);
		
		modele.ajouterEtablissement(etablissement);
		
		modele.envoyerCourriel(etablissement);
	}
	
}
