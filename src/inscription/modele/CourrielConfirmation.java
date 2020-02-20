package inscription.modele;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class CourrielConfirmation {

	private static final String USER = "credigit.bankera@gmail.com";
	private static final String PASSWORD = "78f6ENKUgp2HHtr33Da4q";

	private Session session;

	public CourrielConfirmation() {
		Task<Void> connexion = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Platform.runLater(() -> {
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
					+ "Si vous rencontrez des difficultés, n'hésitez pas à contacter notre service à la clientèle au 418-803-5698.\n\n"
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
