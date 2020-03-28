package terminal.vue;

import java.text.DecimalFormat;

import java.util.Observable;

import com.sun.javafx.collections.ObservableListWrapper;


import java.util.List;

import commun.LigneFacture;
import commun.Transaction;
import javafx.beans.property.StringProperty;
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
	private TableView<LigneFacture> factureTable;

	@FXML
	private Label sousTotalLabel;

	@FXML
	private Label taxesLabel;

	@FXML
	private Label totalLabel;
	
	@FXML
	private Label noFactureLabel;
	
	@FXML
	private Label nomEtablissementLabel;


	@FXML
	private TableView<LigneFacture> tabView;

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


		DecimalFormat df1 = new DecimalFormat("####.##");

		noFactureLabel.setText(trans.getNumero() + "");
		sousTotalLabel.setText(df1.format(trans.getSousTotal()));
		taxesLabel.setText(df1.format(trans.getMontantTaxes()));
		totalLabel.setText(df1.format(trans.getMontantTotal()));

		nomEtablissementLabel.setText(trans.getNomEtablissement());
		
		chargerTabView();
		
		tabView.setItems(new ObservableListWrapper<LigneFacture>(trans.ligneFactureArray));
		tabView.refresh();
	}

	private void chargerTabView() {
		tabView.getStyleClass().add("table-view");
		tabView.setEditable(false);

		TableColumn<LigneFacture, String> column1 = new TableColumn<>("Produit(s)");
		column1.setCellValueFactory(new PropertyValueFactory<>("nom"));
		column1.getStyleClass().add("align-left");

		TableColumn<LigneFacture, Float> column2 = new TableColumn<>("Quantité");
		column2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		column2.getStyleClass().add("align-center");

		TableColumn<LigneFacture, String> column3 = new TableColumn<>("Prix");
		column3.setCellValueFactory(new PropertyValueFactory<>("prixString"));
		column3.getStyleClass().add("align-right");
		
		tabView.getColumns().addAll(column1, column2, column3);
		tabView.setPlaceholder(new Label("Le panier est vide !"));
	}
}
