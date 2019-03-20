package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.*;

/**
 * 
 * Classe {@code AfficheurRechercheTitre} permettant de creer une interface graphique afin
 * d'effectuer une recherche d'un document par titre
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class AfficheurRechercheTitre extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Biblioteque ou il faut effectuer la recherche par titre
	 */
	private Bibliotheque 	bibliotheque;
	
	// Composants SWING
	private JButton		    buttonHome;
	private JButton 			bouton;
	private JTextField 		titleField;
	private JTextArea		textArea;
	
	/**
	 * Constructeur de la classe
	 * @param bibliotheque Bibliotheque ou il faut effectuer la recherche
	 */
	public AfficheurRechercheTitre(Bibliotheque bibliotheque, int width, int height, Point location){
		super();
		this.bibliotheque = bibliotheque;
		build(width, height, location); // On initialise notre fenetre
	}

	private void build(int width, int height, Point location){
		setTitle("Afficheur bibliotheque");				// On donne un titre a l'application
		setSize(width, height);							// On donne une taille a notre fenetre
		this.setLocation(location);					// On centre la fenetre sur l'ecran
		setResizable(true);								// On interdit la redimensionnement de la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// On dit a l'application de se fermer lors du clic sur la croix
		
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		
		panel.setLayout(new GridLayout(20, 1));
		panel.setBackground(Color.white);
		
		// Bouton retour au menu principal
		buttonHome = new JButton("Retourner au menu principal");
		buttonHome.addActionListener(this);
		panel.add(buttonHome);
		
		// Demande le titre du livre
		label = new JLabel("Titre du livre a rechercher:");
		panel.add(label);
		titleField = new JTextField();
		panel.add(titleField);
		
		label = new JLabel("");
		panel.add(label);
		
		// Bouton "rechercher"
		bouton = new JButton("Rechercher");
		bouton.addActionListener(this);
		panel.add(bouton);
		
		label = new JLabel("");
		panel.add(label);
		
		// JTextArea ou le resultat de la recherche sera affiche
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		panel.add(textArea);
		
		return panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object 	source = e.getSource();
		
		if (source == bouton){
			Document doc;
			
			doc = bibliotheque.getDocumentParTitre(titleField.getText());
			
			if (doc == null) {
				JOptionPane.showMessageDialog(this, "Document introuvable, veuillez entrer "
						+ "le titre d'un document existant (Attention aux majuscules)",
						"Erreure rencontree", JOptionPane.WARNING_MESSAGE);
			} else {
				textArea.setText(doc.toAfficheur());
			}
		}
		
		if (source == buttonHome) {
			AfficheurBibliotheque fenetre = new AfficheurBibliotheque(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true);
		}
	}
}