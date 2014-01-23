package commun;


import java.awt.Point;

import vue.InterfacePrincipale;
import exceptions.ExceptionCoefficientDirecteurInfini;
import exceptions.ExceptionErreurCoordonneesGPS;

public class Utilitaire {
	
	public Utilitaire(){
		
	}
	private static double REPERE_X_MAX=500;
	private static double REPERE_Y_MAX=500;
	

	public double getREPERE_X_MAX() {
		return REPERE_X_MAX;
	}

	public double getREPERE_Y_MAX() {
		return REPERE_Y_MAX;
	}

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
		int y =  (int) (ratioDistance*(coordonneesProchainSiteDGAC.getY()-coordonneesDernierSiteDGAC.getY())+coordonneesDernierSiteDGAC.getY());

		return new Point(x,y);
	}
	
	public static Point transformeCoordonneesGPSenCoordonneesRefrentiel(String latitude,String NS,String longitude, String EO) throws ExceptionErreurCoordonneesGPS{

		
		double latitudeCONVERSION, longitudeCONVERSION;

		double heureE=Double.valueOf((String) latitude.subSequence(0,2));
		double heureN=Double.valueOf((String) longitude.subSequence(0,3));
		double minuteE=Double.valueOf((String) latitude.subSequence(3,5));
		double minuteN=Double.valueOf((String) longitude.subSequence(4,6));
		double secondeE=Double.valueOf((String) latitude.subSequence(6,10));
		double secondeN=Double.valueOf((String) longitude.subSequence(7,11));

		latitudeCONVERSION=Double.valueOf(heureN)+minuteN/60+secondeN/3600;
		longitudeCONVERSION=heureE+minuteE/60+secondeE/3600;
		
		if(NS.equals("S"))
			longitudeCONVERSION=-longitudeCONVERSION;
		if(EO.equals("W")){
			latitudeCONVERSION=-latitudeCONVERSION;
		}
			
		return transformeCoordonneesGPSenCoordonneesRefrentiel (latitudeCONVERSION,longitudeCONVERSION);	
	}
	
	public static Point transformeCoordonneesGPSenCoordonneesRefrentiel (double latitude, double longitudinale) throws ExceptionErreurCoordonneesGPS{ 
		
		@SuppressWarnings("unused")
		final double LONGITUDE_MIN=51.75;
		final double LONGITUDE_MAX=40.94;
		final double LATITUDE_MIN=-5.1;
		@SuppressWarnings("unused")
		final double LATITUDE_MAX=10.107;
		
		double X,Y;
		
		X=((REPERE_X_MAX/(LATITUDE_MAX-LATITUDE_MIN))*latitude-((REPERE_X_MAX*LATITUDE_MIN)/(LATITUDE_MAX-LATITUDE_MIN)));
		Y=((REPERE_Y_MAX/(LONGITUDE_MAX-LONGITUDE_MIN))*longitudinale-((REPERE_Y_MAX*LONGITUDE_MIN)/(LONGITUDE_MAX-LONGITUDE_MIN)));
		
		
		if(X>REPERE_X_MAX || Y>REPERE_Y_MAX){
			throw new ExceptionErreurCoordonneesGPS();
		}
		return new Point((int)X,(int)Y);
	}
}
