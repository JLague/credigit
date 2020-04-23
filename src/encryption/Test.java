package encryption;

public class Test {

	public static int bitwiseMultiply(int n1, int n2) {
		int a = n1, b = n2, result = 0;
		while (b != 0) {
			if ((b & 01) != 0) {
				result = result ^ a;
			}
			a <<= 1;
			b >>= 1;
		}
		return result;
	}

	public static int[] bitwiseLongDivision(int a, int d) {

		// Get leftmost bit of a and d
		int lmba = 0, lmbd = 0;
		while ((a >> lmba++) != 0);
		while ((d >> lmbd++) != 0);

		// Met les variables à leur valeurs réelles
		lmba -= 2;
		lmbd -= 2;
		
		int shift = lmba - lmbd;
		int quotient = 0;

		int lmb;
		while (lmba != (lmbd - 1)) {
			
			// Retrieve leftmost bit
			lmb = (1 << lmba--) & a;
			
			quotient <<= 1;
			// Effectue une étape de la division
			if (lmb != 0) {
				a ^= (d << shift);
				quotient +=1;
			}


			shift--;
		}
		
		int[] temp = {a, quotient};
		return temp;
		
	}
	
//	public static int getInverseMultiplicatif(int a)
//	{
//		boolean flag = true;
//		int inverse = 0;
//		
////		for(int i = 2; i <= 256 && flag; i++)
////		{
//			System.out.println(1);
//			if(bitwiseLongDivision(bitwiseMultiply(a,1), 355) == 0b1)
//			{
//				inverse = 1;
//				flag = false;
//			}
//			System.out.println("ok");
////		}
//		
//		return inverse;
//	}
	
	
	public static int[] xpgcd(int a, int b)
	{
		int[] tab = null;
		
		if (b == 0)
		{
			if( a > 0)
			{
				 tab =  new int[3];
				 tab[0] = 1;
				 tab[1] = 0;
				 tab[2] = a;
			}
			else
			{
				tab =  new int[3];
				 tab[0] = -1;
				 tab[1] = 0;
				 tab[2] = -a;
			}
				
		}
		
		
		if(tab == null)
		{
			int[] res = bitwiseLongDivision(a,b);
			
			int q = res[1];
			int r = res[0];
			
			int[] temp = xpgcd(b,r);
			
			tab =  new int[3];
			 tab[0] = temp[1];
			 tab[1] = temp[0] - q * temp[1];
			 tab[2] = temp[2];
		}
		
		
		return tab;	
		
	}

	public static void main(String[] args) {

//		bitwiseMultiply(84, 13);
//		bitwiseMultiply(0x95, 0x8A);
//		
//
//		bitwiseLongDivision(932, 0b101100011);
//		bitwiseLongDivision(16254, 283);
//		bitwiseLongDivision(20226, 283);
//		bitwiseLongDivision(20226, 283);
//		
//		System.out.println(getInverseMultiplicatif(13));
//		System.out.println(bitwiseLongDivision(bitwiseMultiply(13, 67), 355));
//		System.out.println(bitwiseLongDivision(bitwiseMultiply(13, 68), 355));
//		System.out.println(bitwiseLongDivision(bitwiseMultiply(13, 69), 355));
//		System.out.println(bitwiseLongDivision(bitwiseMultiply(13, 70), 355));
//		System.out.println(bitwiseLongDivision(bitwiseMultiply(13, 71), 355));
		
//		int[] tab = xpgcd(16,429);
//		
//		System.out.println(tab[0]);
//		System.out.println(tab[1]);
//		System.out.println(tab[2]);
//		
//		int[] tab1 = xpgcd(355,84);
//		
//		System.out.println(tab1[0]);
//		System.out.println(tab1[1]);
//		System.out.println(tab1[2]);
		
		int[] tab2 = xpgcd(355,84);
		
		System.out.println(tab2[0]);
		System.out.println(tab2[1]);
		System.out.println(tab2[2]);
		
		byte b = (byte) tab2[1];
		
		int s = b ^ (b >>> 7) ^ (b >>> 6) ^ (b >>> 5) ^ (b >>> 4) ^ 99;
		
		System.out.println(s);
		
		int j = (bitwiseLongDivision(bitwiseMultiply(b,31 ), 257))[0] ^ 99;
		
		System.out.println(j);
		
		int k = (b * 31)%257 ^ 99;
		
		System.out.println(k);
//		System.out.println(bitwiseLongDivision(bitwiseMultiply(13, 154), 355)[0]);
	}

}
