package inscription.exception;

/**
 * Classe permettant de lancer des Exceptions lorsqu'un client ne peut pas être
 * crée
 * 
 * @author Bank-era Corp.
 *
 */
public class ExceptionCreationCompte extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3680537470376091983L;
	private String messageAffichage = null;

	/**
	 * Constructeur par défaut.
	 */
	public ExceptionCreationCompte() {
		super();
		setMessageAffichage("Erreur!");
	}

	/**
	 * Constructeur avec un message à afficher en paramètre.
	 * 
	 * @param message
	 */
	public ExceptionCreationCompte(String message) {
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
	 * @param messageAffichage
	 */
	public void setMessageAffichage(String messageAffichage) {
		this.messageAffichage = messageAffichage;
	}
}
