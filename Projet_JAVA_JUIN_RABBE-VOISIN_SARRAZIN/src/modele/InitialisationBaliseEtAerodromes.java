/**
 * 
 */
package modele;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;

import commun.Utilitaire;
import exceptions.ExceptionErreurCoordonneesGPS;
import tests.pouet;

/**
 * @author Thomas
 */
public class InitialisationBaliseEtAerodromes extends JFrame {
	
	/**
	 * Fenetre Initialisation
	 */
	
	private static final long serialVersionUID = 4115488596423491532L;

	private JFrame selection_du_fichier;

	private JPanel aero;
	private JPanel bali;
	private JPanel bouton;
	private JLabel aerodrome;
	private JLabel balise;
	private JTextField jTextaero;
	private JTextField jTextbali;
	private JFileChooser parcourir;



	private final JButton boutonannuler = new JButton("Annuler");
	private final JButton boutonvalider = new JButton("Valider");
	private final JButton boutonChoixaero = new JButton(">");
	private final JButton boutonChoixbali = new JButton(">");


	public InitialisationBaliseEtAerodromes() {
		//Programme de la fenetre
		super("Initialisation");
		Container globale = getContentPane();

		aero = new JPanel(); //Panel contenant les informations relatives au fichier Aerodrome
		bali = new JPanel(); //Panel contenant les informations relatives au fichier Aerodrome
		bouton = new JPanel(); //Panel contenant les boutons ANNULER et VALIDER
		aerodrome = new JLabel("Aerodromes : ");
		balise = new JLabel("Balises    : ");
		jTextaero = new JTextField(" 			");
		jTextbali = new JTextField(" 			");

		//Definition du Panel aerodrome
		aero.add(aerodrome);
		aero.add(jTextaero);
		aero.add(boutonChoixaero);

		//Definition du Panel balise
		bali.add(balise);
		bali.add(jTextbali);
		bali.add(boutonChoixbali);

		//Definition du Panel bouton
		bouton.add(boutonannuler);
		bouton.add(boutonvalider);

		globale.setLayout(new BorderLayout());
		globale.add(aero,BorderLayout.NORTH);
		globale.add(bali,BorderLayout.CENTER);
		globale.add(bouton,BorderLayout.SOUTH);

		boutonannuler.addActionListener(new ActionValiderInitialisationBaliseEtAerodromes());
		boutonannuler.setActionCommand("ANNULER");
		boutonvalider.addActionListener(new ActionValiderInitialisationBaliseEtAerodromes());
		boutonvalider.setActionCommand("VALIDER");
		boutonChoixaero.addActionListener(new ActionValiderInitialisationBaliseEtAerodromes());
		boutonChoixaero.setActionCommand("PARCOURIR_AERO");
		boutonChoixbali.addActionListener(new ActionValiderInitialisationBaliseEtAerodromes());
		boutonChoixbali.setActionCommand("PARCOURIR_BALI");
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * Definition de la fenetre du JFileChooser
		 */
		selection_du_fichier = new JFrame();
		selection_du_fichier.getContentPane();
		parcourir = new JFileChooser();
		selection_du_fichier.add(parcourir);
	}


	public class ActionValiderInitialisationBaliseEtAerodromes implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			AbstractButton bouton = (AbstractButton)e.getSource();


			if(bouton.getActionCommand().equals("VALIDER")){
				String fic_aerodrome=jTextaero.getText();
				String fic_balise=jTextbali.getText();
				String tampon=null;
				BufferedReader fe;
				try {
					/*
					 * Traitement du fichier Balise.txt
					 */
					fe = new BufferedReader(new FileReader(fic_balise));
					while ( (tampon=fe.readLine()) != null)
					{
							StringTokenizer t=new StringTokenizer(tampon," ");
							//Lecture des infos
							
							String nom=t.nextToken();
							String prem_coord=t.nextToken();
							String prem_direction=t.nextToken();
							String deux_coord=t.nextToken();
							String deux_direction=t.nextToken();
							try {
								pouet.listeBalises.add(new Balise(nom, Utilitaire.transformeCoordonneesGPSenCoordonneesRefrentiel(prem_coord,prem_direction,deux_coord,deux_direction)) );
							} catch (ExceptionErreurCoordonneesGPS e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}							
					}
					fe.close();   
					System.out.println("Balise traitee"); 
					
					/*
					 * Traitement du fichier Aerodrome.txt
					 */
					fe = new BufferedReader(new FileReader(fic_aerodrome));
					fe.readLine();	//Suppresion du texte en en-tete
					fe.readLine();	//Suppresion du texte en en-tete
					String [] nom_aero_split;
					while ( (tampon=fe.readLine()) != null)
					{
						StringTokenizer t=new StringTokenizer(tampon,",");
						//Lecture des infos
						String latitude=t.nextToken();
						String longitude=t.nextToken();
						longitude.substring(1); //Retrait de l'espace
						double lat = Double.valueOf(latitude);
						double longi= Double.valueOf(longitude);
						String nomcomplet=t.nextToken();
						nomcomplet = nomcomplet.substring(2,nomcomplet.length()-1);
						nom_aero_split=nomcomplet.split(" ");
						String codeOACI=nom_aero_split[nom_aero_split.length-1];
						codeOACI = codeOACI.substring(1, 5);

						if(nomcomplet.charAt(1) == 'C'){ // Cas particulier de 'Chambley planet'air
							nomcomplet="'Chambley planet'air";
							codeOACI="LFJY";
						}
						else if(nomcomplet.charAt(0) == '6'){ // Cas particulier de 67 BATZENDORF
							nomcomplet="67 BATZENDORF";
							codeOACI="";
						}
						else nomcomplet=nomcomplet.substring(0,nomcomplet.length()- 7 ); //Nom de l'aerodrome sans son code OACI
							
						//Verification de l'intégrité des informations sur la console
						System.out.println("Code OACI : " + codeOACI + " - Nom : " + nomcomplet);
						System.out.println("Latitude : " + latitude + " Longitude : " + longitude);
						
						try {
							pouet.listeAerodromes.add(new Aerodrome(nomcomplet, codeOACI,Utilitaire.transformeCoordonneesGPSenCoordonneesRefrentiel(lat, longi)));
						} catch (ExceptionErreurCoordonneesGPS e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					fe.close();
					System.out.println("Aerodrome traitee");   
				}
				catch (FileNotFoundException ex) {
					//Logger.getLogger(MonAppli.class.getName()).log(Level.SEVERE, null, ex);
				}
				catch (IOException ex) {
					//Logger.getLogger(MonAppli.class.getName()).log(Level.SEVERE, null, ex);
				}
				System.exit(0);
			}
			
			/*
			 * Ouverture du JFileChooser pour le fichier Aerodrome.txt
			 */
			if(bouton.getActionCommand().equals("PARCOURIR_AERO")){
				String nomdufichierselectionne;
				selection_du_fichier.pack();
				selection_du_fichier.setVisible(true);
				int returnVal = parcourir.showOpenDialog(selection_du_fichier);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					nomdufichierselectionne=parcourir.getSelectedFile().getAbsolutePath();
					jTextaero.setText(nomdufichierselectionne);
					selection_du_fichier.setVisible(false);
				}
			}
			/*
			 * Ouverture du JFileChooser pour le fichier Balise.txt
			 */
			if(bouton.getActionCommand().equals("PARCOURIR_BALI")){
				String nomdufichierselectionne;
				selection_du_fichier.pack();
				selection_du_fichier.setVisible(true);
				int returnVal = parcourir.showOpenDialog(selection_du_fichier);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					nomdufichierselectionne=parcourir.getSelectedFile().getAbsolutePath();
					jTextbali.setText(nomdufichierselectionne);
					selection_du_fichier.setVisible(false);
				}
			}
			if(bouton.getActionCommand().equals("ANNULER")){
				System.exit(0);
			}
		}
	}
}
