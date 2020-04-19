package encryption;

import java.util.*;

/**
 * Classe permettant d'encrypter un message
 * 
 * @author Bank-era Corp.
 *
 */
public class Encryption {
	
	public static final int TAILLE = 4;
	
	private TrousseauClef cles;
	
	private List<int[][]> texte;
	
	private String texteEncrypte = null;
	
	public Encryption(String cle, String texte)
	{
		remplirTexte(texte);
		creerTrousseauCle(cle);
	}
	
	public String getTextEncrypte()
	{
		return texteEncrypte;
	}
	
	private void remplirTexte(String texte)
	{
		this.texte = new ArrayList<int[][]>();
		int cpt = 0;
		
		for(int i = 0; i < texte.length(); i =+ TAILLE*TAILLE)
		{
			this.texte.add(new int[TAILLE][TAILLE]);

			
			for(int j = 0; j < TAILLE; j++)
			{
				for(int k = 0; k < TAILLE ; k++)
				{
					if(i < texte.length())
						this.texte.get(cpt)[k][j] = (int) texte.charAt(i);
					else
						this.texte.get(cpt)[k][j] = (int) ' ';
				}
			}
			
			cpt++;
		}
	}
	
	private void creerTrousseauCle(String cle)
	{
		int[][] clePremiere = new int[TAILLE][TAILLE];
		
		int cpt= 0;
		
		for(int j = 0; j < TAILLE; j++)
		{
			for(int k = 0; k < TAILLE ; k++)
			{
				if(j < cle.length())
					clePremiere[k][j] = (int) cle.charAt(cpt);
				else
					clePremiere[k][j] = (int) ' ';
				
				cpt++;
			}
		}
		
		cles = new TrousseauClef(false, clePremiere);
	}
}
