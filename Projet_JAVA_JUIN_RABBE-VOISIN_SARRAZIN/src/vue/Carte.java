package vue;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.views.AbstractView;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.view.mxGraph;

import commun.Utilitaire;
import exceptions.ExceptionErreurCoordonneesGPS;

public class Carte extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point[] p4= new Point [1000];
	Point[] p5= new Point [1000];
	Point[] p6= new Point [1000];
	Point[] p7= new Point [1000];
	private mxGraph graph;
	boolean normale=true, clic=false;
	float f=2;
	int z=0,m=0;

	private int Xpoint=10, Ypoint=10;
	
	public int getXpoint() {
		return Xpoint;
	}

	public void setXpoint(int xpoint) {
		Xpoint = xpoint;
	}

	public int getYpoint() {
		return Ypoint;
	}

	public void setYpoint(int ypoint) {
		Ypoint = ypoint;
	}
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
		//this.addMouseWheelListener(new ActionMouseZoom(g));
		this.addMouseListener(new ActionMouseZoom(g));
		
		if(normale==true){
			for(int j=0;j<(i-1);j++){
			
				g.fillOval((int)p4 [j].getX(), (int) p4 [j].getY(), Xpoint, Ypoint);
			}
			for(int l=0;l<(k-1);l++){	
				g.setColor(Color.red);
				g.fillOval((int)p5 [l].getX(), (int) p5 [l].getY(), Xpoint, Ypoint);
			
			}
		}else{
			if(clic==true)
			g.clearRect(0,0,1000,1000);
			 for(int j=0;j<(z-1);j++){
			g.fillOval((int)p6 [j].getX(), (int)p6 [j].getY(), Xpoint, Ypoint);
			 }
			 for(int j=0;j<(m-1);j++){
				 	g.setColor(Color.red);
					g.fillOval((int)p7 [j].getX(), (int)p7 [j].getY(), Xpoint, Ypoint);
					 }
			clic=false;
			
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
	//public class ActionMouseZoom implements MouseWheelListener, org.w3c.dom.events.MouseEvent{
	public class ActionMouseZoom implements MouseListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Graphics g;
		public ActionMouseZoom(Graphics g){
			this.g=g;
		}
		

		@Override
		//public void mouseWheelMoved(MouseWheelEvent e) {
			public void mouseClicked(MouseEvent e) {	
			System.out.println("on y est");
			Point p1=e.getPoint();
			
			System.out.println(p1.getX()+":"+p1.getY());
			clic=true;
			
			//int tours = e.getWheelRotation();
			//if(tours>0){
				f=(float) (f+0.1);
				System.out.println(f);
				normale=false;
				z=0;
				m=0;
				System.out.println(normale);
				
			//}
			//if(tours<0){
			//	f=(float) (f-0.2);
			//}
		    double X=(int) ((int)(new Utilitaire().getREPERE_X_MAX())/f);
		    System.out.println(X);
		    double Y=(int) ((int)(new Utilitaire().getREPERE_Y_MAX())/f);	
		    System.out.println(Y);
		    Xpoint= (int) (10/(f/2));
		    Ypoint=(int)(10/(f/2));
		    System.out.println(Xpoint+"3"+Ypoint);
		    
		    for(int j=0;j<(k-1);j++){
		    	
				if( (int)p5 [j].getX() > (int)(p1.getX()-X) && (int)p5 [j].getX() < (int)(p1.getX()+X)){
					if( (int)p5 [j].getY() > (int)(p1.getY()-Y) && (int)p5 [j].getY() < (int)(p1.getY()+Y)){
						System.out.println("ok");
						p7[m]=new Point((int) -((f/2)*(-p5[j].getX()+p1.getX()-500/f)),(int) -((f/2)*(-p5[j].getY()+p1.getY()-550/f)));
						
						System.out.println(p7[m].getX()+":"+p7[m].getY());
						m++;
					}
						System.out.println(m);
			}
		    }
		    
		    p5=p7;
		    k=m;
		    for(int j=0;j<(i-1);j++){
		    	
				if( (int)p4 [j].getX() > (int)(p1.getX()-X) && (int)p4 [j].getX() < (int)(p1.getX()+X)){
					if( (int)p4 [j].getY() > (int)(p1.getY()-Y) && (int)p4 [j].getY() < (int)(p1.getY()+Y)){
						System.out.println("ok");
						p6[z]=new Point((int) -((f/2)*(-p4[j].getX()+p1.getX()-500/f)),(int) -((f/2)*(-p4[j].getY()+p1.getY()-550/f)));
						
						System.out.println(p6[z].getX()+":"+p6[z].getY());
						z++;
					}
						System.out.println(z);
			}
		    }
		    p4=p6;
		    i=z;
		    
		    repaint();	
			
		/*	
			@SuppressWarnings("unused")
			final double LONGITUDE_MIN=51.75;
			final double LONGITUDE_MAX=40.94;
			final double LATITUDE_MIN=-5.1;
			@SuppressWarnings("unused")
			final double LATITUDE_MAX=10.107;
			
			double X,Y;
			
			X=((REPERE_X_MAX/(LATITUDE_MAX-LATITUDE_MIN))*latitude-((REPERE_X_MAX*LATITUDE_MIN)/(LATITUDE_MAX-LATITUDE_MIN)));
			Y=((REPERE_Y_MAX/(LONGITUDE_MAX-LONGITUDE_MIN))*longitudinale-((REPERE_Y_MAX*LONGITUDE_MIN)/(LONGITUDE_MAX-LONGITUDE_MIN)));
			
			*/
		    
		         
		   
		             
		    
			
			
		}


		//@Override
		/*public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}*/


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		


	


	}
}

	

