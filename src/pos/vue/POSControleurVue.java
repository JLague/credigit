package pos.vue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pos.ctrl.POSControleur;
import pos.exception.ExceptionCreationCompte;
import pos.exception.ExceptionProduitEtablissement;
import pos.modele.DataProduit;
import pos.modele.DataVendeur;
import pos.modele.LigneFacture;
import pos.modele.Produit;
import pos.utils.ImageUtil;

/**
 * Classe permettant de contrôler les vues du POS
 * 
 * @author Bank-era Corp.
 */
public class POSControleurVue implements IPOSControleurVue {

	// Définition des constantes reliées à l'interface du POS
	private static final String[][] CLAVIER = { { "7", "8", "9", "DEL" }, { "4", "5", "6", "ENTER" }, { "1", "2", "3" },
			{ "C", "0", ".", "00" } };
	private static final String LOGIN = "POS.fxml";
	private static final String MAIN_VIEW = "MainPOS.fxml";
	private static final String VIEW_INSCRIPTION_VENDEUR = "NouveauVendeur.fxml";

	// Conteneurs des fichiers FXML
	private POSControleur ctrl;
	private VBox rootVBox;
	private BorderPane rootBP;

	/**
	 * Scène courante du POS
	 */
	private Scene scene;

	// Déclaration des éléments du login
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	@FXML
	private Button createAccountBtn;
	@FXML
	private TextField nomEtablissementLoginField;

	// Déclaration des éléments du POS
	@FXML
	private Button produitBtn;
	@FXML
	private Label messageBonjour;
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
	private Label skuLbl;
	@FXML
	private Label nomLbl;
	@FXML
	private Label descLbl;
	@FXML
	private Label prixLbl;
	@FXML
	private Label resetLbl;
	@FXML
	private Label fournisseurLbl;
	@FXML
	private Button ajoutProduitBouton;
	@FXML
	private TextField skuProduitTextField;
	@FXML
	private TextField nomProduitTextField;
	@FXML
	private TextField prixProduitTextField;
	@FXML
	private TextField coutantProduitTextField;
	@FXML
	private TextField quantiteProduitTextField;
	@FXML
	private TextField fournisseurProduitTextField;
	@FXML
	private Button creerBouton;
	@FXML
	private TextArea descriptionProduitTextArea;
	@FXML
	private ImageView imageProduitImageView;

	// Déclaration des éléments de la grille de produits
	@FXML
	private GridPane gridProduits;
	@FXML
	private ScrollPane produitsScroll;

	// Déclaration des éléments de la création d'un vendeur
	@FXML
	private TextField prenomVendeurTextField;
	@FXML
	private TextField nomVendeurTextField;
	@FXML
	private TextField usernameVendeurTextField;
	@FXML
	private PasswordField vendeurPasswordField1;
	@FXML
	private PasswordField vendeurPasswordField2;
	@FXML
	private TextField courrielVendeurTextField;
	@FXML
	private TextField nomEtablissementCreationVendeur;

	@FXML
	private Button ajoutBtn;
	@FXML
	private Button modBtn;
	@FXML
	private HBox buttonHBox;
	@FXML
	private Button voirItemBtn;

	// Déclaration des éléments du clavier
	private GridPane clavierGrid;
	private Button[][] clavierButtons;
	private VBox clavierBox;
	private TextField clavierText;

	Button modifier;
	Button retour;

	Button enregistrer;
	Button annuler;
	Button supprimer;
	
	Button ajouter;
	
	List<Produit> produits = new ArrayList<>();

	/**
	 * Pane contenant la grille des produits
	 */
	private Pane paneProduit;

	/**
	 * AnchorPane contenant le formulaire de création de produit
	 */
	private AnchorPane creationProduitPane;

	/**
	 * AnchorPane contenant le formulaire de modification de produit
	 */
	private AnchorPane modificationProduitPane;

	/**
	 * TableView contenant les résultats de la recherche d'un produit
	 */
	private TableView<Produit> rechercheResultat = null;

	/**
	 * Le produit sélectionné
	 */
	private Produit produitCourant;

	/**
	 * Constructeur prenant un contrôleur et qui charge la première vue du POS
	 * 
	 * @param ctrl le contrôleur de l'application
	 */
	public POSControleurVue(POSControleur ctrl) {
		this.ctrl = ctrl;
		ouvrirLoginVendeur();
	}

	/**
	 * Méthode servant à ouvrir la vue où les vendeurs peuvent se connecter
	 */
	private void ouvrirLoginVendeur() {
		creerScene(LOGIN, rootVBox);
		createAccountBtn.setOnMouseClicked((me) -> ouvrirVueInscriptionVendeur());
	}

	/**
	 * Méthode permettant de modifier les informations d'un produit exsitant ou de
	 * le supprimer
	 */
	private void modificationSuppressionProduit() {
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("CreationProduitPOS.fxml"));
		loader1.setController(this);

		try {
			modificationProduitPane = loader1.load();
			middlePane.getChildren().clear();
			middlePane.getChildren().add(modificationProduitPane);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Se charge de l'ouverture de la vue pour la modification des produits
	 */
	@FXML
	private void ouvrirVueModProdHandler() {

		Produit temp = this.rechercheResultat.getSelectionModel().getSelectedItem();

		modificationSuppressionProduit();
		modifier = new Button("Modifier cet item");
		modifier.getStyleClass().add("buttons-1");
		retour = new Button("Annuler");
		retour.getStyleClass().add("buttons-1");
		
		modifier.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				modificationProduit(temp);
			}
		});

		retour.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ouvrirVuePrincipale();
				}
		});
		
		skuProduitTextField.setDisable(true);
		nomProduitTextField.setDisable(true);
		prixProduitTextField.setDisable(true);
		coutantProduitTextField.setDisable(true);
		quantiteProduitTextField.setDisable(true);
		fournisseurProduitTextField.setDisable(true);
		descriptionProduitTextArea.setDisable(true);

		
		DecimalFormat df1 = new DecimalFormat("###.##");
		DecimalFormat df2 = new DecimalFormat("###.###");

		skuProduitTextField.setText(temp.getSku() + "");
		nomProduitTextField.setText(temp.getNom() + "");
		prixProduitTextField.setText(df1.format(temp.getPrix()));
		coutantProduitTextField.setText(df2.format(temp.getCoutant()));
		quantiteProduitTextField.setText(temp.getQuantite() + "");
		fournisseurProduitTextField.setText(temp.getFournisseur() + "");
		descriptionProduitTextArea.setText(temp.getDescription() + "");
		imageProduitImageView.setImage(ImageUtil.convertFromBytes(temp.getImage()));
		
		imageProduitImageView.setFitHeight(247);
		imageProduitImageView.setFitHeight(247);
		
		buttonHBox.getChildren().clear();
		buttonHBox.getChildren().addAll(modifier, retour);
	}

	private void modificationProduit(Produit p) {
		enregistrer = new Button("Enregistrer");
		enregistrer.getStyleClass().add("buttons-1");

		annuler = new Button("Annuler");
		annuler.getStyleClass().add("buttons-1");

		supprimer = new Button("Supprimer");
		supprimer.getStyleClass().add("buttons-1");

		skuProduitTextField.setDisable(false);
		nomProduitTextField.setDisable(false);
		prixProduitTextField.setDisable(false);
		coutantProduitTextField.setDisable(false);
		quantiteProduitTextField.setDisable(false);
		fournisseurProduitTextField.setDisable(false);
		descriptionProduitTextArea.setDisable(false);

		buttonHBox.getChildren().clear();
		buttonHBox.getChildren().addAll(enregistrer, annuler, supprimer);

		enregistrer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Produit temp = new Produit();
					temp.setSku(Integer.parseInt(skuProduitTextField.getText()));
					temp.setNom(nomProduitTextField.getText());
					temp.setPrix(Float.parseFloat(prixProduitTextField.getText()));
					temp.setCoutant(Float.parseFloat(coutantProduitTextField.getText()));
					temp.setQuantite(Integer.parseInt(quantiteProduitTextField.getText()));
					temp.setDescription(descriptionProduitTextArea.getText());
					temp.setImage(ImageUtil.convertToBytes(imageProduitImageView.getImage()));
					temp.setFournisseur(fournisseurProduitTextField.getText());
					ctrl.modifierProduit(p, temp);
					VueDialogue.produitModifie();
					populerGridProduit();
					ouvrirVuePrincipale();
				} catch(ExceptionProduitEtablissement e) {
					VueDialogue.erreurProduit(e.getMessage());
				} catch (Exception e) {
					System.out.println("Could not save the images :(");
				}
			}

		});

		supprimer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (ctrl.getInventaire().remove(p)) {
					ctrl.updateEtablissement();
					VueDialogue.produitEfface();
					populerGridProduit();
					ouvrirVuePrincipale();
				}
				
			}
		});

		annuler.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ouvrirVuePrincipale();
			}
		});
	}

	/**
	 * Méthode servant à ouvrir la vue utilisé pour ajouter des vendeurs.
	 */
	private void ouvrirVueInscriptionVendeur() {
		creerScene(VIEW_INSCRIPTION_VENDEUR, rootVBox);
		ctrl.chargerScene(this.scene, "Inscription vendeur", false);
	}

	@FXML
	void onEnterPressed(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			connect(null);
		}
	}

	@FXML
	private void connect(ActionEvent ae) {
		try {
			ctrl.connexion(userField.getText(), passField.getText(), nomEtablissementLoginField.getText());
			ouvrirVuePrincipale();
		} catch (ExceptionProduitEtablissement e) {
			VueDialogue.erreurConnexionDialogue(e.getMessage());
		}

	}

	/**
	 * Méthode servant à ouvrir la vue principale
	 */
	private void ouvrirVuePrincipale() {
		// Création et chargement de la scène du POS
		creerScene(MAIN_VIEW, rootBP);
		ctrl.chargerScene(this.scene, "POS", true);

		messageBonjour.setText("Bonjour, " + ctrl.getNomVendeur());

		// Chargement de la vue
		ajoutBtn.setDisable(true);
		chargerClavier();
		chargerTableView();
		chargerGridProduit();
		chargerAjoutProduit();

		// Vue par défaut au lancement de l'application
		middlePane.getChildren().add(paneProduit);
		middlePane.setAlignment(Pos.TOP_CENTER);

		// Fait en sorte que les Label ne dépassent pas les limites de l'écran
		AnchorPane.setRightAnchor(sousTotalLbl, 0.0);
		AnchorPane.setRightAnchor(taxesLbl, 0.0);
		AnchorPane.setRightAnchor(totalLbl, 0.0);

	}

	/**
	 * Si les données sont valides les entre dans la base de données
	 * 
	 * @param event
	 */
	@FXML
	private void inscriptionVendeurHandler(ActionEvent event) {
		// Peuplement de l'objet transitoire
		DataVendeur data = new DataVendeur();
		data.setPrenom(prenomVendeurTextField.getText());
		data.setNom(nomVendeurTextField.getText());
		data.setUsername(usernameVendeurTextField.getText());
		data.setCourriel(courrielVendeurTextField.getText());

		try {
			// Si les mots de passe sont différents
			if (vendeurPasswordField1.getText().equals(vendeurPasswordField2.getText())) {
				data.setPassword(vendeurPasswordField1.getText());
			} else
				throw new ExceptionCreationCompte("Les deux mots de passes entrés sont différents");

			// On cherche le numéro de l'établissement
			long numero = ctrl.getNumeroEtablissement(nomEtablissementCreationVendeur.getText());
			String numeroEtablissement = ouvrirDialogueNip();

			// On vérifie si les numéros sont pareils
			if (numeroEtablissement.equals(Long.toString(numero))) {
				ctrl.creerVendeur(data);
			} else {
				throw new ExceptionCreationCompte("Le code administrateur n'est pas valide");
			}

		} catch (ExceptionCreationCompte e) {
			VueDialogue.erreurCreationDialogue(e.getMessageAffichage());
			data = null;
		} catch (ExceptionProduitEtablissement e) {
			VueDialogue.erreurCreationDialogue(e.getMessageAffichage());
			data = null;
		}

		if (data != null) {
			VueDialogue.compteCreeSansCourriel();
			annulerHandler(event);
		}
	}

	/**
	 * Ouvre le dialogue qui demande à l'utilisateur le nip
	 * 
	 * @return
	 */
	private String ouvrirDialogueNip() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NipDialoguePOS.fxml"));
		Parent parent;
		try {
			NipDialogueControleurVue dialogController = new NipDialogueControleurVue();
			fxmlLoader.setController(dialogController);

			parent = fxmlLoader.load();
			Scene scene = new Scene(parent, 300, 200);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Code administrateur");
			stage.showAndWait();

			return dialogController.getNip();

		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Annule le processus de création d'un nouveau vendeur
	 * 
	 * @param event
	 */
	@FXML
	void annulerHandler(ActionEvent event) {
		ouvrirLoginVendeur();
		ctrl.chargerScene(this.scene, "Login", false);
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
	private void chargerAjoutProduit() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("CreationProduitPOS.fxml"));
		loader.setController(this);

		try {
			creationProduitPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant de charger la grid de Produits qui est affichée lorsqu'on
	 * pèse sur le boutons Produit
	 */
	private void chargerGridProduit() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("GridProduits.fxml"));
		loader.setController(this);

		try {
			paneProduit = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		produitsScroll.setFitToWidth(true);
		produitsScroll.setFitToHeight(true);

		gridProduits.setHgap(100);
		gridProduits.setVgap(38);

		populerGridProduit();
		voirItemBtn.setDisable(true);
	}

	/**
	 * Popule le grid de produits à l'aide de la liste des produits
	 */
	private void populerGridProduit() {
		produits = ctrl.getInventaire();
		int cpt = 0;

		for (Produit p : produits) {
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
		// Instantie le wrapper du Produit
		VBox wrapper = new ProduitWrapper(p);
		GridPane.setConstraints(wrapper, x, y);
		
		// Fonctionnalité d'ajout du produit
		wrapper.setOnMouseClicked((me) -> addProduitATransaction(me));
		
		return wrapper;
	}

	/**
	 * Méthode appelée par les wrappers des produits dans le grid. Elle retrouve le
	 * produit à l'aide de son nom et la charge dans l'interface
	 * 
	 * @param me le mouse event
	 */
	@FXML
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
		ProduitWrapper source = (ProduitWrapper) me.getSource();
		produitCourant = source.getProduit();

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
		clavierText.setPromptText("Prix : 3.99 et SKU: .123");
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
						} else if (s == "DEL" && s.length() != 0) {
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
		rechercheResultat.getStyleClass().add("my-table");
		rechercheResultat.setEditable(false);
		rechercheResultat.autosize();
	}

	/**
	 * Crée le TableView contenant les résultats de la recherche
	 */
	private void creerTableViewRecherche() {
		rechercheResultat = new TableView<Produit>();

		TableColumn<Produit, String> column1 = new TableColumn<>("Sku");
		column1.setCellValueFactory(new PropertyValueFactory<>("sku"));

		TableColumn<Produit, String> column2 = new TableColumn<>("Nom");
		column2.setCellValueFactory(new PropertyValueFactory<>("nom"));

		TableColumn<Produit, String> column3 = new TableColumn<>("Prix");
		column3.setCellValueFactory(new PropertyValueFactory<>("prix"));

		TableColumn<Produit, String> column4 = new TableColumn<>("Fournisseur");
		column4.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));

		TableColumn<Produit, String> column5 = new TableColumn<>("Description");
		column5.setCellValueFactory(new PropertyValueFactory<>("description"));

		rechercheResultat.getColumns().add(column1);
		rechercheResultat.getColumns().add(column2);
		rechercheResultat.getColumns().add(column3);
		rechercheResultat.getColumns().add(column4);
		rechercheResultat.getColumns().add(column5);

		column1.prefWidthProperty().bind(rechercheResultat.widthProperty().multiply(0.1));
		column2.prefWidthProperty().bind(rechercheResultat.widthProperty().multiply(0.2));
		column3.prefWidthProperty().bind(rechercheResultat.widthProperty().multiply(0.1));
		column4.prefWidthProperty().bind(rechercheResultat.widthProperty().multiply(0.2));
		column5.prefWidthProperty().bind(rechercheResultat.widthProperty().multiply(0.4));

		column1.setResizable(false);
		column2.setResizable(false);
		column3.setResizable(false);
		column4.setResizable(false);
		column5.setResizable(false);
	}

	/**
	 * Affiche la grille de produits
	 * 
	 * @param event
	 */
	@FXML
	private void produitHandler(ActionEvent event) {
		middlePane.getChildren().clear();
		ajoutBtn.setDisable(true);
		middlePane.getChildren().add(paneProduit);
		voirItemBtn.setDisable(true);
	}

	/**
	 * Afficher le clavier
	 * 
	 * @param event
	 */
	@FXML
	private void clavierHandler(ActionEvent event) {
		middlePane.getChildren().clear();
		ajoutBtn.setDisable(false);
		middlePane.getChildren().add(clavierBox);
		voirItemBtn.setDisable(false);
	}

	/**
	 * Affiche le formulaire de création de produit
	 * 
	 * @param event
	 */
	@FXML
	private void ajoutHandle(ActionEvent event) {
		middlePane.getChildren().clear();
		middlePane.getChildren().add(creationProduitPane);
		
		ajouter = new Button("Créer le produit !");
		buttonHBox.getChildren().add(ajouter);
		
		ajouter.getStyleClass().add("buttons-1");
		
		ajouter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				creerProduitHandler(event);
				
			}
		});

	}

	/**
	 * Rechercher un produit
	 */
	private void search() {
		rechercheResultat.getItems().clear();

		ArrayList<Produit> listProd = ctrl.search(clavierText.getText());

		rechercheResultat.getItems().addAll(listProd);

		rechercheResultat.refresh();
	}

	/**
	 * Ajoute le resultat de la recherche à la facture
	 * 
	 * @param event
	 */
	@FXML
	void ajouterSelection(ActionEvent event) {
		Produit temp = this.rechercheResultat.getSelectionModel().getSelectedItem();

		if (temp != null) {
			ctrl.ajouterProduitATransaction(temp);
		}

		factureTable.refresh();
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

	/**
	 * Permet d'enlever la ligne sélectionné dans la facture
	 * 
	 * @param event
	 */
	@FXML
	private void enleverSelection(ActionEvent event) {
		LigneFacture temp = this.factureTable.getSelectionModel().getSelectedItem();

		if (temp != null) {
			ctrl.enleverProduit(temp.getProduit());
		}

	}

	/**
	 * Permet de créer une nouvelle transaction
	 * 
	 * @param event
	 */
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

	/**
	 * Permet d'ouvrir un file chooser pour sélectionner une image pour le produit
	 */
	private Image choisirImage() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Sélectionner une image pour votre produit");
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		File file = fc.showOpenDialog(scene.getWindow());
		Image image = null;

		if (file != null) {
			try {
				FileInputStream fis = new FileInputStream(file);
				image = new Image(fis);
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return image;
	}

	/**
	 * Permet de générer un objet transitoire et de créer un produit
	 * 
	 * @param event
	 */
	@FXML
	private void creerProduitHandler(ActionEvent event) {
		DataProduit data = null;
		try {
			data = new DataProduit(Long.parseLong(skuProduitTextField.getText()), nomProduitTextField.getText(),
					Float.parseFloat(prixProduitTextField.getText()),
					Float.parseFloat(coutantProduitTextField.getText()), fournisseurProduitTextField.getText(),
					Integer.parseInt(quantiteProduitTextField.getText()), descriptionProduitTextArea.getText(),
					ImageUtil.convertToBytes(imageProduitImageView.getImage()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		try {
			ctrl.creerProduit(data);
		} catch (ExceptionProduitEtablissement e) {
			VueDialogue.erreurCreationDialogue(e.getMessage());
			data = null;
		}

		if (data != null) {
			VueDialogue.produitCree();
			resetChampsProduit(null);
			populerGridProduit();
		}
	}

	@FXML
	void resetChampsProduit(MouseEvent event) {
		skuProduitTextField.clear();
		nomProduitTextField.clear();
		prixProduitTextField.clear();
		coutantProduitTextField.clear();
		descriptionProduitTextArea.clear();
		fournisseurProduitTextField.clear();
		quantiteProduitTextField.clear();
		imageProduitImageView.setImage(null);
	}

	

	@FXML
	private void descriptionProduitHandler(KeyEvent event) {

	}

	@FXML
	private void fournisseurProduitHandler(KeyEvent event) {

	}

	@FXML
	private void imageProduitHandler(MouseEvent event) {
		imageProduitImageView.setPreserveRatio(false);
		Image im = choisirImage();
		if (im != null)
			imageProduitImageView.setImage(im);
	}

	@FXML
	private void prixProduitHandler(KeyEvent event) {
		String text = prixProduitTextField.getText() + event.getCharacter();
		if (!text.matches("^\\d+\u002E?(\\d{1,2})?$")) {
			event.consume();
		}
	}

	@FXML
	private void coutantProduitHandler(KeyEvent event) {
		String text = coutantProduitTextField.getText() + event.getCharacter();
		if (!text.matches("^\\d+\u002E?(\\d{1,3})?$")) {
			event.consume();
		}

	}

	@FXML
	private void nomProduitHandler(KeyEvent event) {

	}

	@FXML
	private void quantiteProduitHandler(KeyEvent event) {
		if (!event.getCharacter().matches("[0-9]")) {
			event.consume();
		}
	}

	@FXML
	private void skuProduitHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[0-9]")) {
			event.consume();
		}

	}

	@FXML
	private void nvCourrielHandler(KeyEvent event) {
		if (!event.getCharacter().matches("[A-z\u0040\u002E\u005F0-9\u002D]")) {
			event.consume();
		}
	}

	@FXML
	private void nvNomHandler(KeyEvent event) {
		if (!event.getCharacter().matches("[A-z\u0080-\u00ff]")) {
			event.consume();
		}
	}

	@FXML
	private void nvUtilisateurHandler(KeyEvent event) {
		if (!event.getCharacter().matches("[0-9A-z\u0080-\u00ff]")) {
			event.consume();
		}
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
}
