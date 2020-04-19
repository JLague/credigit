package encryption;

/**
 * Cette classe s'occupe de garder et générer les clés utilisé par l'algorithme.
 */
public class TrousseauClef {
	int[][] cléActuelle;
	int round;

	public TrousseauClef(boolean encrypte, int[][] clé) {
		if (encrypte) {
			round = 10;
		} else {
			round = 1;
		}

		cléActuelle = new int[4][4];
		this.cléActuelle = clé;
	}

	public int[][] getProchaine() {

		for (int i = 0; i < cléActuelle[0].length; i++) {
			cléActuelle[i][0] = cléActuelle[i][0] + fonctionG(cléActuelle[0])[i];
			cléActuelle[i][1] = Math.floorMod(cléActuelle[i][0] ^ cléActuelle[i][1], 256);
			cléActuelle[i][2] = Math.floorMod(cléActuelle[i][1] ^ cléActuelle[i][2], 256);
			cléActuelle[i][3] = Math.floorMod(cléActuelle[i][2] ^ cléActuelle[i][3], 256);
		}

		round++;
		return cléActuelle;
	}

	public int[][] getPrecedente() {

		round--;
		return cléActuelle;
	}

	private int[] fonctionG(int[] w) {
		int[] mot = { w[1], w[2], w[3], w[0] };

		for (int i = 0; i < w.length; i++) {
			mot[i] = subsAvant(mot[i]);
		}

		mot[0] = mot[0] ^ round;

		return mot;
	}

	// Volé fonction temporaire // TODO remplacer
	private static int subsAvant(int k) {

		int[] t = new int[256];
		for (int i = 0, x = 1; i < 256; i++) {
			t[i] = x;
			x ^= (x << 1) ^ ((x >>> 7) * 0x11B);
		}
		int[] S = new int[256];
		S[0] = 0x63;
		for (int i = 0; i < 255; i++) {
			int x = t[255 - i];
			x |= x << 8;
			x ^= (x >> 4) ^ (x >> 5) ^ (x >> 6) ^ (x >> 7);
			S[t[i]] = (x ^ 0x63) & 0xFF;
		}
		return S[k];
	}

	// Volé fonction temporaire // TODO remplacer
	private static int subsArriere(int k) {

		int[] t = new int[256];
		for (int i = 0, x = 1; i < 256; i++) {
			t[i] = x;
			x ^= (x << 1) ^ ((x >>> 7) * 0x11B);
		}
		int[] S = new int[256];
		S[0] = 0x63;
		for (int i = 0; i < 255; i++) {
			int x = t[255 - i];
			x |= x << 8;
			x ^= (x >> 4) ^ (x >> 5) ^ (x >> 6) ^ (x >> 7);
			S[t[i]] = (x ^ 0x63) & 0xFF;
		}

		return t[k];
	}

	public static void main(String[] args) {
		int[][] clé = { { 0x00, 0x01, 0x02, 0x03 }, { 0x04, 0x05, 0x06, 0x07 }, { 0x08, 0x09, 0x0a, 0x0b },
				{ 0x0c, 0x0d, 0x0e, 0x0f } };
		TrousseauClef t1 = new TrousseauClef(false, clé);

		
			System.out.println(MatriceUtilitaires.toStringMat(t1.getProchaine()));
			System.out.println(MatriceUtilitaires.toStringMat(t1.getPrecedente()));
			System.out.println(MatriceUtilitaires.toStringMat(t1.getPrecedente()));

	}
}
