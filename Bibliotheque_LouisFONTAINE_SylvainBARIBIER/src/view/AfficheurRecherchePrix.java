package view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import model.*;

/**
 * 
 * Classe {@code AfficheurRecherchePrix} permettant de creer une interface graphique afin
 * d'effectuer une recherche d'un roman par prix litteraire
 *
 * @version 1.0
 * 
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class AfficheurRecherchePrix extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Biblioteque ou il faut effectuer la recherche par prix litteraire
	 */
	private Bibliotheque 	bibliotheque;
	
	// Composants SWING
	private JButton		    buttonHome;
	private JButton 			bouton;
	private JRadioButton 	aucun;
	private JRadioButton 	goncourt;
	private JRadioButton 	medicis;
	private JRadioButton 	interallie;
	private JTextArea		textArea;
	private JLabel 			label;
	private JPanel 			panel;
	
	/**
	 * Constructeur de la classe
	 * @param bibliotheque Bibliotheque ou il faut effectuer la recherche
	 */
	public AfficheurRecherchePrix(Bibliotheque bibliotheque, int width, int height, Point location){
		super();
		this.bibliotheque = bibliotheque;	
		build(width, height, location); // On initialise notre fenetre
	}

	private void build(int width, int height, Point location){
		setTitle("Afficheur bibliotheque");				// On donne un titre a l'application
		setSize(width, height);							// On donne une taille a notre fenetre
		this.setLocation(location);				// On centre la fenetre sur l'ecran
		setResizable(true);								// On interdit la redimensionnement de la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// On dit a l'application de se fermer lors du clic sur la croix
		
		setContentPane(buildContentPane());
	}
	
	private JScrollPane buildContentPane(){
		panel = new JPanel();
		
		panel.setLayout(new GridLayout(20, 1));
		panel.setBackground(Color.white);
		
		
		buttonHome = new JButton("Retourner au menu principal");
		buttonHome.addActionListener(this);
		panel.add(buttonHome);
		
		// Demande le prix litteraire du livre
		label = new JLabel("Prix literaire :");
		panel.add(label);
		aucun = new JRadioButton("Aucun");
        goncourt = new JRadioButton("Goncourt");
        medicis = new JRadioButton("Medicis");
        interallie = new JRadioButton("Interallie");
        
        // On groupe les boutons pour ne pouvoir selectionner qu'un seul choix
	    ButtonGroup group = new ButtonGroup();
	    group.add(aucun);
	    group.add(goncourt);
	    group.add(medicis);
	    group.add(interallie);
	    
	    panel.add(aucun);
	    panel.add(goncourt);
	    panel.add(medicis);
        panel.add(interallie);
		
		bouton = new JButton("Rechercher");
		bouton.addActionListener(this);
		panel.add(bouton);
		
		JScrollPane panelPane = new JScrollPane(panel);
		return panelPane;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object 	source = e.getSource();
		int prixLitteraire = -1;
		
		if (source == bouton){
			
			if (aucun.isSelected()) {
				prixLitteraire = Roman.AUCUN;
			} else if (goncourt.isSelected()) {
				prixLitteraire = Roman.GONCOURT;
			} else if (medicis.isSelected()) {
				prixLitteraire = Roman.MEDICIS;
			} else if (interallie.isSelected()) {
				prixLitteraire = Roman.INTERALLIE;
			}
			
			List<Roman> RomansParPrix = bibliotheque.getRomansParPrix(prixLitteraire);
			for ( Roman doc : RomansParPrix) {
						textArea = new JTextArea(doc.toAfficheur() + "\n");
						panel.add(textArea);
			}
			bouton.setVisible(false);
		}
		
		if (source == buttonHome) {
			AfficheurBibliotheque fenetre = new AfficheurBibliotheque(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true);
		}
	}
}