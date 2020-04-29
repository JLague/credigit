package commun;

import org.bson.codecs.Codec;

public abstract class CryptableCodec<T> implements Codec<T> {

	/**
	 * La clé AES
	 */
	protected String cle;
	
	/**
	 * Donne la clé AES qui permet à la classe d'encrypter et décrypter
	 * 
	 * @param cle la clé AES
	 */
	public void setCle(String cle) {
		this.cle = cle;
	}
}
