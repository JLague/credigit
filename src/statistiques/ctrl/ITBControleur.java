package statistiques.ctrl;

import java.time.LocalDate;
import java.util.ArrayList;

import commun.Etablissement;
import commun.Transaction;
import commun.exception.ExceptionProduitEtablissement;
import javafx.scene.Scene;

/**
 * Classe définissant les comportements obligatoires de TBControleur
 * 
 * @author Bank-era Corp.
 *
 */
public interface ITBControleur {

	/**
	 * Getter de l'attribut scène
	 * 
	 * @return la scène
	 */
	public Scene getScene();

	/**
	 * Méthode permettant de changer de scène
	 * 
	 * @param scene      - La scène
	 * @param title      - Le titre de la scène
	 * @param fullscreen - Si elle est en pleine écran ou non
	 */
	public void chargerScene(Scene scene, String title, boolean fullscreen);

	/**
	 * Méthode permettant de connecter un utilisateur
	 * 
	 * @param username         - Le nom d'utilisateur
	 * @param password         - Le mot de passe de l'utilisateur
	 * @param nomEtablissement - L'établissement où l'utilisateur travaille
	 * @throws ExceptionProduitEtablissement
	 */
	public void connexion(String username, String password, String nomEtablissement)
			throws ExceptionProduitEtablissement;

	/**
	 * Méthode retournant la liste de transaction de la journée
	 * 
	 * @param etablissement - L'établissement courant
	 * @return la liste de transaction de la journée
	 */
	public ArrayList<Transaction> getTransactionToday(Etablissement etablissement);

	/**
	 * Getter de l'attribut etablissement
	 * 
	 * @return l'établissement
	 */
	public Etablissement getEtablissement();

	/**
	 * Méthode retournant la liste des transactions du X jour avant la date
	 * sélectionnée
	 * 
	 * @param etablissement - L'établissement courant
	 * @param i             - Le i jour avant
	 * @return la liste des transactions du X jour avant la date sélectionnée
	 */
	public ArrayList<Transaction> getTransactionAvant(Etablissement etablissement, int i);

	/**
	 * Méthode retournant les ventes brutes du jour sélectionné
	 * 
	 * @param etablissement - L'établissement courant
	 * @return le total des ventes brutes du jour sélectionné
	 */
	public float getVentesBrutesToday(Etablissement etablissement);

	/**
	 * Méthode retournant les ventes brutes du jour avant le jour sélectionné
	 * 
	 * @param etablissement - L'établissement courant
	 * @return le total des ventes brutes du jour avant le jour sélectionné
	 */
	public float getVentesBrutesHier(Etablissement etablissement);

	/**
	 * Méthode retournant les profits pour la journée sélectionnée
	 * 
	 * @param etablissement - L'établissement courant
	 * @return les profits pour la journée sélectionnée
	 */
	public float getProfitToday(Etablissement etablissement);

	/**
	 * Méthode retournant les profits pour la journée avant celle sélectionnée
	 * 
	 * @param etablissement - L'établissement courant
	 * @return les profits pour la journée avant celle sélectionnée
	 */
	public double getProfitHier(Etablissement etablissement);

	/**
	 * Méthode retournant les profits de la X journée avant la journée sélectionnée
	 * 
	 * @param etablissement - L'établissement courant
	 * @param jourAvant     - La X journée avant
	 * @return les profits de la X journée avant la journée sélectionnée
	 */
	public float getProfitsAvant(Etablissement etablissement, int jourAvant);

	/**
	 * Méthode retournant le nombre de transactions de la journée sélectionnée
	 * 
	 * @param etablissement - L'établissement courant
	 * @return le nombre de transactions de la journée sélectionnée
	 */
	public int getNbTransactionToday(Etablissement etablissement);

	/**
	 * Méthode retournant le nombre de transactions de la journée avant celle
	 * sélectionnée
	 * 
	 * @param etablissement - l'établissement courant
	 * @return le nombre de transactions de la journée avant celle sélectionnée
	 */
	public int getNbTransactionHier(Etablissement etablissement);

	/**
	 * Setter de l'attribut date
	 * 
	 * @param value - La date à setter
	 */
	public void setDate(LocalDate value);

	/**
	 * Méthode retournant le nombre de transactions de la semaine courante
	 * 
	 * @param e - L'établissement courant
	 * @return le nombre de transactions de la semaine courante
	 */
	public int getNbTransactionSemCourante(Etablissement e);

	/**
	 * Méthode retournant le nombre de transactions de la semaine précédent la
	 * semaine courante
	 * 
	 * @param e - L'établissement courant
	 * @return retournant le nombre de transactions de la semaine précédent la
	 *         semaine courante
	 */
	public int getNbTransactionSemPrecedente(Etablissement e);
}
