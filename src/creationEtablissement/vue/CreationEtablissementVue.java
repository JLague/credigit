package creationEtablissement.vue;

import java.io.IOException;

import creationEtablissement.controleur.CreationEtablissementCtrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import pos.exception.ExceptionCreationCompte;
import pos.exception.ExceptionProduitEtablissement;



public class CreationEtablissementVue {
	
	private CreationEtablissementCtrl ctrl;
	
	@FXML
	private BorderPane rootBP;

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
	
	public CreationEtablissementVue(CreationEtablissementCtrl ctrl)
	{
		this.ctrl = ctrl;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreationEtablissement.fxml"));

		try {

			fxmlLoader.setController(this);

			rootBP = fxmlLoader.load();
			scene = new Scene(rootBP);
		
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public Scene getScene()
	{
		return scene;
	}
	
	@FXML
	private void creerHandle(ActionEvent event) {
		
		try {
			ctrl.creerEtablissement(nom.getText(), adresse.getText(), Float.valueOf(balance.getText()), courriel.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ExceptionProduitEtablissement e) {
			VueDialogue.erreurCreationDialogue(e.getMessage());
		} catch (ExceptionCreationCompte e) {
			VueDialogue.erreurCreationDialogue(e.getMessage());
		}
	}

}
