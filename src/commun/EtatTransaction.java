package commun;

/**
 * État de la transaction
 * 
 * @author Bank-era Corp.
 * 
 *         Scan : Le vendeur scan les items pour les ajouter au panier 
 *         Empreinte : Le terminal demande au client d'apposer son empreinte 
 *         Attente : Le terminal communique avec la base de données et vérifie l'empreinte
 *         Confirmation : le terminal confirme au client que la transaction a été effectuée
 *         Erreur : Un erreur s'est produit lors de la transaction
 *
 */
public enum EtatTransaction {
	SCAN, EMPREINTE, ATTENTE, CONFIRMATION, ERREUR, NULL
}
