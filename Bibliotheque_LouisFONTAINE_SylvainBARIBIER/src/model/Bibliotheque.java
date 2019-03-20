package model;

import java.util.*;

/**
 * 
 * Classe {@code Bibliotheque} permettant de representer une bibliotheque
 * contenant une liste de documents
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class Bibliotheque implements Cloneable {
	/**
	 * Liste des documents de la bibliotheque
	 */
	private List<Document> documents;

	/**
	 * Constructeur d'une bibliotheque dont la liste de documents est vide.
	 */
	public Bibliotheque() {
		this.documents = new ArrayList<Document>();
	}
	
	/**
	 * Fonction renvoyant la liste des documents de la bibliotheque.
	 * @return La liste des documents de la bibliotheque.
	 */
	public List<Document> getDocuments() {
		return documents;
	}

	/**
	 * Fonction renvoyant le document a l'index i passe en parrametre
	 * @param i index du document a retourner
	 * @return le i eme document de la liste des documents s'il existe, 
	 * ou null sinon.
	 */
	public Document getDocument(int i) {
		
		try{
			return this.documents.get(i);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Utilisation de getDocument avec un index invalide !");
			return null;
		}
	}
	
	/**
	 * Fonction renvoyant le document de la biblioteque ayant le même titre que
	 * celui passe en argument
	 * @param str Le titre du document a retourner
	 * @return Le document ayant le même titre que celui passe en parrametre 
	 * s'il existe, ou null sinon.
	 */
	public Document getDocumentParTitre(String str) {
		
		for ( Document doc : documents ) {
			if(doc.getTitre().equals(str)) {
				return doc;
			}
		}
		return null;
	}
	
	/**
	 * Fonction permettant de renvoyer une liste de Roman possedant le même prix
	 * litteraire que celui passe en parrametre
	 * @param prixLitterraire Le prix litteraire des document sa retourner
	 * @return La liste des Romans ayant le même prix litteraire que
	 * celui passe en parrametre
	 */
	public List<Roman> getRomansParPrix(int prixLitterraire) {
		List<Roman> documentsParPrix = new ArrayList<Roman>();
		Roman roman;
		
		for ( Document doc : documents ) {
			if (doc instanceof Roman) {
				//Downcasting 
				roman = (Roman) doc;
				if (roman.getPrixLitteraire() == prixLitterraire) {
					documentsParPrix.add(roman);
				}
			}
		}
		return documentsParPrix;
	}
	
	/**
	 * Fonction permettant d'ajouter un livre a la bibliotheque
	 * @param doc Le document a ajouter a la bibliotheque
	 * @return true si l'ajout du document a ete realise, faux sinon
	 * @throws Exception - Si le document est un livre et que le nombre de page
	 * n'est pas un nombre superieur a 0
	 */
	public boolean addDocument(Document doc) throws Exception {
		try {
			if (doc instanceof Livre) {
				//Downcasting 
				Livre livre = (Livre) doc;
				
				if (livre.getNbPages() < 1) {
					throw new Exception("ERREUR : Ajout du Livre \"" + livre.getTitre() + "\" imposible : Nombre de pages invalide");
				}
				
			}
			this.documents.add(doc);
			return true;
		} catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
			return false;
		}
	}
	
	/**
	 * Supprime de la bibliotheque le document passe en argument a la fonction
	 * @param doc Le document a supprimer de la bibliotheque
	 * @return true si le document a ete supprime, retourne une exception sinon
	 */
	public boolean removeDocument(Document doc) throws Exception {
			int index = this.documents.indexOf(doc);
			
			// Si le document n'existe pas dans la bibliotheque, on retourn false
			if(index == -1) {
				throw new Exception("ERREUR : Supression du document" + doc.getTitre() + "impossible");
			}
			else {
				this.documents.remove(index);
				return true;
			}
	}
	
	@Override
	public String toString() {
		return "Bibliotheque de " + documents.size() + " documents";
	}
	
	/**
	 * Trie la liste de documents de la par ordre alphabetique en 
	 * fonction des titres
	 */
	public void sort() {
		this.documents.sort(Document.ComparatorTitre);
	}
	
	@Override  
	public Bibliotheque clone() { 
		Bibliotheque cl = new Bibliotheque(); 
		Document cloneDoc;
		
		for (Document doc : documents) {
			cloneDoc = doc.clone();
			cl.documents.add(cloneDoc);
		}  
		return cl;  
	}
}
