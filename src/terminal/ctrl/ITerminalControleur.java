package terminal.ctrl;

import java.math.BigInteger;
import java.util.List;

import commun.Transaction;
import encryption.CleRSA;
import javafx.scene.Scene;

/**
 * Interface définissant les comportements obligatoires de TerminalControleur
 * 
 * @author Bank-era Corp.
 *
 */
public interface ITerminalControleur {

	/**
	 * Update la transaction du TableauDeBord et update la vue avec la nouvelle
	 * transaction reçue
	 * 
	 * @param newTransaction
	 */
	public void updateTransaction(Transaction newTransaction);

	/**
	 * Getter de la scène
	 * 
	 * @return la scène
	 */
	public Scene getScene();

	/**
	 * Méthode permettant d'effectuer la transaction
	 * 
	 * @param empreintes - La liste des empreintes dans la base de données
	 */
	public void effectuerTransaction(List<byte[]> empreintes);
}
