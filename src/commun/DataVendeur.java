package commun;

/**
 * Classe transitoire contenant le data d'un vendeur
 * 
 * @author Bank-era Corp.
 *
 */
public class DataVendeur {

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
	 * Constructeur par défaut
	 */
	public DataVendeur() {

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
	 */
	public void setNom(String nom) {
		this.nom = nom;
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
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return le courriel du vendeur
	 */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * @param courriel le courriel du vendeur à modifier
	 */
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}
}
