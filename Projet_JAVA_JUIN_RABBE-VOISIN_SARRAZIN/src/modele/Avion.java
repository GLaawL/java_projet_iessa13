/**
 * @author antoninrabbe-voisin
 *
 */

package modele;
import java.awt.Point;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import commun.Utilitaire;

import exceptions.ExceptionCoefficientDirecteurInfini;



public class Avion extends JPanel{

	//////////////////////Attributes

	/**
	 * 
	 */
	private static final long serialVersionUID = 1578212749159318359L;
	private String 	indicatif;																	// Nom de l'avion
	private static final ImageIcon IconOK = new ImageIcon("avion9.gif");						// Image de l'avion NORMAL
	private static final ImageIcon IconCOLLISION = new ImageIcon("avion9.gif");				// Image de l'avion COLLISION										// Aerodrome
	private SiteDGAC depart, arrivee;
	private Date 	heureDepart, heureArrive;  													// Heure de depart & d'arrivé
	private int  	niveauVol;																	// Altitude de vol (constante)
	private double 	vitesse;																		// vitesse de vol
	private Trajectoire trajectoire;
	
	private boolean deplacementVerticale = false;

	//////////////////// Constructor

	public Avion (String indicatif,SiteDGAC depart,SiteDGAC arrive, Date heureDepart, double vitesse,int niveauVol){
		this.indicatif = indicatif;
		this.depart = depart;
		this.arrivee = arrive;
		this.heureDepart = heureDepart;
		this.vitesse = vitesse;
		this.niveauVol = niveauVol;
		this.trajectoire = new Trajectoire(depart.getCoordonnees(), arrivee.getCoordonnees());
	}

	public void genererListeDesPlotsDeTrajectoire(){
		Point a = new Point();
		Point b = new Point();
		// A rajouter dans les initialisations !!!
		double distance = 0;
		double temps = 0; // en milliseconde
		
		// ajout du point de départ dans la liste des plots de trajectoire de l'avion
		trajectoire.getListePlots().put(new Date((long) (heureDepart.getTime())), depart.getCoordonnees());
		
		for (int i = 0; i < trajectoire.getListeDesBalisesTraversees().size(); i++) {	
			if(i == 0){
				a = depart.getCoordonnees();
				b = trajectoire.getListeDesBalisesTraversees().get(i).getCoordonnees();
			} else if(i >= 1 && i < trajectoire.getListeDesBalisesTraversees().size()-1){
				a = trajectoire.getListeDesBalisesTraversees().get(i).getCoordonnees();
				b = trajectoire.getListeDesBalisesTraversees().get(i+1).getCoordonnees();
			} else if(i == trajectoire.getListeDesBalisesTraversees().size()-1){
				a = trajectoire.getListeDesBalisesTraversees().get(1).getCoordonnees();
				b = arrivee.getCoordonnees();
			}
			
			distance = Utilitaire.calculerDistanceEntreDeuxPoints(a,b);
			temps = Utilitaire.convertirNombreDHeuresEnMillisecondes(distance/vitesse);
			double distanceParcourueEnUneMilliSeconde = distance/temps;
			double distancePourTableau = 0;
			double coefficientDirecteur = 0;
			try {
				deplacementVerticale = false;
				coefficientDirecteur = Utilitaire.calculerCoefficientDirecteur(a,b);
			} catch (ExceptionCoefficientDirecteurInfini e) {
				deplacementVerticale = true;
				e.printStackTrace();
			}
			
			double tempsDEchantillonnageDesPlots = 1000;
			long tempsDeTrajet = 0; // Pour 1 seconde
			
			/*System.out.println("heure de départ : " + heureDepart.getTime());
			System.out.println("vitesse : " + vitesse);
			System.out.println("distance : " + distance);
			System.out.println("temps : " + temps);
			System.out.println("distanceParcourueEnUneMilliSeconde : " + distanceParcourueEnUneMilliSeconde);
			System.out.println("coefficientDirecteur : " + coefficientDirecteur);
			System.out.println(heureDepart.getTime());
			System.out.println(new Date(heureDepart.getTime()));*/
			
			while(distancePourTableau<distance){
				//System.out.println(new Date(heureDepart.getTime()+tempsDeTrajet));
				tempsDeTrajet += tempsDEchantillonnageDesPlots;
				//System.out.println(Utilitaire.calculerCoordonnee(a,b,coefficientDirecteur, tempsDeTrajet*distanceParcourueEnUneMilliSeconde, distance));
				trajectoire.getListePlots().put(new Date((long) (heureDepart.getTime()+tempsDeTrajet)), Utilitaire.calculerCoordonnee(a,b,coefficientDirecteur, tempsDeTrajet*distanceParcourueEnUneMilliSeconde, distance));
				distancePourTableau+=distanceParcourueEnUneMilliSeconde*tempsDEchantillonnageDesPlots;
			}
			trajectoire.getListePlots().put(new Date((long) (heureDepart.getTime())), trajectoire.getListeDesBalisesTraversees().get(i).getCoordonnees());
		}
		
		
	}
	

	///////////////////////////// Redefine TOSTRING 
	public String toString(){
		return this.indicatif+" "+depart+":"+heureDepart+" "+arrivee+":"+heureArrive+" "+vitesse+" km/h "+ niveauVol+" alt";
	}
	
	
	public SiteDGAC getDepart() {
		return depart;
	}


	public SiteDGAC getArrivee() {
		return arrivee;
	}


	public Trajectoire getTrajectoire() {
		return trajectoire;
	}


	public void setDepart(SiteDGAC depart) {
		this.depart = depart;
	}


	public void setArrivee(SiteDGAC arrivee) {
		this.arrivee = arrivee;
	}


	public void setHeureDepart(Date heureDepart) {
		this.heureDepart = heureDepart;
	}


	public void setTrajectoire(Trajectoire trajectoire) {
		this.trajectoire = trajectoire;
	}

	public String getIndicatif() {
		return indicatif;
	}
	public static ImageIcon getIconok() {
		return IconOK;
	}
	public static ImageIcon getIconcollision() {
		return IconCOLLISION;
	}
	public Date getHeureDepart() {
		return heureDepart;
	}
	public Date getHeureArrive() {
		return heureArrive;
	}
	public int getNiveauVol() {
		return niveauVol;
	}
	public double getVitesse() {
		return vitesse;
	}
	public void setIndicatif(String indicatif) {
		this.indicatif = indicatif;
	}
	public void setHeureArrive(Date heureArrive) {
		this.heureArrive = heureArrive;
	}
	public void setNiveauVol(int niveauVol) {
		this.niveauVol = niveauVol;
	}
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}




}
