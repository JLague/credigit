package terminal.vue;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import terminal.ctrl.TerminalControleur;

public class TerminalControleurVue {
	@FXML
	private AnchorPane root;

	@FXML
	private TableView<LigneFacture> factureTable;

	@FXML
	private Label factureTitleLbl;

	@FXML
	private Label sousTotalLbl;

	@FXML
	private Label taxesLbl;

	@FXML
	private Label totalLbl;

	@FXML
	private Label payerLbl;

	@FXML
	private ImageView empreinteIv;

	@FXML
	private Label paiementAccepteLbl;

	@FXML
	private ImageView paiementAccepteIv;

	private Scene scene;

	@SuppressWarnings("unused")
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
		// reinitialiserInterface();
		chargerTableView();
		factureTable.setVisible(true);
		payerLbl.setVisible(false);
		empreinteIv.setVisible(false);
		paiementAccepteIv.setVisible(false);
		paiementAccepteLbl.setVisible(false);
	}

	@FXML
	private void confimerTerminalHandler(ActionEvent event) {
		System.out.println("clicked");
	}

	public Scene getScene() {
		return scene;
	}

	public void actualiser(Transaction trans) {

		NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));

		sousTotalLbl.setText(cf.format(trans.getSousTotal()));
		taxesLbl.setText(cf.format(trans.getMontantTaxes()));
		totalLbl.setText(cf.format(trans.getMontantTotal()));

		factureTable.setItems(new ObservableListWrapper<LigneFacture>(trans.ligneFactureArray));
		factureTable.refresh();
		modInterface(trans);
	}

	public void modInterface(Transaction trans) {
		switch (trans.getEtat()) {

		default:
		case NULL:
		case SCAN:
		case ERREUR:
			factureTitleLbl.setVisible(true);
			factureTable.setVisible(true);
			payerLbl.setVisible(false);
			empreinteIv.setVisible(false);
			paiementAccepteIv.setVisible(false);
			paiementAccepteLbl.setVisible(false);
			break;

		case EMPREINTE:
		case ATTENTE:
			factureTitleLbl.setVisible(false);
			factureTable.setVisible(false);
			payerLbl.setVisible(true);
			empreinteIv.setVisible(true);
			paiementAccepteIv.setVisible(false);
			paiementAccepteLbl.setVisible(false);
			break;

		case CONFIRMATION:
			factureTitleLbl.setVisible(false);
			factureTable.setVisible(false);
			payerLbl.setVisible(false);
			empreinteIv.setVisible(false);
			paiementAccepteIv.setVisible(true);
			paiementAccepteLbl.setVisible(true);
			break;
		}
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
