package controller;

import model.*;
import view.*;

import java.awt.Point;
import java.io.IOException;

/**
 * 
 * Classe {@code TestDocuments} contenant uniquement une methode main
 * et permettant de tester notre programme en creant une bibliotheque
 * en lançant l'interface graphique
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class TestDocuments {
	
	public static void main(String[] args) throws CloneNotSupportedException, IOException {
		Bibliotheque bibliotheque = new Bibliotheque();
		AfficheurBibliotheque fenetre;
		
		Document[] documents = {
			new Livre("L'archipel du Goulag", "Soljenitsyne", 250),
			new Roman("Rouge Bresil", "Rufin", 120, Roman.GONCOURT),
			new Revue("Le point", 03, 2014),
			new Roman("Le mendiant", "Wiesel", 150, Roman.MEDICIS),
			new Livre("La condition humaine", "Malraux", 130),
			new Manuel("Manuel qualite ISO 9001", "AFNOR", -1, 3),
			new Revue("Revue 1", 05, 2015),
			new Revue("Revue 2", 05, 2015),
			new Revue("Revue 3", 05, 2015),
			new Revue("Revue 4", 05, 2015),
			new Revue("Revue 5", 05, 2015),
			new Revue("Revue 6", 05, 2015),
			new Revue("Revue 7", 05, 2015),
			new Revue("Revue 8", 05, 2015),
			new Revue("Revue 9", 05, 2015),
			new Revue("Revue 10", 05, 2015),
			new Manuel("Manuel 1", "Auteur 1", 99, 1),
			new Manuel("Manuel 2", "Auteur 2", 99, 1),
			new Manuel("Manuel 3", "Auteur 3", 99, 1),
			new Manuel("Manuel 4", "Auteur 4", 99, 1),
			new Manuel("Manuel 5", "Auteur 5", 99, 1),
			new Manuel("Manuel 6", "Auteur 6", 99, 1),
			new Manuel("Manuel 7", "Auteur 7", 99, 1),
			new Manuel("Manuel 8", "Auteur 8", 99, 1),
			new Manuel("Manuel 9", "Auteur 9", 99, 1),
			new Manuel("Manuel 10", "Auteur 10", 99, 1),
			new Livre("Livre 1", "Auteur 11", 130),
			new Livre("Livre 2", "Auteur 12", 130),
			new Livre("Livre 3", "Auteur 13", 130),
			new Livre("Livre 4", "Auteur 14", 130),
			new Livre("Livre 5", "Auteur 15", 130),
			new Livre("Livre 6", "Auteur 16", 130),
			new Livre("Livre 7", "Auteur 17", 130),
			new Livre("Livre 8", "Auteur 18", 130),
			new Livre("Livre 9", "Auteur 19", 130),
			new Livre("Livre 10", "Auteur 20", 130),
			new Roman("Roman 1", "Auteur 21", 150, Roman.MEDICIS),
			new Roman("Roman 2", "Auteur 22", 150, Roman.MEDICIS),
			new Roman("Roman 3", "Auteur 23", 150, Roman.GONCOURT),
			new Roman("Roman 4", "Auteur 24", 150, Roman.GONCOURT),
			new Roman("Roman 5", "Auteur 25", 150, Roman.AUCUN),
			new Roman("Roman 6", "Auteur 26", 150, Roman.AUCUN),
			new Roman("Roman 7", "Auteur 27", 150, Roman.AUCUN),
			new Roman("Roman 8", "Auteur 28", 150, Roman.INTERALLIE),
			new Roman("Roman 9", "Auteur 29", 150, Roman.INTERALLIE),
			new Roman("Roman 10", "Auteur 30", 150, Roman.INTERALLIE),
			
		};
	
		for ( Document doc : documents ) {
			try {
				bibliotheque.addDocument(doc);
			} catch (Exception myException) {
				System.out.println(myException.getMessage());
			}
		}
		
		Affichage.afficherDocuments(bibliotheque.getDocuments());
		Affichage.afficherAuteurs(bibliotheque.getDocuments());
		
		Point location = new Point();
		fenetre = new AfficheurBibliotheque(bibliotheque, 540, 540, location);
		fenetre.setVisible(true);
		
	}
}
