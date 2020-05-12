package statistiques.modele;

import java.time.LocalDate;
import java.util.ArrayList;

import commun.Etablissement;
import commun.LigneFacture;
import commun.Transaction;
import commun.Vendeur;

/**
 * Classe offrant des méthodes statiques qui permettent d'analyser plusieurs
 * aspects d'un établissement, comme les ventes de la journée, le nombre de
 * transactions totales, etc.
 * 
 * @author Bank-era
 *
 */
public class Analyse {

	/**
	 * Constante soustraite au temps présent en millisescondes afin d'éviter que le
	 * numéro de facture soit trop long
	 */
	@SuppressWarnings("unused")
	private static final long CONST = 1585281727947L;
	/**
	 * Date pour laquelle on veut obtenir les renseignements
	 */
	@SuppressWarnings("unused")
	private static LocalDate dateReference;

	/**
	 * Retourne le nombre de produits de l'établissement
	 * 
	 * @param e l'établissement à analyser
	 * @return le nombre de produits
	 */
	public static int getNbProduit(Etablissement e) {
		return e.getInventaire().size();
	}

	/**
	 * Retourne les transactions effectuées lors de la journée spécifiée par la date
	 * passée en paramètres
	 * 
	 * @param e    l'établissement à analyser
	 * @param date la date à analyser
	 * @return les transactions effectuées
	 */
	public static ArrayList<Transaction> getTransactionToday(Etablissement e, LocalDate date) {

		ArrayList<Transaction> transHebdo = new ArrayList<Transaction>();

		for (Transaction t : e.getTransactions()) {
			if (t.getHeure().substring(0, 10).equals(date.toString().replace('-', '/'))) {
				transHebdo.add(t);
			}
		}

		return transHebdo;
	}

	/**
	 * Retourne les transactions de l'établissement durant le nombre de jour
	 * spécifié avant la date spécifiée
	 * 
	 * @param e         l'établissement à analyser
	 * @param jourAvant le nombre de jours avant
	 * @param date      la date à analyser
	 * @return les transactions
	 */
	public static ArrayList<Transaction> getTransactionAvant(Etablissement e, int jourAvant, LocalDate date) {
		LocalDate dateTemp = date.minusDays(jourAvant);

		ArrayList<Transaction> transHebdo = new ArrayList<Transaction>();

		for (Transaction t : e.getTransactions()) {
			if (t.getHeure().substring(0, 10).equals(dateTemp.toString().replace('-', '/'))) {
				transHebdo.add(t);
			}
		}

		return transHebdo;
	}

	/**
	 * Retourne le nombre de transactions effectuées durant la journée spécifiée en
	 * paramètre
	 * 
	 * @param e    l'établissement à analyser
	 * @param date la date à analyser
	 * @return le nombre de transactions
	 */
	public static int getNbTransactionToday(Etablissement e, LocalDate date) {
		return getTransactionToday(e, date).size();
	}

	/**
	 * Retourne le nombre de transaction effectué la journée précédente à la journée
	 * spécifié en param
	 * 
	 * @param e    l'etablissement à analyser
	 * @param date la date qui va servir de référence
	 * @return le nombre de transaction effectué la journée précédente
	 */
	public static int getNbTransactionHier(Etablissement e, LocalDate date) {
		return getTransactionAvant(e, 1, date).size();
	}

	/**
	 * Retourne le total de ventes brutes accompli lors de la journée passé en param
	 * 
	 * @param e    l'etablissement à analyser
	 * @param date la date qui va servir de référence
	 * @return le total de ventes brutes accompli
	 */
	public static float getVentesBrutesToday(Etablissement e, LocalDate date) {
		ArrayList<Transaction> listTr = getTransactionToday(e, date);
		float total = 0;

		for (Transaction tr : listTr) {
			total += tr.getSousTotal();
		}

		return total;
	}

	/**
	 * Retourne le total de ventes brutes accompli lors de la journée précécente à
	 * la journée passé en param
	 * 
	 * @param e    l'etablissement à analyser
	 * @param date la date qui va servir de référence
	 * @return le total de ventes brutes accompli la journée précédente
	 */
	public static float getVentesBrutesHier(Etablissement e, LocalDate date) {
		ArrayList<Transaction> listTr = getTransactionAvant(e, 1, date);
		float total = 0;

		for (Transaction tr : listTr) {
			total += tr.getSousTotal();
		}

		return total;
	}

	/**
	 * Retourne les profits engrangé lors de la journée passé en param
	 * 
	 * @param e    l'etablissement à analyser
	 * @param date la date qui va servir de référence
	 * @return les profits engrangé
	 */
	public static float getProfitToday(Etablissement e, LocalDate date) {
		ArrayList<Transaction> listTr = getTransactionToday(e, date);
		float profit = 0;

		for (Transaction tr : listTr) {
			for (LigneFacture ligneFac : tr.ligneFactureArray) {
				profit += (ligneFac.getProduit().getPrix() - ligneFac.getProduit().getCoutant())
						* ligneFac.getQuantite();
			}
		}
		return profit;
	}

	/**
	 * Retourne les profits engrangé lors de la journée précédente à la journée
	 * passé en param
	 * 
	 * @param e    l'etablissement à analyser
	 * @param date la date qui va servir de référence
	 * @return les profits engrangé lors de la journée précédente
	 */
	public static float getProfitHier(Etablissement e, LocalDate date) {
		ArrayList<Transaction> listTr = getTransactionAvant(e, 1, date);
		float profit = 0;

		for (Transaction tr : listTr) {
			for (LigneFacture ligneFac : tr.ligneFactureArray) {
				profit += (ligneFac.getProduit().getPrix() - ligneFac.getProduit().getCoutant())
						* ligneFac.getQuantite();
			}
		}
		return profit;
	}

	/**
	 * Retourne un arraylist des utilisateurs de l'établissement
	 * 
	 * @param e l'etablissement à analyser
	 * @return un arraylist des utilisateurs
	 */
	public static ArrayList<Vendeur> getUtilisateurs(Etablissement e) {
		return (ArrayList<Vendeur>) e.getUtilisateurs();
	}

	/**
	 * Change la date de référence
	 * 
	 * @param date la nouvelle date
	 */
	public static void setDate(LocalDate date) {
		dateReference = date;
	}

	/**
	 * Retourne le nombre de transaction effectué lors de la semaine courante
	 * 
	 * @param e    l'etablissement à analyser
	 * @param date la date qui va servir de référence
	 * @return le nombre de transaction effectué lors de la semaine courante
	 */
	public static int getNbTransactionSemCourante(Etablissement e, LocalDate date) {
		int somme = 0;

		for (int i = 0; i < 7; i++) {
			somme += getTransactionAvant(e, i, date).size();
		}

		return somme;
	}

	/**
	 * Retourne le nombre de transaction effectué lors de la semaine précédente
	 * 
	 * @param e    l'etablissement à analyser
	 * @param date la date qui va servir de référence
	 * @return le nombre de transaction effectué lors de la semaine précédente
	 */
	public static int getNbTransactionSemPrecedente(Etablissement e, LocalDate date) {
		int somme = 0;

		for (int i = 7; i < 14; i++) {
			somme += getTransactionAvant(e, i, date).size();
		}

		return somme;
	}
}
