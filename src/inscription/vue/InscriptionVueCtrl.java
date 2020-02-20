package inscription.vue;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

import inscription.controleur.InscriptionCtrl;
import inscription.modele.DataTransition;
import inscription.modele.ExceptionCreationCompte;
import inscription.modele.LocalAdresse;
import inscription.modele.Questions;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class InscriptionVueCtrl {

	@FXML
	private StackPane root;

	@FXML
	private ScrollPane scrollPane;

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
	private PasswordField nasPasswordField;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TextField villeTextField;

	@FXML
	private TextField provinceTextField;

	@FXML
	private TextField adresseTextField;

	@FXML
	private TextField appartementTextField;

	@FXML
	private TextField codePostalTextField;

	@FXML
	private TextField paysTextField;

	@FXML
	private TextField reponse1TextField;

	@FXML
	private TextField reponse2TextField;

	@FXML
	private ChoiceBox<Questions> question1Choice;

	@FXML
	private ChoiceBox<Questions> question2Choice;

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

	public Scene getScene() {
		return scene;
	}

	@FXML
	public void decouvrirHandler(MouseEvent event) {
		Timeline scroll = new Timeline();
		scroll.setCycleCount(1);
		scroll.setAutoReverse(false);

		KeyFrame frame = new KeyFrame(Duration.seconds(2),
				new KeyValue(scrollPane.vvalueProperty(), scrollPane.getVmax() / 2, Interpolator.EASE_IN));

		scroll.getKeyFrames().addAll(frame);
		scroll.play();

	}

	@FXML
	public void inscrireBtnHandler(ActionEvent event) {

		if (scrollPane.getVvalue() < scrollPane.getVmax()) {
			Timeline scroll = new Timeline();
			scroll.setCycleCount(1);
			scroll.setAutoReverse(false);

			KeyFrame frame = new KeyFrame(Duration.seconds(2),
					new KeyValue(scrollPane.vvalueProperty(), scrollPane.getVmax(), Interpolator.EASE_IN));

			scroll.getKeyFrames().addAll(frame);
			scroll.play();
		}

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
			ctrl.envoyerDataClient(creerDataTransition());
			break;

		}

		etapeActuelle = nouvelleEtape;
	}

	private DataTransition creerDataTransition() {
		DataTransition data = new DataTransition();

		try {

			data.setNom(nomTextField.getText());
			data.setPrenom(prenomTextField.getText());
			data.setEmail(emailTextField.getText());
			data.setNumero(telephoneTextField.getText());
			data.setNas(nasPasswordField.getText());
			LocalAdresse adresse = new LocalAdresse(adresseTextField.getText(), codePostalTextField.getText(),
					villeTextField.getText(), provinceTextField.getText(), paysTextField.getText());
			data.setAdresse(adresse);
			data.setDate(datePicker.getValue());

			ArrayList<Questions> questions = new ArrayList<Questions>();
			questions.add(question1Choice.getSelectionModel().getSelectedItem());
			questions.add(question2Choice.getSelectionModel().getSelectedItem());
			data.setQuestions(questions);

			ArrayList<String> reponses = new ArrayList<String>();
			reponses.add(reponse1TextField.getText());
			reponses.add(reponse2TextField.getText());
			data.setReponses(reponses);

			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			data.setEmpreinte(empreinte);

		} catch (ExceptionCreationCompte e) {
			System.out.println("Erreur sur la création de l'adresse.");
		}

		return data;
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
	void nasPasswordFieldHandler(KeyEvent event) {
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

		if (!event.getCharacter().matches("[A-z0-9\u0020\u0027\u002D]")) {
			event.consume();
		}

	}

	@FXML
	void appartementTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[0-9]")) {
			event.consume();
		}
	}

	@FXML
	void codePostalTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[0-9A-z\\u0020]")) {
			event.consume();
		}
	}

	@FXML
	void paysTextFieldHandler(KeyEvent event) {
		if (!event.getCharacter().matches("[A-z\u00E9\u0020\u0027\u002D]")) {
			event.consume();
		}
	}

	@FXML
	void provinceTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u00E9\u0020\u0027\u002D]")) {
			event.consume();
		}

	}

	@FXML
	void villeTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u00E9\u0020\u0027\u002D]")) {
			event.consume();
		}
	}

}
