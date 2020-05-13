package commun;

import java.io.IOException;
import java.util.List;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import sk.mimac.fingerprint.FingerprintException;
import sk.mimac.fingerprint.FingerprintSensor;
import sk.mimac.fingerprint.adafruit.AdafruitSensor;

/**
 * Classe permettant de faciliter l'utilisation du lecteur d'empreintes
 * digitales et de matcher des empreintes en utilisant la librairie sourceAFIS
 * 
 * @author Bank-era Corp.
 *
 */
public class EmpreinteUtil {

	/**
	 * Le port série sur Windows
	 */
	private static final String WIN_SERIAL_PORT = "COM7";

	/**
	 * Le port série sur GNU/Linux
	 */
	private static final String LINUX_SERIAL_PORT = "/dev/ttyUSB0";

	/**
	 * La largeur de l'image du lecteur d'empreintes digitales
	 */
	private static final int LARGEUR_EMPREINTE = 256;

	/**
	 * La hauteur de l'image du lecteur d'empreintes digitales
	 */
	private static final int HAUTEUR_EMPREINTE = 288;

	/**
	 * Le seul minimal pour avoir un match d'empreintes
	 */
	private static final double THRESHOLD = 40.0;

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
			System.err.println("Your OS is not supported.");

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
			while (!sensor.genImage())
				;

			empreinte = sensor.uploadImage();
			sensor.close();

		} catch (FingerprintException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return decompresserEmpreinte(empreinte);
	}

	/**
	 * Méthode permettant de créer un FingerprintTemplate (sourceAFIS) avec une
	 * image d'empreinte digitale scannée par le lecteur d'empreintes
	 * 
	 * @param empreinte l'empreinte servant à créer le template
	 * @return le template de l'empreinte
	 */
	public static FingerprintTemplate getTemplate(byte[] empreinte) {
		return new FingerprintTemplate(
				new FingerprintImage().grayscale(LARGEUR_EMPREINTE, HAUTEUR_EMPREINTE, empreinte));
	}

	/**
	 * Méthode permettant de matcher une empreinte parmi une liste de candidat
	 * 
	 * @param empreinte l'image de l'empreinte à matcher
	 * @param candidats les images des candidats possibles
	 * @return L'empreinte si elle matche sinon null
	 */
	public static byte[] matchEmpreinte(byte[] empreinte, List<byte[]> candidats) {
		// Créer le template et le matcher
		FingerprintTemplate probe = getTemplate(empreinte);
		FingerprintMatcher matcher = new FingerprintMatcher().index(probe);
		byte[] match = null;

		// Loop sur tous les candidats possibles et retient celui avec le score le plus
		// haut
		double high = 0;
		for (byte[] candidat : candidats) {
			FingerprintTemplate template = getTemplate(candidat);
			double score = matcher.match(template);
			if (score > high) {
				high = score;
				match = candidat;
			}
		}

		return high >= THRESHOLD ? match : null;

	}

	/**
	 * Permet de décompresser les empreintes venant directement du lecteur
	 * d'empreinte. Le byte 1010 0101 devient alors 1010 0000 et 0101 0000
	 * 
	 * @param empreinte l'empreinte à décompresser
	 * @return l'empreinte décompressée
	 */
	public static byte[] decompresserEmpreinte(byte[] empreinte) {
		byte[] empreinteImg = new byte[empreinte.length * 2];
		int count = 0;

		for (int i = 0; i < empreinte.length; i++) {
			empreinteImg[count++] = (byte) (empreinte[i] & (0b1111 << 4));
			empreinteImg[count++] = (byte) ((empreinte[i] & 0b1111) << 4);
		}

		return empreinteImg;
	}
}
