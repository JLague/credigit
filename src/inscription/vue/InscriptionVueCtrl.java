package inscription.vue;

import java.io.IOException;

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

	private InscriptionCtrl ctrl;
	private Scene scene;

	public InscriptionVueCtrl(InscriptionCtrl ctrl) {
		this.ctrl = ctrl;

		try {

			FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("InscriptionVue.fxml"));
			FXMLLoader step1Loader = new FXMLLoader(getClass().getResource("step1Vue.fxml"));

			mainLoader.setController(this);
			step1Loader.setController(this);

			root = mainLoader.load();

			Pane step1Pane = step1Loader.load();
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
		System.out.println("Click");
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

	}

	@FXML
	void nasTextFieldHandler(KeyEvent event) {

	}

	@FXML
	void nomTextFieldHandler(KeyEvent event) {

	}

	@FXML
	void prenomTextFieldHandler(KeyEvent event) {

	}

	@FXML
	void telephoneTextFieldHandler(KeyEvent event) {

	}

}
