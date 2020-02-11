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
import pos.ctrl.POSControleur;

public class LoginPOSControleurVue implements IPOSControleurVue {

	/**
	 * Contrôleur de l'application
	 */
	private POSControleur ctrl;
	
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

	// TODO Enlever?
	/**
	 * Boutons servant à se créer un compte
	 */
	@FXML
	private Button createAccountBtn;

	/**
	 * Constructeur prenant un contrôleur
	 * 
	 * @throws IOException
	 */
	public LoginPOSControleurVue(POSControleur ctrl) throws IOException {

		this.ctrl = ctrl;
		
		// Load first view
		FXMLLoader loader = new FXMLLoader(getClass().getResource("POS.fxml"));
		loader.setController(this);
		root = loader.load();
		scene = new Scene(root);

		appliquerCSSVue1();
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
		
		connectBtn.setOnMouseClicked((me) -> ctrl.launchMainView());
	}

	/**
	 * Méthode interne servant à appliquer les différents styles pour la première
	 * vue à partir de la feuille de style
	 */
	private void appliquerCSSVue1() {
		scene.getStylesheets().add("styles/POS.css");
		root.getStyleClass().add("root-1");

		connectBtn.getStyleClass().add("buttons-1");
		createAccountBtn.getStyleClass().add("buttons-1");

		userField.getStyleClass().add("fields-1");
		passField.getStyleClass().add("fields-1");
	}

	/**
	 * Méthode interne servant à enlever les styles de la première vue afin
	 * d'ajouter ceux de la deuxième
	 */
	private void enleverCSS() {
		root.getStyleClass().clear();
		connectBtn.getStyleClass().clear();
		createAccountBtn.getStyleClass().clear();
		userField.getStyleClass().clear();
		passField.getStyleClass().clear();
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
