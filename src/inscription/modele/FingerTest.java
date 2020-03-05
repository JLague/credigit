package inscription.modele;

import java.io.IOException;

import sk.mimac.fingerprint.FingerprintException;
import sk.mimac.fingerprint.FingerprintSensor;
import sk.mimac.fingerprint.adafruit.*;

public class FingerTest {

	public static void main(String[] args) {
		FingerprintSensor sensor = new AdafruitSensor("COM5");
		
		try {
			sensor.connect();
			sensor.setSecurityLevel(1);

			while (!sensor.hasFingerprint());

			// Finger is on sensor
			Integer fingerId = sensor.searchFingerprint();

			if (fingerId != null) {
				System.out.println("Finger with ID: " + fingerId);
			} else {

				while (sensor.hasFingerprint()) {
					Thread.sleep(50);
				}

				byte[] model = null;
				while ((model = sensor.createModel()) == null) {
					Thread.sleep(50);
				}
				sensor.saveStoredModel(2);
				sensor.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				sensor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
