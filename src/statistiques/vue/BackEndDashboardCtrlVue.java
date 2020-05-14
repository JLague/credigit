package statistiques.vue;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

import commun.Etablissement;
import commun.Transaction;
import commun.exception.ExceptionProduitEtablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pos.vue.VueDialogue;
import statistiques.ctrl.TBControleur;

/**
 * Classe servant de contrôleur de la vue du tableau de statistiques
 * 
 * @author Bank-era Corp.
 *
 */
public class BackEndDashboardCtrlVue implements IBackEndDashboardCtrlVue {

	private static final String LOGIN = "LoginTB.fxml";
	private static final String MAIN_VIEW = "BackEndDashboard.fxml";

	/**
	 * L'établissement de l'utilisateur connecté
	 */
	private Etablissement etablissement;

	/**
	 * Le contrôleur principal de l'application
	 */
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

	@FXML
	private LineChart<String, Number> lineChart;

	NumberFormat cf1;

	/**
	 * Constructeur du contrôleur de la vue
	 * 
	 * @param ctrl - Le contrôleur principal de l'application
	 */
	public BackEndDashboardCtrlVue(TBControleur ctrl) {
		cf1 = NumberFormat.getCurrencyInstance(new Locale("fr", "CA"));
		this.ctrl = ctrl;
		ouvrirLoginVendeur();

		CategoryAxis axeX = new CategoryAxis();
		axeX.setLabel("Journée");

		NumberAxis axeY = new NumberAxis();
		axeY.setLabel("Profits");

		lineChart = new LineChart<String, Number>(axeX, axeY);
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

		etablissement = ctrl.getEtablissement();

		ctrl.setDate(datePicker.getValue());

		produitsLbl.setText(etablissement.getInventaire().size() + "");
		transactionsLbl1.setText(ctrl.getTransactionToday(etablissement).size() + "");

		double pourcentage = (double) ctrl.getTransactionToday(etablissement).size()
				/ (double) ctrl.getTransactionAvant(etablissement, 1).size();
		transactionsPourcentageLbl.setText(String.format("%.2f", pourcentage) + " %");
		if (ctrl.getTransactionToday(etablissement).size() == 0
				|| ctrl.getTransactionAvant(etablissement, 1).size() == 0) {
			icTransactionsUp.setVisible(false);
			icTransactionsDown.setVisible(false);
			transactionsPourcentageLbl.setTextFill(Color.WHITE);
			transactionsPourcentageLbl.setVisible(false);
		} else if (pourcentage > 0) {
			icTransactionsDown.setVisible(false);
			icTransactionsUp.setVisible(true);
			transactionsPourcentageLbl.setTextFill(Color.RED);
		} else if (pourcentage < 0) {
			icTransactionsDown.setVisible(true);
			icTransactionsUp.setVisible(false);
			transactionsPourcentageLbl.setTextFill(Color.GREEN);
		} else {
			icTransactionsDown.setVisible(false);
			icTransactionsUp.setVisible(false);
			transactionsPourcentageLbl.setTextFill(Color.BLUE);
		}

		ventesBrutesLbl.setText(cf1.format(ctrl.getVentesBrutesToday(etablissement)));

		double pourcentageVentesBrutes = (double) ctrl.getVentesBrutesToday(etablissement)
				/ (double) ctrl.getVentesBrutesHier(etablissement);

		ventesBrutesPourcentageLbl.setText(String.format("%.2f", pourcentageVentesBrutes) + " %");
		if (ctrl.getVentesBrutesToday(etablissement) == 0 || ctrl.getVentesBrutesHier(etablissement) == 0) {
			icVentesBrutesDown.setVisible(false);
			icVentesBrutesUp.setVisible(false);
			ventesBrutesPourcentageLbl.setTextFill(Color.WHITE);
			ventesBrutesPourcentageLbl.setVisible(false);
		} else if (pourcentageVentesBrutes > 0) {
			icVentesBrutesDown.setVisible(false);
			icVentesBrutesUp.setVisible(true);
			ventesBrutesPourcentageLbl.setTextFill(Color.RED);
		} else if (pourcentageVentesBrutes < 0) {
			icVentesBrutesDown.setVisible(true);
			icVentesBrutesUp.setVisible(false);
			ventesBrutesPourcentageLbl.setTextFill(Color.GREEN);
		} else {
			icVentesBrutesDown.setVisible(false);
			icVentesBrutesUp.setVisible(false);
			ventesBrutesPourcentageLbl.setTextFill(Color.BLUE);
		}

		profitsLbl.setText(cf1.format(ctrl.getProfitToday(etablissement)));

		double pourcentageProfit = (double) ctrl.getProfitToday(etablissement)
				/ (double) ctrl.getProfitHier(etablissement);

		profitsPourcentageLbl.setText(String.format("%.2f", pourcentageProfit) + " %");
		if (ctrl.getProfitToday(etablissement) == 0 || ctrl.getProfitHier(etablissement) == 0) {
			icProfitsDown.setVisible(false);
			icProfitsUp.setVisible(false);
			profitsPourcentageLbl.setTextFill(Color.WHITE);
			profitsPourcentageLbl.setVisible(false);
		} else if (pourcentageVentesBrutes > 0) {
			icProfitsDown.setVisible(false);
			icProfitsUp.setVisible(true);
			profitsPourcentageLbl.setTextFill(Color.RED);
		} else if (pourcentageVentesBrutes < 0) {
			icProfitsDown.setVisible(true);
			icProfitsUp.setVisible(false);
			profitsPourcentageLbl.setTextFill(Color.GREEN);
		} else {
			icProfitsDown.setVisible(false);
			icProfitsUp.setVisible(false);
			profitsPourcentageLbl.setTextFill(Color.BLUE);
		}

		try {
			semaineCouranteLbl.setText(ctrl.getNbTransactionSemCourante(etablissement) + "");
			semaineDerniereLbl.setText(ctrl.getNbTransactionSemPrecedente(etablissement) + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		actualiserTB(etablissement);

		lineChart.getData().clear();

		// Semaine courante
		XYChart.Series<String, Number> serie1 = new XYChart.Series<>();
		serie1.getData().add(new XYChart.Data<String, Number>("7 jours avant", ctrl.getProfitsAvant(etablissement, 7)));
		serie1.getData().add(new XYChart.Data<String, Number>("6 jours avant", ctrl.getProfitsAvant(etablissement, 6)));
		serie1.getData().add(new XYChart.Data<String, Number>("5 jours avant", ctrl.getProfitsAvant(etablissement, 5)));
		serie1.getData().add(new XYChart.Data<String, Number>("4 jours avant", ctrl.getProfitsAvant(etablissement, 4)));
		serie1.getData().add(new XYChart.Data<String, Number>("3 jours avant", ctrl.getProfitsAvant(etablissement, 3)));
		serie1.getData().add(new XYChart.Data<String, Number>("2 jours avant", ctrl.getProfitsAvant(etablissement, 2)));
		serie1.getData().add(new XYChart.Data<String, Number>("1 jours avant", ctrl.getProfitsAvant(etablissement, 1)));
		serie1.getData().add(new XYChart.Data<String, Number>("Aujourd'hui", ctrl.getProfitToday(etablissement)));

		// Semaine précédente
		XYChart.Series<String, Number> serie2 = new XYChart.Series<>();
		serie2.getData()
				.add(new XYChart.Data<String, Number>("7 jours avant", ctrl.getProfitsAvant(etablissement, 15)));
		serie2.getData()
				.add(new XYChart.Data<String, Number>("6 jours avant", ctrl.getProfitsAvant(etablissement, 14)));
		serie2.getData()
				.add(new XYChart.Data<String, Number>("5 jours avant", ctrl.getProfitsAvant(etablissement, 13)));
		serie2.getData()
				.add(new XYChart.Data<String, Number>("4 jours avant", ctrl.getProfitsAvant(etablissement, 12)));
		serie2.getData()
				.add(new XYChart.Data<String, Number>("3 jours avant", ctrl.getProfitsAvant(etablissement, 11)));
		serie2.getData()
				.add(new XYChart.Data<String, Number>("2 jours avant", ctrl.getProfitsAvant(etablissement, 10)));
		serie2.getData().add(new XYChart.Data<String, Number>("1 jours avant", ctrl.getProfitsAvant(etablissement, 9)));
		serie2.getData().add(new XYChart.Data<String, Number>("Aujourd'hui", ctrl.getProfitsAvant(etablissement, 8)));

		lineChart.getData().add(serie1);
		lineChart.getData().add(serie2);

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

	/**
	 * Méthode permettant d'actualiser le tableView des transactions
	 * 
	 * @param etablissement - L'établissement courant
	 */
	private void actualiserTB(Etablissement etablissement) {
		transactionsTb.getItems().clear();
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

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
