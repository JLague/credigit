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

	
	public static ArrayList<Transaction> getTransactionToday(Etablissement e) {

		LocalDate date = LocalDate.now();

		ArrayList<Transaction> transHebdo = new ArrayList<Transaction>();

		for (Transaction t : e.getTransactions()) {
			if (Integer.parseInt(t.getHeure()) > dateReference.toEpochDay() - CONST) {
				transHebdo.add(t);
			}
		}

		return transHebdo;
	}

	public static ArrayList<Transaction> getTransactionAvant(Etablissement e, int jourAvant) {
		LocalDate date = LocalDate.now().minusDays(jourAvant);

		ArrayList<Transaction> transHebdo = new ArrayList<Transaction>();

		for (Transaction t : e.getTransactions()) {
			if (Integer.parseInt(t.getHeure()) > dateReference.toEpochDay() - CONST
					&& Integer.parseInt(t.getHeure()) < LocalDate.now().toEpochDay() - CONST) {
				transHebdo.add(t);
			}
		}

		return transHebdo;
	}

	public static int getNbTransactionToday(Etablissement e) {
		return getTransactionToday(e).size();
	}

	public static int getNbTransactionHier(Etablissement e) {
		return getTransactionAvant(e, 1).size();
	}

	public static float getVentesBrutesToday(Etablissement e) {
		ArrayList<Transaction> listTr = getTransactionToday(e);
		float total = 0;

		for (Transaction tr : listTr) {
			total += tr.getSousTotal();
		}

		return total;
	}

	public static float getVentesBrutesHier(Etablissement e) {
		ArrayList<Transaction> listTr = getTransactionAvant(e, 1);
		float total = 0;

		for (Transaction tr : listTr) {
			total += tr.getSousTotal();
		}

		return total;
	}

	public static float getProfitToday(Etablissement e) {
		ArrayList<Transaction> listTr = getTransactionToday(e);
		float profit = 0;

		for (Transaction tr : listTr) {
			for (LigneFacture ligneFac : tr.ligneFactureArray) {
				profit += (ligneFac.getProduit().getPrix() - ligneFac.getProduit().getCoutant())
						* ligneFac.getQuantite();
			}
		}
		return profit;
	}

	public static float getProfitHier(Etablissement e) {
		ArrayList<Transaction> listTr = getTransactionAvant(e, 1);
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
