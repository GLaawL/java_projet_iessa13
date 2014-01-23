package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import commun.Utilitaire;
import exceptions.ExceptionErreurCoordonneesGPS;

public class Carte extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point[] p4= new Point [1000];
	Point[] p5= new Point [1000];
	int i=0,k=0;
	
	public Carte(){
		String fichier1="aerodrome.txt";
		String fichier2="balise.txt";
    	String tampon=null, tampon1=null;;
    	BufferedReader fe,fe1;
        try {
            fe = new BufferedReader(new FileReader(fichier1));
            while ( (tampon=fe.readLine()) != null)
                {
                	
                	StringTokenizer t=new StringTokenizer(tampon,", ");
                	double latitude=Double.valueOf(t.nextToken());
                	double longitude=Double.valueOf(t.nextToken());
                	String nom=t.nextToken();
                	
                	try {
                	
						p4 [i]=Utilitaire.transformeCoordonneesGPSenCoordonneesRefrentiel(latitude,longitude);
						i++;

                	} catch (NumberFormatException e) {
                		
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExceptionErreurCoordonneesGPS e) {
						// TODO Auto-generated catch block
						e.printStackTrace();					
					}
                }
             fe.close();                
        } catch (FileNotFoundException ex) {
            
        }
        catch (IOException ex) {
            
        }
        try {
            fe1 = new BufferedReader(new FileReader(fichier2));
           
            while ( (tampon1=fe1.readLine()) != null)
                {
                	
                	StringTokenizer t1=new StringTokenizer(tampon1," ");                	
                	String nom1=t1.nextToken();                	
                	String altitude=t1.nextToken();                
                	String NS=t1.nextToken();                	
                	String longitude=t1.nextToken();               	
                	String EO=t1.nextToken();
                	
                	
                	try {
                		
						p5 [k]=Utilitaire.transformeCoordonneesGPSenCoordonneesRefrentiel(altitude,NS,longitude,EO);
						k++;

                	} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExceptionErreurCoordonneesGPS e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
                	
                }
             fe1.close();                
        } catch (FileNotFoundException ex) {
            
        }
        catch (IOException ex) {
            
        }
		this.setBackground(Color.WHITE);
		
	}
	
	public void paintComponent(Graphics g){
		
		for(int j=0;j<(i-1);j++){
			
			g.fillOval((int)p4 [j].getX(), (int) p4 [j].getY(), 5, 5);
		}
		for(int l=0;l<(k-1);l++){	
			g.setColor(Color.red);
			g.fillOval((int)p5 [l].getX(), (int) p5 [l].getY(), 5, 5);
		}
		

		
		//System.out.println("Carte -> paintComponent");
		/*super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		//TEST		
		g2.drawImage(avion.getImageIcon().getImage(), (int) avion.getCoordonneeEcranActuelle().getX(), (int) avion.getCoordonneeEcranActuelle().getY(), this);
		AffineTransform at = g2.getTransform();
		at.rotate(Math.toRadians(90));
		
		for (int i = 0; i < avion.getBalisesTraversees().size(); i++) {
			if(i+1 < avion.getBalisesTraversees().size()){
				g2.setColor(Color.RED);
				g2.drawLine((int)avion.getBalisesTraversees().get(i).getX()+5, (int)avion.getBalisesTraversees().get(i).getY()+5, (int)avion.getBalisesTraversees().get(i+1).getX()+5, (int)avion.getBalisesTraversees().get(i+1).getY()+5);
				g2.setColor(Color.BLACK);
			}
			g2.fillOval((int)avion.getBalisesTraversees().get(i).getX(), (int)avion.getBalisesTraversees().get(i).getY(), 10, 10);
		}
		g2.drawImage(avion.getImageIcon().getImage(), (int)avion.getCoordonneeEcranActuelle().getX(), (int)avion.getCoordonneeEcranActuelle().getY(), this);
	*/
	}
	
}
