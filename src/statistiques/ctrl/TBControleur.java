package statistiques.ctrl;

import java.util.ArrayList;
import java.util.List;

import commun.Etablissement;
import commun.Produit;
import commun.Transaction;
import commun.Vendeur;
import commun.exception.ExceptionProduitEtablissement;
import javafx.scene.Scene;
import statistiques.application.TBApplication;
import statistiques.modele.Analyse;
import statistiques.modele.Connexion;
import statistiques.vue.BackEndDashboardCtrlVue;

public class TBControleur {

	private Analyse Analyse;

	private TBApplication app;

	private BackEndDashboardCtrlVue vue;

	private Connexion connexion;

	private Etablissement etablissement;

	public TBControleur(TBApplication tBApplication) {
		app = tBApplication;
		vue = new BackEndDashboardCtrlVue(this);
		connexion = new Connexion();
		Analyse = new Analyse();
	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void chargerScene(Scene scene, String title, boolean fullscreen) {
		app.chargerScene(scene, title, fullscreen);
	}

	public void connexion(String username, String password, String nomEtablissement)
			throws ExceptionProduitEtablissement {
		etablissement = connexion.connecter(username, password, nomEtablissement);

	}

	public ArrayList<Transaction> getTransactionToday(Etablissement etablissement) {
		return Analyse.getTransactionToday(etablissement);
	}

	public ArrayList<Transaction> getTransactionAvant(Etablissement etablissement, int i) {
		return Analyse.getTransactionAvant(etablissement, i);
	}

	public float getVentesBrutesToday(Etablissement etablissement) {
		return Analyse.getVentesBrutesToday(etablissement);
	}

	public float getVentesBrutesHier(Etablissement etablissement) {
		return Analyse.getVentesBrutesHier(etablissement);
	}

	public float getProfitToday(Etablissement etablissement) {
		return Analyse.getProfitToday(etablissement);
	}

	public double getProfitHier(Etablissement etablissement) {
		return Analyse.getProfitHier(etablissement);
	}

	public int getNbTransactionToday(Etablissement etablissement) {
		return Analyse.getNbTransactionToday(etablissement);
	}

	public int getNbTransactionHier(Etablissement etablissement) {
		return Analyse.getNbTransactionHier(etablissement);
	}

}
