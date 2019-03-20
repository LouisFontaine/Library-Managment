package model;

import java.util.Comparator;

/**
 * 
 * Classe {@code Document} est une classe abstraite, classe mère de {@code Livre},
 * {@code Manuel}, {@code Revue} et {@code Roman} permettant de representer un document
 * 
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 *
 */

public abstract class Document implements Cloneable {
	/**
	 * Numero d'enregistrement du document
	 */
	protected int numEnreg;
	/**
	 * Titre du document
	 */
	protected String titre;
	/**
	 * Nombre d'enregistrements de documents dans la bibliotheque
	 */
	static private int nbreInstances = 0;
	
	/**
	 * Constructeur d'un document vide
	 */
	public Document () {
		
	}
	
	/**
	 * Constructeur d'un document avec un titre
	 * @param titre Titre du document
	 */
	public Document (String titre) {
	    this.titre = titre;
	    this.numEnreg = Document.nbreInstances + 1;
	    Document.nbreInstances++;
	}
	
	/**
	 * Constructeur utilisé pour le clonnage d'un document
	 * @param titre Titre du document a cloner
	 * @param numEnreg Numéro d'enregistrement du document a cloner
	 */
	public Document (String titre, int numEnreg) {
	    this.titre = titre;
	    this.numEnreg = numEnreg;
	}
	
	// Getters
	public int getNumEnreg() {
		return this.numEnreg;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public int getNbreInstances() {
		return Document.nbreInstances;
	}
	
	// Setter
	public void setTitre(String Titre) {
		this.titre = Titre;
	}
	
	// Methodes
	@Override
	public String toString() {
		return "\nNumero Enregistrement: " + this.numEnreg + "\nTitre: " + this.titre;
	}
	
	/**
	 * Methode utilise par les fonctions SWING pour stocker les informations d'un document
	 * dans une chaine de carracteres
	 * @return String Les informations du document
	 */
	public String toAfficheur() {
		return this.numEnreg + " -  \"" + this.titre + "\"";
	}
	
	/**
	 * Methode utilisé par la fonction d'écriture dans un fichier
	 * Elle permet de retourner une chaine de caractère formaté
	 * @return String un ligne au format CSV avec les informations d'un document
	 */
	public String toFile() {
		return this.numEnreg + ";" + this.titre + ";";
	}
	
	@Override
	public abstract Document clone();
	
	/**
	 * Comparateur permettant de comparer deux documents à partir de leur titre
	 */
	public static Comparator<Document> ComparatorTitre = new Comparator<Document>() {
	      
        @Override
        public int compare(Document d1, Document d2) {
            return d1.getTitre().compareToIgnoreCase(d2.getTitre());
        }
    };
}
