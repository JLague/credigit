package pos.vue;

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
	 * Méthode permettant d'afficher une boîte de dialogue signifiant que le produit
	 * est bel et bien créé
	 */
	public static void produitCree() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Le produit a été créé avec succès!");
		alert.setContentText("Le produit a été créé et ajouté à la base de données.");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
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
		dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}
	
	public static void erreurConnexionDialogue(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Erreur lors de la connexion");
		alert.setContentText(message);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}
	
	public static void erreurProduit(String message) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erreur lors de la création ou de la modification du produit");
			alert.setContentText(message);
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			alert.showAndWait();
	}
	
	public static void produitEfface() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Le produit a été effacé");
		alert.setContentText("Le produit a été effacé avec succès.");
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}
}
