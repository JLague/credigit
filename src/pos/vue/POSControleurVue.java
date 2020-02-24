package pos.vue;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
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
	private static final String LOGIN = "POS.fxml";
	private static final String MAIN_VIEW = "MainPOS.fxml";

	private POSControleur ctrl;
	private VBox rootVBox;
	private BorderPane rootBP;
	private Scene scene;

	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	@FXML
	private Button connectBtn;
	@FXML
	private Button createAccountBtn;
	@FXML
	private Button produitBtn;
	@FXML
	private Button clavierBtn;
	@FXML
	private TextField searchBar;
	@FXML
	private VBox middlePane;
	@FXML
	private TableView<LigneFacture> factureTable;
	@FXML
	private Label sousTotalLbl;
	@FXML
	private Label taxesLbl;
	@FXML
	private Label totalLbl;

	private BorderPane gridProduit;

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
		chargerTableView();
		chargerGridProduit();

		middlePane.getChildren().add(gridProduit);
		middlePane.setAlignment(Pos.TOP_CENTER);
	}

	/**
	 * Méthode permettant de charger le TableView contenant la facture
	 */
	@SuppressWarnings("unchecked")
	private void chargerTableView() {
		factureTable.getStyleClass().add("table-view");
		factureTable.setEditable(false);

		TableColumn<LigneFacture, String> column1 = new TableColumn<>("Produit(s)");
		column1.setCellValueFactory(new PropertyValueFactory<>("nom"));
		column1.getStyleClass().add("align-left");

		TableColumn<LigneFacture, Float> column2 = new TableColumn<>("Quantité");
		column2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		column2.getStyleClass().add("align-center");

		TableColumn<LigneFacture, String> column3 = new TableColumn<>("Prix");
		column3.setCellValueFactory(new PropertyValueFactory<>("prixString"));
		column3.getStyleClass().add("align-right");

		createNewTransaction();

		factureTable.getColumns().addAll(column1, column2, column3);
		factureTable.setPlaceholder(new Label(""));
	}

	/**
	 * Méthode permettant de charger la grid de Produits qui est affichée lorsqu'on
	 * pèse sur le boutons Produit
	 */
	private void chargerGridProduit() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("GridProduits.fxml"));
		loader.setController(this);

		try {
			gridProduit = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
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

		clavierButtons[1][3].setMinWidth(200);
		clavierButtons[0][3].setMinWidth(200);
		clavierButtons[3][3].setMinWidth(200);

		VBox.setMargin(clavierText, new Insets(50, 0, 50, 0));
		clavierBox.getChildren().addAll(clavierText, clavierGrid);
	}

	@FXML
	private void produitHandler(ActionEvent event) {
		middlePane.getChildren().clear();
		middlePane.getChildren().add(gridProduit);
	}

	@FXML
	private void clavierHandler(ActionEvent event) {
		middlePane.getChildren().clear();
		middlePane.getChildren().add(clavierBox);
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

	@FXML
	private void enleverSelection(ActionEvent event) {
		LigneFacture temp = this.factureTable.getSelectionModel().getSelectedItem();
		
		if(temp != null)
		{
			ctrl.enleverProduit(temp.getProduit());
		}
		
	}

	@FXML
	private void annuler(ActionEvent event) {
		this.createNewTransaction();
	}

	/**
	 * Méthode interne permettant de créer une nouvelle transaction et d'associer
	 * les Labels de prix à celle-ci.
	 */
	private void createNewTransaction() {
		factureTable.setItems(ctrl.getLignesFacture());

		List<StringProperty> properties = ctrl.getPrixProperties();
		
		sousTotalLbl.textProperty().bind(properties.get(0));
		taxesLbl.textProperty().bind(properties.get(1));
		totalLbl.textProperty().bind(properties.get(2));

		Produit test1 = new Produit(1, "test1", 10, 10, "SiFang", "Une description");
		ctrl.ajouterProduitATransaction(test1);
	}

}
