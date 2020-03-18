package inscription.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import inscription.exception.ExceptionCreationCompte;
import inscription.modele.Client;
import inscription.modele.DataClient;
import inscription.modele.LocalAdresse;
import inscription.modele.Questions;

/**
 * Classe testant la classe Client
 * 
 * @author Bank-era Corp.
 *
 */
public class ClientTest {

	Client client1, client2;

	@Before
	public void testClient() {

		try {
			LocalDate localDate = LocalDate.of(2002, 1, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "youremail@here.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "    111 111 111",
					questions, reponses, "555-555-5555", empreinte));

		} catch (ExceptionCreationCompte e) {
			fail("Ne devrait jamais arriver ici");
		}

		try {
			LocalDate localDate = LocalDate.of(2002, 2, 17);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.RUE);
			questions.add(Questions.COURS);
			reponses.add("Intégrale");
			reponses.add("Mathématiques");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 0;
			client2 = new Client(new DataClient("LeRond d'Alembert", "Jean", "mathislife@math.com", localDate,

					new LocalAdresse("Pi/2 rue du sinus", "G5C 4F7", "Cosinus", "Trigo", "Fonction"), "123 456 789",
					questions, reponses, "1-555-555-5598", empreinte));
		} catch (ExceptionCreationCompte e) {
			fail("Ne devrait jamais arriver ici");
		}
	}

	@Test
	public void testsInvalides() {

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient(null, "Christophe", "youremail@here.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Nom null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("", "Christophe", "youremail@here.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Nom vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", null, "youremail@here.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Prénom null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "", "youremail@here.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Pénom vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", null, localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Email null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Email vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", null,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Date null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(1910, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Année trop ancienne");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2005, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Pas Majeur pour l'année");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			Calendar cal = Calendar.getInstance();
			LocalDate localDate = LocalDate.of(2002, cal.get(Calendar.MONTH) + 2, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Pas Majeur pour le mois");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			Calendar cal = Calendar.getInstance();
			LocalDate localDate = LocalDate.of(2002, cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH) + 1);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Pas Majeur pour le jour");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate, null,
					"111 111 111", questions, reponses, "555-555-5555", empreinte));

			fail("Adresse null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), null, questions,
					reponses, "555-555-5555", empreinte));

			fail("NAS null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "9111111111",
					questions, reponses, "555-555-5555", empreinte));

			fail("NAS trop long");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "11111111",
					questions, reponses, "555-555-5555", empreinte));

			fail("NAS trop court");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111", null,
					reponses, "555-555-5555", empreinte));

			fail("Question null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ANIMAL);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Questions identiques");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "555-555-5555", empreinte));

			fail("1 seule question");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, null, "555-555-5555", empreinte));

			fail("Réponses null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "555-555-5555", empreinte));

			fail("1 seule réponse");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "555-555-5555", empreinte));

			fail("La première réponse est vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "555-555-5555", empreinte));

			fail("La seconde réponse est vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, null, empreinte));

			fail("Numéro de téléphone null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "555-555-555512", empreinte));

			fail("Numéro de téléphone trop long");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "55-555-5555", empreinte));

			fail("Numéro de téléphone trop court");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "555-555-5555", null));

			fail("Empreinte null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			LocalDate localDate = LocalDate.of(2001, 4, 24);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.ANIMAL);
			questions.add(Questions.ECOLE);
			reponses.add("Chien");
			reponses.add("St-Pierre");
			byte[] empreinte = new byte[0];
			client1 = new Client(new DataClient("Duchesne", "Christophe", "myemail@gmail.com", localDate,
					new LocalAdresse("555 rue Yolo", "12", "G5C 4F7", "Québec", "Québec", "Canada"), "111111111",
					questions, reponses, "555-555-5555", empreinte));

			fail("Empreinte vide");
		} catch (ExceptionCreationCompte e) {

		}

	}

	@Test
	public void testGetNom() {
		assertTrue(client1.getNom().equals("Duchesne"));
		assertTrue(client2.getNom().equals("LeRond d'Alembert"));
	}

	@Test
	public void testGetPrenom() {
		assertTrue(client1.getPrenom().equals("Christophe"));
		assertTrue(client2.getPrenom().equals("Jean"));
	}

	@Test
	public void testGetEmail() {
		assertTrue(client1.getEmail().equals("youremail@here.com"));
		assertTrue(client2.getEmail().equals("mathislife@math.com"));
	}

	@Test
	public void testGetDate() {
		assertTrue(client1.getDate().equals(LocalDate.of(2002, 1, 24)));
		assertTrue(client2.getDate().equals(LocalDate.of(2002, 2, 17)));
	}

	@Test
	public void testGetAdresse() {
		assertTrue(client1.getAdresse() != null);
		assertTrue(client2.getAdresse() != null);

	}

	@Test
	public void testGetNas() {
		assertTrue(client1.getNas().equals("111111111"));
		assertTrue(client2.getNas().equals("123456789"));
	}

	@Test
	public void testGetSolde() {
		assertTrue(client1.getSolde() == 0);
		assertTrue(client2.getSolde() == 0);
	}

	@Test
	public void testGetLimiteCredit() {
		assertTrue(client1.getLimiteCredit() == 500);
		assertTrue(client2.getLimiteCredit() == 500);
	}

	@Test
	public void testGetEmpreinte() {
		assertTrue(client1.getEmpreinte()[0] == 1);
		assertTrue(client1.getEmpreinte()[1] == 1);
		assertTrue(client2.getEmpreinte()[0] == 1);
		assertTrue(client2.getEmpreinte()[1] == 0);
	}

	@Test
	public void testGetQuestions() {
		assertTrue(client1.getQuestions().get(0) == Questions.ANIMAL);
		assertTrue(client1.getQuestions().get(1) == Questions.ECOLE);

		assertTrue(client2.getQuestions().get(0) == Questions.RUE);
		assertTrue(client2.getQuestions().get(1) == Questions.COURS);

	}

	@Test
	public void testGetReponses() {
		assertTrue(client1.getReponses().get(0).equals("Chien"));
		assertTrue(client1.getReponses().get(1).equals("St-Pierre"));

		assertTrue(client2.getReponses().get(0).equals("Intégrale"));
		assertTrue(client2.getReponses().get(1).equals("Mathématiques"));
	}

	@Test
	public void testGetNumero() {
		assertTrue(client1.getNumero().equals("5555555555"));
		assertTrue(client2.getNumero().equals("15555555598"));
	}

	@Test
	public void testGetTransaction() {
		assertTrue(client1.getTransaction() != null);
		assertTrue(client2.getTransaction() != null);
	}
}
