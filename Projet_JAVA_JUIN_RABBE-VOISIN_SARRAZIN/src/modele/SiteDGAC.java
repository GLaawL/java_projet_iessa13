package modele;

import java.awt.Point;

public abstract class SiteDGAC {

	String nom;
	Point coordonnees;
	
	public SiteDGAC(String nom, int x, int y){
		this.nom=nom;
		this.coordonnees = new Point(x,y);
	}

	public String getNom() {
		return nom;
	}

	public Point getCoordonnees() {
		return coordonnees;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCoordonnees(Point coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	public String toString(){
		return this.nom;
	}
}
