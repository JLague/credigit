package pos.vue;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pos.ctrl.POSControleur;
import pos.modele.LigneFacture;
import pos.modele.Produit;

public class POSControleurVue implements IPOSControleurVue {

	private static final String[][] CLAVIER = { { "7", "8", "9", "DEL" }, { "4", "5", "6", "ENTER" }, { "1", "2", "3" },
			{ "C", "0", ".", "00" } };

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
	private VBox middlePane;

	@FXML
	private TableView<LigneFacture> factureTable;

	private GridPane clavierGrid;

	private Button[][] clavierButtons;

	private VBox clavierBox;

	private TextField clavierText;

	private ObservableList<LigneFacture> facture;

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
		chargerTableView();

		// TODO mettre la vue des produits lors du chargement
		middlePane.getChildren().add(clavierBox);
		middlePane.setAlignment(Pos.TOP_CENTER);
	}

	@SuppressWarnings("unchecked")
	private void chargerTableView() {
		facture = FXCollections.observableArrayList();

		factureTable.getStyleClass().add("table-view");
		factureTable.setEditable(false);

		TableColumn<LigneFacture, String> column1 = new TableColumn<>("Produit(s)");
		column1.setCellValueFactory(new PropertyValueFactory<>("nom"));
		column1.getStyleClass().add("align-left");

		TableColumn<LigneFacture, Float> column2 = new TableColumn<>("Quantité");
		column2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		column2.getStyleClass().add("align-center");

		TableColumn<LigneFacture, String> column3 = new TableColumn<>("Prix");
		column3.setCellValueFactory(new PropertyValueFactory<>("prix"));
		column3.getStyleClass().add("align-right");

		factureTable.getColumns().addAll(column1, column2, column3);
		factureTable.setPlaceholder(new Label(""));
		factureTable.setItems(facture);

		Produit test1 = new Produit(1, "test1", 10, 10, "SiFang", "Une description");
		Produit test2 = new Produit(2, "test2", 10, 10, "SiFang", "Une autre description");

		ajouterProduitAFacture(test1, 10);
		ajouterProduitAFacture(test2, 5);
		ajouterProduitAFacture(test1, 5);
	}
	
	/**
	 * Méthode permettant d'ajouter une certaine quantité d'un certain produit à la
	 * facture
	 * 
	 * @param produit le produit à ajouter
	 * @param quantite la quantité à ajouter
	 */
	private void ajouterProduitAFacture(Produit produit, int quantite) {
		boolean estDansFacture = false;
		for (LigneFacture item : facture) {
			if (item.getProduit().equals(produit)) {
				estDansFacture = true;
				item.setQuantite(item.getQuantite() + quantite);
			}
		}

		if (!estDansFacture) {
			facture.add(new LigneFacture(produit, quantite));
		}
	}

	/**
	 * Méthode permettant de charger le clavier dans clavierBox à l'ouverture de la
	 * vue
	 */
	private void chargerClavier() {
		clavierBox = new VBox();
		clavierBox.setAlignment(Pos.CENTER);

		clavierText = new TextField();
		clavierText.setMinHeight(50);
		clavierText.setMaxWidth(500);
		clavierText.setEditable(false);
		clavierText.getStyleClass().add("clavier-text");
		clavierText.setAlignment(Pos.CENTER_RIGHT);

		clavierGrid = new GridPane();
		clavierGrid.setAlignment(Pos.CENTER);

		clavierButtons = new Button[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!(i == 2 && j == 3)) {
					GridPane.setConstraints(clavierButtons[i][j] = new Button(CLAVIER[i][j]), j, i);
					clavierGrid.getChildren().add(clavierButtons[i][j]);
					clavierButtons[i][j].setMinSize(150, 150);
					clavierButtons[i][j].getStyleClass().add("clavier-buttons");
				}
			}
		}

		VBox.setMargin(clavierText, new Insets(50, 0, 50, 0));
		clavierBox.getChildren().addAll(clavierText, clavierGrid);
	}

	/**
	 * Méthode qui permet de charger un fichier FXML
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
