package modele;

import java.awt.Point;


public class Balise extends SiteDGAC{

	public Balise(String nom, int x, int y) {
		super(nom, x, y);
	}
	
	public Balise(String nom, Point coordonnees) {
		super(nom, coordonnees);
	}
}
