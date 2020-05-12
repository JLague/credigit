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
	private static final long CONST = 1585281727947L;
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

	public static int getNbTransactionHier(Etablissement e, LocalDate date) {
		return getTransactionAvant(e, 1, date).size();
	}

	public static float getVentesBrutesToday(Etablissement e, LocalDate date) {
		ArrayList<Transaction> listTr = getTransactionToday(e, date);
		float total = 0;

		for (Transaction tr : listTr) {
			total += tr.getSousTotal();
		}

		return total;
	}

	public static float getVentesBrutesHier(Etablissement e, LocalDate date) {
		ArrayList<Transaction> listTr = getTransactionAvant(e, 1, date);
		float total = 0;

		for (Transaction tr : listTr) {
			total += tr.getSousTotal();
		}

		return total;
	}

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

	public static ArrayList<Vendeur> getUtilisateurs(Etablissement e) {
		return (ArrayList<Vendeur>) e.getUtilisateurs();
	}

	public static void setDate(LocalDate date) {
		dateReference = date;
	}

	public static int getNbTransactionSemCourante(Etablissement e, LocalDate date) {
		int somme = 0;
		
		for (int i = 0; i < 7; i++) {
			somme += getTransactionAvant(e, i, date).size();
		}
		
		return somme;
	}
	
	public static int getNbTransactionSemPrecedente(Etablissement e, LocalDate date) {
		int somme = 0;
		
		for (int i = 7; i < 14; i++) {
			somme += getTransactionAvant(e, i, date).size();
		}
		
		return somme;
	}
}
