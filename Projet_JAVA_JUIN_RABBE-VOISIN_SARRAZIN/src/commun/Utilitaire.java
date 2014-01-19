package commun;

import java.awt.Point;

import exceptions.ExceptionCoefficientDirecteurInfini;

public class Utilitaire {

	

	public static double calculerDistanceEntreDeuxPoints(Point a, Point b) {
		return Math.sqrt(Math.pow(b.getX()-a.getX(), 2)+Math.pow(b.getY()-a.getY(), 2));
	}
	
	public static int convertirNombreDHeuresEnMillisecondes(double nombre){
		return (int) (nombre*60*60*1000);
	}
	
	public static int convertirNombreDeMinutesEnMillisecondes(double nombre){
		return (int) (nombre*60*1000);
	}
	
	public static int convertirNombreDeSecondesEnMillisecondes(double nombre){
		return (int) (nombre*1000);
	}

	public static double calculerCoefficientDirecteur(Point a, Point b) throws ExceptionCoefficientDirecteurInfini {
		int deltaX = (int) (b.getX() - a.getX()); 
		int deltaY = (int) (b.getY() - a.getY());
		double coefficientDirecteur = 0;
		
		if(deltaX == 0){ // Cas particulier où trajectoire parallèle à l'axe des ordonnées
			throw new ExceptionCoefficientDirecteurInfini();
		} else {
			coefficientDirecteur = (double) deltaY / deltaX;
		}
		return coefficientDirecteur;
	}

	public static Point calculerCoordonnee(Point coordonneesDernierSiteDGAC, Point coordonneesProchainSiteDGAC, double coefficientDirecteur, double distanceParcourueDepuisLeDernierSiteDGAC, double distanceTotaleAParcourirEntreLesDeuxSitesDGAC) {
		
		double ratioDistance = distanceParcourueDepuisLeDernierSiteDGAC/distanceTotaleAParcourirEntreLesDeuxSitesDGAC;
		int x = (int) (ratioDistance*(coordonneesProchainSiteDGAC.getX()-coordonneesDernierSiteDGAC.getX())+coordonneesDernierSiteDGAC.getX());
		int y = (int) (ratioDistance*(coordonneesProchainSiteDGAC.getY()-coordonneesDernierSiteDGAC.getY())+coordonneesDernierSiteDGAC.getY());

		return new Point(x,y);
	}
}
