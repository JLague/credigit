package terminal.modele;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.vandeseer.easytable.RepeatedHeaderTableDrawer;
import org.vandeseer.easytable.settings.HorizontalAlignment;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.Row.RowBuilder;
import org.vandeseer.easytable.structure.Table.TableBuilder;
import org.vandeseer.easytable.structure.cell.TextCell;

import commun.LigneFacture;
import commun.Transaction;

/**
 * Cette classe permet de créer et d'envoyer des factures
 * 
 * @author Bank-era
 *
 */
public class FactureUtil {

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
	private static Session session;

	/**
	 * Constructeur de la connection du serveur d'envoi de courriels
	 */
	private static void connexionCourriel() {

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

	}

	/**
	 * Envoye le courriel au client
	 * 
	 * @param prenom           - Le prenom du client
	 * @param courriel         - Le courriel du client
	 * @param nomEtablissement - Le nom de l'établissment
	 * @param heure            - L'heure de la transaction
	 * @param fichier          - Le fichier de la facture
	 * @return Vrai si le courriel est envoyé sinon faux
	 */
	private static boolean envoyerCourriel(String prenom, String courriel, String nomEtablissement, String heure,
			File fichier) {

		boolean envoye = true;

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USER));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(courriel));
			message.setSubject("Facture de votre achat - " + nomEtablissement + " - " + heure);

			// Crée le corps du message
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Bonjour, \n\n" + "Voici en pièce jointe la facture de" + " votre achat réalisé à "
					+ nomEtablissement + ".\n\nMerci de faire confiance à la grande famille de Crédigit,"
					+ "\n\nCredigit\nUne filiale de Bank-era\n\n\n");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);

			// Attache le fichier au courriel
			messageBodyPart1 = new MimeBodyPart();
			DataSource source = new FileDataSource(fichier);
			messageBodyPart1.setDataHandler(new DataHandler(source));
			messageBodyPart1.setFileName("Facture " + nomEtablissement + "/" + heure + ".pdf");
			multipart.addBodyPart(messageBodyPart1);

			message.setContent(multipart);

			// Envoie le message
			Transport.send(message);

		} catch (MessagingException ex) {
			envoye = false;
		}

		return envoye;
	}

	/**
	 * Permet de créer le fichier de la facture et appelle les méthodes pour
	 * l'envoyer par courriel
	 * 
	 * @param prenomClient   - Le prenom du client
	 * @param nomClient      - Le nom du client
	 * @param courrielClient - Le courriel du client
	 * @param transaction    - La transaction que le client a fait
	 * @return Vrai si le courriel s'envoye sinon faux
	 * @throws IOException - Erreur dans la création du fichier
	 */
	public static boolean envoyerFacture(String prenomClient, String nomClient, String courrielClient,
			Transaction transaction) throws IOException {
		boolean retour = false;

		connexionCourriel();

		String filename = "facture" + prenomClient + ".pdf";
		String workingDirectory = System.getProperty("user.home");
		String absoluteFilePath = workingDirectory + File.separator + filename;

		try (PDDocument doc = new PDDocument()) {

			PDPage myPage = new PDPage();
			doc.addPage(myPage);

			// Créer le titre Facture
			try (PDPageContentStream contTitre = new PDPageContentStream(doc, myPage, AppendMode.APPEND, false)) {
				contTitre.beginText();

				contTitre.setFont(PDType1Font.TIMES_ROMAN, 25);
				contTitre.setLeading(14.5f);

				contTitre.newLineAtOffset(250, 700);

				String line0 = "FACTURE";
				contTitre.showText(line0);
				contTitre.newLine();

				contTitre.endText();

			}

			// Crée les informations générales du client
			try (PDPageContentStream cont = new PDPageContentStream(doc, myPage, AppendMode.APPEND, false)) {
				cont.beginText();
				cont.setFont(PDType1Font.TIMES_ROMAN, 12);
				cont.setLeading(14.5f);

				cont.newLineAtOffset(25, 650);
				String line1 = "Client : " + prenomClient + " " + nomClient;
				cont.showText(line1);
				cont.newLine();

				String line2 = "Endroit de la transaction : " + transaction.getNomEtablissement() + ", "
						+ transaction.getEtablissement().getAdresse();
				cont.showText(line2);
				cont.newLine();

				String line3 = "Heure de la transaction : " + transaction.getHeure();
				cont.showText(line3);
				cont.newLine();

				String line4 = "Numéro de la transaction : " + transaction.getNumero();
				cont.showText(line4);
				cont.newLine();

				String line5 = "Courriel de l'établissment : " + transaction.getEtablissement().getCourriel();
				cont.showText(line5);
				cont.newLine();
				cont.newLine();

				cont.endText();
			}

			// Crée le tableau avec les produits achetés et les montants
			try (PDPageContentStream contentStream = new PDPageContentStream(doc, myPage, AppendMode.APPEND, false)) {

				TableBuilder myTable = Table.builder();
				myTable.addColumnsOfWidth(200, 150, 100, 100);

				RowBuilder row = Row.builder();
				row.add(TextCell.builder().text(String.valueOf("Produit")).borderWidth(1).backgroundColor(Color.GRAY)
						.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text(String.valueOf("Quantité")).borderWidth(1)
								.backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text(String.valueOf("Prix unitaire")).borderWidth(1)
								.backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text(String.valueOf("Prix")).borderWidth(1).borderWidth(1)
								.backgroundColor(Color.GRAY).horizontalAlignment(HorizontalAlignment.CENTER).build());

				myTable.addRow(row.build());

				NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));

				for (int i = 0; i < transaction.ligneFactureArray.size(); i++) {

					RowBuilder row1 = Row.builder();

					LigneFacture lignesFacture = transaction.ligneFactureArray.get(i);

					row1.add(TextCell.builder().text(lignesFacture.getNom()).borderWidth(1).backgroundColor(Color.WHITE)
							.build())
							.add(TextCell.builder().text(String.valueOf(lignesFacture.getQuantite())).borderWidth(1)
									.backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER)
									.build())
							.add(TextCell.builder().text(String.valueOf(cf.format(lignesFacture.getPrixUnitaire())))
									.borderWidth(1).backgroundColor(Color.WHITE)
									.horizontalAlignment(HorizontalAlignment.CENTER).build())
							.add(TextCell.builder().text(lignesFacture.getPrixString()).borderWidth(1)
									.backgroundColor(Color.WHITE).horizontalAlignment(HorizontalAlignment.CENTER)
									.build());

					myTable.addRow(row1.build());

				}

				RowBuilder rowTemp = Row.builder();
				rowTemp.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.GRAY)
						.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.GRAY)
								.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.GRAY)
								.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text("").borderWidth(1).borderWidth(1).backgroundColor(Color.GRAY)
								.horizontalAlignment(HorizontalAlignment.CENTER).build());

				myTable.addRow(rowTemp.build());

				RowBuilder row2 = Row.builder();
				row2.add(TextCell.builder().text(String.valueOf("Sous-Total")).borderWidth(1)
						.backgroundColor(Color.WHITE).build())
						.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text(String.valueOf(cf.format(transaction.getSousTotal())))
								.borderWidth(1).borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER).build());

				myTable.addRow(row2.build());

				RowBuilder row3 = Row.builder();
				row3.add(TextCell.builder().text(String.valueOf("Taxe")).borderWidth(1).backgroundColor(Color.WHITE)
						.build())
						.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text(String.valueOf(cf.format(transaction.getMontantTaxes())))
								.borderWidth(1).borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER).build());

				myTable.addRow(row3.build());

				RowBuilder row4 = Row.builder();
				row4.add(TextCell.builder().text(String.valueOf("Montant Total")).borderWidth(1)
						.backgroundColor(Color.WHITE).build())
						.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER)
								.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text("").borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER)
								.horizontalAlignment(HorizontalAlignment.CENTER).build())
						.add(TextCell.builder().text(String.valueOf(cf.format(transaction.getMontantTotal())))
								.borderWidth(1).borderWidth(1).backgroundColor(Color.WHITE)
								.horizontalAlignment(HorizontalAlignment.CENTER).build());

				myTable.addRow(row4.build());

				// Permet de dessiner le tableau sur plusieurs pages s'il est trop grand
				RepeatedHeaderTableDrawer.builder().table(myTable.build()).startX(25).startY(570F).endY(50F).build()
						.draw(() -> doc, () -> new PDPage(PDRectangle.A4), 25f);

			}

			// Enregistre le document dans un fichier pour l'envoi du courriel
			File fichier = new File(absoluteFilePath);
			doc.save(fichier);
			retour = envoyerCourriel(prenomClient, courrielClient, transaction.getNomEtablissement(),
					transaction.getHeure(), fichier);

			// Supprime le fichier du terminal
			try {
				fichier.delete();

			} catch (Exception e) {

				e.printStackTrace();

			}
		}

		return retour;

	}

}