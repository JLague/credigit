package pos.vue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pos.ctrl.POSControleur;
import pos.modele.ExceptionProduitEtablissement;
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
	@FXML
	private GridPane gridProduits;
	@FXML
	private ScrollPane produitsScroll;

	private BorderPane borderPaneProduit;

	@FXML
	private Label skuLbl;

	@FXML
	private Label nomLbl;

	@FXML
	private Label descLbl;

	@FXML
	private Label prixLbl;

	@FXML
	private Label fournisseurLbl;

	private Produit produitCourant;

	private GridPane clavierGrid;
	private Button[][] clavierButtons;
	private VBox clavierBox;
	private TextField clavierText;
	TableView rechercheResultat = null;

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

		middlePane.getChildren().add(borderPaneProduit);
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
			borderPaneProduit = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		produitsScroll.setFitToWidth(true);
		produitsScroll.setFitToHeight(true);
		gridProduits.setHgap(110);
		gridProduits.setVgap(38);

		populerGridProduit();
	}

	/**
	 * Popule le grid de produits à l'aide de la liste des produits
	 */
	private void populerGridProduit() {
		List<Produit> listeProduits = ctrl.getListeProduits();
		int cpt = 0;

		for (Produit p : listeProduits) {
			VBox vbox = creerProduitWrapper(p, cpt % 3, cpt / 3);
			vbox.getStyleClass().add("vboxProduit");
			gridProduits.getChildren().add(vbox);
			cpt++;
		}
	}

	/**
	 * Crée un wrapper autour du produit avant de l'ajouter au GridPane
	 * 
	 * @param p le produit
	 * @param x la position en x dans le GridPane
	 * @param y la position en y dans le GridPane
	 * @return le HBox wrapper
	 */
	private VBox creerProduitWrapper(Produit p, int x, int y) {
		// TODO ajouter les images des produits
		VBox vbox = new VBox();
		ImageView image = new ImageView(new Image(getClass().getResource("/images/produit_img.png").toExternalForm()));
		image.setFitHeight(234);
		image.setFitWidth(234);

		Label nom = new Label(p.getNom());
		Label prix = new Label("$" + p.getPrix());

		VBox.setMargin(nom, new Insets(8, 0, 0, 8));
		VBox.setMargin(prix, new Insets(2, 0, 0, 8));
		vbox.getChildren().addAll(image, nom, prix);
		vbox.setOnMouseClicked((me) -> addProduitATransaction(me));

		GridPane.setConstraints(vbox, x, y);
		return vbox;
	}

	/**
	 * Méthode appelée par les wrappers des produits dans le grid. Elle retrouve le
	 * produit à l'aide de son nom et la charge dans l'interface
	 * 
	 * @param me le mouse event
	 */
	private void loadProduit(MouseEvent me) {

		// Change info
		skuLbl.setText(Long.toString(produitCourant.getSku()));
		nomLbl.setText(produitCourant.getNom());
		descLbl.setText(produitCourant.getDescription());

		// Local currency
		NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));
		prixLbl.setText(cf.format(produitCourant.getPrix()));

		fournisseurLbl.setText(produitCourant.getFournisseur());
	}

	/**
	 * Ajoute le produit chargé à la facture
	 * 
	 * @param me le mouse event
	 */
	@FXML
	private void addProduitATransaction(MouseEvent me) {
		VBox source = (VBox) me.getSource();
		Label l = (Label) source.getChildren().get(1);
		produitCourant = ctrl.getProduitFromString(l.getText());

		ctrl.ajouterProduitATransaction(produitCourant);
		factureTable.refresh();
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

					clavierButtons[i][j].setOnMouseClicked(e -> {
						Button butonSource = (Button) e.getSource();
						String s = butonSource.getText().trim();

						if (s == "00" | Character.isDigit(s.charAt(0)) | s == ".") {
							clavierText.setText(clavierText.getText().trim() + s);
						} else if (s == "DEL") {
							clavierText.setText(clavierText.getText().substring(0, clavierText.getText().length() - 1));
						} else if (s == "C") {
							clavierText.clear();
						} else if (s == "ENTER") {
							search();
						}
					});
				}
			}

		}

		clavierButtons[1][3].setMinWidth(200);
		clavierButtons[0][3].setMinWidth(200);
		clavierButtons[3][3].setMinWidth(200);

		VBox.setMargin(clavierText, new Insets(50, 0, 50, 0));
		clavierBox.getChildren().addAll(clavierText, clavierGrid);

		creerTableViewRecherche();

		clavierBox.getChildren().add(rechercheResultat);

		rechercheResultat.getStyleClass().add("table-view");
		rechercheResultat.setEditable(false);
	}

	private void creerTableViewRecherche() {
		rechercheResultat = new TableView();

		TableColumn<String, Produit> column1 = new TableColumn<>("Sku");
		column1.setCellValueFactory(new PropertyValueFactory<>("sku"));

		TableColumn<String, Produit> column2 = new TableColumn<>("Nom");
		column2.setCellValueFactory(new PropertyValueFactory<>("nom"));

		TableColumn<String, Produit> column3 = new TableColumn<>("Prix");
		column2.setCellValueFactory(new PropertyValueFactory<>("prix"));

		TableColumn<String, Produit> column4 = new TableColumn<>("Fournisseur");
		column2.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));

		TableColumn<String, Produit> column5 = new TableColumn<>("Description");
		column2.setCellValueFactory(new PropertyValueFactory<>("description"));

		rechercheResultat.getColumns().add(column1);
		rechercheResultat.getColumns().add(column2);
		rechercheResultat.getColumns().add(column3);
		rechercheResultat.getColumns().add(column4);
		rechercheResultat.getColumns().add(column5);
	}

	@FXML
	private void produitHandler(ActionEvent event) {
		middlePane.getChildren().clear();
		middlePane.getChildren().add(borderPaneProduit);
	}

	@FXML
	private void clavierHandler(ActionEvent event) {
		middlePane.getChildren().clear();
		middlePane.getChildren().add(clavierBox);
	}

	@SuppressWarnings("unchecked")
	private void search() {
		rechercheResultat.getItems().clear();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			stream.write(new byte[2]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rechercheResultat.getItems()
					.add(new Produit(1800, "produitTest", 10, 20, "KE Inc", 0, "Da best produit", stream));
		} catch (ExceptionProduitEtablissement e) {
			e.printStackTrace();
		}

		rechercheResultat.refresh();
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

		if (temp != null) {
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
		ctrl.creerNouvelleTransaction();
		factureTable.setItems(ctrl.getLignesFacture());

		List<StringProperty> properties = ctrl.getPrixProperties();

		sousTotalLbl.textProperty().bind(properties.get(0));
		taxesLbl.textProperty().bind(properties.get(1));
		totalLbl.textProperty().bind(properties.get(2));
	}

}
