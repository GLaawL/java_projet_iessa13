package modele;

public class Aerodrome extends SiteDGAC {

	String OACI;
	
	public Aerodrome(String nom, String OACI, int x, int y){
		super(nom, x, y);
		this.OACI = OACI;
	}

	
}
