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

	private InscriptionCtrl ctrl;
	private Scene scene;
	private int etapeActuelle = 1;
	private List<Pane> etapes;

	public InscriptionVueCtrl(InscriptionCtrl ctrl) {
		this.ctrl = ctrl;

		try {

			FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("InscriptionVue.fxml"));
			FXMLLoader step1Loader = new FXMLLoader(getClass().getResource("step1Vue.fxml"));
			FXMLLoader step2Loader = new FXMLLoader(getClass().getResource("step2Vue.fxml"));

			
			step1Loader.setController(this);
			step2Loader.setController(this);
			mainLoader.setController(this);
			

			root = mainLoader.load();

			Pane step1Pane = step1Loader.load();
			Pane step2Pane = step2Loader.load();
			etapes = new ArrayList<Pane>();
			etapes.add(step1Pane);
			etapes.add(step2Pane);

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
//		int nouvelleEtape = 0;
//		switch (etapeActuelle) {
//		case 1:
//			stepPane.getChildren().clear();
//			stepPane.getChildren().add(etapes.get(1));
//			ivStep1.setImage(new Image(getClass().getResource("step1.png").getPath()));
//			nouvelleEtape = 2;
//			break;
//
//		case 2:
//			stepPane.getChildren().clear();
//			stepPane.getChildren().add(etapes.get(1));
//			nouvelleEtape = 2;
//			break;
//
//		}
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
		
		if(!event.getCharacter().matches("[A-z\u0040\u002E\u005F0-9\u002D]"))
		{
			event.consume();
		}

	}

	@FXML
	void nasTextFieldHandler(KeyEvent event) {
		
		if(!event.getCharacter().matches("[0-9]") && !event.getCharacter().matches(" "))
		{
			event.consume();
		}

	}

	@FXML
	void nomTextFieldHandler(KeyEvent event) {
		
		if(!event.getCharacter().matches("[A-z\u0080-\u00ff]"))
		{
			event.consume();
		}
	}

	@FXML
	void prenomTextFieldHandler(KeyEvent event) {
		
		if(!event.getCharacter().matches("[A-z\u0080-\u00ff]"))
		{
			event.consume();
		}

	}

	@FXML
	void telephoneTextFieldHandler(KeyEvent event) {
		
		if(!event.getCharacter().matches("[0-9]") && !event.getCharacter().matches("-"))
		{
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
