package inscription.modele;

/**
 * Enumération créant des questions de sécurités
 * 
 * @author Bank-era Corp.
 *
 */
public enum Questions {

	ECOLE("Quelle école secondaire avez-vous fréquenté en secondaire 1?"), HEROS("Qui était votre héros d'enfance?"),
	ANIMAL("Quel était le nom de votre premier animal?"),
	AMOUR("Qui est la première personne que vous avez embrassée?"),
	GRANDMEREP("Quel est le nom de famille de jeune fille de votre grand-mère paternelle?"),
	GRANDMEREM("Quel est le nom de famille de jeune fille de votre grand-mère maternelle?"),
	RUE("Sur quelle rue habitiez-vous lors de votre enfance?"),
	COURS("Quel était votre cours favoris à l'école secondaire?");

	/**
	 * Le texte de la question
	 */
	private String texte;

	/**
	 * Construit une question
	 * 
	 * @param texte - Le texte de la question
	 */
	private Questions(String texte) {
		this.texte = texte;
	}

	/**
	 * Retourne le texte de la question
	 * 
	 * @return Le texte de la question
	 */
	public String getTexte() {
		return texte;
	}
	
	
	public static Questions getQuestionFromString(String texte) {
		Questions temp = null;
		
		for(Questions q : Questions.values()) {
			if(q.texte.equals(texte)) {
				temp = q;
			}
		}
		
		return temp;
	}

}
