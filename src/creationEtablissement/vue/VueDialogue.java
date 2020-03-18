package creationEtablissement.vue;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

/**
 * Classe permettant de créer des dialogues dans différentes situations
 * 
 * @author Bank-era Corp.
 */
public class VueDialogue {

	private static final String CSS_URL = "/styles/etablissement/Dialogue.css";

	/**
	 * Méthode permettant d'afficher une boîte d'erreur signifiant qu'il y a une
	 * erreur lors de la création d'un établissement
	 * 
	 * @param message - Le message d'erreur
	 */
	public static void erreurCreationDialogue(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Erreur lors de la création de l'établissement");
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
		Label label = new Label("L'établissement a été ajouté avec succès à la base de données\n et un email a été envoyé au gérant!");
		label.setWrapText(true);
		alert.getDialogPane().setContent(label);

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}

}
