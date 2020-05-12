package statistiques.vue;

import java.io.IOException;
import java.time.LocalDate;

import commun.Etablissement;
import commun.Transaction;
import commun.exception.ExceptionProduitEtablissement;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pos.vue.VueDialogue;
import statistiques.ctrl.TBControleur;

public class BackEndDashboardCtrlVue {

	private static final String LOGIN = "LoginTB.fxml";
	private static final String MAIN_VIEW = "BackEndDashboard.fxml";

	private Etablissement etablissement;
	private TBControleur ctrl;

	@FXML
	private Label refreshBtn;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Label produitsLbl;

	@FXML
	private Label transactionsLbl1;

	@FXML
	private ImageView icTransactionsDown;

	@FXML
	private ImageView icTransactionsUp;

	@FXML
	private Label transactionsPourcentageLbl;

	@FXML
	private Label ventesBrutesLbl;

	@FXML
	private ImageView icVentesBrutesDown;

	@FXML
	private ImageView icVentesBrutesUp;

	@FXML
	private Label ventesBrutesPourcentageLbl;

	@FXML
	private Label profitsLbl;

	@FXML
	private ImageView icProfitsDown;

	@FXML
	private ImageView icProfitsUp;

	@FXML
	private Label profitsPourcentageLbl;

	@FXML
	private Label semaineCouranteLbl;

	@FXML
	private Label semaineDerniereLbl;

	@FXML
	private TextField userField;

	@FXML
	private PasswordField passField;

	@FXML
	private TextField nomEtablissementLoginField;

	@FXML
	private Button createAccountBtn;

	@FXML
	private VBox root;

	private VBox rootVBox;
	private AnchorPane rootBP;

	private Scene scene;

	@FXML
	private TableView<Transaction> transactionsTb;

	@FXML
	private TableColumn<Transaction, String> c1;

	@FXML
	private TableColumn<Transaction, String> c2;

	@FXML
	private TableColumn<Transaction, String> c3;

	@FXML
	private TableColumn<Transaction, String> c4;

	@FXML
	private TableColumn<Transaction, String> c5;

	@FXML
	private BarChart<String, Number> chart;

	public BackEndDashboardCtrlVue(TBControleur ctrl) {
		this.ctrl = ctrl;
		ouvrirLoginVendeur();

		CategoryAxis axeX = new CategoryAxis();
		axeX.setLabel("Journée");

		NumberAxis axeY = new NumberAxis();
		axeY.setLabel("Transaction");

		chart = new BarChart<String, Number>(axeX, axeY);
		chart.setTitle("Nombre de transaction au courant de la semaine dernière");
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
	 * Méthode servant à ouvrir la vue où les vendeurs peuvent se connecter
	 */
	private void ouvrirLoginVendeur() {
		creerScene(LOGIN, rootVBox);
	}

	@FXML
	void refreshHandler(MouseEvent event) {
		// TODO Contacter la base de donnée afin de retirer le dernier etablissement
		// TODO Loader l'établissement dans la variable etablissement

		etablissement = ctrl.getEtablissement();

		produitsLbl.setText(etablissement.getInventaire().size() + "");
		transactionsLbl1.setText(ctrl.getTransactionToday(etablissement).size() + "");

		double pourcentage = (double) ctrl.getTransactionToday(etablissement).size()
				/ (double) ctrl.getTransactionAvant(etablissement, 1).size();
		transactionsPourcentageLbl.setText(pourcentage + "");
		if (ctrl.getTransactionToday(etablissement).size() == 0 || ctrl.getTransactionAvant(etablissement,1).size() == 0) {
			icTransactionsUp.setVisible(false);
			icTransactionsDown.setVisible(false);
			transactionsPourcentageLbl.setTextFill(Color.WHITE);
			transactionsPourcentageLbl.setVisible(false);
		} else if (pourcentage > 0) {
			icTransactionsDown.setVisible(true);
			icTransactionsUp.setVisible(false);
			transactionsPourcentageLbl.setTextFill(Color.RED);
		} else if (pourcentage < 0) {
			icTransactionsDown.setVisible(false);
			icTransactionsUp.setVisible(true);
			transactionsPourcentageLbl.setTextFill(Color.GREEN);
		} else {
			icTransactionsDown.setVisible(false);
			icTransactionsUp.setVisible(false);
			transactionsPourcentageLbl.setTextFill(Color.BLUE);
		}

		ventesBrutesLbl.setText(ctrl.getVentesBrutesToday(etablissement) + "");

		double pourcentageVentesBrutes = (double) ctrl.getVentesBrutesToday(etablissement)
				/ (double) ctrl.getVentesBrutesHier(etablissement);

		ventesBrutesPourcentageLbl.setText(pourcentageVentesBrutes + "");
		if (ctrl.getVentesBrutesToday(etablissement) == 0 || ctrl.getVentesBrutesHier(etablissement) == 0) {
			icVentesBrutesDown.setVisible(false);
			icVentesBrutesUp.setVisible(false);
			ventesBrutesPourcentageLbl.setTextFill(Color.WHITE);
			ventesBrutesPourcentageLbl.setVisible(false);
		} else if (pourcentageVentesBrutes > 0) {
			icVentesBrutesDown.setVisible(true);
			icVentesBrutesUp.setVisible(false);
			ventesBrutesPourcentageLbl.setTextFill(Color.RED);
		} else if (pourcentageVentesBrutes < 0) {
			icVentesBrutesDown.setVisible(false);
			icVentesBrutesUp.setVisible(true);
			ventesBrutesPourcentageLbl.setTextFill(Color.GREEN);
		} else {
			icVentesBrutesDown.setVisible(false);
			icVentesBrutesUp.setVisible(false);
			ventesBrutesPourcentageLbl.setTextFill(Color.BLUE);
		}

		profitsLbl.setText(ctrl.getProfitToday(etablissement) + "");

		double pourcentageProfit = (double) ctrl.getProfitToday(etablissement)
				/ (double) ctrl.getProfitHier(etablissement);

		profitsPourcentageLbl.setText(pourcentageProfit + "");
		if (ctrl.getProfitToday(etablissement) == 0 || ctrl.getProfitHier(etablissement) == 0) {
			icProfitsDown.setVisible(false);
			icProfitsUp.setVisible(false);
			profitsPourcentageLbl.setTextFill(Color.WHITE);
			profitsPourcentageLbl.setVisible(false);
		} else if (pourcentageVentesBrutes > 0) {
			icProfitsDown.setVisible(true);
			icProfitsUp.setVisible(false);
			profitsPourcentageLbl.setTextFill(Color.RED);
		} else if (pourcentageVentesBrutes < 0) {
			icProfitsDown.setVisible(false);
			icProfitsUp.setVisible(true);
			profitsPourcentageLbl.setTextFill(Color.GREEN);
		} else {
			icProfitsDown.setVisible(false);
			icProfitsUp.setVisible(false);
			profitsPourcentageLbl.setTextFill(Color.BLUE);
		}

		semaineCouranteLbl.setText(ctrl.getNbTransactionToday(etablissement) + "");
		semaineDerniereLbl.setText(ctrl.getNbTransactionHier(etablissement) + "");

		actualiserTB(etablissement);

		chart.getData().clear();

		XYChart.Series<String, Number> jminus7 = new XYChart.Series<>();
		jminus7.setName("7 days ago");
		jminus7.getData().add(new XYChart.Data<>("", ctrl.getTransactionAvant(etablissement, 7).size()));

		XYChart.Series<String, Number> jminus6 = new XYChart.Series<>();
		jminus6.setName("6 days ago");
		jminus6.getData().add(new XYChart.Data<>("", ctrl.getTransactionAvant(etablissement, 6).size()));

		XYChart.Series<String, Number> jminus5 = new XYChart.Series<>();
		jminus5.setName("5 days ago");
		jminus5.getData().add(new XYChart.Data<>("", ctrl.getTransactionAvant(etablissement, 5).size()));

		XYChart.Series<String, Number> jminus4 = new XYChart.Series<>();
		jminus4.setName("4 days ago");
		jminus4.getData().add(new XYChart.Data<>("", ctrl.getTransactionAvant(etablissement, 4).size()));

		XYChart.Series<String, Number> jminus3 = new XYChart.Series<>();
		jminus3.setName("3 days ago");
		jminus3.getData().add(new XYChart.Data<>("", ctrl.getTransactionAvant(etablissement, 3).size()));

		XYChart.Series<String, Number> jminus2 = new XYChart.Series<>();
		jminus2.setName("2 days ago");
		jminus2.getData().add(new XYChart.Data<>("", ctrl.getTransactionAvant(etablissement, 2).size()));

		XYChart.Series<String, Number> jminus1 = new XYChart.Series<>();
		jminus1.setName("1 days ago");
		jminus1.getData().add(new XYChart.Data<>("", ctrl.getTransactionAvant(etablissement, 1).size()));

		XYChart.Series<String, Number> j0 = new XYChart.Series<>();
		j0.setName("Aujourd'hui");
		j0.getData().add(new XYChart.Data<>("", ctrl.getTransactionToday(etablissement).size()));

		// chart.getData().addAll(jminus7, jminus6, jminus5, jminus4, jminus3, jminus2,
		// jminus1, j0);

	}

	@FXML
	void connect(ActionEvent event) {
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
		// Création et chargement de la scène du Tableau de bord
		creerScene(MAIN_VIEW, rootBP);
		ctrl.chargerScene(this.scene, "Tableau de Bord", true);
		datePicker.setValue(LocalDate.now());
		datePickerHandler();
		refreshHandler(null);
	}

	@FXML
	void onEnterPressed(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			connect(null);
		}
	}

	private void actualiserTB(Etablissement etablissement) {
		c1.setCellValueFactory(new PropertyValueFactory<>("numero"));
		c2.setCellValueFactory(new PropertyValueFactory<>("heure"));
		c3.setCellValueFactory(new PropertyValueFactory<>("produits"));
		c4.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));
		c5.setCellValueFactory(new PropertyValueFactory<>("montantTotal"));

		for (Transaction tr : ctrl.getTransactionToday(etablissement)) {
			transactionsTb.getItems().add(tr);
		}

	}

	@FXML
	private void datePickerHandler() {
		ctrl.setDate(datePicker.getValue());
	}

	public Scene getScene() {
		return this.scene;
	}

}
