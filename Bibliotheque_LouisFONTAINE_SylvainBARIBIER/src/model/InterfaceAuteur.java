package model;

/**
 * 
 * Definit un document qui possede un auteur
 * 
 * @version 1.0
 * 
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public interface InterfaceAuteur {
	
	/**
	 * Methode permmettant de recuperer le nom de l'auteur d'un document possedant un auteur
	 * @return String L'auteur du document
	 */
	abstract public String getAuteur();
	
}
