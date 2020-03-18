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

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant que le compte
	 * est bel et bien créé
	 */
	public static void compteCree() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Établissement créé avec succès!");
		alert.setContentText(
				"L'établissement a été ajouté avec succès à la base de données et un email a été envoyé au gérant!");

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}

}
