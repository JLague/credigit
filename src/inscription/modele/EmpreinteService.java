package inscription.modele;

import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sk.mimac.fingerprint.FingerprintSensor;
import sk.mimac.fingerprint.adafruit.AdafruitSensor;

public class EmpreinteService extends Service<Void> {
	private byte[] empreinte = null;

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Platform.runLater(() -> {
					FingerprintSensor sensor = new AdafruitSensor("COM5");

					try {
						sensor.connect();
						sensor.setSecurityLevel(1);

						while (!sensor.hasFingerprint())
							;
						while (sensor.hasFingerprint()) {
							Thread.sleep(50);
						}

						while ((empreinte = sensor.createModel()) == null) {
							Thread.sleep(50);
						}

						System.out.println(empreinte);

					} catch (Exception e) {
					} finally {
						try {
							sensor.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				return null;
			}

		};

	}

	public byte[] getEmpreinte() {
		return empreinte;
	}

}
