package encryption;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Classe utilitaires pour les calculs mathématiques
 *
 * @author Dylan Renaud
 * @author Justin Lagüe
 */
public class MathUtilitaires
{

	/**
	 * Calcul de la factorielle d'une valeur > ou = à 1. Doit être récursif.
	 *
	 * @param valFact la valeur
	 *
	 * @return la valeur de la factorielle pour la valeur reçue. 1 si la valeur
	 *         reçue n'est pas valide.
	 */
	// TODO fact - Compléter le code de la méthode
	public static double fact(int valFact)
	{
		return valFact > 1 ? valFact * fact(valFact - 1) : 1;
	}

	/**
	 * Retourne le modulo de valeurs positive ou négative reçue.
	 *
	 * Attention : Vérifier vos résultats surtout pour des valeurs négatives!!!
	 * Voir la définition mathématique (partie entière E) sur wiki
	 * https://fr.wikipedia.org/wiki/Modulo_(op%C3%A9ration)
	 * 
	 * Ce calculateur donne les bons résultats:
	 * https://www.miniwebtool.com/modulo-calculator/
	 *
	 * @param pVal la valeur
	 * @param pMod le modulo
	 *
	 * @return la valeur du modulo.
	 *
	 * @throws ArithmeticException pour la division par zéro.
	 */
	// TODO modulo - Compléter le code de la méthode
	public static int modulo(int pVal, int pMod) throws ArithmeticException
	{
		int valeur = 0;
		if (pMod != 0)
			valeur = (pVal % pMod + pMod) % pMod;

		else
			throw new ArithmeticException();

		return valeur;
	}

	/**
	 * Retourne un ensemble des diviseurs positifs distincts d'une valeur
	 * entière positive ou négative. Utilise méthode modulo. Ex. 12 ou -12
	 * donnerait 1,2,3,4,6,12
	 *
	 * @param pVal: la valeur
	 *
	 * @return un ensemble de diviseurs positifs ou null si la valeur est 0
	 *         (infinité de valeurs).
	 */
	// TODO diviseursDe - Compléter le code de la méthode
	public static SortedSet<Integer> diviseursDe(int pVal)
	{
		SortedSet<Integer> diviseurs = new TreeSet<Integer>();

		if (pVal != 0)
		{
			for (int i = 1; i <= Math.abs(pVal); i++)
			{
				if (modulo(pVal, i) == 0)
				{
					diviseurs.add(i);
				}
			}
		}

		return diviseurs.isEmpty() ? null : diviseurs;
	}

	/**
	 * Retourne vrai si la valeur reçue est un nombre premier.
	 *
	 * Un nombre premier est plus grand que 1 et admet exactement deux diviseurs
	 * distincts entiers et positifs qui sont alors 1 et lui-même.
	 *
	 * @param pVal , la valeur
	 *
	 * @return vrai si la valeur reçue est un nombre premier, faux sinon
	 */
	// TODO estPremier - Compléter le code de la méthode
	public static boolean estPremier(int pVal)
	{
		return pVal > 1 && diviseursDe(pVal).size() == 2;
	}

	/**
	 * Retourne un ensemble des X nombres premiers entre 1 et la valeur reçue.
	 *
	 * @param pVal la valeur
	 *
	 * @return un ensemble des X nombres premiers ou null si aucun nombre
	 *         premier trouvé.
	 */
	// TODO xPremier - Compléter le code de la méthode
	public static SortedSet<Integer> xPremier(int pVal)
	{
		SortedSet<Integer> premiers = new TreeSet<Integer>();

		for (int i = 1; i <= pVal; i++)
		{
			if (estPremier(i))
			{
				premiers.add(i);
			}
		}

		return premiers.isEmpty() ? null : premiers;
	}

	/**
	 * Retourne le Plus Grand Commun Diviseur entre deux nombres. Utilise la
	 * méthode modulo et la récursivité.
	 *
	 * Il est possible d'utiliser la division euclidienne récursive pour trouver
	 * le PGCD (Plus Grand Commun Diviseur)
	 *
	 * @param pVal1 une valeur
	 * @param pVal2 une autre valeur
	 *
	 * @return le PGCD ou 0
	 */
	// TODO PGCD - Compléter le code de la méthode
	public static int PGCD(int pVal1, int pVal2)
	{
		int rep = 0;
		int mod = 0;

		if (pVal1 != 0 && pVal2 != 0)
		{
			mod = modulo(pVal1, pVal2);
			rep = mod == 0 ? pVal2 : PGCD(pVal2, mod);
		}

		return rep;

	}

	/**
	 * Retourne l'ensemble des nombres qui sont "premier entre eux" avec la
	 * valeur valRef reçue. La valeur valDepart reçue étant la valeur de départ
	 * de la recherche de ces nombres.
	 *
	 * En mathématiques, on dit que 2 entiers a et b sont premiers entre eux, si
	 * leur plus grand commun diviseur est égal à 1 ; en d'autres termes, s'ils
	 * n'ont aucun diviseur autre que 1 et –1 en commun. De manière équivalente,
	 * ils sont premiers entre eux si, et seulement si, ils n'ont aucun facteur
	 * premier en commun.
	 *
	 * Exemple : l'ensemble des nombres qui sont "premier entre eux" avec 26 en
	 * partant de 1 est {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25}
	 *
	 * @param valDepart la valeur de départ. Avant de chercher les "premier
	 *            entre eux" , si valDepart est négative, la mettre à 1.
	 * @param valRef la valeur de référence.
	 *
	 * @return un ensemble des X nombres "premiers entres eux" avec valRef à
	 *         partir de valDepart reçue ou null.
	 */
	// TODO xPremierEntreEux - Compléter le code de la méthode
	public static SortedSet<Integer> xPremierEntreEux(int valDepart, int valRef)
	{
		SortedSet<Integer> premiersEntreEux = new TreeSet<Integer>();

		// Met valDepart à 1 si négative
		valDepart = valDepart < 0 ? 1 : valDepart;

		for (int i = valDepart; i < valRef; i++)
		{
			if (PGCD(i, valRef) == 1)
			{
				premiersEntreEux.add(i);
			}
		}

		return premiersEntreEux.isEmpty() ? null : premiersEntreEux;
	}

	/**
	 * Retourne aléatoirement une valeur entière entre 2 valeurs reçues. Inverse
	 * le min et le max s'ils ne sont pas correctes (en ordre).
	 *
	 * @param pMin une valeur
	 * @param pMax une autre valeur
	 *
	 * @return la valeur générée aléatoirement.
	 */
	// TODO alea - Compléter le code de la méthode
	public static int alea(int pMin, int pMax)
	{
		if (pMin > pMax)
		{
			// Pérmuter les deux valeurs avec XOR
			pMin = pMin ^ pMax ^ (pMax = pMin);
		}

		return (int) (Math.random() * (pMax - pMin + 1)) + pMin;
	}

	/**
	 * Calcule le nombre de combinaisons différentes possibles si l'on choisit
	 * un nombre d'éléments dans un ensemble sans remise, sans tenir compte de
	 * l'ordre et sans répétition.
	 *
	 * Voir "Wikipédia" : https://fr.wikipedia.org/wiki/Combinatoire
	 *
	 * @param nbrElement le nombre d'éléments dans l'ensemble
	 * @param nbrElementPris longueur des combinaisons ou le nombre d'éléments
	 *            pris pour chaque combinaison.
	 *
	 * @return le nombre de combinaisons possible.
	 */
	public static int nbrCombinaison(int nbrElement, int nbrElementPris)
	{
		return (int) ((MathUtilitaires.fact(nbrElement)
				/ MathUtilitaires.fact(nbrElement - nbrElementPris))
				/ MathUtilitaires.fact(nbrElementPris));
	}
}
