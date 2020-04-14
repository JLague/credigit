package pos.application;

import javafx.scene.Scene;

/**
 * Interface définissant les comportements obligatoires de POSApplication
 * 
 * @author Bank-era Corp.
 *
 */
public interface IPOSApplication {

	/**
	 * Permet de changer la scène liée à l'application. Assume que la scène a été
	 * changé
	 * 
	 * @param scene la nouvelle scène
	 * @param title le nouveau titre de l'application
	 */
	public void chargerScene(Scene scene, String title, boolean fullscreen);

}
