package encryption;

public class Test {

	public static void bitwiseMultiply(int n1, int n2) {
		int a = n1, b = n2, result = 0;
		while (b != 0) {
			if ((b & 01) != 0) {
				result = result ^ a;
			}
			a <<= 1;
			b >>= 1;
		}
		System.out.println(result);
	}

	public static void bitwiseLongDivision(int a, int d) {
//		d <<= (Integer.toBinaryString(a).length() - Integer.toBinaryString(d).length());
//
//		boolean flag = true;
//
//		if (d < a && d != 0) {
//
//			while (flag) {
//				if ((Integer.toBinaryString(a).length() - Integer.toBinaryString(d).length()) >= 0) {
//					if (a >= d) {
//						int temp = Integer.toBinaryString(a).length();
//
//						a ^= d;
//						d >>= temp - Integer.toBinaryString(a).length();
//					} else
//						flag = false;
//				} else
//					flag = false;
//			}
//
//		} else if (d == a)
//			a = 0;

		// Get leftmost bit of a and d
		int lmba = 0, lmbd = 0;
		while ((a >> lmba++) != 0);
		while ((d >> lmbd++) != 0);

		// Met les variables à leur valeurs réelles
		lmba -= 2;
		lmbd -= 2;
		
		int shift = lmba - lmbd;

		int lmb;
		while (lmba != (lmbd - 1)) {
			
			// Retrieve leftmost bit
			lmb = (1 << lmba--) & a;
			
			// Effectue une étape de la division
			if (lmb != 0) {
				a ^= (d << shift);
			}

			shift--;
		}

		System.out.println(d + "  :  " + a);
	}

	public static void main(String[] args) {

		bitwiseMultiply(84, 13);
		bitwiseMultiply(0x95, 0x8A);
		

		bitwiseLongDivision(932, 0b101100011);
		bitwiseLongDivision(16254, 283);
		bitwiseLongDivision(20226, 283);

	}

}
