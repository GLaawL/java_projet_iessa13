package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class InterfacePrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu menu = new JMenu("Fichiers");
	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem menuItemInitialisation = new JMenuItem("Initialisation Aerodromes et Balises")
					, menuItemChargerPlansDeVol = new JMenuItem("Charger plans de vol")
					, menuItemAjouterPlansDeVol = new JMenuItem("Ajouter plans de vol")
					, menuItemDerouterAvion = new JMenuItem("Derouter avion")
					, menuItemQuitter = new JMenuItem("Quitter");
	private Carte carte = new Carte();
	
	public InterfacePrincipale(){
		
		// Gestion de la barre des menus
		this.initialiserMenu();
		
		// Initialisation du JPanel
		this.setContentPane(carte);
		
		//Initialisation de la JFrame
		this.setTitle("PROJET JAVA By Juin & Rabbe-Voisin & Sarrazin");
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initialiserMenu(){
		// Ajout des ActionListeners
		menuItemInitialisation.addActionListener(new ActionInitialisation());
		menuItemChargerPlansDeVol.addActionListener(new ActionChargerPlansDeVol());
		menuItemAjouterPlansDeVol.addActionListener(new ActionAjouterPlansDeVol());
		menuItemDerouterAvion.addActionListener(new ActionDerouter());
		menuItemQuitter.addActionListener(new ActionInitialisation());
		//Ajout des menus
		menu.add(menuItemInitialisation);
		menu.add(menuItemChargerPlansDeVol);
		menu.add(menuItemAjouterPlansDeVol);
		menu.add(menuItemDerouterAvion);
		menu.add(menuItemQuitter);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	
	public Carte getCarte(){
		return carte;
	}
	
	public class ActionInitialisation implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			new InitialisationBalisesEtAerodrome();
		}
	}
	
	public class ActionChargerPlansDeVol implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			new ChargerPlansDeVol();
		}
	}
	
	public class ActionAjouterPlansDeVol implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			new AjouterPlansDeVol();
		}
	}
	
	public class ActionDerouter implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			new Derouter();
		}
	}
	
	public class ActionQuitter implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
}

