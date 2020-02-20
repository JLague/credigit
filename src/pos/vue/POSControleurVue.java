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
import pos.modele.Transaction;

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

	private GridPane clavierGrid;
	private Button[][] clavierButtons;
	private VBox clavierBox;
	private TextField clavierText;

	private Transaction transaction;
	private ObservableList<LigneFacture> lignesFacture;

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
		lignesFacture = FXCollections.observableArrayList();

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
		factureTable.setItems(lignesFacture);

		Produit test1 = new Produit(1, "test1", 10, 10, "SiFang", "Une description");
		Produit test2 = new Produit(2, "test2", 10, 10, "SiFang", "Une autre description");
		
		transaction = new Transaction();
		transaction.addProduit(test1);
		transaction.addProduit(test2);
	}
	
	private void updatePrix()
	{
		
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
