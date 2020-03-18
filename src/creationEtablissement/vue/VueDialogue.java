package creationEtablissement.vue;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;

/**
 * Classe permettant de créer des dialogues dans différentes situations
 * 
 * @author Bank-era Corp.
 */
public class VueDialogue {

	private static final String CSS_URL = "/styles/pos/Dialogue.css";

	/**
	 * Méthode permettant d'afficher une boîte d'erreur signifiant qu'il y a une
	 * erreur lors de la création d'un client
	 * 
	 * @param message - Le message d'erreur
	 */
	public static void erreurCreationDialogue(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Erreur lors de la création d'un compte ");
		alert.setContentText(message);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();

	}

}
