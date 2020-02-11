package inscription.modele;

import java.time.LocalDate;

/**
 * Cette classe permet de créer des clients.
 * @author Bank-era Corp.
 *
 */
public class DataClient {

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
	 * La banque du client
	 */
	private Banque banque;
	
	/**
	 * Les questions de sécurité du client
	 */
	private String[] questions;
	
	/**
	 * Les réponses des questions de sécurité du client
	 */
	private String[] reponses;
	
	/**
	 * Le numéro de téléphone du client
	 */
	private String numero;
	
	/**
	 * Les transactions du client
	 */
	private Transaction[] transaction;
	
	

	public DataClient(DataTransition data) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date = date;
		this.adresse = adresse;
		this.nas = nas;
		this.solde = solde;
		this.limiteCredit = limiteCredit;
		this.empreinte = empreinte;
		this.banque = banque;
		this.questions = questions;
		this.reponses = reponses;
		this.numero = numero;
		this.transaction = transaction;
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
		this.empreinte = empreinte;
	}

	/**
	 * Retourne la banque du client
	 * @return La banque du client
	 */
	public Banque getBanque() {
		return banque;
	}

	/**
	 * Modifie la banque du client
	 * @param banque - La banque du client à modifier
	 */
	private void setBanque(Banque banque) {
		this.banque = banque;
	}

	/**
	 * Retourne la liste de questions du clients
	 * @return La liste de questions du clients
	 */
	public String[] getQuestions() {
		return questions;
	}

	/**
	 * Modifie la liste de questions du clients
	 * @param questions - La liste de questions du clients à modifier
	 */
	private void setQuestions(String[] questions) {
		this.questions = questions;
	}

	/**
	 * Retourne la liste de réponses du client
	 * @return La liste de réponses du client
	 */
	public String[] getReponses() {
		return reponses;
	}

	/**
	 * Modifie la liste de réponses du client
	 * @param reponses - La liste de réponses du client à modifier
	 */
	private void setReponses(String[] reponses) {
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
		this.numero = numero;
	}

	/**
	 * Retourne les transactions du client
	 * @return Les transactions du client
	 */
	public Transaction[] getTransaction() {
		return transaction;
	}

	
	
}
