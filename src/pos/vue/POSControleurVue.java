package pos.vue;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class POSControleurVue implements IPOSControleurVue {

	/**
	 * VBox utilisé comme root de la vue
	 */
	private VBox root;

	/**
	 * Scène de l'application du POS
	 */
	private Scene scene;

	/**
	 * Image du logo pour le POS
	 */
	@FXML
	private ImageView logoPOS;
	
	/**
	 * Champ de texte pour le nom d'utilisateur
	 */
	@FXML
	private TextField userField;
	
	/**
	 * Champ de texte pour le mot de passe
	 */
	@FXML
	private PasswordField passField;
	
	/**
	 * Bouton servant à se connecter
	 */
	@FXML
	private Button connectBtn;
	
	
	//TODO Enlever?
	/**
	 * Boutons servant à se créer un compte
	 */
	@FXML
	private Button createAccountBtn;

	/**
	 * Constructeur par défaut qui instantie la vue
	 * 
	 * @throws IOException
	 */
	public POSControleurVue() throws IOException {

		// Load first view
		FXMLLoader loader = new FXMLLoader(getClass().getResource("POS.fxml"));
		loader.setController(this);
		root = loader.load();
		scene = new Scene(root);
		
		appliquerCSS();
		setBestSizes();
	}

	private void setBestSizes() {
		// User field
		userField.setMaxWidth(400);
		userField.setMinHeight(30);
		
		// Password field
		passField.setMaxWidth(400);
		passField.setMinHeight(30);
		
		// Buttons
		connectBtn.setMinSize(300, 50);
		createAccountBtn.setMinSize(300, 50);
	}

	/**
	 * Méthode interne servant à appliquer les différents styles à partir de la
	 * feuille de style
	 */
	private void appliquerCSS() {
		scene.getStylesheets().add("styles/POS.css");
		root.getStyleClass().add("view1");
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
