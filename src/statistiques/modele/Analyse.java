package statistiques.modele;

import commun.Transaction;
import commun.Vendeur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import commun.Etablissement;
import commun.LigneFacture;

public class Analyse {

	/**
	 * Constante soustraite au temps présent en millisescondes afin d'éviter que le
	 * numéro de facture soit trop long
	 */
	private static final long CONST = 1585281727947L;
	private static LocalDate dateReference;

	public static int getNbProduit(Etablissement e) {
		return e.getInventaire().size();
	}

	public static ArrayList<Transaction> getTransactionToday(Etablissement e, LocalDate date) {

		ArrayList<Transaction> transHebdo = new ArrayList<Transaction>();

		for (Transaction t : e.getTransactions()) {
			if (t.getHeure().substring(0, 10).equals(date.toString().replace('-', '/'))) {
				transHebdo.add(t);
			}
		}

		return transHebdo;
	}

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

	public static ArrayList<Vendeur> getUtilisateur(Etablissement e) {
		return (ArrayList<Vendeur>) e.getUtilisateurs();
	}

	public void setDate(LocalDate date) {
		this.dateReference = date;
	}
}
