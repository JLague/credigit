package inscription.modele;

import java.io.IOException;

import sk.mimac.fingerprint.FingerprintException;
import sk.mimac.fingerprint.FingerprintSensor;
import sk.mimac.fingerprint.adafruit.AdafruitSensor;

/**
 * 
 * Classe permettant de scanner une empreinte digitale
 * 
 * @author Bank-era Corp.
 *
 */
public final class EmpreinteUtil {

	/**
	 * Le port série sur Windows
	 */
	private static final String WIN_SERIAL_PORT = "COM7";

	/**
	 * Le port série sur GNU/Linux
	 */
	private static final String LINUX_SERIAL_PORT = "/dev/ttyUSB0";

	/**
	 * Le nom de l'OS
	 */
	private static String osName;

	/**
	 * @return Le port série selon l'OS
	 */
	private static String getSerialPort() {
		String serialPort = null;

		if (osName == null)
			osName = System.getProperty("os.name");

		if (osName.indexOf("win") >= 0)
			serialPort = WIN_SERIAL_PORT;
		else if (osName.indexOf("linux") >= 0)
			serialPort = LINUX_SERIAL_PORT;
		else
			System.out.println("Your OS is not supported.");

		return serialPort;
	}

	/**
	 * Permet de scanner une empreinte
	 * 
	 * @return l'empreinte
	 */
	public static byte[] getEmpreinte() {
		byte[] empreinte = null;
		FingerprintSensor sensor = new AdafruitSensor(getSerialPort());

		try {
			sensor.connect();

			// Attend d'avoir une empreinte
			while (!sensor.hasFingerprint());

			// L'empreinte est scannée, on attend qu'elle soit ôtée
			while (sensor.hasFingerprint()) {
				Thread.sleep(50);
			}

			// L'empreinte doit être rescannée lors de la création du modèle
			while ((empreinte = sensor.createModel()) == null) {
				Thread.sleep(50);
			}

			sensor.close();

		} catch (FingerprintException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return empreinte;
	}

}
