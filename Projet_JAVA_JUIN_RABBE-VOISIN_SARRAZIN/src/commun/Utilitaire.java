package commun;

import java.awt.Point;

import exceptions.ExceptionCoefficientDirecteurInfini;
import exceptions.ExceptionErreurCoordonneesGPS;

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
		
		if(deltaX == 0){ // Cas particulier o trajectoire parallle ˆ l'axe des ordonnŽes
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
	
	public static Point transformeCoordonneesGPSenCoordonneesRefrentiel(float heureE, float minuteE, float secondeE, float heureN, float minuteN, float secondeN) throws ExceptionErreurCoordonneesGPS{
		// TraitŽ les exceptions COORDONEES_OBSOLETE
		
		float latitudeCONVERSION, longitudeCONVERSION;
		
		
		
		latitudeCONVERSION=heureE+minuteE/60+secondeE/3600;
		longitudeCONVERSION=heureN+minuteN/60+secondeN/3600;
		return transformeCoordonneesGPSenCoordonneesRefrentiel (latitudeCONVERSION,longitudeCONVERSION);
		
	}
	
	public static Point transformeCoordonneesGPSenCoordonneesRefrentiel (float latitude, float longitudinale) throws ExceptionErreurCoordonneesGPS{ 
		
		@SuppressWarnings("unused")
		final double LATITUDE=0;
		final double LATITUDE_MAX=0;
		final double LONGITUDE_MIN=0;
		@SuppressWarnings("unused")
		final double LONGITUDE_MAX=0;
		final double REPERE_X_MAX=0;
		final double REPERE_Y_MAX=0;
		double X,Y;
		
		X=(REPERE_X_MAX/(LATITUDE_MAX-LONGITUDE_MIN)-REPERE_X_MAX);
		Y=(REPERE_Y_MAX/(LATITUDE_MAX-LONGITUDE_MIN)-REPERE_Y_MAX);
		
		if(X>REPERE_X_MAX || Y>REPERE_Y_MAX){
			throw new ExceptionErreurCoordonneesGPS();
		}
			
		return new Point((int)X,(int)Y);
			
	}
}
