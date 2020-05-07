package statistiques.vue;

import commun.Etablissement;
import javafx.fxml.FXML;
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
	private TableView<?> transactionsTb;
	
	@FXML
    private TableColumn<String, String> c1;

    @FXML
    private TableColumn<String, String> c2;

    @FXML
    private TableColumn<String, String> c3;

    @FXML
    private TableColumn<String, String> c4;

    @FXML
    private TableColumn<String, String> c5;


	@FXML
	void refreshHandler(MouseEvent event) {
		// TODO Contacter la base de donnée afin de retirer le dernier etablissement
		// TODO Loader l'établissement dans la variable etablissement

		etablissement = null;

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

	}

	public BackEndDashboardCtrlVue(TBControleur ctrl) {
		this.ctrl = ctrl;
	}

	private void actualiserTB(Etablissement etablissement) {
		c1 = new TableColumn<>("First Name");
		c1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

	    c2 = new TableColumn<>("Last Name");
	    c2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	    
	    c3 = new TableColumn<>("First Name");
	    c3.setCellValueFactory(new PropertyValueFactory<>("firstName"));

	    c4 = new TableColumn<>("Last Name");
	    c4.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	    
	    c5 = new TableColumn<>("First Name");
	    c5.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	}

}
