package vue;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyEventPostProcessor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.views.AbstractView;

import commun.Utilitaire;
import exceptions.ExceptionErreurCoordonneesGPS;

public class Carte extends JPanel{
	

	private static final long serialVersionUID = 1L;
	private ArrayList<Point> p4=new ArrayList();
	private ArrayList<Point> p5=new ArrayList();
	private ArrayList<Point> p6=new ArrayList();
	private ArrayList<Point> p7=new ArrayList();
	private ArrayList<Point> p8=new ArrayList();
	private ArrayList<Point> p9=new ArrayList();
	private Point p1= new Point(1,1);
	private Point p2= new Point(1,1);
	private static JButton b1= new JButton (new ImageIcon("fleche_haut.png"));
	private static JButton b2= new JButton (new ImageIcon("fleche_gauche.png"));
	private static JButton b3= new JButton (new ImageIcon("fleche_centre.png"));
	private static JButton b4= new JButton (new ImageIcon("fleche_droite.png"));
	private static JButton b5= new JButton (new ImageIcon("fleche_bas.png"));
	private static JLabel l1= new JLabel();
	private Container c1=new Container();
	private Container c2=new Container();
	private boolean normale=true, clic=false, zoom=false, Cliked=true;
	private float f=2;
	private int Xpoint=5, Ypoint=5,z=0,m=0,p=0,i=0,k=0;
	
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
                	
						p4.add(Utilitaire.transformeCoordonneesGPSenCoordonneesRefrentiel(latitude,longitude));
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
						p5.add(Utilitaire.transformeCoordonneesGPSenCoordonneesRefrentiel(altitude,NS,longitude,EO));
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
		this.addKeyListener(new ActionClavier());
		
		this.addMouseWheelListener(new ActionMouseZoom());
		b1.setSize(40, 40);
		b1.setLocation(418, 3);
		b1.addMouseListener(new ActionDecalageHaut());
		b2.setSize(40, 40);
		b2.setLocation(376, 45);
		b2.addMouseListener(new ActionDecalageDroite());
		b3.setSize(40, 40);
		b3.setLocation(418, 45);
		b3.addMouseListener(new ActionVueNormale());
		b4.setSize(40, 40);
		b4.setLocation(460, 45);
		b4.addMouseListener(new ActionDecalageGauche());
		b5.setSize(40, 40);
		b5.setLocation(418, 87);
		b5.addMouseListener(new ActionDecalageBas());
		if(Cliked==true){
		
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		}	
		if(normale==true){
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			b5.setVisible(false);
			System.out.println("OK1");
		}
		
		if(normale==true){
			g.clearRect(0,0,10000,10000);
			//System.out.println("OK2");
			for(int j=0;j<p4.size();j++){
				g.fillOval((int)p4.get(j).getX(), (int) p4.get(j).getY(), Xpoint, Ypoint);
			}
			for(int l=0;l<p5.size();l++){	
				g.setColor(Color.red);
				g.fillOval((int)p5.get(l).getX(), (int)p5.get(l).getY(), Xpoint, Ypoint);
			}
		}else{
			g.clearRect(0,0,1000,1000);
			for(int j=0;j<p6.size();j++){
				 g.fillOval((int)p6.get(j).getX(), (int)p6.get(j).getY(), Xpoint, Ypoint);
			 }
			 for(int j=0;j<p7.size();j++){
				 	g.setColor(Color.red);
					g.fillOval((int)p7.get(j).getX(), (int)p7.get(j).getY(), Xpoint, Ypoint);
			}
			clic=false;			
		}	
	}

	public class ActionMouseZoom implements MouseWheelListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			public void mouseWheelMoved(MouseWheelEvent e) {	
			
				b1.setVisible(true);
				b2.setVisible(true);
				b3.setVisible(true);
				b4.setVisible(true);
				b5.setVisible(true);
				repaint();
			//if(zoom==false){
				p1=e.getPoint();
				//System.out.println("boude pas");
				//System.out.println(p1.getX()+":"+p1.getY());
				zoom=true;
			//}
			/*else{
				p++;
				p2=e.getPoint();
				p1=new Point((int)((f/2)*(p2.getX()-p1.getX()+(500/f))),(int)((f/2)*(p2.getY()-p1.getY()+(500/f))));
				System.out.println("Ca marche");
				System.out.println(p1.getX()+":"+p1.getY());
			}*/
			
				
			p6.removeAll(p6);
			p7.removeAll(p7);
			clic=true;
			int tours = e.getWheelRotation();
			if(tours>0){
				f=(float) (f+0.01);
				normale=false;
				z=0;
				m=0;
				
			}
			if(tours<0){
				f=(float) (f-0.01);
				normale=false;
			}
		    double X=(int) ((int)(new Utilitaire().getREPERE_X_MAX())/f);
		    double Y=(int) ((int)(new Utilitaire().getREPERE_Y_MAX())/f);	
		    for(int j=0;j<p5.size();j++){
				p7.add(new Point((int) -((f/2)*(-p5.get(j).getX()+p1.getX()-500/f)),(int) -((f/2)*(-p5.get(j).getY()+p1.getY()-550/f))));
		    }
		    for(int j=0;j<p4.size();j++){
				p6.add(new Point((int) -((f/2)*(-p4.get(j).getX()+p1.getX()-500/f)),(int) -((f/2)*(-p4.get(j).getY()+p1.getY()-550/f))));
		    }
		    repaint();	
			}
	}
	public class ActionVueNormale implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Cliked==true){
				normale=true;
				p6.clear();
				p7.clear();
				System.out.println(normale);
				repaint();
				Cliked=false;
			}
		}

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
			Cliked=true;
		}
		
	}
	

	public class ActionDecalageDroite implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Cliked==true){
				
				for(int j=0;j<p7.size();j++){
					p8.add(new Point((int)(p7.get(j).getX()-2),(int)(p7.get(j).getY())));	
				}
				p7.clear();
				p7.addAll(p8);
				p8.clear();
				
				for(int j=0;j<p6.size();j++){
					p9.add(new Point((int)(p6.get(j).getX()-2),(int)(p6.get(j).getY())));
				}
				p6.clear();
				p6.addAll(p9);
				p9.clear();
				repaint();
				Cliked=false;
			}
			
		}
		
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
			Cliked=true;
			// TODO Auto-generated method stub
				
		}
		
	}
	public class ActionDecalageGauche implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
if(Cliked==true){
				
				for(int j=0;j<p7.size();j++){
					p8.add(new Point((int)(p7.get(j).getX()+2),(int)(p7.get(j).getY())));	
				}
				p7.clear();
				p7.addAll(p8);
				p8.clear();
				
				for(int j=0;j<p6.size();j++){
					p9.add(new Point((int)(p6.get(j).getX()+2),(int)(p6.get(j).getY())));
				}
				p6.clear();
				p6.addAll(p9);
				p9.clear();
				repaint();
				Cliked=false;
			}
		}

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
			Cliked=true;
		}
		
	}
	public class ActionDecalageHaut implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Cliked==true){
				
				for(int j=0;j<p7.size();j++){
					p8.add(new Point((int)(p7.get(j).getX()),(int)(p7.get(j).getY()-2)));								
				}
				p7.clear();					
				
				p7.addAll(p8);
				p8.clear();
				for(int j=0;j<p6.size();j++){
					p9.add(new Point((int)(p6.get(j).getX()),(int)(p6.get(j).getY()-2)));
				}
				p6.clear();
				p6.addAll(p9);
				p9.clear();
				repaint();
				Cliked=false;
			}
		}

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
			Cliked=true;
		}
		
	}
	public class ActionDecalageBas implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Cliked==true){
				
				for(int j=0;j<p7.size();j++){
					p8.add(new Point((int)(p7.get(j).getX()),(int)(p7.get(j).getY()+2)));	
				}
				p7.clear();
				p7.addAll(p8);
				p8.clear();
				
				for(int j=0;j<p6.size();j++){
					p9.add(new Point((int)(p6.get(j).getX()),(int)(p6.get(j).getY()+2)));
				}
				p6.clear();
				p6.addAll(p9);
				p9.clear();
				repaint();
				Cliked=false;
			}
		}

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
			Cliked=true;
		}
		
	}
	public class ActionClavier implements KeyListener, KeyEventDispatcher, KeyEventPostProcessor{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			System.out.println("work");
			if (key == KeyEvent.VK_SPACE) {
				System.out.println("sa marche");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			System.out.println("work");
			if (key == KeyEvent.VK_SPACE) {
				System.out.println("sa marche");
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("work");
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_SPACE) {
				System.out.println("sa marche");
			}
		}

		@Override
		public boolean postProcessKeyEvent(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("work");
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_SPACE) {
				System.out.println("sa marche");
			}
			return false;
		}

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("work");
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_SPACE) {
				System.out.println("sa marche");
			}
			return false;
		}
		
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
			//test2
			g2.drawImage(avion.getImageIcon().getImage(), (int)avion.getCoordonneeEcranActuelle().getX(), (int)avion.getCoordonneeEcranActuelle().getY(), this);
		*/
}

