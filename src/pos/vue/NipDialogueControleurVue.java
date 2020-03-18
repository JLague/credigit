package pos.vue;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NipDialogueControleurVue {

	@FXML
	private PasswordField nip1;

	@FXML
	private PasswordField nip2;

	@FXML
	private PasswordField nip3;

	@FXML
	private PasswordField nip4;

	@FXML
	private PasswordField nip5;

	private String nip;

	public NipDialogueControleurVue() {
		nip = "";
	}

	@FXML
	public void initialize() {
		registerListener(nip1, nip2);
		registerListener(nip2, nip3);
		registerListener(nip3, nip4);
		registerListener(nip4, nip5);
	}

	private void registerListener(PasswordField pf1, PasswordField pf2) {
		pf1.textProperty().addListener((obs, oldText, newText) -> {
			if (oldText.length() < 1 && newText.length() >= 1) {
				pf2.requestFocus();
			}
		});
	}

	public void setNipString(String nipString) {
		this.nip = nipString;
	}

	public String getNip() {
		return nip;
	}

	@FXML
	void DernierChiffreHandler(KeyEvent event) {
		if (!nip1.getText().isEmpty() && !nip2.getText().isEmpty() && !nip3.getText().isEmpty()
				&& !nip4.getText().isEmpty() && event.getCharacter().matches("[0-9]")) {
			nip += nip1.getText();
			nip += nip2.getText();
			nip += nip3.getText();
			nip += nip4.getText();
			nip += event.getCharacter();
			nip.trim();
			closeStage(event);
		}
	}

	private void closeStage(KeyEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
