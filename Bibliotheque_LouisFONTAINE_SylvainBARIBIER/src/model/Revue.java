package model;

/**
 * 
 * Classe {@code Revue} est une sous classe de {@code Document} permettant de representer une revue
 *
 * @version 1.0
 * 
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class Revue extends Document implements Cloneable {
	/**
	 * Mois de sortie de la revue
	 */
	protected int mois;
	
	/**
	 * Annee de sortie de la revue
	 */
	protected int annee; 
	
	/**
	 * Constructeur d'une revue avec un titre, un mois et une annee
	 * @param titre Titre de la revue
	 * @param mois Mois de la revue
	 * @param annee Annee de la revue
	 */
	public Revue (String titre, int mois, int annee) {
	    super(titre);
	    this.mois = mois;
	    this.annee = annee;
	}
	
	/**
	 * Constructeur utilisé pour le clonage d'une revue
	 * @param titre Titre de la revue à cloner
	 * @param numEnreg Numéro d'enregistrement de la revue à cloner
	 * @param mois Mois de la revue à cloner
	 * @param annee Annee de la revue a cloner
	 */
	public Revue (String titre, int numEnreg, int mois, int annee) {
	    super(titre, numEnreg);
	    this.mois = mois;
	    this.annee = annee;
	}
	
	// Getters
	public int getMois() {
		return this.mois;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	// Setters
	public void setMois(int mois) {
		this.mois = mois;
	}
	
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	// Methodes
	@Override
	public String toString() {
		return super.toString() + "\nMois: " + this.mois + "\nAnnee: " + this.annee;
	}
	
	@Override
	public String toAfficheur() {
		return super.toAfficheur() + ", " + this.getMois() + ", " + this.getAnnee();
	}
	
	@Override
	public String toFile() {
		return "REVUE;" + super.toFile() + this.getMois() + ";" + this.getAnnee() + ";\n";
	}
	
	@Override
	public Revue clone() {
		return new Revue(this.getTitre(), this.getNumEnreg(), this.getMois(), this.getAnnee());
	}
}
