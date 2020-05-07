package pos.application;

import java.util.ArrayList;
import java.util.List;

import commun.Etablissement;
import commun.Produit;
import commun.Transaction;
import pos.modele.Analyse;

public class TBControleur {
	
	private Analyse modele;
	
	public TBControleur() {
		modele = new Analyse();
	}

	public ArrayList<Transaction> getTransactionToday(Etablissement etablissement) {
		return modele.getTransactionToday(etablissement);
	}

	public ArrayList<Transaction> getTransactionAvant(Etablissement etablissement, int i) {
		return modele.getTransactionAvant(etablissement, i);
	}

	public float getVentesBrutesToday(Etablissement etablissement) {
		return modele.getVentesBrutesToday(etablissement);
	}

	public float getVentesBrutesHier(Etablissement etablissement) {
		return modele.getVentesBrutesHier(etablissement);
	}

	public float getProfitToday(Etablissement etablissement) {
		return modele.getProfitToday(etablissement);
	}

	public double getProfitHier(Etablissement etablissement) {
		return modele.getProfitHier(etablissement);
	}

	public int getNbTransactionToday(Etablissement etablissement) {
		return modele.getNbTransactionToday(etablissement);
	}

	public int getNbTransactionHier(Etablissement etablissement) {
		return modele.getNbTransactionHier(etablissement);
	}

}
