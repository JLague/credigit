package terminal.vue;

import java.text.DecimalFormat;

import com.sun.javafx.collections.ObservableListWrapper;

import commun.LigneFacture;
import commun.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import terminal.ctrl.TerminalControleur;

public class TerminalControleurVue {
	@FXML
	private AnchorPane root;

	@FXML
	private TableView<LigneFacture> factureTable;

	@FXML
	private Label sousTotalLbl;

	@FXML
	private Label taxesLbl;

	@FXML
	private Label totalLbl;

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

		chargerTableView();
		DecimalFormat df1 = new DecimalFormat("###.##");

		sousTotalLbl.setText(df1.format(trans.getSousTotal()) + "$");
		taxesLbl.setText(df1.format(trans.getMontantTaxes()) + "$");
		totalLbl.setText(df1.format(trans.getMontantTotal()) + "$");

		factureTable.setItems(new ObservableListWrapper<LigneFacture>(trans.ligneFactureArray));
		factureTable.refresh();
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

		factureTable.getColumns().addAll(column1, column2, column3);
		factureTable.setPlaceholder(new Label("La facture est vide."));
	}
}
