package pos.modele;

import java.util.ArrayList;

import commun.Etablissement;
import commun.Transaction;

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

}
