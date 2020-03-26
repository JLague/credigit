package pos.vue;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

/**
 * Classe permettant de créer des dialogues dans différentes situations
 * 
 * @author Bank-era Corp.
 */
public class VueDialogue extends Alert {

	private static final String CSS_URL = "/styles/pos/Dialogue.css";

	public VueDialogue(String titre, String message, AlertType type) {
		super(type);
		this.setHeaderText(titre);
		this.setContentText(message);
		DialogPane dialogPane = this.getDialogPane();
		dialogPane.getStylesheets().add(VueDialogue.class.getResource(CSS_URL).toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		this.showAndWait();
	}

	/**
	 * Méthode permettant d'afficher une boîte d'erreur signifiant qu'il y a une
	 * erreur lors de la création d'un client
	 * 
	 * @param message - Le message d'erreur
	 */
	public static void erreurCreationDialogue(String message) {
		new VueDialogue("Erreur lors de la création d'un compte ", message, AlertType.ERROR);
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant que le produit
	 * est bel et bien créé
	 */
	public static void produitCree() {
		new VueDialogue("Le produit a été créé avec succès!", "Le produit a été créé et ajouté à la base de données.",
				AlertType.INFORMATION);
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant que le compte
	 * est bel et bien créé
	 */
	public static void compteCreeSansCourriel() {
		new VueDialogue("Votre compte a été créé avec succès!", "Nous sommes heureux de vous compter parmis nous!",
				AlertType.INFORMATION);
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant qu'une erreur
	 * s'est produite lors de la connexion
	 */
	public static void erreurConnexionDialogue(String message) {
		new VueDialogue("Erreur lors de la connexion", message, AlertType.ERROR);
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant qu'une erreur
	 * s'est produite lors de la création ou la modification d'un produit
	 */
	public static void erreurProduit(String message) {
		new VueDialogue("Erreur lors de la création ou de la modification du produit", message, AlertType.ERROR);
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant qu'un produit
	 * est bel et bien effacé
	 */
	public static void produitEfface() {
		new VueDialogue("Le produit a été effacé", "Le produit a été effacé avec succès.", AlertType.INFORMATION);
	}

	/**
	 * Méthode permettant d'afficher une boîte de dialogue signifiant qu'un produit
	 * est bel et bien modifié
	 */
	public static void produitModifie() {
		new VueDialogue("Le produit a été modifié", "Le produit a été modifié avec succès.", AlertType.INFORMATION);
	}
}
