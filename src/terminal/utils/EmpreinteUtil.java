package terminal.utils;

import java.io.IOException;
import java.util.List;

import sk.mimac.fingerprint.FingerprintException;
import sk.mimac.fingerprint.FingerprintSensor;
import sk.mimac.fingerprint.adafruit.AdafruitSensor;

/**
 * Classe comportant des méthodes utiles à la manipulation d'empreintes
 * digitales
 * 
 * @author Bank-era Corp.
 *
 */
public class EmpreinteUtil {

	/**
	 * Nombre maximal d'empreintes sur le sensor d'empreintes
	 */
	private static final int MAX_EMPREINTES = 162;

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
		else if (osName.indexOf("nux") >= 0)
			serialPort = LINUX_SERIAL_PORT;
		else
			System.out.println("Your OS is not supported.");

		return serialPort;
	}

	/**
	 * Scan l'empreinte et la prend directement du buffer sans créer de modèle
	 * 
	 * @return l'empreinte scannée
	 */
	public static byte[] getEmpreinte() {
		FingerprintSensor sensor = new AdafruitSensor(getSerialPort());
		byte[] empreinte = null;

		try {
			sensor.connect();

			// Scan fingerprint
			while (!sensor.hasFingerprint()) {
				Thread.sleep(50);
			}

			// Get fingerprint directly from buffer
			empreinte = sensor.upload();

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

	/**
	 * Permet de comparer des empreintes à celle scannée
	 * 
	 * @param empreinteAVerifier l'empreinte à vérifier
	 * @param empreintes         les empreintes dans les quelles il faut vérifier
	 * @return l'empreinte si elle est trouvée, sinon null
	 */
	public static byte[] matchEmpreinte(byte[] empreinteAVerifier, List<byte[]> empreintes) {
		FingerprintSensor sensor = new AdafruitSensor(getSerialPort());
		byte[] empreinte = null;

		try {
			sensor.connect();

			int loop = empreintes.size() / MAX_EMPREINTES + 1;
			int realIndex = 0;
			Integer index = null;

			// Loop every time max fingerprints is achieved
			for (int i = 0; i < loop; i++) {

				// Upload max fingerprints and get index in empreintes
				for (int j = 0; j < MAX_EMPREINTES && (realIndex = j + (i * MAX_EMPREINTES)) < empreintes.size(); j++) {
					if (empreintes.get(realIndex).length < 100) // Fix for bug with test cases
						continue;
					sensor.saveModel(empreintes.get(realIndex), j);
				}

				// Put fingerprint in buffer
				sensor.saveToBuffer(empreinteAVerifier);

				// Search for fingerprint
				index = sensor.searchFingerprint();
				if (index != null) {
					empreinte = empreintes.get(index);
					break;
				}
			}

			sensor.close();

		} catch (FingerprintException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return empreinte;
	}
}