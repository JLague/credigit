package connexion;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 * Classe permettant d'envoyer des courriels
 * 
 * @author Bank-era Corp.
 *
 */
public class CourrielConfirmation {

	/**
	 * String représentant le compte d'envoi
	 */
	private static final String USER = "credigit.bankera@gmail.com";
	/**
	 * String représentant le mot de passe de connection du compte d'envoi
	 */
	private static final String PASSWORD = "78f6ENKUgp2HHtr33Da4q";

	/**
	 * Session de communication avec le serveur
	 */
	private Session session;

	/**
	 * Constructeur de la connection du serveur d'envoi de courriels
	 */
	public CourrielConfirmation() {
		Task<Void> connexion = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Platform.runLater(() -> {
					System.out.println("Création de la connection...");
					Properties properties = System.getProperties();
					properties.put("mail.smtp.host", "smtp.gmail.com");
					properties.put("mail.smtp.port", "587");
					properties.put("mail.smtp.auth", "true");
					properties.put("mail.smtp.starttls.enable", "true");

					session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(USER, PASSWORD);
						}
					});
				});

				return null;
			}

		};

		new Thread(connexion).start();
	}

	/**
	 * Méthode permettant d'envoyer un courriel de bienvenue
	 * 
	 * @param adresse - L'adresse du correspondant
	 * @param Prenom  - Le prénom du correspondant
	 * @return Vrai si le courriel est envoyé avec succès, faux sinon
	 */
	public boolean envoyerCourriel(String adresse, String Prenom) {

		boolean envoye = true;

		String to = adresse;
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USER));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Bienvenue dans la famille!");

			// 3) Crée le corps du message
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Bonjour " + Prenom + ",\n\nLa grande famille de Credigit est heureuse de vous"
					+ " compter comme un nouveau membre! Avec Credigit, vous rendez vos transactions bancaires plus sécuritaires.\n\n"
					+ "Si vous rencontrez des difficultés, n'hésitez pas à contacter notre service à la clientèle au 418-XXX-XXXX.\n\n"
					+ "Continuez à avoir l'argent au bout de votre doigt,\n\n" + "L'équipe de Crédigit\n"
					+ "Une filiale de Bank-era Corp");

			// 4) Set le contenu du message
			message.setContent(new MimeMultipart(messageBodyPart1));

			// 5) Envoir le message
			Transport.send(message);

		} catch (MessagingException ex) {
			envoye = false;
		}

		return envoye;
	}

}
