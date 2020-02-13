package inscription.modele;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Objet transitoire contenant des renseignements sur un Data Client
 * @author Bank-era Corp.
 *
 */
public class DataTransition {
	
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
	 * L'empreinte du client 
	 */
	private byte[] empreinte;
	
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
	public void setNom(String nom) {

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
	public void setPrenom(String prenom) {
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
	public void setEmail(String email) {
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
	public void setDate(LocalDate date) {
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
	public void setAdresse(LocalAdresse adresse) {
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
	public void setNas(String nas) {
		this.nas = nas;
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
	public void setQuestions(ArrayList<String> questions) {
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
	public void setReponses(ArrayList<String> reponses) {
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
	public void setNumero(String numero) {
		this.numero = numero;
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
	public void setEmpreinte(byte[] empreinte) {
		this.empreinte = empreinte;
	}

}
