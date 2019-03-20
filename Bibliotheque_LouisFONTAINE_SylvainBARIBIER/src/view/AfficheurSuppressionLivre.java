package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.*;

/**
 * 
 * Classe {@code AfficheurSuppressionLivre} permettant de creer une interface graphique afin
 * d'effectuer la suppression d'un document de la bibliotheque
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class AfficheurSuppressionLivre extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Biblioteque ou supprimer le livre
	 */
	private Bibliotheque 	bibliotheque;
	
	// Composants SWING
	private JButton		    buttonHome;
	private JButton 			buttonSupprimer;
	private JTextField 		titleField;
	
	/**
	 * Constructeur de la classe
	 * @param bibliotheque Bibliotheque ou il faut supprimer le livre
	 */
	public AfficheurSuppressionLivre(Bibliotheque bibliotheque, int width, int height, Point location){
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
		label = new JLabel("Titre du livre a supprimer :");
		panel.add(label);
		titleField = new JTextField();
		panel.add(titleField);
		
		label = new JLabel("");
		panel.add(label);
		
		// Bouton supprimer
		buttonSupprimer = new JButton("Supprimer");
		buttonSupprimer.addActionListener(this);
		panel.add(buttonSupprimer);
		
		return panel;
	}
	
	/**
	 * Description : fonction qui s'execute si l'utilisateur appuie sur le bouton "Valider"
	 * Elle permet d'afficher dans la console les inforamtions du livre que l'utilisateur 
	 * veut ajouter ainsi que de l'ajouter a une biblioteque
	 */
	public void actionPerformed(ActionEvent e) {
		Object 	source = e.getSource();
		
		if (source == buttonSupprimer){
			Document doc;
			// On recupere le document de la bibliotheque correspondant avec le titre 
			doc = bibliotheque.getDocumentParTitre(titleField.getText());
	
			try {
				bibliotheque.removeDocument(doc);
				
				JOptionPane.showMessageDialog(this, "Le document \""+ doc.getTitre() + "\" a bien ete supprime de la bibliotheque",
						"", JOptionPane.PLAIN_MESSAGE);
				
				bibliotheque.sort();
				
				this.dispose();
					 
				AfficheurBibliotheque fenetre = new AfficheurBibliotheque(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
				fenetre.setVisible(true);
				
			} catch (Exception error) {
				JOptionPane.showMessageDialog(this, "Document introuvable, veuillez entrer le titre d'un document existant",
						"Erreure rencontree", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (source == buttonHome) {
			AfficheurBibliotheque fenetre = new AfficheurBibliotheque(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true);
		}
	}
}