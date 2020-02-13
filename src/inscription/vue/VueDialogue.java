package inscription.vue;



import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 * Classe permettant de créer des dialogues dans différentes situations
 * 
 * @author Bank-era Corp.
 */
public class VueDialogue {
	
	/**
	 * Méthode statique permettant d'afficher un message de confirmation avant de
	 * quitter
	 */
	public static void quitterDialogue() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation avant de quitter");
		alert.setHeaderText("Êtes-vous certain de vouloir quiter?");
		alert.setContentText("Séletionnez votre choix.");
		ButtonType buttonTypeOui = new ButtonType("Oui");
		ButtonType buttonTypeNon = new ButtonType("Non", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);
		Optional<ButtonType> resultat = alert.showAndWait();

		if (resultat.get() == buttonTypeOui) {
			Platform.exit();
		}
	}

	
	/**
	 * Méthode permettant d'afficher une boîte d'erreur signifiant qu'il y a une erreur lors de la création d'un client
	 * @param meaasage - Le message d'erreur
	 */
	public static void erreurCreationDialogue(String message) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur lors de la cration d'un compte ");
		alert.setHeaderText(message);
		alert.showAndWait();
	
	}

}
