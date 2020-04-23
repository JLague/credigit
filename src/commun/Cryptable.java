package commun;

public interface Cryptable {

	/**
	 * Encrypte tous les attributs de l'objet
	 * 
	 * @param cle la clé AES
	 */
	public void encrypter(String cle);
	
	/**
	 * Décrypte tous les attributs de l'objet
	 * 
	 * @param cle la clé AES
	 */
	public void decrypter(String cle);
}
