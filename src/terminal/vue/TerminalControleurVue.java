package terminal.vue;

import java.text.NumberFormat;
import java.util.Locale;

import com.sun.javafx.collections.ObservableListWrapper;

import commun.EtatTransaction;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import terminal.ctrl.TerminalControleur;
import terminal.modele.gpio.Buzzer;
import terminal.modele.gpio.BuzzerSounds;
import terminal.modele.gpio.RgbLed;

/**
 * Classe permettant de contrôler la vue du terminal
 * 
 * @author Bank-era Corp.
 *
 */
public class TerminalControleurVue implements ITerminalControleurVue {

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
	private Label paiementRefuseLbl;

	@FXML
	private ImageView paiementAccepteIv;

	private Buzzer buzzer;

	private RgbLed led;

	private Scene scene;

	@SuppressWarnings("unused")
	private TerminalControleur ctrl;

	/**
	 * Constructeur du contrôleur de vue
	 * 
	 * @param ctrl - Le contrôleur principal
	 */
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

		// Charge la feuille de style (pour le Raspberry Pi)
		root.getStylesheets().add("styles/terminal/Terminal.css");

		if (System.getProperty("os.arch").equals("arm")) {
			buzzer = new Buzzer();
			led = new RgbLed();
		}

		// reinitialiserInterface();
		chargerTableView();
		factureTable.setVisible(true);
		payerLbl.setVisible(false);
		empreinteIv.setVisible(false);
		paiementAccepteIv.setVisible(false);
		paiementAccepteLbl.setVisible(false);
		paiementRefuseLbl.setVisible(false);
	}

	@FXML
	private void confimerTerminalHandler(ActionEvent event) {
		System.out.println("clicked");
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void actualiser(Transaction trans) {

		NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));

		sousTotalLbl.setText(cf.format(trans.getSousTotal()));
		taxesLbl.setText(cf.format(trans.getMontantTaxes()));
		totalLbl.setText(cf.format(trans.getMontantTotal()));

		factureTable.setItems(new ObservableListWrapper<LigneFacture>(trans.ligneFactureArray));
		factureTable.refresh();
		modInterface(trans);
	}

	/**
	 * Méthode modifiant l'interface selon la situation
	 * 
	 * @param trans - La transaction actuelle
	 */
	public void modInterface(Transaction trans) {
		switch (trans.getEtat()) {

		default:
		case NULL:
		case SCAN:
			factureTitleLbl.setVisible(true);
			factureTable.setVisible(true);
			payerLbl.setVisible(false);
			empreinteIv.setVisible(false);
			paiementAccepteIv.setVisible(false);
			paiementAccepteLbl.setVisible(false);
			paiementRefuseLbl.setVisible(false);
			break;

		case EMPREINTE:
		case ATTENTE:
			factureTitleLbl.setVisible(false);
			factureTable.setVisible(false);
			payerLbl.setVisible(true);
			empreinteIv.setVisible(true);
			paiementAccepteIv.setVisible(false);
			paiementAccepteLbl.setVisible(false);
			paiementRefuseLbl.setVisible(false);
			break;

		case CONFIRMATION:
			factureTitleLbl.setVisible(false);
			factureTable.setVisible(false);
			payerLbl.setVisible(false);
			empreinteIv.setVisible(false);
			paiementAccepteIv.setVisible(true);
			paiementAccepteIv.setImage(new Image(getClass().getResource("/images/terminal/ic_accepte.png").toExternalForm()));
			paiementAccepteLbl.setVisible(true);
			paiementRefuseLbl.setVisible(false);
			jouerConfirmation(EtatTransaction.CONFIRMATION);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;

		case ERREUR:
			factureTitleLbl.setVisible(false);
			factureTable.setVisible(false);
			payerLbl.setVisible(false);
			empreinteIv.setVisible(false);
			paiementAccepteIv.setVisible(true);
			paiementAccepteIv.setImage(new Image(getClass().getResource("/images/terminal/ic_erreur.png").toExternalForm()));
			paiementAccepteLbl.setVisible(false);
			paiementRefuseLbl.setVisible(true);
			jouerConfirmation(EtatTransaction.ERREUR);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

	/**
	 * Permet de jouer un son de confirmation selon l'état de la transaction
	 * 
	 * @param et l'état de la transaction
	 */
	private void jouerConfirmation(EtatTransaction et) {
		BuzzerSounds son = null;
		int couleur = 0;

		switch (et) {
		case CONFIRMATION:
			son = BuzzerSounds.CONFIRME;
			couleur = RgbLed.GREEN;
			break;
		case ERREUR:
			son = BuzzerSounds.REFUSE;
			couleur = RgbLed.RED;
			break;
		default:
			break;
		}

		if (son != null && buzzer != null && led != null) {
			buzzer.playSong(son);
			led.setHigh(couleur);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			led.setAllLow();
		}
	}
}
