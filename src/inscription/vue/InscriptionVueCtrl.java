package inscription.vue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import inscription.controleur.InscriptionCtrl;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class InscriptionVueCtrl {

	@FXML
	private ScrollPane root;

	@FXML
	private AnchorPane anchorTop;

	@FXML
	private ImageView ivDecouvrirPublicite;

	@FXML
	private AnchorPane anchorMid;

	@FXML
	private ImageView ivStep1;

	@FXML
	private ImageView ivStep2;

	@FXML
	private ImageView ivStep3;

	@FXML
	private ImageView ivStep4;

	@FXML
	private Pane stepPane;

	@FXML
	private Button continuerBtn;

	@FXML
	private TextField nomTextField;

	@FXML
	private TextField prenomTextField;

	@FXML
	private TextField emailTextField;

	@FXML
	private TextField telephoneTextField;

	@FXML
	private TextField nasTextField;

	@FXML
	private TextField villeTextField;

	@FXML
	private TextField provinceTextField;

	@FXML
	private TextField adresseTextField;

	@FXML
	private TextField appartementTextField;

	@FXML
	private TextField codePostalTextField1;

	@FXML
	private TextField reponse1TextField;

	@FXML
	private TextField reponse2TextField;

	@FXML
	private ChoiceBox<?> question1Choice;

	@FXML
	private ChoiceBox<?> question2Choice;

	private InscriptionCtrl ctrl;
	private Scene scene;
	private EtapesVues etapeActuelle = EtapesVues.ETAPE1;
	private List<Pane> etapes;

	public InscriptionVueCtrl(InscriptionCtrl ctrl) {
		this.ctrl = ctrl;

		try {

			// Load toutes les vues des différentes étapes
			FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("InscriptionVue.fxml"));
			FXMLLoader step1Loader = new FXMLLoader(getClass().getResource("step1Vue.fxml"));
			FXMLLoader step2Loader = new FXMLLoader(getClass().getResource("step2Vue.fxml"));
			FXMLLoader step3Loader = new FXMLLoader(getClass().getResource("step3Vue.fxml"));
			FXMLLoader step4Loader = new FXMLLoader(getClass().getResource("step4Vue.fxml"));

			mainLoader.setController(this);
			step1Loader.setController(this);
			step2Loader.setController(this);
			step3Loader.setController(this);
			step4Loader.setController(this);

			root = mainLoader.load();

			Pane step1Pane = step1Loader.load();
			Pane step2Pane = step2Loader.load();
			Pane step3Pane = step3Loader.load();
			Pane step4Pane = step4Loader.load();

			// Stocke toutes les vues pour avoir accès à l'information en tout temps
			etapes = new ArrayList<Pane>();
			etapes.add(step1Pane);
			etapes.add(step2Pane);
			etapes.add(step3Pane);
			etapes.add(step4Pane);

			stepPane.getChildren().add(step1Pane);

			scene = new Scene(root);

		} catch (IOException e) {
			System.err.println("Erreur de chargement du fxml!");
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {

	}

	public Scene getScene() {
		return scene;
	}

	@FXML
	public void decouvrirHandler(MouseEvent event) {
		Timeline scroll = new Timeline();
		scroll.setCycleCount(1);
		scroll.setAutoReverse(false);

		KeyFrame frame = new KeyFrame(Duration.seconds(2),
				new KeyValue(root.vvalueProperty(), root.getVmax() / 2, Interpolator.EASE_IN));

		scroll.getKeyFrames().addAll(frame);
		scroll.play();

	}

	@FXML
	public void continuerBtnHandler(ActionEvent event) {
		EtapesVues nouvelleEtape = null;
		switch (etapeActuelle) {
		case ETAPE1:
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(1));
			ivStep1.setImage(new Image(getClass().getResource("/images/step1.png").toExternalForm()));
			ivStep2.setImage(new Image(getClass().getResource("/images/step2_bleu.png").toExternalForm()));
			nouvelleEtape = EtapesVues.ETAPE2;
			break;

		case ETAPE2:
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(2));
			ivStep2.setImage(new Image(getClass().getResource("/images/step2.png").toExternalForm()));
			ivStep3.setImage(new Image(getClass().getResource("/images/step3_bleu.png").toExternalForm()));
			nouvelleEtape = EtapesVues.ETAPE3;
			break;

		case ETAPE3:
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(3));
			ivStep3.setImage(new Image(getClass().getResource("/images/step3.png").toExternalForm()));
			ivStep4.setImage(new Image(getClass().getResource("/images/step4_bleu.png").toExternalForm()));
			continuerBtn.setText("Terminer");
			nouvelleEtape = EtapesVues.ETAPE4;
			break;

		case ETAPE4:
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(3));
			ivStep3.setImage(new Image(getClass().getResource("/images/step3.png").toExternalForm()));
			ivStep4.setImage(new Image(getClass().getResource("/images/step4_bleu.png").toExternalForm()));
			//continuerBtn.setDisable(true);
			nouvelleEtape = EtapesVues.ETAPE4;
			break;

		}

		etapeActuelle = nouvelleEtape;
	}

	@FXML
	public void ivStep1Handler(MouseEvent event) {

	}

	@FXML
	void ivStep2Handler(MouseEvent event) {

	}

	@FXML
	void ivStep3Handler(MouseEvent event) {

	}

	@FXML
	void ivStep4Handler(MouseEvent event) {

	}

	@FXML
	void emailTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u0040\u002E\u005F0-9\u002D]")) {
			event.consume();
		}

	}

	@FXML
	void nasTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[0-9]") && !event.getCharacter().matches(" ")) {
			event.consume();
		}

	}

	@FXML
	void nomTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u0080-\u00ff]")) {
			event.consume();
		}
	}

	@FXML
	void prenomTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u0080-\u00ff]")) {
			event.consume();
		}

	}

	@FXML
	void telephoneTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[0-9]") && !event.getCharacter().matches("-")) {
			event.consume();
		}

	}

	@FXML
	void adresseTextFieldHandler(KeyEvent event) {

	}

	@FXML
	void appartementTextFieldHandler(KeyEvent event) {

	}

	@FXML
	void codePostalFieldHandler(KeyEvent event) {

	}

	@FXML
	void provinceTextFieldHandler(KeyEvent event) {

	}

	@FXML
	void villeTextFieldHandler(KeyEvent event) {

	}

}
