package creationEtablissement.vue;

import java.io.IOException;

import creationEtablissement.controleur.CreationEtablissementCtrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pos.exception.ExceptionCreationCompte;
import pos.exception.ExceptionProduitEtablissement;

public class CreationEtablissementVueCtrl {

	private CreationEtablissementCtrl ctrl;

	@FXML
	private AnchorPane rootAP;

	private Scene scene;

	@FXML
	private TextField nom;

	@FXML
	private TextField adresse;

	@FXML
	private TextField balance;

	@FXML
	private TextField courriel;

	@FXML
	private Button creer;

	public CreationEtablissementVueCtrl(CreationEtablissementCtrl ctrl) {
		this.ctrl = ctrl;

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreationEtablissementVue.fxml"));

		try {

			fxmlLoader.setController(this);

			rootAP = fxmlLoader.load();
			scene = new Scene(rootAP);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public Scene getScene() {
		return scene;
	}

	@FXML
	private void creerHandler(ActionEvent event) {

		try {
			ctrl.creerEtablissement(nom.getText(), adresse.getText(), Float.valueOf(balance.getText()),
					courriel.getText());
			VueDialogue.compteCree();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ExceptionProduitEtablissement e) {
			VueDialogue.erreurCreationDialogue(e.getMessage());
		} catch (ExceptionCreationCompte e) {
			VueDialogue.erreurCreationDialogue(e.getMessage());
		}
	}

}
