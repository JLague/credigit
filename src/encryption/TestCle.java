package encryption;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCle {
	TrousseauClef t1;
	TrousseauClef t2;
	TrousseauClef t3;
	TrousseauClef t4;
	TrousseauClef t5;

	String[] clefTest = { "[ 0] k = 000102030405060708090a0b0c0d0e0f", "[ 1] k = d6aa74fdd2af72fadaa678f1d6ab76fe",
			"[ 2] k = b692cf0b643dbdf1be9bc5006830b3fe", "[ 3] k = b6ff744ed2c2c9bf6c590cbf0469bf41",
			"[ 4] k = 47f7f7bc95353e03f96c32bcfd058dfd", "[ 5] k = 3caaa3e8a99f9deb50f3af57adf622aa",
			"[ 6] k = 5e390f7df7a69296a7553dc10aa31f6b", "[ 7] k = 14f9701ae35fe28c440adf4d4ea9c026",
			"[ 8] k = 47438735a41c65b9e016baf4aebf7ad2", "[ 9] k = 549932d1f08557681093ed9cbe2c974e",
			"[10] k = 13111d7fe3944a17f307a78b4d2b30c5" };

	@Before
	public void setUp() {
		t1 = new TrousseauClef(0, "000102030405060708090a0b0c0d0e0f");
		t2 = new TrousseauClef(0, "102030405060708090a0b0c0d0e0f");
		t3 = new TrousseauClef(0, "000102030405060708090a0b0c0d0e0f5345435435");
		t4 = new TrousseauClef(-2, "000102030405060708090a0b0c0d0e0f");
		t5 = new TrousseauClef(15, "000102030405060708090a0b0c0d0e0f");

	}

	@Test
	public void testRound() {
		assertEquals(t1.round, 0);
		assertEquals(t2.round, 0);
		assertEquals(t3.round, 0);
		assertEquals(t4.round, 0);
		assertEquals(t5.round, 0);
	}

	@Test
	public void testInitialisationCle() {
		assertEquals(t1.clef, "000102030405060708090a0b0c0d0e0f");
		assertEquals(t2.clef, "000102030405060708090a0b0c0d0e0f");
		assertEquals(t3.clef, "000102030405060708090a0b0c0d0e0f");
		assertEquals(t4.clef, "000102030405060708090a0b0c0d0e0f");
		assertEquals(t5.clef, "000102030405060708090a0b0c0d0e0f");
	}

	@Test
	public void testGenererProchaine() {
		for (int i = 0; i < 10; i++) {
			// Test pour la clef 1
			assertEquals(t1.toString(), clefTest[i]);
			t1.genererProchaine();
			assertEquals(t1.round, i + 1);
			assertEquals(t1.toString(), clefTest[i+1]);

			// Test pour la clef 2
			assertEquals(t2.toString(), clefTest[i]);
			t2.genererProchaine();
			assertEquals(t2.round, i + 1);
			assertEquals(t2.toString(), clefTest[i+1]);

			// Test pour la clef 3
			assertEquals(t3.toString(), clefTest[i]);
			t3.genererProchaine();
			assertEquals(t3.round, i + 1);
			assertEquals(t3.toString(), clefTest[i+1]);

			// Test pour la clef 4
			assertEquals(t4.toString(), clefTest[i]);
			t4.genererProchaine();
			assertEquals(t4.round, i + 1);
			assertEquals(t4.toString(), clefTest[i+1]);

			// Test pour la clef 5
			assertEquals(t5.toString(), clefTest[i]);
			t5.genererProchaine();
			assertEquals(t5.round, i + 1);
			assertEquals(t5.toString(), clefTest[i+1]);
		}
	}

}
