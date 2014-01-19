package vue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Carte extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Carte(){
		
		this.setBackground(Color.WHITE);
		
	}
	
	public void paintComponent(Graphics g){
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
