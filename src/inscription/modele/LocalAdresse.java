package inscription.modele;

/**
 * Classe permettant de créer un objet représantant une adresse
 * 
 * @author Bank-era Corp.
 *
 */
public class LocalAdresse {

	/**
	 * L'adresse du client
	 */
	private String adresse;

	/**
	 * Le numéro de l'appartement du client
	 */
	private String appartement;

	/**
	 * Le code postal du client
	 */
	private String codePostal;

	/**
	 * La ville du client
	 */
	private String ville;

	/**
	 * La province ou l'état du client
	 */
	private String etat;

	/**
	 * Le pays de la personne
	 */
	private String pays;

	/**
	 * Construit l'adresse du client avec un appartement
	 * 
	 * @param adresse     - L'adresse
	 * @param appartement - Le numéro d'appartement
	 * @param codePostal  - Le code postal
	 * @param ville       - La ville
	 * @param etat        - L'état
	 * @param pays        - Le pays
	 */
	public LocalAdresse(String adresse, String appartement, String codePostal, String ville, String etat, String pays)
			throws ExceptionCreationCompte {

		setAdresse(adresse);
		setAppartement(appartement);
		setCodePostal(codePostal);
		setVille(ville);
		setEtat(etat);
		setPays(pays);
	}

	/**
	 * Construit l'adresse du client sans appartement
	 * 
	 * @param adresse    - L'adresse
	 * @param codePostal - Le code postal
	 * @param ville      - La ville
	 * @param etat       - L'état
	 * @param pays       - Le pays
	 */
	public LocalAdresse(String adresse, String codePostal, String ville, String etat, String pays)
			throws ExceptionCreationCompte {

		setAdresse(adresse);
		appartement = "";
		setCodePostal(codePostal);
		setVille(ville);
		setEtat(etat);
		setPays(pays);
	}

	/**
	 * Retourne l'adresse du client
	 * 
	 * @return L'adresse du client
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse du client
	 * 
	 * @param adresse - La nouvelle adresse du client
	 */
	private void setAdresse(String adresse) throws ExceptionCreationCompte {
		if (validerAdresse(adresse)) {
			this.adresse = adresse.trim();
		} else {
			throw new ExceptionCreationCompte("Votre adresse n'est pas valide.");
		}

	}

	/**
	 * Retourne le numéro d'appartement du client
	 * 
	 * @return Le numéro d'appartement du client
	 */
	public String getAppartement() {
		return appartement;
	}

	/**
	 * Modifie le numéro d'appartement du client
	 * 
	 * @param appartement - Le nouveau numéro d'appartement du client
	 */
	private void setAppartement(String appartement) throws ExceptionCreationCompte {
		if (validerAppartement(appartement)) {
			this.appartement = appartement;
		} else {
			throw new ExceptionCreationCompte("Votre numéro d'appartement n'est pas valide.");
		}

	}

	/**
	 * Retourne le code postal du client
	 * 
	 * @return Le code postal du client
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * Modifie le code postal du client
	 * 
	 * @param codePostal - Le nouveau code postal du client
	 */
	private void setCodePostal(String codePostal) throws ExceptionCreationCompte {
		if (validerCodePostal(codePostal)) {
			this.codePostal = arrangerString(codePostal);
		} else {
			throw new ExceptionCreationCompte("Votre code postal n'est pas valide.");
		}

	}

	/**
	 * Modifie la ville du client
	 * 
	 * @return La ville du client
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Modifie la ville du client
	 * 
	 * @param ville - la nouvelle ville du client
	 */
	private void setVille(String ville) throws ExceptionCreationCompte {
		if (ville != null && validerString(ville)) {
			this.ville = arrangerString(ville);
		} else {
			throw new ExceptionCreationCompte("Votre ville n'est pas valide.");
		}
	}

	/**
	 * Retourne l'état du client
	 * 
	 * @return L'état du client
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Modifie l'état du client
	 * 
	 * @param etat - Le nouvel état du client
	 */
	private void setEtat(String etat) throws ExceptionCreationCompte {
		if (etat != null && validerString(etat)) {
			this.etat = arrangerString(etat);
		} else {
			throw new ExceptionCreationCompte("Votre état n'est pas valide.");
		}
	}

	/**
	 * Retourne le pays du client
	 * 
	 * @return Le pays du client
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * Modifie le pays du client
	 * 
	 * @param pays - Le nouvel état du client
	 */
	private void setPays(String pays) throws ExceptionCreationCompte {
		if (pays != null && validerString(pays)) {
			this.pays = arrangerString(pays);
		} else {
			throw new ExceptionCreationCompte("Votre pays n'est pas valide.");
		}
	}

	/**
	 * Valide que l'adresse n'est pas nulle et n'est pas vide
	 * 
	 * @param adresse - L'adresse à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerAdresse(String adresse) {
		boolean valide = false;

		if (adresse != null && adresse.length() != 0)
			valide = true;

		return valide;
	}

	/**
	 * Valide que le numéro d'appartement est plus grand que 0
	 * 
	 * @param appartement - Le numéro d'appartement à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerAppartement(String appartement) {
		boolean valide = false;

		if (appartement != null)
			valide = true;

		return valide;
	}

	/**
	 * Valide que le code postal est valide
	 * 
	 * @param codePostal - Le code postal à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerCodePostal(String codePostal) {
		boolean valide = false;

		codePostal = arrangerString(codePostal);

		if (codePostal.length() == 6)
			valide = true;

		return valide;
	}

	/**
	 * Valide que la string n'est pas vide ou nulle
	 * 
	 * @param nom - Le nom de la string à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerString(String nom) {
		boolean valide = false;

		nom = arrangerString(nom);

		if (nom != null && nom.length() != 0)
			valide = true;

		return valide;
	}

	/**
	 * Enlève les espaces de la string et mets les lettres en majuscule
	 * 
	 * @param code - La string à formatter
	 */
	private String arrangerString(String code) {
		code = code.trim();
		code = code.toUpperCase();
		code = code.replaceAll(" ", "");

		return code;
	}

}
