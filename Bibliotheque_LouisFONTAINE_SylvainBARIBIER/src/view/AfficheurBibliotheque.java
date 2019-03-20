package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import javax.swing.*;

import csv.*;
import model.*;

/**
 * 
 * Classe {@code AfficheurBibliotheque} permettant de creer une interface graphique afin d'afficher
 * le contenue d'un objet de la classe bibliotheque
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */ 

@SuppressWarnings("serial")
public class AfficheurBibliotheque extends JFrame implements ActionListener {
	/**
	 * Biblioteque a afficher
	 */
	private Bibliotheque bibliotheque;
	
	// Composants Swing
	private JButton		b1;
	private JButton		b2;
	private JButton		b3;
	private JButton		b4;
	private JButton		b5;
	private JButton		b6;
	private JButton		b7;
	private JButton		b8;
	private JLabel		label;
	
	/**
	 * Constructeur de la classe
	 * @param bibliotheque Bibliotheque a afficher
	 */
	public AfficheurBibliotheque(Bibliotheque bibliotheque, int width, int height, Point location){
		super();
		this.bibliotheque = bibliotheque;
		
		build(width, height, location); // On initialise notre fenetre
	}
	
	private void build(int width, int height, Point location){
		setTitle("Afficheur bibliotheque");				// On donne un titre a l'application
		setSize(width, height);							// On donne une taille a notre fenetre
		this.setLocation(location);						// On centre la fenetre sur l'ecran
		setResizable(true);								// On interdit la redimensionnement de la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// On dit a l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
	}
	
	private JScrollPane buildContentPane(){
		Collection<Document> docs = bibliotheque.getDocuments();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		panel.setBackground(Color.white);
		
		b1 = new JButton("Trier par ordre alphabetique");
		b1.addActionListener(this);
		panel.add(b1);
									
		b2 = new JButton("Inserer un roman");
		b2.addActionListener(this);
		panel.add(b2);
									
		b3 = new JButton("Supprimer un document");
		b3.addActionListener(this);
		panel.add(b3);
		
		b4 = new JButton("Rechercher un document par titre");
		b4.addActionListener(this);
		panel.add(b4);
		
		b5 = new JButton("Rechercher un Roman par prix litteraire");
		b5.addActionListener(this);
		panel.add(b5);
		
		b6 = new JButton("Demarrer une nouvelle session avec un clone de cette biblioteque");
		b6.addActionListener(this);
		panel.add(b6);
		
		b7 = new JButton("Importer une bibliotheque a partir du fichier \"bibliotheque.csv\"");
		b7.addActionListener(this);
		panel.add(b7);
		
		b8 = new JButton("Sauvegarder la bibliotheque actuelle dans le fichier \"bibliotheque.csv\"");
		b8.addActionListener(this);
		panel.add(b8);
		
		// Affichage de la liste des livres de la biblioteque
		label = new JLabel("LIVRES :");
		panel.add(label);
		for ( Document doc : docs ) {
			label = new JLabel(doc.toAfficheur());
			panel.add(label);
		}
		
		// Affichage de la liste des auteurs de la biblioteque
		label = new JLabel("");
		panel.add(label);
		label = new JLabel("AUTEURS :");
		panel.add(label);
		for ( Document doc : docs ) {
			if(doc instanceof Livre) {
				//Downcasting 
				Livre livre = (Livre) doc;
				
				label = new JLabel(livre.getAuteur());
			}
			panel.add(label);
		}
		
		JScrollPane panelPane = new JScrollPane(panel);
		return panelPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source == b1) {
			this.dispose();
			
			// On trie la bibliotheque
			bibliotheque.sort();
			
			JOptionPane.showMessageDialog(this, "Trie de la bibliotheque terminee",
					"", JOptionPane.PLAIN_MESSAGE);
			
			// On cree une nouvelle fenêtre d'affichage des livres
			AfficheurBibliotheque fenetre = new AfficheurBibliotheque(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true); //On la rend visible
			
		} else if(source == b2) {
			this.dispose();
			
			// On cree une fenêtre de creation de livre
			AfficheurCreationLivre fenetre = new AfficheurCreationLivre(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true); //On la rend visible
			
		} else if(source == b3) {
			this.dispose();
			
			// On cree une fenêtre de creation de livre
			AfficheurSuppressionLivre fenetre = new AfficheurSuppressionLivre(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true); //On la rend visible
			
		} else if(source == b4) {
			this.dispose();
			
			// On cree une fenêtre de recherche de livre par titre
			AfficheurRechercheTitre fenetre = new AfficheurRechercheTitre(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true); //On la rend visible
			
		} else if(source == b5) {
			this.dispose();
			
			// On cree une fenêtre de recherche de livre par prix litteraire
			AfficheurRecherchePrix fenetre = new AfficheurRecherchePrix(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true); //On la rend visible

		} else if(source == b6) {
			Bibliotheque clone;
			
			// On clone la bibliotheque
			clone = bibliotheque.clone();
			
			JOptionPane.showMessageDialog(this, "Clonage de la bibliotheque terminee",
					"", JOptionPane.PLAIN_MESSAGE);
			this.dispose();
			
			// On cree une fenêtre avec le clone de la bibliotheque
			AfficheurBibliotheque fenetre = new AfficheurBibliotheque(clone, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true); //On la rend visible

		} else if(source == b7) {
			Bibliotheque newBibliotheque = new Bibliotheque();
			
			ExportImportCSV.readDocuments(newBibliotheque);
			
			JOptionPane.showMessageDialog(this, "Importation de la bibliotheque terminee",
					"", JOptionPane.PLAIN_MESSAGE);
			
			this.dispose();
			
			// On cree une fenêtre de recherche de livre par prix litteraire
			AfficheurBibliotheque fenetre = new AfficheurBibliotheque(newBibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true); //On la rend visible

		} else if(source == b8) {
			this.dispose();
			
			// On lance la fonction de sauvegarde de la bibliotheque
			ExportImportCSV.writeDocuments(bibliotheque.getDocuments());
			
			JOptionPane.showMessageDialog(this, "Exportation de la bibliotheque terminee",
					"", JOptionPane.PLAIN_MESSAGE);
			
			// On cree une nouvelle fenêtre d'affichage des livres
			AfficheurBibliotheque fenetre = new AfficheurBibliotheque(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true); //On la rend visible
		}
	}
}
