package terminal.vue;

import java.text.DecimalFormat;

import commun.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import terminal.ctrl.TerminalControleur;

public class TerminalControleurVue {

	@FXML
	private Label nomEtablissementLabel;

	@FXML
	private Label noFactureLabel;

	@FXML
	private Label sousTotalLabel;

	@FXML
	private Label taxesLabel;

	@FXML
	private Label totalLabel;

	private Pane root;
	private Scene scene;
	private TerminalControleur ctrl;

	public TerminalControleurVue(TerminalControleur ctrl) {
		this.ctrl = ctrl;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("vueTerminal.fxml"));
		loader.setController(this);

		try {
			root = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		scene = new Scene(root);
	}

	@FXML
	private void confimerTerminalHandler(ActionEvent event) {
		System.out.println("clicked");
	}

	public Scene getScene() {
		return scene;
	}

	public void actualiser(Transaction trans) {
		
		DecimalFormat df1 = new DecimalFormat("###.##");
		
		noFactureLabel.setText(trans.getNumero()+"");
		sousTotalLabel.setText(df1.format(trans.getSousTotal()));
		taxesLabel.setText(df1.format(trans.getMontantTaxes()));
		totalLabel.setText(df1.format(trans.getMontantTotal()));
	}

}
