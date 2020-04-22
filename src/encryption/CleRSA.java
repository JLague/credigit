package encryption;

import java.math.BigInteger;

/**
 * Classe permettant de définir les paramètres d'une clé RSA. Une clé RSA est
 * composée de trois parties: La clé privée, la clé publique et le module.
 * 
 * @author Bank-era Corp.
 *
 */
public class CleRSA {

	/**
	 * La clé
	 */
	private BigInteger clePrivee;

	/**
	 * La clé plublique
	 */
	private BigInteger clePublique;

	/**
	 * Le module
	 */
	private BigInteger module;

	/**
	 * Constructeur de l'objet
	 * 
	 * @param clePrivee   - La clé privée
	 * @param clePublique - La clé publique
	 * @param module      - Le module
	 */
	public CleRSA(BigInteger clePrivee, BigInteger clePublique, BigInteger module) {
		setClePrivee(clePrivee);
		setClePublique(clePublique);
		setModule(module);
	}

	/**
	 * Getter de l'attribut clePrivee
	 * 
	 * @return la clé privée
	 */
	public BigInteger getClePrivee() {
		return clePrivee;
	}

	/**
	 * Setter de l'attribut clePrivee
	 * 
	 * @param clePrivee - la clé privée à set
	 */
	public void setClePrivee(BigInteger clePrivee) {
		this.clePrivee = clePrivee;
	}

	/**
	 * Getter de l'attribut clePublique
	 * 
	 * @return la clé publique
	 */
	public BigInteger getClePublique() {
		return clePublique;
	}

	/**
	 * Setter de l'attribut clePublique
	 * 
	 * @param clePublique - La clé publique à set
	 */
	public void setClePublique(BigInteger clePublique) {
		this.clePublique = clePublique;
	}

	/**
	 * Getter de l'attribut module
	 * 
	 * @return le module
	 */
	public BigInteger getModule() {
		return module;
	}

	/**
	 * Setter de l'attribut module
	 * 
	 * @param module - Le module à set
	 */
	public void setModule(BigInteger module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "CleRSA [clePrivee=" + clePrivee + ", clePublique=" + clePublique + ", module=" + module + "]";
	}
}
