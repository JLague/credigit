package pos.modele;

import exception.ExceptionCreationCompte;

public class Vendeur {

	/**
	 * le prénom du vendeur
	 */
	private String prenom;

	/**
	 * Le nom du vendeur
	 */
	private String nom;

	/**
	 * Le nom d'utilisateur du vendeur
	 */
	private String username;

	/**
	 * Le mot de passe du vendeur
	 */
	private String password;

	/**
	 * Le courriel du vendeur
	 */
	private String courriel;

	/**
	 * Constructeur appelé lorsqu'on reprend un vendeur déjà dans la base de donnée
	 * 
	 * @param data les informations du vendeur
	 * @param encryptedPassword le mot de passe encrypté du vendeur
	 * @throws ExceptionCreationCompte
	 */
	public Vendeur(DataVendeur data, String encryptedPassword) throws ExceptionCreationCompte {
		this(data);
		this.password = encryptedPassword;
	}

	/**
	 * Constructeur utilisé lors de la création du compte d'un vendeur
	 * 
	 * @param data les informations du vendeur
	 * @throws ExceptionCreationCompte
	 */
	public Vendeur(DataVendeur data) throws ExceptionCreationCompte {
		setPrenom(data.getPrenom());
		setNom(data.getNom());
		setUsername(data.getUsername());
		setPassword(data.getPassword());
		setCourriel(data.getCourriel());
	}

	/**
	 * @return le prenom du vendeur
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom le prenom du vendeur à modifier
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return le nom du vendeur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom le nom du vendeur à modifier
	 * @throws ExceptionCreationCompte
	 */
	public void setNom(String nom) throws ExceptionCreationCompte {
		if (validerNom(nom)) {
			this.nom = nom.trim();
		} else {
			throw new ExceptionCreationCompte("Votre nom n'est pas valide.");
		}
	}

	/**
	 * @return le username du vendeur
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username le username du vendeur à modifier
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return le password du vendeur
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password le password du vendeur à modifier
	 * @throws ExceptionCreationCompte
	 */
	public void setPassword(String password) throws ExceptionCreationCompte {
		if (validerPassword(password)) {
			this.password = encryption.SHAUtility.hashPassword(password);
		} else {
			throw new ExceptionCreationCompte("Le mot de passe doit contenir au moins 8 caractères.");
		}
	}

	/**
	 * @return le courriel du vendeur
	 */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * @param courriel le courriel du vendeur à modifier
	 * @throws ExceptionCreationCompte
	 */
	public void setCourriel(String courriel) throws ExceptionCreationCompte {
		if (validerCourriel(courriel)) {
			this.courriel = courriel;
		} else {
			throw new ExceptionCreationCompte("L'addresse courriel n'est pas valide.");
		}
	}

	private boolean validerNom(String nom) {
		return nom != null && nom.length() > 0;
	}

	private boolean validerPassword(String password) {
		return password != null && password.length() > 8;
	}

	private boolean validerCourriel(String courriel) {
		return courriel != null && courriel.length() > 0;
	}
}
