package model;

/**
 * 
 * Classe {@code Manuel} est une sous classe de {@code Livre} permettant de representer
 * un manuel
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class Manuel extends Livre implements Cloneable {
	protected int niveau; // Niveau du manuel
	
	/**
	 * Constructeur d'un manuel avec un titre, auteur, nombre de page et niveau
	 * @param titre Titre du manuel
	 * @param auteur Auteur du manuel
	 * @param nbPages Nombre de pages
	 * @param niveau Niveau du manuel
	 */
	public Manuel (String titre, String auteur, int nbPages, int niveau) {
	    super(titre, auteur, nbPages);
	    this.niveau = niveau;
	}
	
	/**
	 * Constructeur utilisé pour le clonage d'un Manuel
	 * @param titre Titre du manuel à cloner
	 * @param numEnreg Numéro d'enregistrement du manuel à cloner
	 * @param auteur Auteur du manuel à cloner
	 * @param nbPages Nombre de pages du manuel à cloner
	 * @param niveau Niveau du manuel à cloner
	 */
	public Manuel (String titre, int numEnreg, String auteur, int nbPages, int niveau) {
	    super(titre, numEnreg, auteur, nbPages);
	    this.niveau = niveau;
	}
	
	// Getters
	public int getNiveau() {
		return this.niveau;
	}
	
	// Setters
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	// Methode
	@Override
	public String toString() {
		return super.toString() + "\nNiveau: " + this.getNiveau();
	}
	
	@Override
	public String toAfficheur() {
		return super.toAfficheur() + ", " + this.getNiveau();
	}
	
	@Override
	public String toFile() {
		return "MANUEL;" + this.getNumEnreg() + ";" + this.getTitre() + ";" + this.getAuteur() + ";"
				+ this.getNbPages() + ";" + this.getNiveau() + "\n";
	}
	
	@Override
	public Manuel clone() {
		return new Manuel(this.getTitre(), this.getNumEnreg(), this.getAuteur(), this.getNbPages(),
				this.getNiveau());
	}
}
