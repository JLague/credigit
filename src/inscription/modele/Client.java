package inscription.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;

import inscription.exception.ExceptionCreationCompte;
import pos.modele.Transaction;

/**
 * Cette classe permet de créer des clients.
 * 
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
	private String empreinte;

	/**
	 * Les questions de sécurité du client
	 */
	private ArrayList<Questions> questions;

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

	/**
	 * Crée un client
	 * 
	 * @param data - Un objet transitoire contenant les informations sur le client
	 * @throws ExceptionCreationCompte
	 */
	public Client(DataClient data) throws ExceptionCreationCompte {

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
	 * Ajoute les informations du compte tels que la liste de transaction, le solde
	 * et la limite de credit
	 */
	private void ajouterInfoCompte() {
		transaction = new ArrayList<Transaction>();
		setSolde(0);
		setLimiteCredit(500);
	}

	/**
	 * Retourne le nom du client
	 * 
	 * @return Le nom du client
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du client
	 * 
	 * @param nom - Le nom du client à modifier
	 */
	private void setNom(String nom) throws ExceptionCreationCompte {

		if (validerNom(nom)) {
			this.nom = nom.trim();
		} else {
			throw new ExceptionCreationCompte("Votre nom n'est pas valide.");
		}

	}

	/**
	 * Retourne le prénom du client
	 * 
	 * @return Le prénom du client
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie le prénom du client
	 * 
	 * @param prenom - Le prénom du client à modifier
	 */
	private void setPrenom(String prenom) throws ExceptionCreationCompte {

		if (validerNom(prenom)) {
			this.prenom = prenom.trim();
		} else {
			throw new ExceptionCreationCompte("Votre prénom n'est pas valide.");
		}

	}

	/**
	 * Retourne le email du client
	 * 
	 * @return Le email du client
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifie le email du client
	 * 
	 * @param email - Le email du client à modfier
	 */
	private void setEmail(String email) throws ExceptionCreationCompte {

		if (email != null && email.length() != 0) {
			this.email = email;
		} else {
			throw new ExceptionCreationCompte("Votre email n'est pas valide.");
		}
	}

	/**
	 * Retourne la date de naissance du client
	 * 
	 * @return La date de naissance du client
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Modifie la date de naissance du client
	 * 
	 * @param date - La date de naissance du client à modifier
	 */
	private void setDate(LocalDate date) throws ExceptionCreationCompte {

		if (date != null && date.getYear() > 1910) {
			if (validerMajeur(date)) {
				this.date = date;
			} else {
				throw new ExceptionCreationCompte(
						"Vous n'êtes pas majeur. Selon les lois, vous ne pouvez pas appliquer pour une carte "
								+ "de crédit.");
			}

		} else {
			throw new ExceptionCreationCompte("Votre date de naissance n'est pas valide.");
		}

	}

	/**
	 * Retourne l'adresse du client
	 * 
	 * @return L'adresse du client
	 */
	public LocalAdresse getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse du client
	 * 
	 * @param adresse - L'adresse du client à modifier
	 */
	private void setAdresse(LocalAdresse adresse) throws ExceptionCreationCompte {

		if (adresse != null)
			this.adresse = adresse;
		else {
			throw new ExceptionCreationCompte("Votre adresse est vide");
		}

	}

	/**
	 * Retourne le NAS du client
	 * 
	 * @return Le NAS du client
	 */
	public String getNas() {
		return nas;
	}

	/**
	 * Modifie le NAS du client
	 * 
	 * @param nas - Le NAS du client à modifier
	 */
	private void setNas(String nas) throws ExceptionCreationCompte {

		if (validerNas(nas)) {
			this.nas = formatterNumero(nas);
		} else {
			throw new ExceptionCreationCompte("Votre numéro d'assurace sociale n'est pas valide.");
		}
	}

	/**
	 * Retourne le solde du client
	 * 
	 * @return Le solde du client
	 */
	public int getSolde() {
		return solde;
	}

	/**
	 * Modifie le solde du client
	 * 
	 * @param solde - Le solde du client à modifier
	 */
	private void setSolde(int solde) {
		this.solde = solde;
	}

	/**
	 * Retourne la limite de crédit du client
	 * 
	 * @return La limite de crédit du client
	 */
	public int getLimiteCredit() {
		return limiteCredit;
	}

	/**
	 * Modifie la limite de crédit du client
	 * 
	 * @param limiteCredit - La limite de crédit du client à modifier
	 */
	private void setLimiteCredit(int limiteCredit) {
		this.limiteCredit = limiteCredit;
	}

	/**
	 * Retourne l'empreinte du client
	 * 
	 * @return L'empreinte du client
	 */
	public String getEmpreinte() {
		return empreinte;
	}

	/**
	 * Modifie l'empreinte du client
	 * 
	 * @param empreinte - L'empreinte du client à modifier
	 */
	private void setEmpreinte(byte[] empreinte) throws ExceptionCreationCompte {

		if (empreinte != null && empreinte.length != 0) {
			this.empreinte = Base64.getEncoder().encodeToString(empreinte);
			System.out.println("Longueur" + empreinte.length);
			for(byte b : empreinte)
			{
				System.out.print(b);
			}
			System.out.println();
			System.out.println("Empreinte: " + this.empreinte);
		} else {
			throw new ExceptionCreationCompte("Votre empreinte n'est pas valide.");
		}
	}

	/**
	 * Retourne la liste de questions du clients
	 * 
	 * @return La liste de questions du clients
	 */
	public ArrayList<Questions> getQuestions() {
		return questions;
	}

	/**
	 * Modifie la liste de questions du clients
	 * 
	 * @param questions - La liste de questions du clients à modifier
	 */
	private void setQuestions(ArrayList<Questions> questions) throws ExceptionCreationCompte {
		if (questions != null && questions.size() == 2 && !questions.get(0).equals(questions.get(1))) {
			this.questions = questions;
		} else {
			throw new ExceptionCreationCompte(
					"Vos questions de sécurité ne sont pas valides. Assurez-vous d'avoir deux questions "
							+ "de sécurité différentes.");
		}
	}

	/**
	 * Retourne la liste de réponses du client
	 * 
	 * @return La liste de réponses du client
	 */
	public ArrayList<String> getReponses() {
		return reponses;
	}

	/**
	 * Modifie la liste de réponses du client
	 * 
	 * @param reponses - La liste de réponses du client à modifier
	 */
	private void setReponses(ArrayList<String> reponses) throws ExceptionCreationCompte {

		if (reponses != null && reponses.size() == 2 && reponses.get(0).length() != 0
				&& reponses.get(1).length() != 0) {
			this.reponses = reponses;
		} else {
			throw new ExceptionCreationCompte(
					"Vos réponses ne sont pas valides. Assurez-vous d'avoir bien remplir les champs des"
							+ " réponses.");
		}

	}

	/**
	 * Retourne le numéro de téléphone du client
	 * 
	 * @return Le numéro de téléphone du client
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Modifie le numéro de téléphone du client
	 * 
	 * @param numero - Le numéro de téléphone du client à modifier
	 */
	private void setNumero(String numero) throws ExceptionCreationCompte {

		if (validerNumero(numero)) {
			this.numero = formatterNumero(numero);
		} else {
			throw new ExceptionCreationCompte("Votre numéro de téléphone n'est pas valide.");
		}
	}

	/**
	 * Retourne les transactions du client
	 * 
	 * @return Les transactions du client
	 */
	public ArrayList<Transaction> getTransaction() {
		return transaction;
	}

	/**
	 * Valider que le nom n'est pas nulle ou vide
	 * 
	 * @param nom - Le nom à valider
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerNom(String nom) {
		return nom != null && nom.length() != 0;
	}

	/**
	 * Valide que la personne est majeure
	 * 
	 * @param date - La date de naissance de la personne
	 * @return Vrai si majeur, sinon faux
	 */
	private boolean validerMajeur(LocalDate date) {
		boolean majeur = false;

		if (LocalDateTime.now().toLocalDate().getYear() - date.getYear() > MAJORITE) {
			majeur = true;
		} else if (LocalDateTime.now().toLocalDate().getYear() - date.getYear() > MAJORITE - 1) {
			if (LocalDateTime.now().toLocalDate().getMonthValue() - date.getMonthValue() > 0) {
				majeur = true;
			} else if (LocalDateTime.now().toLocalDate().getMonthValue() - date.getMonthValue() == 0) {
				if (LocalDateTime.now().toLocalDate().getDayOfMonth() - date.getDayOfMonth() > 0) {
					majeur = true;
				}
			}
		}

		return majeur;
	}

	/**
	 * Valide le numéro d'assurance sociale
	 * 
	 * @param Nas - Le numéro d'assurance sociale
	 * @return Vrai si valide sinon faux
	 */
	private boolean validerNas(String Nas) {
		return Nas != null && formatterNumero(Nas).length() == 9;
	}

	/**
	 * Valide que le numéro de téléphone est entre 10 et 11 chiffre
	 * 
	 * @param numero
	 * @return
	 */
	private boolean validerNumero(String numero) {
		boolean valide = false;

		if (numero != null) {
			numero = formatterNumero(numero);

			if (numero.length() >= 10 && numero.length() <= 11)
				valide = true;
		}

		return valide;
	}

	/**
	 * Formatte la String reçue en paramètre
	 * 
	 * @param numero - La String à formatter
	 * @return La String formattée
	 */
	private String formatterNumero(String numero) {
		ArrayList<Character> caracter = new ArrayList<>();

		for (int i = 0; i < numero.length(); i++) {
			caracter.add((Character) numero.charAt(i));
		}

		for (int i = 0; i < caracter.size(); i++) {
			if (!Character.isDigit(caracter.get(i))) {
				caracter.remove(i);
				i--;
			}
		}

		String temp = "";

		for (int i = 0; i < caracter.size(); i++) {
			temp += caracter.get(i);
		}

		return temp;
	}

}
