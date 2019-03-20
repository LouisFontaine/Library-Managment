package model;

/**
 * 
 * Classe {@code Livre} est une sous classe de {@code Document} et une classe mère de
 * la classe {@code Manuel} et {@code Roman} permettant de representer un livre
 * 
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class Livre extends Document implements InterfaceAuteur, Cloneable {
	/**
	 * Nom de l'auteur du livre
	 */
	protected String auteur;
	
	/**
	 * Nombre de pages du livre
	 */
	protected int nbPages;
	
	/**
	 * Constructeur d'un Livre avec un titre, auteur et nombre de page
	 * @param titre Titre du livre
	 * @param auteur Auteur de livre
	 * @param nbPages Nombre de pages de livre
	 */
	public Livre (String titre, String auteur, int nbPages) {
	    super(titre);
	    this.auteur = auteur;
	    this.nbPages = nbPages;
	}
	
	/**
	 * Constructeur utilisé pour le clonage d'un livre
	 * @param titre Titre du livre à cloner
	 * @param numEnreg Numéro d'enregistrement du livre à cloner
	 * @param auteur Auteur du livre à cloner
	 * @param nbPages Nombre de pages du livre à cloner
	 */
	public Livre (String titre, int numEnreg, String auteur, int nbPages) {
	    super(titre, numEnreg);
	    this.auteur = auteur;
	    this.nbPages = nbPages;
	}
	
	// Getters
	public String getAuteur() {
		return this.auteur;
	}
	
	public int getNbPages() {
		return this.nbPages;
	}
	
	// Setters
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	
	// Methodes
	@Override
	public String toString() {
		return super.toString() + "\nAuteur: " + this.getAuteur() + "\nNombre de Pages: " + this.getNbPages();
	}
	
	@Override
	public String toAfficheur() {
		return super.toAfficheur() +  ", \"" + this.getAuteur() + "\", " + this.getNbPages() + " p";
	}
	
	@Override
	public String toFile() {
		return "LIVRE;" + super.toFile() + this.getAuteur() + ";" + this.getNbPages() + ";\n";
	}
	
	@Override
	public Livre clone() {
		return new Livre(this.getTitre(), this.getNumEnreg(), this.getAuteur(), this.getNbPages());
	}
}
