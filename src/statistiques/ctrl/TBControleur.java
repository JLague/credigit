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

/**
 * Classe servant de contrôleur de l'application du tableau de statistiques
 * 
 * @author Bank-era Corp.
 *
 */
public class TBControleur implements ITBControleur {

	/**
	 * L'application
	 */
	private TBApplication app;

	/**
	 * La vue
	 */
	private BackEndDashboardCtrlVue vue;

	/**
	 * La connexion avec la base de données
	 */
	private Connexion connexion;

	/**
	 * l'établissement de l'utilisateur connecté
	 */
	private Etablissement etablissement;

	/**
	 * La date sélectionnée
	 */
	private LocalDate date;

	/**
	 * Constructeur du contrôleur
	 * 
	 * @param tBApplication - L'application
	 */
	public TBControleur(TBApplication tBApplication) {
		app = tBApplication;
		connexion = new Connexion();
		vue = new BackEndDashboardCtrlVue(this);
	}

	@Override
	public Scene getScene() {
		return vue.getScene();
	}

	@Override
	public void chargerScene(Scene scene, String title, boolean fullscreen) {
		app.chargerScene(scene, title, fullscreen);
	}

	@Override
	public void connexion(String username, String password, String nomEtablissement)
			throws ExceptionProduitEtablissement {
		etablissement = connexion.connecter(username, password, nomEtablissement);

	}

	@Override
	public ArrayList<Transaction> getTransactionToday(Etablissement etablissement) {
		return Analyse.getTransactionToday(etablissement, date);
	}

	@Override
	public Etablissement getEtablissement() {
		return etablissement;
	}

	@Override
	public ArrayList<Transaction> getTransactionAvant(Etablissement etablissement, int i) {
		return Analyse.getTransactionAvant(etablissement, i, date);
	}

	@Override
	public float getVentesBrutesToday(Etablissement etablissement) {
		return Analyse.getVentesBrutesToday(etablissement, date);
	}

	@Override
	public float getVentesBrutesHier(Etablissement etablissement) {
		return Analyse.getVentesBrutesHier(etablissement, date);
	}

	@Override
	public float getProfitToday(Etablissement etablissement) {
		return Analyse.getProfitToday(etablissement, date);
	}

	@Override
	public double getProfitHier(Etablissement etablissement) {
		return Analyse.getProfitHier(etablissement, date);
	}

	@Override
	public float getProfitsAvant(Etablissement etablissement, int jourAvant) {
		return Analyse.getProfitsAvant(etablissement, jourAvant, date);
	}

	@Override
	public int getNbTransactionToday(Etablissement etablissement) {
		return Analyse.getNbTransactionToday(etablissement, date);
	}

	@Override
	public int getNbTransactionHier(Etablissement etablissement) {
		return Analyse.getNbTransactionHier(etablissement, date);
	}

	@Override
	public void setDate(LocalDate value) {
		this.date = value;

	}

	@Override
	public int getNbTransactionSemCourante(Etablissement e) {
		return Analyse.getNbTransactionSemCourante(e, date);
	}

	@Override
	public int getNbTransactionSemPrecedente(Etablissement e) {
		return Analyse.getNbTransactionSemPrecedente(e, date);
	}

}
