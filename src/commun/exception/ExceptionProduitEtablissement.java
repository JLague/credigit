package commun.exception;

/**
 * Classe permettant de lancer des Exceptions lorsqu'un produit ou établissment
 * ne peut pas être crée
 * 
 * @author Bank-era Corp.
 *
 */
public class ExceptionProduitEtablissement extends Exception {

	private static final long serialVersionUID = -5755924989599046100L;

	/**
	 * Le message à afficher lors d'erreur
	 */
	private String messageAffichage = null;

	/**
	 * Constructeur par défaut.
	 */
	public ExceptionProduitEtablissement() {
		super();
		setMessageAffichage("Erreur!");
	}

	/**
	 * Constructeur avec un message à afficher en paramètre.
	 * 
	 * @param message - Le message d'erreur
	 */
	public ExceptionProduitEtablissement(String message) {
		super(message);
		setMessageAffichage(message);
	}

	/**
	 * Permet d'obtenir le message à afficher dans les boîtes de dialogue
	 * 
	 * @return le message à afficher
	 */
	public String getMessageAffichage() {
		return messageAffichage;
	}

	/**
	 * Permet de définir le message à afficher dans les boîtes de dialogue
	 * 
	 * @param messageAffichage - Le message à afficher
	 */
	public void setMessageAffichage(String messageAffichage) {
		this.messageAffichage = messageAffichage;
	}
}
