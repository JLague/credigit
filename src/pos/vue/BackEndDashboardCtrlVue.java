package pos.vue;

import commun.Etablissement;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pos.modele.TBControleur;

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
	void refreshHandler(MouseEvent event) {
		// TODO Contacter la base de donnée afin de retirer le dernier etablissement
		// TODO Loader l'établissement dans la variable etablissement

		etablissement = null;

		produitsLbl.setText(etablissement.getInventaire().size() + "");
		transactionsLbl1.setText(ctrl.getTransactionToday(etablissement).size()+"");
		
		double pourcentage = (double) ctrl.getTransactionToday(etablissement).size() / (double) ctrl.getTransactionAvant(etablissement, 1).size();
		transactionsPourcentageLbl.setText(pourcentage+"");
		if (pourcentage > 0) {
			icTransactionsDown.setDisable(true);
			icTransactionsUp.setDisable(false);
		}
		else if (pourcentage < 0){
			icTransactionsUp.setDisable(true);
			icTransactionsDown.setDisable(false);
		}
		else {
			icTransactionsDown.setDisable(true);
			icTransactionsUp.setDisable(true);
		}

	}

	public BackEndDashboardCtrlVue(TBControleur ctrl) {
		this.ctrl = ctrl;
	}

}
