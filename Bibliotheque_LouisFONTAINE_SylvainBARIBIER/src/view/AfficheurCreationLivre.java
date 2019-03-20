package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.*;

/**
 * 
 * Classe {@code AfficheurCreationLivre} permettant de creer une interface graphique afin
 * d'ajouter un livre a une bibliotheque
 *
 * @version 1.0
 *
 * @since 01/04/2018
 *
 * @author Louis Fontaine
 * @author Sylvain Barbier
 * 
 */

public class AfficheurCreationLivre extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Biblioteque ou inserer le Livre
	 */
	private Bibliotheque 	bibliotheque;
	
	// Composants Swing
	private JButton		    buttonHome;
	private JButton 			bouton;
	private JTextField 		titreField;
	private JTextField 		auteurField;
	private JTextField 		nbPagesField;
	private JRadioButton 	aucun;
	private JRadioButton 	goncourt;
	private JRadioButton 	medicis;
	private JRadioButton 	interallie;
	private	JLabel 			label;
	
	/**
	 * Constructeur de la classe
	 * @param bibliotheque Bibliotheque ou inserer le livre
	 */
	public AfficheurCreationLivre(Bibliotheque bibliotheque, int width, int height, Point location){
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
		
		panel.setLayout(new GridLayout(20, 1));
		panel.setBackground(Color.white);
		
		// Bouton retour a la page d'accueil
		buttonHome = new JButton("Retourner au menu principal");
		buttonHome.addActionListener(this);
		panel.add(buttonHome);
		
		// Demande le titre du livre
		label = new JLabel("Titre :");
		panel.add(label);
		titreField = new JTextField();
		panel.add(titreField);
		
		label = new JLabel("");
		panel.add(label);
		
		// Demande l'auteur du livre
		label = new JLabel("Auteur :");
		panel.add(label);
		auteurField = new JTextField();
		panel.add(auteurField);
		
		label = new JLabel("");
		panel.add(label);
		
		// Demande le nombre de pages du livre
		label = new JLabel("Nb pages :");
		panel.add(label);
		nbPagesField = new JTextField();
		panel.add(nbPagesField);
		
		label = new JLabel("");
		panel.add(label);
		
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
        
        label = new JLabel("");
		panel.add(label);
		
		// Bouton "valider"
		bouton = new JButton("Valider");
		bouton.addActionListener(this);
		panel.add(bouton);
		
		return panel;
	}
	
	/**
	 * Description : fonction qui s'execute si l'utilisateur appuie sur le bouton "Valider"
	 * Elle permet d'afficher dans la console les inforamtions du livre que l'utilisateur 
	 * veut ajouter ainsi que de l'ajouter a une biblioteque
	 */
	public void actionPerformed(ActionEvent e) {
		Object 	source = e.getSource();	// Permet de savoir sur quel bouton l'utilisateur a clique
		int 		nbPages = 0;				// Stocke le nombre de page du roman 
		int 		prixLitteraire = -1;		// Stocke le prix litteraire du roman
		
		// Bouton valider
		if (source == bouton){
			
			// On atribue une valeur entre 0 et 4 en fonction du choix du prix litteraire saisi par l'utilisateur
			if (aucun.isSelected()) {
				prixLitteraire = Roman.AUCUN;
				System.out.println("Aucun");
			} else if (goncourt.isSelected()) {
				prixLitteraire = Roman.GONCOURT;
				System.out.println("Goncourt");
			} else if (medicis.isSelected()) {
				prixLitteraire = Roman.MEDICIS;
				System.out.println("Medicis");
			} else if (interallie.isSelected()) {
				prixLitteraire = Roman.INTERALLIE;
				System.out.println("Interallie");
			}

			if (titreField.getText().isEmpty()) {
				
				// Si l'utilisateur n'a pas saisi de titre
				JOptionPane.showMessageDialog(this, "Veuillez saisir le titre du roman a inserer",
						"Erreure rencontree", JOptionPane.WARNING_MESSAGE);
				
			} else if (auteurField.getText().isEmpty()) {
				
				// Si l'utilisateur n'a pas saisi de nom d'auteur
				JOptionPane.showMessageDialog(this, "Veuillez saisir le nom de l'auteur du roman a inserer",
						"Erreure rencontree", JOptionPane.WARNING_MESSAGE);
				
			} else if (nbPagesField.getText().isEmpty()) {
				
				// Si l'utilisateur n'a pas saisi le nombre de pages
				JOptionPane.showMessageDialog(this, "Veuillez saisir le nombre de page du roman a inserer",
						"Erreure rencontree", JOptionPane.WARNING_MESSAGE);
				
			}
			else if (prixLitteraire == -1) {
				// Si l'utilisateur n'a pas saisi de pris litteraire
				JOptionPane.showMessageDialog(this, "Veuillez selectionner un prix",
						"Erreure rencontree", JOptionPane.WARNING_MESSAGE);
			} else {
				
				// On convertie le nombre de page (String) en int
				try {
					nbPages = Integer.parseInt(nbPagesField.getText());
				} catch (NumberFormatException er) {
					System.out.print("Nombre de pages incorect");
				}
				
				// On affiche dans la console le sinformations du livre saisies
				System.out.println("Ajout du roman : ");
				System.out.println("Titre : " + titreField.getText());
				System.out.println("Auteur : " + auteurField.getText());
				System.out.println("Nombre de Page : " + nbPages);
				System.out.print("Prix litteraire : ");
				
				// On ajoute le livre a la bibliotheque
				Document document = new Roman(titreField.getText(), auteurField.getText(), nbPages, prixLitteraire);
				
				try {
					bibliotheque.addDocument(document);
				} catch (Exception myException) {
					// Si l'utilisateur n'a pas saisi de nombre de page ou a saisi un nombre de page inferieur a 1
					JOptionPane.showMessageDialog(this, "Veuillez saisir un nombre de pages superieur a 0",
							"Erreure rencontree", JOptionPane.WARNING_MESSAGE);
				}
				
				// On ferme la fenêtre
				this.dispose();
				
				// On recre un fenêtre d'affichage de bibliotheque
				AfficheurBibliotheque fenetre = new AfficheurBibliotheque(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
				fenetre.setVisible(true);
			}
		}
		
		// Bouton Home
		if (source == buttonHome) {
			AfficheurBibliotheque fenetre = new AfficheurBibliotheque(bibliotheque, this.getWidth(), this.getHeight(), this.getLocation());
			fenetre.setVisible(true);
		}
	}
}