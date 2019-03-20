package model;

/**
 * 
 * Classe {@code Roman} est une sous classe de {@code Livre} permettant de representer un roman
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class Roman extends Livre implements Cloneable {
	/**
	 * Prix litteraire du Livre
	 */
	protected int prixLitteraire;
	
	public final static 	int AUCUN = 0;		// Constante pour prixLitteraire nommee AUCUN
	public final static 	int GONCOURT = 1;	// Constante pour prixLitteraire nommee GONCOURT
	public final static 	int MEDICIS = 2;		// Constante pour prixLitteraire nommee MEDICIS
	public final static 	int INTERALLIE = 3;	// Constante pour prixLitteraire nommee INTERALLIE
	
	/**
	 * Constructeur d'un roman avec un titre, un auteur, un nombre de page et un prix literraire
	 * @param titre Titre du roman à creer
	 * @param auteur Auteur du roman à creer
	 * @param nbPages Nombre de pages du roman à ceer
	 * @param prixLitteraire Prix litteraire du roman à creer
	 */
	public Roman (String titre, String auteur, int nbPages, int prixLitteraire) {
	    super(titre, auteur, nbPages);
	    this.prixLitteraire = prixLitteraire;
	}
	
	/**
	 * Constructeur utilisé pour le clonage d'un roman
	 * @param titre Titre du roman à cloner
	 * @param numEnreg Numero d'enregistrement du roman à cloner
	 * @param auteur Auteur du roman à cloner
	 * @param nbPages Nombre de pages du roman à cloner
	 * @param prixLitteraire Prix litteraire du roman à cloner
	 */
	public Roman (String titre, int numEnreg, String auteur, int nbPages, int prixLitteraire) {
	    super(titre, numEnreg, auteur, nbPages);
	    this.prixLitteraire = prixLitteraire;
	}
	
	// Getter
	public int getPrixLitteraire() {
		return this.prixLitteraire;
	}
	
	// Setter
	public void setPrixLitteraire(int prixLitteraire) {
		this.prixLitteraire = prixLitteraire;
	}
	
	// Methode
	@Override
	public String toString() {
		return super.toString() + "\nPrix Litterraire: " + this.prixLitteraire;
	}
	
	@Override
	public String toAfficheur() {
		if (this.prixLitteraire == Roman.AUCUN) {
			return super.toAfficheur() + ", Aucun prix";
		}
		else if (this.prixLitteraire == Roman.GONCOURT) {
			return super.toAfficheur() + ", Goncourt";
		}
		else if (this.prixLitteraire == Roman.MEDICIS) {
			return super.toAfficheur() + ", Medicis";
		}
		else if (this.prixLitteraire == Roman.INTERALLIE) {
			return super.toAfficheur() + ", Interallie";
		} else {
			return "";
		}
	}
	
	@Override
	public String toFile() {
		return "ROMAN;" + this.getNumEnreg() + ";" + this.getTitre() + ";" + this.getAuteur() + ";"
				+ this.getNbPages() + ";" + this.getPrixLitteraire() + "\n";
	}
	
	@Override
	public Roman clone() {
		return new Roman(this.getTitre(), this.getNumEnreg(), this.getAuteur(), this.getNbPages(), this.getPrixLitteraire());
	}
}
