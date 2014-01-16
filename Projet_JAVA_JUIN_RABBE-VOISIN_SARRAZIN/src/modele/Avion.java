/**
 * @author antoninrabbe-voisin
 *
 */

package modele;
import java.sql.Time;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class Avion extends JPanel{
	
	//////////////////////Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1578212749159318359L;
	private String 	indicatif;																	// Nom de l'avion
	private static final ImageIcon IconOK = new ImageIcon("avion9.gif");						// Image de l'avion NORMAL
	private static final ImageIcon IconCOLLISION = new ImageIcon("avion9.gif");				// Image de l'avion COLLISION
	//private Balise 	baliseDepart=null, baliseArrive=null; 												// Balise
	//private Aerodrome 	aerodromeDepart=null, aerodromeArrive=null;												// Aerodrome
	private SiteDGAC depart, arrivee;
	private Time 	heureDepart, heureArrive;  													// Heure de depart & d'arrivé
	private int  	niveauVol;																	// Altitude de vol (constante)
	private int 	vitesse;																		// vitesse de vol
	
	//////////////////// Constructor

	public Avion (String indicatif,SiteDGAC depart,SiteDGAC arrive,Time heureDepart, Time heureArrive,int vitesse,int niveauVol){
		this.indicatif = indicatif;
		this.depart = depart;
		this.arrivee = arrive;
		this.heureDepart = heureDepart;
		this.heureArrive = heureArrive;
		this.vitesse = vitesse;
		this.niveauVol = niveauVol;
	}
	
	/*// Constructeur Aerodrome to Aerodrome
	public Avion (String indicatif,Aerodrome aerodromeDepart,Aerodrome aerodromeArrive,Time heureDepart, Time heureArrive,int vitesse,int niveauVol){
		this.indicatif = indicatif;
		this.aerodromeDepart = aerodromeDepart;
		this.aerodromeArrive = aerodromeArrive;
		this.heureDepart = heureDepart;
		this.heureArrive = heureArrive;
		this.vitesse = vitesse;
		this.niveauVol = niveauVol;
	}
	
	// Constructeur Balise to Balise
	public Avion (String indicatif,Balise baliseDepart,Balise baliseArrive,Time heureDepart, Time heureArrive,int vitesse,int niveauVol){
		this.indicatif = indicatif;
		this.baliseDepart = baliseDepart;
		this.baliseArrive = baliseArrive;
		this.heureDepart = heureDepart;
		this.heureArrive = heureArrive;
		this.vitesse = vitesse;
		this.niveauVol = niveauVol;		
	}
	
	// Constructeur Balise to Aerodrome
	public Avion (String indicatif,Balise baliseDepart,Aerodrome aerodromeArrive,Time heureDepart, Time heureArrive,int vitesse,int niveauVol){
		this.indicatif = indicatif;
		this.baliseDepart = baliseDepart;
		this.aerodromeArrive = aerodromeArrive;
		this.heureDepart = heureDepart;
		this.heureArrive = heureArrive;
		this.vitesse = vitesse;
		this.niveauVol = niveauVol;

		
	}
	
	// Constructeur Aerodrome to Balise
	public Avion (String indicatif,Aerodrome aerodromeDepart,Balise baliseArrive,Time heureDepart, Time heureArrive,int vitesse,int niveauVol ){
		this.indicatif = indicatif;
		this.aerodromeDepart = aerodromeDepart;
		this.baliseArrive = baliseArrive;
		this.heureDepart = heureDepart;
		this.heureArrive = heureArrive;
		this.vitesse = vitesse;
		this.niveauVol = niveauVol;
		
	}*/
	
	
	 ///////////////////////////// Redefine TOSTRING 
	
	public String toString(){
		return this.indicatif+" "+depart+":"+heureDepart+" "+arrivee+":"+heureArrive+" "+vitesse+" km/h "+ niveauVol+" alt";
		/*if(baliseDepart==null && baliseArrive==null)
			return this.indicatif+" "+aerodromeDepart+":"+heureDepart+" "+aerodromeArrive+":"+heureArrive+" "+vitesse+" km/h "+ niveauVol+" alt";
		else if(aerodromeDepart==null && baliseArrive==null)
			return this.indicatif+" "+baliseDepart+":"+heureDepart+" "+aerodromeArrive+":"+heureArrive+" "+vitesse+" km/h "+ niveauVol+" alt";
		else if(baliseDepart==null && aerodromeArrive==null)
			return this.indicatif+" "+aerodromeDepart+":"+heureDepart+" "+baliseArrive+":"+heureArrive+" "+vitesse+" km/h "+ niveauVol+" alt";
		else
			return this.indicatif+" "+baliseDepart+":"+heureDepart+" "+baliseArrive+":"+heureArrive+" "+vitesse+" km/h "+ niveauVol+" alt";*/
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
	/*public Balise getBaliseDepart() {
		return baliseDepart;
	}
	public Balise getBaliseArrive() {
		return baliseArrive;
	}
	public Aerodrome getAerodromeDepart() {
		return aerodromeDepart;
	}
	public Aerodrome getAerodromeArrive() {
		return aerodromeArrive;
	}*/
	public Time getHeureDepart() {
		return heureDepart;
	}
	public Time getHeureArrive() {
		return heureArrive;
	}
	public int getNiveauVol() {
		return niveauVol;
	}
	public int getVitesse() {
		return vitesse;
	}
	public void setIndicatif(String indicatif) {
		this.indicatif = indicatif;
	}
	/*public void setBaliseDepart(Balise baliseDepart) {
		this.baliseDepart = baliseDepart;
	}
	public void setBaliseArrive(Balise baliseArrive) {
		this.baliseArrive = baliseArrive;
	}
	public void setAerodromeDepart(Aerodrome aerodromeDepart) {
		this.aerodromeDepart = aerodromeDepart;
	}
	public void setAerodromeArrive(Aerodrome aerodromeArrive) {
		this.aerodromeArrive = aerodromeArrive;
	}
	public void setHeureDepart(Time heureDepart) {
		this.heureDepart = heureDepart;
	}*/
	public void setHeureArrive(Time heureArrive) {
		this.heureArrive = heureArrive;
	}
	public void setNiveauVol(int niveauVol) {
		this.niveauVol = niveauVol;
	}
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	
	
	
}
