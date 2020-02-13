package pos.vue;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pos.ctrl.POSControleur;

public class POSControleurVue implements IPOSControleurVue {

	private static final String[][] CLAVIER = { { "7", "8", "9", "DEL" }, { "4", "5", "6", "ENTER" },
			{ "1", "2", "3"}, { "C", "0", ".", "00" } };

	/**
	 * Nom du fichier pour le login
	 */
	private static final String LOGIN = "POS.fxml";

	/**
	 * Nom du fichier pour la vue principale
	 */
	private static final String MAIN_VIEW = "MainPOS.fxml";

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
	 * Pane au centre de la vue
	 */
	@FXML
	private Pane middlePane;

	private GridPane clavierGrid;

	private Button[][] clavierButtons;

	private VBox clavierBox;

	private TextField clavierText;

	/**
	 * Constructeur prenant un contrôleur et qui charge la première vue du POS
	 * 
	 * @param ctrl le contrôleur de l'application
	 */
	public POSControleurVue(POSControleur ctrl) {

		this.ctrl = ctrl;

		creerScene(LOGIN, rootVBox);

		connectBtn.setOnMouseClicked((me) -> ouvrirVuePrincipale());
	}

	/**
	 * Méthode servant à ouvrir la vue principale
	 */
	private void ouvrirVuePrincipale() {
		// Création et chargement de la scène du POS
		creerScene(MAIN_VIEW, rootBP);
		ctrl.chargerScene(this.scene, "POS");

		// Chargement de la vue
		chargerClavier();

		// TODO mettre la vue des produits lors du chargement
		middlePane.getChildren().add(clavierBox);
	}

	/**
	 * Méthode permettant de charger le clavier dans clavierBox à l'ouverture de la
	 * vue
	 */
	private void chargerClavier() {
		clavierBox = new VBox(20);

		clavierText = new TextField();
		clavierText.setEditable(false);
		clavierText.getStyleClass().add("clavier-text");
		clavierText.setAlignment(Pos.CENTER_RIGHT);

		clavierGrid = new GridPane();
		clavierButtons = new Button[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!(i == 2 && j == 3)) {
					GridPane.setConstraints(clavierButtons[i][j] = new Button(CLAVIER[i][j]), j, i);
					clavierGrid.getChildren().add(clavierButtons[i][j]);
					clavierButtons[i][j].setMinSize(100, 100);
					clavierButtons[i][j].getStyleClass().add("clavier-buttons");
				}
			}
		}
		clavierBox.getChildren().addAll(clavierText, clavierGrid);
	}

	/**
	 * Méthode qui permet de charger le deuxième fichier FXML
	 */
	private void creerScene(String url, Pane root) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
		loader.setController(this);

		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene = new Scene(root);
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
