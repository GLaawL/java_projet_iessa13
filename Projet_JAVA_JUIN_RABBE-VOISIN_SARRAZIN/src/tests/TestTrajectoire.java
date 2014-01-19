package tests;

import java.awt.Point;
import java.util.Date;

import commun.Utilitaire;

import modele.Aerodrome;
import modele.Avion;
import modele.Balise;

public class TestTrajectoire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Point p2 = new Point(100,100);
		Point p3 = new Point(100,150);
		Point p4 = new Point(300,150);
		Point p5 = new Point(400,130);
		Point p6 = new Point(100,270);
		Point p7 = new Point(70,100);
		Point p8 = new Point(20,100);
		
		//long heureDeDepart = System.currentTimeMillis()+Utilitaire.convertirNombreDHeuresEnMillisecondes(1);
		//long heureDeDepart = System.currentTimeMillis()+Utilitaire.convertirNombreDeMinutesEnMillisecondes(1);
		long heureDeDepart = System.currentTimeMillis()+Utilitaire.convertirNombreDeSecondesEnMillisecondes(10);
		
		Avion avion = new Avion("Pouet", new Balise("A", 20, 10), new Aerodrome("B", "LFPO", 20, 50), new Date(heureDeDepart), 1000, 10000);
		
		avion.getTrajectoire().ajouterBalisesTraversees(new Balise("AOC",p2));
		avion.getTrajectoire().ajouterBalisesTraversees(new Balise("AOC",p3));
		avion.getTrajectoire().ajouterBalisesTraversees(new Balise("AOC",p4));
		avion.getTrajectoire().ajouterBalisesTraversees(new Balise("AOC",p5));
		avion.getTrajectoire().ajouterBalisesTraversees(new Balise("AOC",p6));
		avion.getTrajectoire().ajouterBalisesTraversees(new Balise("AOC",p7));
		avion.getTrajectoire().ajouterBalisesTraversees(new Balise("AOC",p8));
		
		avion.genererListeDesPlotsDeTrajectoire();
		
		boolean fin = false;
		while(!fin){
			Date heureActuelle = new Date(System.currentTimeMillis());
			System.out.println("Date et Heure Actuelle: " + heureActuelle);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(heureActuelle.compareTo(avion.getHeureDepart()) >= 0 ){
				for (int i = 0; i < avion.getTrajectoire().getListePlots().size(); i++) {
					if(avion.getTrajectoire().getListePlots().containsKey(new Date(heureDeDepart+i*1000))){
						System.out.println("Date : " + new Date(heureDeDepart+i*1000));
						System.out.println("CoordonnŽes : " + avion.getTrajectoire().getListePlots().get(new Date(heureDeDepart+i*1000)));
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}	
					if(i == avion.getTrajectoire().getListePlots().size()-1){
						fin = true;
					}
				}
			}
		}
		

	}

}
