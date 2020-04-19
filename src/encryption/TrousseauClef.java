package encryption;

import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe s'occupe de garder et générer les clés utilisé par l'algorithme.
 */
public class TrousseauClef {
	int[][] cléActuelle;
	int round;

	int[] w1;
	int[] w2;
	int[] w3;
	int[] w4;

	public TrousseauClef(boolean encrypte, int[][] clé) {
		if (encrypte) {
			round = 10;
		} else {
			round = 1;
		}

		w1 = new int[4];
		w2 = new int[4];
		w3 = new int[4];
		w4 = new int[4];

		cléActuelle = new int[4][4];
		this.cléActuelle = clé;
	}

	public int[][] getProchaine() {

		for (int i = 0; i < 4; i++) {
			w1[i] = cléActuelle[i][0];
			w2[i] = cléActuelle[i][1];
			w3[i] = cléActuelle[i][2];
			w4[i] = cléActuelle[i][3];
		}

		round++;
		return cléActuelle;
	}

	public int[][] getPrecedente() {

		round--;
		return cléActuelle;
	}

	private int substitutionAvant(int k) {

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
	private int substitutionArriere(int k) {

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
		TrousseauClef t1 = new TrousseauClef(true, null);
		System.out.println(t1.substitutionAvant(0x59));
		System.out.println(t1.substitutionArriere(0xCB));
	}

}
