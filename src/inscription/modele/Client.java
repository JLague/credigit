package inscription.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import pos.modele.Transaction;

/**
 * Cette classe permet de créer des clients.
 * @author Bank-era Corp.
 *
 */
public class Client {

	public static final int MAJORITE = 18;
	
	/**
	 * Le nom du client
	 */
	private String nom;
	
	/**
	 * Le prénom du client
	 */
	private String prenom;
	
	/**
	 * L'email du client
	 */
	private String email;
	
	/**
	 * La date de naissance du client
	 */
	private LocalDate date;
	
	/**
	 * L'adresse du client
	 */
	private LocalAdresse adresse;
	
	/**
	 * Le numéro d'assurance social du client
	 */
	private String nas;
	
	/**
	 * Le solde du client
	 */
	private int solde;
	
	/**
	 * La limite de crédit du client
	 */
	private int limiteCredit;
	
	/**
	 * L'empreinte du client 
	 */
	private byte[] empreinte;
	
	/**
	 * Les questions de sécurité du client
	 */
	private ArrayList<String> questions;
	
	/**
	 * Les réponses des questions de sécurité du client
	 */
	private ArrayList<String> reponses;
	
	/**
	 * Le numéro de téléphone du client
	 */
	private String numero;
	
	/**
	 * Les transactions du client
	 */
	private ArrayList<Transaction> transaction;
	
	

	public Client(DataTransition data) {
		
		setNom(data.getNom());
		setPrenom(data.getPrenom());
		setEmail(data.getEmail());
		setDate(data.getDate());
		setAdresse(data.getAdresse());
		setNas(data.getNas());
		setQuestions(data.getQuestions());
		setReponses(data.getReponses());
		setNumero(data.getNumero());
		setEmpreinte(data.getEmpreinte());
		
		ajouterInfoCompte();
		
	}
	
	/**
	 * Ajoute les informations du compte tels que la liste de transaction, le solde et la limite de credit
	 */
	private void ajouterInfoCompte()
	{
		transaction = new ArrayList<Transaction>();
		setSolde(0);
		setLimiteCredit(500);
	}

	/**
	 * Retourne le nom du client
	 * @return Le nom du client
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du client
	 * @param nom - Le nom du client à modifier
	 */
	private void setNom(String nom) {
		
		if(validerNom(nom))
			this.nom = nom;
	}

	/**
	 * Retourne le prénom du client
	 * @return Le prénom du client
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie le prénom du client
	 * @param prenom - Le prénom du client à modifier
	 */
	private void setPrenom(String prenom) {
		
		if(validerNom(prenom))
			this.prenom = prenom;
	}

	/**
	 * Retourne le email du client
	 * @return Le email du client
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifie le email du client
	 * @param email - Le email du client à modfier
	 */
	private void setEmail(String email) {
		
		if(email != null && email.length() != 0)
			this.email = email;
	}

	/**
	 * Retourne la date de naissance du client
	 * @return La date de naissance du client
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Modifie la date de naissance du client
	 * @param date - La date de naissance du client à modifier
	 */
	private void setDate(LocalDate date) {
		
		if(date != null && date.getYear() > 1910 && validerMajeur(date))
			this.date = date;
	}

	/**
	 * Retourne l'adresse du client
	 * @return L'adresse du client
	 */
	public LocalAdresse getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse du client
	 * @param adresse - L'adresse du client à modifier
	 */
	private void setAdresse(LocalAdresse adresse) {
		
		if(adresse != null)
			this.adresse = adresse;
	}

	/**
	 * Retourne le NAS du client
	 * @return Le NAS du client
	 */
	public String getNas() {
		return nas;
	}

	/**
	 * Modifie le NAS du client
	 * @param nas - Le NAS du client à modifier
	 */
	private void setNas(String nas) {
		
		if(validerNas(nas))
			this.nas = nas;
	}
	
	/**
	 * Retourne le solde du client
	 * @return Le solde du client
	 */
	public int getSolde() {
		return solde;
	}

	/**
	 * Modifie le solde du client
	 * @param solde - Le solde du client à modifier
	 */
	private void setSolde(int solde) {
		this.solde = solde;
	}

	/**
	 * Retourne la limite de crédit du client
	 * @return La limite de crédit du client
	 */
	public int getLimiteCredit() {
		return limiteCredit;
	}

	/**
	 * Modifie la limite de crédit du client
	 * @param limiteCredit - La limite de crédit du client à modifier
	 */
	private void setLimiteCredit(int limiteCredit) {
		this.limiteCredit = limiteCredit;
	}

	/**
	 * Retourne l'empreinte du client
	 * @return L'empreinte du client
	 */
	public byte[] getEmpreinte() {
		return empreinte;
	}

	/**
	 * Modifie l'empreinte du client
	 * @param empreinte - L'empreinte du client à modifier
	 */
	private void setEmpreinte(byte[] empreinte) {
		
		if(empreinte != null && empreinte.length != 0)
			this.empreinte = empreinte;
	}


	/**
	 * Retourne la liste de questions du clients
	 * @return La liste de questions du clients
	 */
	public ArrayList<String> getQuestions() {
		return questions;
	}

	/**
	 * Modifie la liste de questions du clients
	 * @param questions - La liste de questions du clients à modifier
	 */
	private void setQuestions(ArrayList<String> questions) {
		
		if(questions != null && questions.size() == 2)
			this.questions = questions;
	}

	/**
	 * Retourne la liste de réponses du client
	 * @return La liste de réponses du client
	 */
	public ArrayList<String> getReponses() {
		return reponses;
	}

	/**
	 * Modifie la liste de réponses du client
	 * @param reponses - La liste de réponses du client à modifier
	 */
	private void setReponses(ArrayList<String> reponses) {
		
		if(reponses != null && reponses.size() == 2)
			this.reponses = reponses;
	}

	/**
	 * Retourne le numéro de téléphone du client
	 * @return Le numéro de téléphone du client
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Modifie le numéro de téléphone du client
	 * @param numero - Le numéro de téléphone du client à modifier
	 */
	private void setNumero(String numero) {
		
		//À faire
		this.numero = numero;
	}

	/**
	 * Retourne les transactions du client
	 * @return Les transactions du client
	 */
	public ArrayList<Transaction> getTransaction() {
		return transaction;
	}

	/**
	 * Valider que le nom n'est pas nulle ou vide
	 * @param nom - Le nom à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerNom(String nom)
	{
		boolean valide = false;
		
		nom = arrangerString(nom);

		if(nom != null && nom.length() != 0)
			valide = true;
		
		return valide;
	}
	
	/**
	 * Enlève les espaces de la string et mets les lettres en majuscule
	 * @param code - La string à formatter
	 */
	private String arrangerString(String code)
	{
		code = code.trim();
		code = code.toUpperCase();

		return code;
	}
	
	/**
	 * Valide que la personne est majeure
	 * 
	 * @param date - La date de naissance de la personne
	 * @return Vrai si majeur, sinon faux
	 */
	private boolean validerMajeur(LocalDate date)
	{
		boolean majeur = false;
		
		if(LocalDateTime.now().toLocalDate().getYear() - date.getYear() > MAJORITE)
		{
			majeur = true;
		}
		else if(LocalDateTime.now().toLocalDate().getYear() - date.getYear() > MAJORITE -1)
		{
			if(LocalDateTime.now().toLocalDate().getMonthValue() - date.getMonthValue() > 0)
			{
				majeur = true;
			}
			else if(LocalDateTime.now().toLocalDate().getMonthValue() - date.getMonthValue() == 0)
			{
				if(LocalDateTime.now().toLocalDate().getDayOfMonth() - date.getDayOfMonth() > 0)
				{
					majeur = true;
				}
			}
		}
		
		return majeur;
	}
	
	private boolean validerNas(String Nas)
	{
		//Valider que c'est 9 chiffres?
		
		boolean valide = false;
		
		Nas = Nas.replaceAll(" ", "");
		
		if(Nas.length() == 9)
		{
			valide = true;
		}
		
		return valide;
	}
}
