package pos.vue;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pos.utils.ImageUtil;
import commun.*;
/**
 * 
 * Classe permettant de wrapper des produits dans un VBox affichant leur image,
 * nom, prix et SKU
 * 
 * @author Bank-era Corp.
 *
 */
public class ProduitWrapper extends VBox {
	private Produit produit;

	/**
	 * Consctructeur permettant d'associer un produit au wrapper à la création de
	 * l'objet
	 * 
	 * @param produit le produit dans le wrapper
	 */
	public ProduitWrapper(Produit produit) {
		this.produit = produit;
		
		// Ajoute l'image
		ImageView image = new ImageView(ImageUtil.convertFromBytes(produit.getImage()));
		image.setStyle("-fx-border-radius: 10px, 10px, 0px, 0px;");
		image.setFitHeight(234);
		image.setFitWidth(234);
		
		// Ajoute le nom et le prix
		Label nom = new Label(produit.getNom());
		NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));
		Label prix = new Label("Prix : " + cf.format(produit.getPrix()));

		GridPane.setValignment(this, VPos.CENTER);
		GridPane.setHalignment(this, HPos.CENTER);
		
		
		// Set les margins
		VBox.setMargin(nom, new Insets(8, 0, 0, 8));
		VBox.setMargin(prix, new Insets(2, 0, 2, 8));
		
		// Ajoute les éléments au wrapper
		this.getChildren().addAll(image, nom, prix);
	}

	/**
	 * @param produit le nouveau produit
	 */
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	/**
	 * @return le produit
	 */
	public Produit getProduit() {
		return this.produit;
	}

}
