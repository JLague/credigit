package inscription.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import inscription.modele.Client;
import inscription.modele.DataTransition;
import inscription.modele.ExceptionCreationCompte;
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
			client1 = new Client(new DataTransition("Duchesne", "Christophe", "youremail@here.com", localDate,
					new LocalAdresse("555 rue Yolo", 12, "G5C 4F7", "Québec", "Québec", "Canada"), "111 111 111", questions,
					reponses, "555-555-5555", empreinte));
		} catch (ExceptionCreationCompte e) {
			 fail("Ne devrait jamais arriver ici");
		}
		
		try {
			LocalDate localDate = LocalDate.of(1987, 6, 30);
			ArrayList<Questions> questions = new ArrayList<Questions>();
			ArrayList<String> reponses = new ArrayList<String>();
			questions.add(Questions.RUE);
			questions.add(Questions.COURS);
			reponses.add("Intégrale");
			reponses.add("Mathématiques");
			byte[] empreinte = new byte[2];
			empreinte[0] = 1;
			empreinte[1] = 1;
			client2 = new Client(new DataTransition("LeRond d'Alembert", "Jean", "mathislife@math.com", localDate,
					new LocalAdresse("Pi/2 rue du sinus", "G5C 4F7", "Cosinus", "Trigo", "Fonction"), "123 456 789", questions,
					reponses, "555-555-5598", empreinte));
		} catch (ExceptionCreationCompte e) {
			 fail("Ne devrait jamais arriver ici");
		}
	}

	@Test
	public void testGetNom() {
		assertTrue(client1.getNom().equals("Duchesne"));
		assertTrue(client2.getNom().equals("LeRond d'Alembert"));
	}

	@Test
	public void testGetPrenom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdresse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNas() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSolde() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLimiteCredit() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmpreinte() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQuestions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReponses() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumero() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumeroInscriptionDuClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterTransaction() {
		fail("Not yet implemented");
	}

}
