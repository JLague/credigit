package statistiques.vue;

import commun.Etablissement;
import commun.Transaction;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import statistiques.ctrl.TBControleur;

public class BackEndDashboardCtrlVue {

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
	private ImageView icVentesBrutesDown1;

	@FXML
	private ImageView icVentesBrutesUp1;

	@FXML
	private Label profitsPourcentageLbl;

	@FXML
	private Label semaineCouranteLbl;

	@FXML
	private Label semaineDerniereLbl;

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
	private BarChart<XYChart.Series<String, Number>, XYChart.Series<String, Number>> chart;

	public BackEndDashboardCtrlVue() {

		CategoryAxis axeX = new CategoryAxis();
		axeX.setLabel("Journée");

		NumberAxis axeY = new NumberAxis();
		axeY.setLabel("Transaction");

		chart = new BarChart<XYChart.Series<String, Number>, XYChart.Series<String, Number>>(axeX, axeY);
		chart.setTitle("Nombre de transaction au courant de la semaine dernière");
	}

	@FXML
	void refreshHandler(MouseEvent event) {
		// TODO Contacter la base de donnée afin de retirer le dernier etablissement
		// TODO Loader l'établissement dans la variable etablissement

		etablissement = null;o

		produitsLbl.setText(etablissement.getInventaire().size() + "");
		transactionsLbl1.setText(ctrl.getTransactionToday(etablissement).size() + "");

		double pourcentage = (double) ctrl.getTransactionToday(etablissement).size()
				/ (double) ctrl.getTransactionAvant(etablissement, 1).size();
		transactionsPourcentageLbl.setText(pourcentage + "");
		if (pourcentage > 0) {
			icTransactionsDown.setDisable(true);
			icTransactionsUp.setDisable(false);
		} else if (pourcentage < 0) {
			icTransactionsUp.setDisable(true);
			icTransactionsDown.setDisable(false);
		} else {
			icTransactionsDown.setDisable(true);
			icTransactionsUp.setDisable(true);
		}

		ventesBrutesLbl.setText(ctrl.getVentesBrutesToday(etablissement) + "");

		double pourcentageVentesBrutes = (double) ctrl.getVentesBrutesToday(etablissement)
				/ (double) ctrl.getVentesBrutesHier(etablissement);

		ventesBrutesPourcentageLbl.setText(pourcentageVentesBrutes + "");
		if (pourcentageVentesBrutes > 0) {
			icVentesBrutesDown.setDisable(true);
			icVentesBrutesUp.setDisable(false);
		} else if (pourcentageVentesBrutes < 0) {
			icVentesBrutesUp.setDisable(true);
			icVentesBrutesDown.setDisable(false);
		} else {
			icVentesBrutesDown.setDisable(true);
			icVentesBrutesUp.setDisable(true);
		}

		profitsLbl.setText(ctrl.getProfitToday(etablissement) + "");

		double pourcentageProfit = (double) ctrl.getProfitToday(etablissement)
				/ (double) ctrl.getProfitHier(etablissement);

		profitsPourcentageLbl.setText(pourcentageProfit + "");
		if (pourcentageVentesBrutes > 0) {
			icVentesBrutesDown1.setDisable(true);
			icVentesBrutesUp1.setDisable(false);
		} else if (pourcentageVentesBrutes < 0) {
			icVentesBrutesUp1.setDisable(true);
			icVentesBrutesDown1.setDisable(false);
		} else {
			icVentesBrutesDown1.setDisable(true);
			icVentesBrutesUp1.setDisable(true);
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

		chart.getData().addAll(jminus7, jminus6, jminus5, jminus4, jminus3, jminus2, jminus1, j0);

	}

	public BackEndDashboardCtrlVue(TBControleur ctrl) {
		this.ctrl = ctrl;
	}

	private void actualiserTB(Etablissement etablissement) {
		c1.setCellValueFactory(new PropertyValueFactory<>("numero"));
		c2.setCellValueFactory(new PropertyValueFactory<>("heure"));
		c3.setCellValueFactory(new PropertyValueFactory<>("produits"));
		c4.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));
		c5.setCellValueFactory(new PropertyValueFactory<>("montantTotal"));

		transactionsTb.getColumns().add(c1);
		transactionsTb.getColumns().add(c2);
		transactionsTb.getColumns().add(c3);
		transactionsTb.getColumns().add(c4);
		transactionsTb.getColumns().add(c5);

		for (Transaction tr : ctrl.getTransactionToday(etablissement)) {
			transactionsTb.getItems().add(tr);
		}

	}

	@FXML
	private void datePickerHandler() {
		ctrl.setDate(datePicker.getValue());
	}

}
