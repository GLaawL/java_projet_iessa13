package tests;

import java.awt.GridLayout;
import java.sql.Time;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Aerodrome;
import modele.Avion;
import modele.Balise;

public class TestAvion extends JPanel{
		

		/**
	 * 
	 */
	private static final long serialVersionUID = 2374031292151009481L;

		public static void main(String[] args) {
			String nom1="AtoA";
			String nom2="BtoB";
			String nom3="AtoB";
			String nom4="BtoA";
			Balise bDepart=new Balise("BREST", 10, 10);
			Balise bArrive= new Balise("MULHOUSE", 300, 10);
			Aerodrome aDepart= new Aerodrome("NICE", "LPAA", 300, 300);
			Aerodrome aArrive=new Aerodrome("Toulouse", "LFBO", 100, 250);
			Time tDepart1=new Time(14,50,00);
			Time tArrive1=new Time(16,00,00);
			Time tDepart2=new Time(04,50,00);
			Time tArrive2=new Time(06,00,00);
			int vitesse=800;
			int altitude=1800;
			
			// TEST DES 4 CONSTRUCTEURS
			Avion a1=new Avion(nom1, aDepart, aArrive, tDepart1, tArrive1, vitesse, altitude); // TEST Aerodrome to Aerodrome
			Avion a2=new Avion(nom2, bDepart, bArrive, tDepart2, tArrive2, vitesse, altitude); // TEST Balise to Balise
			Avion a3=new Avion(nom3, aDepart, bArrive, tDepart1, tArrive1, vitesse, altitude); // TEST Aerodrome to Balise
			Avion a4=new Avion(nom4, bDepart, aArrive, tDepart2, tArrive2, vitesse, altitude); // TEST Balise to Aerodrome
			
			System.out.println(a1);
			System.out.println(a2);
			System.out.println(a3);
			System.out.println(a4);
			
			// TEST DES ICON OK ET COLLISION
			JFrame fenetre= new JFrame("testAVION");
			JPanel pl1= new JPanel();
			pl1.setLayout(new GridLayout(2, 1));
			JLabel p1= new JLabel();
			JLabel p2= new JLabel();
			p1.setIcon(a1.getIconok());
			p2.setIcon(a2.getIconcollision());
			pl1.add(p1);
			pl1.add(p2);
			fenetre.add(pl1);
			fenetre.setLocation(300,300);
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//fenetre.pack();			
			fenetre.setVisible(true);
			
		    }
		
}
