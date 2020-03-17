package inscription.vue;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

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
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource("styles/Dialogue.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		Optional<ButtonType> resultat = alert.showAndWait();

		if (resultat.get() == buttonTypeOui) {
			Platform.exit();
		}
	}

	/**
	 * Méthode permettant d'afficher une boîte d'erreur signifiant qu'il y a une
	 * erreur lors de la création d'un client
	 * 
	 * @param message - Le message d'erreur
	 */
	public static void erreurCreationDialogue(String message) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur lors de la création d'un compte ");
		alert.setHeaderText(message);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets()
				.add(VueDialogue.class.getClassLoader().getResource("styles/Dialogue.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();

	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant que le produit
	 * est bel et bien créé
	 */
	public static void produitCree() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Le produit a été créé avec succès!");
		alert.setContentText("Le produit a été créé et ajouté à la base de données.");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource("styles/Dialogue.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant que le compte
	 * est bel et bien créé
	 */
	public static void compteCree() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Votre compte a été créé avec succès!");
		alert.setContentText(
				"Nous sommes heureux de vous compter parmis nous! Un email vous sera envoyé sous peu confirmant votre adhésion de façon officielle.");

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource("styles/Dialogue.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant que le compte
	 * est bel et bien créé
	 */
	public static void compteCreeSansCourriel() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Votre compte a été créé avec succès!");
		alert.setContentText("Nous sommes heureux de vous compter parmis nous!");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource("styles/Dialogue.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant que le compte
	 * a bel et bien été supprimé
	 */
	public static void compteSupprime() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Votre compte a été supprimé avec succès!");
		alert.setContentText(
				"Nous sommes déçus de vous voir partir :( ... N'hésitez pas à revenir vous créer un compte dans le futur!");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource("styles/Dialogue.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}

}
