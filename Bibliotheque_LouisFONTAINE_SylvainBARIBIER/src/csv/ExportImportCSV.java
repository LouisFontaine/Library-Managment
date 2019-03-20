package csv;

import java.io.*;
import java.util.Collection;

import model.*;

/**
 * 
 * Classe {@code ExportImportCSV} permettant d'exporter et d'importer une
 * bibliotheque dans un fichier .CSV
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public abstract class ExportImportCSV {
	
	/**
	 * Constante contenant le Nom du fichier dont il faut exporter et importer les
	 *  donees
	 */
	private final static String FILE_NAME = "src/bibliotheque.csv";
	
	/**
	 * Fonction permmettant d'importer une bibliotheque prevenant d'unfichier .CSV
	 * @param bibliotheque Bibliotheque où il faut importer les documents
	 */
	public static void readDocuments(Bibliotheque bibliotheque) {
		
		File 	file;
		String	type;
		String 	titre = "";
		String 	auteur;
		int 		numEnreg;
		int 		nbrePages;
		int		mois;
		int 		annee;
		int 		prixLitteraire;
		int 		niveau;
       
        try {
        	
        		// On ouvre le fichier
        		file = new File(FILE_NAME);
			
        		// BufferedReader a besoin d un FileReader
    	   		FileReader fr = new FileReader(file);
			
			// Le BufferedReader imput auquel on donne comme argument le FileReader cree juste au dessus
    	   		BufferedReader br = new BufferedReader(fr);
			
    	   		/* On fait une boucle pour parcourir toutes les lignes du fichier et on stocke la ligne dans la
    	   		 * variable "line" de type String
    	   		 */
    	        for (String line = br.readLine(); line != null; line = br.readLine()) {
    	        		
    	            //System.out.println(line);
    	            
    	            // On recupere le premier champ de la ligne qui est le type de document
    	            type = line.substring(0, line.indexOf(";"));
    	            //System.out.println(type);
    	            
    	            // On supprime le champ de la ligne
    	            line = line.substring(line.indexOf(";") + 1);
    	            
    	            // On recupere le deuxieme champ de la ligne qui est le numero d'enrigestrement de document
    	            numEnreg =  Integer.parseInt(line.substring(0, line.indexOf(";")));
    	            //System.out.println(numEnreg);
    	            
    	            // On supprime le champ de la ligne
    	            line = line.substring(line.indexOf(";") + 1);
    	            
    	            // On recupere le troisieme champ de la ligne qui est le titre de document
    	            titre =  line.substring(0, line.indexOf(";"));
    	            //System.out.println(titre);
    	            
    	            // On supprime le champ de la ligne
    	            line = line.substring(line.indexOf(";") + 1);
    	            
    	            /* On recupere maintenant le quatrieme et le cinquieme champ du document qui est l'auteur et le
    	             * le nombre de pages pour un livre, un roman ou un manuel et qui est le mois et l'annee pour une revue
    	             */
    	            if (type.equals("LIVRE") || type.equals("ROMAN") || type.equals("MANUEL")) {
    	            		// On recupere le nom de l'auteur
    	            		auteur =  line.substring(0, line.indexOf(";"));
        	            //System.out.println(auteur);
        	            
        	            // On supprime le champ de la ligne
        	            line = line.substring(line.indexOf(";") + 1);
        	            
        	            if (type.equals("ROMAN")) {
        	            	
        	            		// On recupere le nombre de pages
            	            nbrePages =  Integer.parseInt(line.substring(0, line.indexOf(";")));
            	            
        	            		// On supprime le champ de la ligne
            	            line = line.substring(line.indexOf(";") + 1);
        	            	
	        	            	// On recupere le prix litteraire
	        	            	prixLitteraire =  Integer.parseInt(line);
	            	        
	            	         // Ajout du roman à la bibliotheque
	            	        	bibliotheque.addDocument(new Roman(titre, numEnreg, auteur, nbrePages, prixLitteraire));
	            	      
        	            } else if (type.equals("MANUEL")) {
        	            		
        	            		// On recupere le nombre de pages
            	            nbrePages =  Integer.parseInt(line.substring(0, line.indexOf(";")));
            	            
        	            		// On supprime le champ de la ligne
            	            line = line.substring(line.indexOf(";") + 1);
        	            		
	        	            	// On recupere le prix litteraire
	        	            	niveau =  Integer.parseInt(line);
	            	        
	            	         // Ajout du manuel à la bibliotheque
	    	            		bibliotheque.addDocument(new Manuel(titre, numEnreg, auteur, nbrePages, niveau));
	    	            		
        	            } else {
        	            		
        	            		// On recupere le nombre de pages
        	            		line = line.substring(0, line.indexOf(";"));
            	            nbrePages =  Integer.parseInt(line);
            	            
        	            		// Ajout du livre à la bibliotheque
        	            		bibliotheque.addDocument(new Livre(titre, numEnreg, auteur, nbrePages));
        	            }
    	            } else if (type.equals("REVUE")) {
    	            
	    	            	// On recupere le mois
	    	            	mois =  Integer.parseInt(line.substring(0, line.indexOf(";")));
	        	            
	        	        // On supprime le champ de la ligne
	        	        line = line.substring(line.indexOf(";") + 1);
	        	        
	        	     	// On recupere l'annee
	        	        line = line.substring(0, line.indexOf(";"));
	        	     	annee =  Integer.parseInt(line);
	    	            //System.out.println(annee);
	        	        
	    	            // Ajout de la revue à bibliotheque
		            bibliotheque.addDocument(new Revue(titre, numEnreg, mois, annee));
	    	        }
    	        }
			
			// Et on le ferme
			br.close();
		    fr.close();
		    
		    return;
			
        } catch(IOException ioe) {
			// On "catch" l exception ici si il y en a une, et on l affiche sur la console 
			System.out.println("Erreur : " + ioe );
        } catch (Exception e) {
        		System.out.println(e.getMessage());
		}
	}
   
	/**
	 * Fonction permmettant d'exporter une bibliotheque dans un fichier .CSV
	 * @param docs Liste de Document de la bibliotheque à exporter
	 */
   public static void writeDocuments(Collection<Document> docs)
   {
	   	File file;
	    String ligne;
	   
		try {
			// On ouvre la file
			file = new File(FILE_NAME);
			
			// On supprime la file pour supprimer le contenue
			file.delete();
			
			// on recre la file avec le même nom
			file = new File(FILE_NAME);
			
			// BufferedWriter a besoin d un FileWriter
			FileWriter fw = new FileWriter(file);
			
			// Le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);
			
			for ( Document doc : docs ) {
				// On met les informations du document dans la chaine de carractere
				ligne = doc.toFile();
				//System.out.println(ligne);
				
				// On marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
				output.write(ligne);
			}
			
			// Ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
			output.flush();
			
			// Et on le ferme
			output.close();
			fw.close();
			
			
		} catch(IOException ioe) {
			// On "catch" l exception ici si il y en a une, et on l affiche sur la console 
			System.out.println("Erreur : " + ioe );
		}
	}
}

