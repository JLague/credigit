package pos.vue;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pos.ctrl.POSControleur;

public class POSControleurVue implements IPOSControleurVue {

	/**
	 * Contrôleur de l'application
	 */
	private POSControleur ctrl;

	/**
	 * VBox utilisé comme root de la première vue
	 */
	private VBox rootVBox;

	/**
	 * BorderPane utilisé comme root de la deuxième vue
	 */
	private BorderPane rootBP;

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
	 * Bouton servant à afficher les produits
	 */
	@FXML
	private Button produitBtn;
	
	/**
	 * Bouton servant à afficher le clavier
	 */
	@FXML
	private Button clavierBtn;
	
	/**
	 * Barre de recherche
	 */
	@FXML
	private TextField searchBar;
	
	/**
	 * Constructeur prenant un contrôleur et qui charge la première vue du POS
	 * 
	 * @param ctrl le contrôleur de l'application
	 */
	public POSControleurVue(POSControleur ctrl) {

		this.ctrl = ctrl;

		// Load first view
		FXMLLoader loader = new FXMLLoader(getClass().getResource("POS.fxml"));
		loader.setController(this);

		try {
			rootVBox = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene = new Scene(rootVBox);

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

		connectBtn.setOnMouseClicked((me) -> {
			//TODO ajouter connexion et faire dans une méthode séparée
			changerScene();
			ctrl.chargerVuePOS();
		});
	}

	/**
	 * Méthode qui permet de charger le deuxième fichier FXML
	 */
	private void changerScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPOS.fxml"));
		loader.setController(this);
		try {
			rootBP = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(rootBP);
	}



	@Override
	public Scene getScene() {
		return this.scene;
	}

}
