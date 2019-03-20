package view;

import java.util.*;

import model.*;

/**
 * 
 * Classe {@code Affichage} permettant de gerer des element d'affichages divers
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class Affichage {
	
	/**
	 * Permet d'afficher dans la console une collection de documents
	 * @param docs Collection de documents a afficher
	 */
	public static void afficherDocuments(Collection<Document> docs) {
		for ( Document doc : docs ) {
			System.out.println(doc);
		}
	}
	
	/**
	 * Permet d'afficher dans la console les auteurs d'une collection de documents
	 * @param docs Collection dont les auteurs doivent etre afficher
	 */
	 public static void afficherAuteurs(Collection<Document> docs) {
		 List<String> auteurs = new ArrayList<String>();
		 for ( Document doc : docs ) {
			 
			 // Seulement les livres ont un auteur
			 if(doc instanceof InterfaceAuteur) {
				 
				 //Downcasting 
				 InterfaceAuteur livre = (InterfaceAuteur) doc;
				 
				 // On ajoute l'auteur a la liste d'auteur
				 auteurs.add(livre.getAuteur());
			 }
		 }
		 
		 // On trie les auteurs par ordre alphabetique
		auteurs.sort(ComparatorAuteur);
		
		// On affiche les auteurs dans la console
		for ( String auteur : auteurs ) {
			System.out.println(auteur);
		}	 
	 }
	 
	 /**
	  * Comparateur de String qui ignore les majuscules
	  */
	 public static Comparator<String> ComparatorAuteur = new Comparator<String>() {
	      
	        @Override
	        public int compare(String d1, String d2) {
	            return d1.compareToIgnoreCase(d2);
	        }
	    };
	 
}
