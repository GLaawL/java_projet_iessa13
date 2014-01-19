package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Trajectoire {

	private Point depart, arrivee;
	
	private HashMap<Date, Point> listePlots = new HashMap<Date, Point>();
	private ArrayList<Balise> listeDesBalisesTraversees = new ArrayList<Balise>();
	
	public Trajectoire(Point depart, Point arrivee){
		this.depart=depart;
		this.arrivee=arrivee;
	}
	
	public void genererLeCheminLePlusCourt(){
		//this.listeDesBalisesTraversees = DisjtraImplementation.getListeDesBalisesCheminLePlusCourt();
	}


	public void sauvegarderTrajectoireDansFichier(){
		
	}

	public void ajouterBalisesTraversees(Balise balise) {
		listeDesBalisesTraversees.add(balise);
	}

	public HashMap<Date, Point> getListePlots() {
		return listePlots;
	}

	public ArrayList<Balise> getListeDesBalisesTraversees() {
		return listeDesBalisesTraversees;
	}
	
}
