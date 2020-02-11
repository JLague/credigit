package inscription.modele;

/**
 * Classe permettant de créer un objet représantant une adresse
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
	private int appartement;
	
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
	 * @param adresse - L'adresse
	 * @param appartement - Le numéro d'appartement
	 * @param codePostal - Le code postal
	 * @param ville - La ville 
	 * @param etat - L'état
	 * @param pays - Le pays
	 */
	public LocalAdresse(String adresse, int appartement, String codePostal, String ville, String etat, String pays)
	{
		
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
	 * @param adresse - L'adresse
	 * @param codePostal - Le code postal
	 * @param ville - La ville 
	 * @param etat - L'état
	 * @param pays - Le pays
	 */
	public LocalAdresse(String adresse, String codePostal, String ville, String etat, String pays)
	{
		
		setAdresse(adresse);
		appartement = -1;
		setCodePostal(codePostal);
		setVille(ville);
		setEtat(etat);
		setPays(pays);
	}

	/**
	 * Retourne l'adresse du client
	 * @return L'adresse du client
	 */
	public String getAdresse() 
	{
		return adresse;
	}

	/**
	 * Modifie l'adresse du client
	 * @param adresse - La nouvelle adresse du client
	 */
	private void setAdresse(String adresse) 
	{
		if(validerAdresse(adresse))
		{
			this.adresse = adresse;
		}
		
	}

	/**
	 * Retourne le numéro d'appartement du client
	 * @return Le numéro d'appartement du client
	 */
	public int getAppartement() 
	{
		return appartement;
	}

	/**
	 * Modifie le numéro d'appartement du client
	 * @param appartement - Le nouveau numéro d'appartement du client
	 */
	private void setAppartement(int appartement) 
	{
		if(validerAppartement(appartement))
		{
			this.appartement = appartement;
		}
		
	}

	/**
	 * Retourne le code postal du client
	 * @return Le code postal du client
	 */
	public String getCodePostal() 
	{
		return codePostal;
	}

	/**
	 * Modifie le code postal du client
	 * @param codePostal - Le nouveau code postal du client
	 */
	private void setCodePostal(String codePostal) 
	{
		if(validerCodePostal(codePostal))
		{
			
			this.codePostal = arrangerString(codePostal);
		}
		
	}

	/**
	 * Modifie la ville du client
	 * @return La ville du client
	 */
	public String getVille() 
	{
		return ville;
	}
	
	/**
	 * Modifie la ville du client
	 * @param ville - la nouvelle ville du client
	 */
	private void setVille(String ville) 
	{
		if(validerString(ville))
		{
			this.ville = arrangerString(ville);
		}
	}

	/**
	 * Retourne l'état du client
	 * @return L'état du client
	 */
	public String getEtat() 
	{
		return etat;
	}

	/**
	 * Modifie l'état du client
	 * @param etat - Le nouvel état du client
	 */
	private void setEtat(String etat) 
	{
		if(validerString(etat))
		{
			this.etat = arrangerString(etat);
		}
	}

	/**
	 * Retourne le pays du client
	 * @return Le pays du client
	 */
	public String getPays()
	{
		return pays;
	}

	/**
	 * Modifie le pays du client
	 * @param pays - Le nouvel état du client
	 */
	private void setPays(String pays) 
	{
		if(validerString(pays))
		{
			this.pays = arrangerString(pays);
		}
	}
	
	/**
	 * Valide que l'adresse n'est pas nulle et n'est pas vide
	 * @param adresse - L'adresse à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerAdresse(String adresse)
	{
		boolean valide = false;
		
		if(adresse != null && adresse.length() != 0)
			valide = true;
		
		return valide;
	}
	
	/**
	 * Valide que le numéro d'appartement est plus grand que 0
	 * @param appartement - Le numéro d'appartement à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerAppartement(int appartement)
	{
		boolean valide = false;
		
		if(appartement > 0)
			valide = true;
		
		return valide;
	}
	
	/**
	 * Valide que le code postal est valide
	 * @param codePostal - Le code postal à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerCodePostal(String codePostal)
	{
		boolean valide = false;
		
		codePostal = arrangerString(codePostal);

		if(codePostal.length() == 6)
			valide = true;
		
		return valide;
	}
	
	/**
	 * Valide que la string n'est pas vide ou nulle
	 * @param nom - Le nom de la string à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerString(String nom)
	{
		boolean valide = false;
		
		nom = arrangerString(nom);

		if(nom != null && nom.length() != 0)
			valide = true;
		
		return valide;
	}
	
	/**
	 * Enlève les espaces du code postal et mets les lettres en majuscule
	 * @param code
	 */
	private String arrangerString(String code)
	{
		code.trim();
		code.toUpperCase();
		code.replaceAll(" ", "");
		
		return code;
	}

}
