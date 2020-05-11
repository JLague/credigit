package statistiques.ctrl;

import java.time.LocalDate;
import java.util.ArrayList;

import commun.Etablissement;
import commun.Transaction;
import commun.exception.ExceptionProduitEtablissement;
import javafx.scene.Scene;
import statistiques.application.TBApplication;
import statistiques.modele.Analyse;
import statistiques.modele.Connexion;
import statistiques.vue.BackEndDashboardCtrlVue;

public class TBControleur {

	private TBApplication app;

	private BackEndDashboardCtrlVue vue;

	private Connexion connexion;

	private Etablissement etablissement;

	private LocalDate date;

	public TBControleur(TBApplication tBApplication) {
		app = tBApplication;
		connexion = new Connexion();
		vue = new BackEndDashboardCtrlVue(this);
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
		return Analyse.getTransactionToday(etablissement, date);
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public ArrayList<Transaction> getTransactionAvant(Etablissement etablissement, int i) {
		return Analyse.getTransactionAvant(etablissement, i, date);
	}

	public float getVentesBrutesToday(Etablissement etablissement) {
		return Analyse.getVentesBrutesToday(etablissement, date);
	}

	public float getVentesBrutesHier(Etablissement etablissement) {
		return Analyse.getVentesBrutesHier(etablissement, date);
	}

	public float getProfitToday(Etablissement etablissement) {
		return Analyse.getProfitToday(etablissement, date);
	}

	public double getProfitHier(Etablissement etablissement) {
		return Analyse.getProfitHier(etablissement, date);
	}

	public int getNbTransactionToday(Etablissement etablissement) {
		return Analyse.getNbTransactionToday(etablissement, date);
	}

	public int getNbTransactionHier(Etablissement etablissement) {
		return Analyse.getNbTransactionHier(etablissement, date);
	}

	public void setDate(LocalDate value) {
		this.date = value;

	}

}
