package inscription.vue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import commun.exception.ExceptionCreationCompte;
import inscription.controleur.InscriptionCtrl;
import inscription.modele.DataClient;
import inscription.modele.LocalAdresse;
import inscription.modele.Questions;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

/**
 * Contrôleur de la vue de l'inscription
 * 
 * @author Bank-era Corp.
 *
 */
public class InscriptionVueCtrl implements IInscriptionVueCtrl {

	private static final String IMAGE_URL = "/images/inscription/";

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
	private Label sectionTitreLabel;

	@FXML
	private Pane stepPane;

	@FXML
	private Button continuerBtn;

	@FXML
	private ImageView desactiverBtn;

	@FXML
	private Button InscrireBtn;

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
	private ChoiceBox<String> question1Choice;

	@FXML
	private ChoiceBox<String> question2Choice;

	@FXML
	private TextField quitterTextField;

	@FXML
	private ImageView ivEmpreinte;

	@FXML
	private ImageView desacEmpreinte;

	/**
	 * Le contrôleur principal de l'application
	 */
	private InscriptionCtrl ctrl;

	/**
	 * La scène de la vue
	 */
	private Scene scene;

	/**
	 * L'étape d'inscription actuelle
	 */
	private EtapesVues etapeActuelle = EtapesVues.ETAPE1;

	/**
	 * Liste des vues des différentes étapes
	 */
	private List<Pane> etapes;

	/**
	 * L'empreinte du client
	 */
	private byte[] empreinte;

	private WorkIndicatorDialog wd = null;

	/**
	 * Constructeur du contrôleur de la vue
	 * 
	 * @param ctrl - Le contrôleur principal de l'application
	 */
	public InscriptionVueCtrl(InscriptionCtrl ctrl) {
		this.ctrl = ctrl;
		empreinte = null;

		try {

			// Load toutes les vues des différentes étapes
			FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("InscriptionVue.fxml"));
			FXMLLoader step1Loader = new FXMLLoader(getClass().getResource("step1Vue.fxml"));
			FXMLLoader step2Loader = new FXMLLoader(getClass().getResource("step2Vue.fxml"));
			FXMLLoader step3Loader = new FXMLLoader(getClass().getResource("step3Vue.fxml"));
			FXMLLoader step4Loader = new FXMLLoader(getClass().getResource("step4Vue.fxml"));
			FXMLLoader stepDesactiverLoader = new FXMLLoader(getClass().getResource("stepDesactiverVue.fxml"));

			// Set le contrôleur de vue actuel pour toutes les vues
			mainLoader.setController(this);
			step1Loader.setController(this);
			step2Loader.setController(this);
			step3Loader.setController(this);
			step4Loader.setController(this);
			stepDesactiverLoader.setController(this);

			root = mainLoader.load();

			Pane step1Pane = step1Loader.load();
			Pane step2Pane = step2Loader.load();
			Pane step3Pane = step3Loader.load();
			Pane step4Pane = step4Loader.load();
			Pane stepDesactiver = stepDesactiverLoader.load();

			// Stocke toutes les vues pour avoir accès à l'information en tout temps
			etapes = new ArrayList<Pane>();
			etapes.add(step1Pane);
			etapes.add(step2Pane);
			etapes.add(step3Pane);
			etapes.add(step4Pane);
			etapes.add(stepDesactiver);

			// Affiche l'étape 1
			stepPane.getChildren().add(step1Pane);

			scene = new Scene(root);

			// Setup des listes de questions
			for (Questions question : Questions.values()) {
				question1Choice.getItems().add(question.getTexte());
				question2Choice.getItems().add(question.getTexte());
			}

		} catch (IOException e) {
			System.err.println("Erreur de chargement du fxml!");
			e.printStackTrace();
		}
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	/**
	 * Méthode gérant le code à lancer lorsque le bouton découvrir est appuyé
	 * 
	 * @param event - Le MouseEvent reçu
	 */
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

	/**
	 * Méthode gérant le code à lancer lorsque le bouton S'inscrire est appuyé
	 * 
	 * @param event - Le ActionEvent reçu
	 */
	@FXML
	public void inscrireBtnHandler(ActionEvent event) {
		clearFields();

		if (etapeActuelle == EtapesVues.ETAPEDESACTIVER) {
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(0));
			setupCompteur(etapeActuelle, EtapesVues.ETAPE1);
			etapeActuelle = EtapesVues.ETAPE1;

			ivStep1.setVisible(true);
			ivStep2.setVisible(true);
			ivStep3.setVisible(true);
			ivStep4.setVisible(true);

			continuerBtn.setText("Continuer");
			continuerBtn.setDisable(false);
			sectionTitreLabel.setText("Créer un compte");
		}

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
	void desactiverBtnHandler(MouseEvent event) {
		clearDeleteFields();

		// Set la nouvelle vue
		stepPane.getChildren().clear();
		stepPane.getChildren().add(etapes.get(4));
		setupCompteur(etapeActuelle, EtapesVues.ETAPEDESACTIVER);
		etapeActuelle = EtapesVues.ETAPEDESACTIVER;
		continuerBtn.setText("Scanner");
		quitterTextField.setDisable(true);
		sectionTitreLabel.setText("Désactivation");

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

	/**
	 * Méthode s'occupant de scanner l'empreinte digitale lors de la désactivation
	 * d'un compte
	 */
	private void scannerEmpreinteDesac() {
		new Thread(() -> {
			byte[] empreinteTemp = commun.EmpreinteUtil.getEmpreinte();

			empreinte = ctrl.verifierEmpreinte(empreinteTemp);

			if (empreinte != null) {
				Platform.runLater(() -> continuerBtn.setText("Désactiver"));
				quitterTextField.setDisable(false);
				desacEmpreinte.setImage(new Image(
						getClass().getResource(IMAGE_URL + "ic_capteur_empreinte_vert.png").toExternalForm()));
			} else {
				javafx.application.Platform.runLater(() -> VueDialogue.comptePasTrouve());
			}
		}).start();
	}

	/**
	 * Méthode gérant le code à lancer lorsque le bouton Continuer est appuyé
	 * 
	 * @param event - Le ActionEvent reçu
	 */
	@FXML
	public void continuerBtnHandler(ActionEvent event) {
		EtapesVues nouvelleEtape = null;
		switch (etapeActuelle) {
		case ETAPE1:
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(1));
			nouvelleEtape = EtapesVues.ETAPE2;
			setupCompteur(etapeActuelle, nouvelleEtape);
			break;

		case ETAPE2:
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(2));
			nouvelleEtape = EtapesVues.ETAPE3;
			setupCompteur(etapeActuelle, nouvelleEtape);
			break;

		case ETAPE3:
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(3));
			continuerBtn.setText("Terminer");
			nouvelleEtape = EtapesVues.ETAPE4;
			setupCompteur(etapeActuelle, nouvelleEtape);
			getEmpreinte();

			break;

		case ETAPE4:
			DataClient data = creerDataTransition();
			nouvelleEtape = EtapesVues.ETAPE4;
			if (data != null) {
				loading(data);
			}
			break;

		case ETAPEDESACTIVER:
			if (empreinte == null) {
				scannerEmpreinteDesac();
			} else if (quitterTextField.getText().equals("QUITTER")) {
				if (ctrl.supprimerCompte(empreinte)) {
					VueDialogue.compteSupprime();
					clearDeleteFields();
				} else {
					VueDialogue.erreurCreationDialogue(
							"Une erreur s'est produite lors de la désactivation de votre compte.");
				}
			} else {
				VueDialogue.erreurCreationDialogue("Vous devez entrer QUITTER pour continuer!");
			}

			nouvelleEtape = EtapesVues.ETAPEDESACTIVER;

			break;

		}

		etapeActuelle = nouvelleEtape;

	}

	/**
	 * Clear les champs servant à supprimer un compte
	 */
	private void clearDeleteFields() {
		desacEmpreinte
				.setImage(new Image(getClass().getResource(IMAGE_URL + "ic_capteur_empreinte.png").toExternalForm()));
		empreinte = null;
		quitterTextField.setText(null);
	}

	/**
	 * Clear tous les champs de la création de compte
	 */
	private void clearFields() {
		empreinte = null;

		datePicker.setValue(null);

		nomTextField.setText(null);
		prenomTextField.setText(null);
		emailTextField.setText(null);
		telephoneTextField.setText(null);
		nasPasswordField.setText(null);
		villeTextField.setText(null);
		provinceTextField.setText(null);
		adresseTextField.setText(null);
		appartementTextField.setText(null);
		codePostalTextField.setText(null);
		paysTextField.setText(null);
		reponse1TextField.setText(null);
		reponse2TextField.setText(null);

		question1Choice.getSelectionModel().clearSelection();
		question2Choice.getSelectionModel().clearSelection();
	}

	/**
	 * Méthode permettant de créer un objet DataTransition avec les informations
	 * entrées
	 * 
	 * @return L'objet DataTransition
	 */
	private DataClient creerDataTransition() {
		DataClient data = new DataClient();

		try {

			data.setNom(nomTextField.getText());
			data.setPrenom(prenomTextField.getText());
			data.setEmail(emailTextField.getText());
			data.setNumero(telephoneTextField.getText());
			data.setNas(nasPasswordField.getText());
			LocalAdresse adresse = new LocalAdresse(adresseTextField.getText(), appartementTextField.getText(),
					codePostalTextField.getText(), villeTextField.getText(), provinceTextField.getText(),
					paysTextField.getText());
			data.setAdresse(adresse);
			data.setDate(datePicker.getValue());

			ArrayList<Questions> questions = new ArrayList<Questions>();
			if (question1Choice.getSelectionModel().getSelectedItem() != null
					&& question2Choice.getSelectionModel().getSelectedItem() != null) {
				for (Questions question : Questions.values()) {
					if (question1Choice.getSelectionModel().getSelectedItem().equals(question.getTexte())) {
						questions.add(question);
					}
				}

				for (Questions question : Questions.values()) {
					if (question2Choice.getSelectionModel().getSelectedItem().equals(question.getTexte())) {
						questions.add(question);
					}
				}
				data.setQuestions(questions);
			}

			ArrayList<String> reponses = new ArrayList<String>();
			reponses.add(reponse1TextField.getText());
			reponses.add(reponse2TextField.getText());
			data.setReponses(reponses);
			data.setEmpreinte(empreinte);

		} catch (ExceptionCreationCompte e) {
			VueDialogue.erreurCreationDialogue(e.getMessageAffichage());
			data = null;
		}

		return data;
	}

	/**
	 * Méthode permettant d'afficher un dialogue de chargement
	 * 
	 * @param data - Le data du client à créer
	 */
	private void loading(DataClient data) {
		wd = new WorkIndicatorDialog(continuerBtn.getScene().getWindow(), "Création du compte... Veuillez patienter.");

		// Lorsque le compte est ajouté
		wd.addTaskEndNotification(complete -> {
			if (complete) {
				VueDialogue.compteCree();
				resetInscription();
			}
			wd = null; // don't keep the object, cleanup
		});

		// Essaye d'ajouter le compte
		wd.exec(() -> {
			try {
				return ctrl.envoyerDataClient(data);
			} catch (ExceptionCreationCompte e) {
				Platform.runLater(() -> VueDialogue.erreurCreationDialogue(e.getMessageAffichage()));
			}

			return false;
		});
	}

	/**
	 * Permet de réinitialiser le formulaire d'inscription
	 */
	private void resetInscription() {
		clearFields();

		EtapesVues nouvelleEtape = EtapesVues.ETAPE1;
		stepPane.getChildren().clear();
		stepPane.getChildren().add(etapes.get(0));
		setupCompteur(etapeActuelle, nouvelleEtape);
		etapeActuelle = nouvelleEtape;

		continuerBtn.setText("Continuer");

		ivEmpreinte
				.setImage(new Image(getClass().getResource(IMAGE_URL + "ic_capteur_empreinte.png").toExternalForm()));
	}

	/**
	 * Méthode gérant le code à lancer lorsque le bouton indiquant l'étape 1 est
	 * appuyé
	 * 
	 * @param event - Le MouseEvent reçu
	 */
	@FXML
	public void ivStep1Handler(MouseEvent event) {
		if (!etapeActuelle.equals(EtapesVues.ETAPE1)) {
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(0));
			continuerBtn.setText("Continuer");
			setupCompteur(etapeActuelle, EtapesVues.ETAPE1);
			etapeActuelle = EtapesVues.ETAPE1;
		}
	}

	/**
	 * Méthode gérant le code à lancer lorsque le bouton indiquant l'étape 2 est
	 * appuyé
	 * 
	 * @param event - Le MouseEvent reçu
	 */
	@FXML
	void ivStep2Handler(MouseEvent event) {
		if (!etapeActuelle.equals(EtapesVues.ETAPE2)) {
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(1));
			continuerBtn.setText("Continuer");
			setupCompteur(etapeActuelle, EtapesVues.ETAPE2);
			etapeActuelle = EtapesVues.ETAPE2;
		}
	}

	/**
	 * Méthode gérant le code à lancer lorsque le bouton indiquant l'étape 3 est
	 * appuyé
	 * 
	 * @param event - Le MouseEvent reçu
	 */
	@FXML
	void ivStep3Handler(MouseEvent event) {
		if (!etapeActuelle.equals(EtapesVues.ETAPE3)) {
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(2));
			continuerBtn.setText("Continuer");
			setupCompteur(etapeActuelle, EtapesVues.ETAPE3);
			etapeActuelle = EtapesVues.ETAPE3;
		}
	}

	/**
	 * Méthode gérant le code à lancer lorsque le bouton indiquant l'étape 4 est
	 * appuyé
	 * 
	 * @param event - Le MouseEvent reçu
	 */
	@FXML
	void ivStep4Handler(MouseEvent event) {
		if (!etapeActuelle.equals(EtapesVues.ETAPE4)) {
			stepPane.getChildren().clear();
			stepPane.getChildren().add(etapes.get(3));
			continuerBtn.setText("Terminer");
			setupCompteur(etapeActuelle, EtapesVues.ETAPE4);
			etapeActuelle = EtapesVues.ETAPE4;
			getEmpreinte();
		}
	}

	/**
	 * Méthode permettant à la vue de gérer la prise de l'empreinte digitale du
	 * client
	 */
	private void getEmpreinte() {
		if (empreinte == null) {
			new Thread(() -> {
				continuerBtn.setDisable(true);
				empreinte = commun.EmpreinteUtil.getEmpreinte();
				continuerBtn.setDisable(false);
				ivEmpreinte.setImage(new Image(
						getClass().getResource(IMAGE_URL + "ic_capteur_empreinte_vert.png").toExternalForm()));
			}).start();
		}
	}

	/**
	 * Méthode qui modifie la couleur des images de l'indicateur de l'étape actuelle
	 * selon l'étape active
	 * 
	 * @param etapeActuelle - L'étape actuelle
	 * @param nouvelleEtape - L'étape où l'on veut se rendre
	 */
	private void setupCompteur(EtapesVues etapeActuelle, EtapesVues nouvelleEtape) {
		switch (etapeActuelle) {
		case ETAPE1:
			ivStep1.setImage(new Image(getClass().getResource(IMAGE_URL + "step1.png").toExternalForm()));
			break;

		case ETAPE2:
			ivStep2.setImage(new Image(getClass().getResource(IMAGE_URL + "step2.png").toExternalForm()));
			break;

		case ETAPE3:
			ivStep3.setImage(new Image(getClass().getResource(IMAGE_URL + "step3.png").toExternalForm()));
			break;

		case ETAPE4:
			ivStep4.setImage(new Image(getClass().getResource(IMAGE_URL + "step4.png").toExternalForm()));
			break;

		case ETAPEDESACTIVER:
			ivStep1.setVisible(false);
			ivStep2.setVisible(false);
			ivStep3.setVisible(false);
			ivStep4.setVisible(false);

		}

		switch (nouvelleEtape) {
		case ETAPE1:
			ivStep1.setImage(new Image(getClass().getResource(IMAGE_URL + "step1_bleu.png").toExternalForm()));
			break;

		case ETAPE2:
			ivStep2.setImage(new Image(getClass().getResource(IMAGE_URL + "step2_bleu.png").toExternalForm()));
			break;

		case ETAPE3:
			ivStep3.setImage(new Image(getClass().getResource(IMAGE_URL + "step3_bleu.png").toExternalForm()));
			break;

		case ETAPE4:
			ivStep4.setImage(new Image(getClass().getResource(IMAGE_URL + "step4_bleu.png").toExternalForm()));
			break;

		case ETAPEDESACTIVER:
			ivStep1.setVisible(false);
			ivStep2.setVisible(false);
			ivStep3.setVisible(false);
			ivStep4.setVisible(false);

		}
	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField de l'email
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void emailTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u0040\u002E\u005F0-9\u002D]")) {
			event.consume();
		}

	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField du NAS
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void nasPasswordFieldHandler(KeyEvent event) {
		if (!event.getCharacter().matches("[0-9]") && !event.getCharacter().matches(" ")) {
			event.consume();
		}

	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField du nom
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void nomTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u0080-\u00ff]")) {
			event.consume();
		}
	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField du prenom
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void prenomTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u0080-\u00ff]")) {
			event.consume();
		}

	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField du téléphone
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void telephoneTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[0-9]") && !event.getCharacter().matches("-")) {
			event.consume();
		}

	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField de l'adresse
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void adresseTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z0-9\u0020\u0027\u002D]")) {
			event.consume();
		}

	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField de l'appartement
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void appartementTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[0-9]")) {
			event.consume();
		}
	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField du code postal
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void codePostalTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[0-9A-z\\u0020]")) {
			event.consume();
		}
	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField du pays
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void paysTextFieldHandler(KeyEvent event) {
		if (!event.getCharacter().matches("[A-z\u00E9\u0020\u0027\u002D]")) {
			event.consume();
		}
	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField de la province
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void provinceTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u00E9\u0020\u0027\u002D]")) {
			event.consume();
		}

	}

	/**
	 * Méthode gérant ce qui peut être entré dans le TextField de la ville
	 * 
	 * @param event - Le KeyEvent reçu
	 */
	@FXML
	void villeTextFieldHandler(KeyEvent event) {

		if (!event.getCharacter().matches("[A-z\u00E9\u0020\u0027\u002D]")) {
			event.consume();
		}
	}

}
