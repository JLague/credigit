package pos.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * Classe utilitaire permettant de convertir des Image en byte[] et des byte[]
 * en Image
 * 
 * @author Bank-era Corp.
 *
 */
public class ImageUtil {
	/**
	 * Permet de convertir un ImageView en array de bytes
	 * 
	 * @param imageView l'image à convertir
	 * @return l'array de bytes
	 */
	public static byte[] convertToBytes(Image image) {
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		byte[] res = null;

		try {
			ImageIO.write(bImage, "png", s);
			res = s.toByteArray();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Permet de convertir un array de bytes en ImageView
	 * 
	 * @param bytes les bytes constituant l'image
	 * @return un ImageView contenant l'image
	 */
	public static Image convertFromBytes(byte[] bytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		BufferedImage bImage = null;
		Image im = null;
		try {
			bImage = ImageIO.read(bis);
			im = SwingFXUtils.toFXImage(bImage, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return im;
	}
}
