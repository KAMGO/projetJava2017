package VUE;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import CLASSES_BEANS.Balade;
import CLASSES_BEANS.Membre;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class V_ajoutBalade extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel contentPane;


	private JLabel lblLieuBalade = new JLabel("lieu de la balade");

	private JTextField lieuBalade = new JTextField();
	private JButton btnRetour = new JButton("Retour");
	private JButton btnValider = new JButton("Valider");
	String  formatted=null;
	private  JLabel lblDateBalade = new JLabel("Date balade");
	private JTextField dateBalade = new JTextField();
	Integer nbreKm=0;
	Float prixUnitaire=(float) 0.0f;
	JLabel lblForfait = new JLabel("forfait");
	private JTextField forfait= new JTextField();

	/**
	 * Create the frame.
	 */
	public V_ajoutBalade(Membre membre) {
		dateBalade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateBalade.setEditable(false);
		dateBalade.setBounds(216, 96, 154, 20);
		dateBalade.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Calendar calendar = Calendar.getInstance();
		setBounds(100, 100, 508, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Integer jour =0;
		Integer mois=0;
		Integer annee =0;
		JLabel lblAjoutBaladeDans = new JLabel("Ajout balade dans categorie :  "+ membre.getStatut());
		lblAjoutBaladeDans.setBounds(149, 11, 242, 14);
		contentPane.add(lblAjoutBaladeDans);
		
		

		calendar.setTime(new Date());
		Integer tabAnnee[] = {calendar.get(Calendar.YEAR),calendar.get(Calendar.YEAR)+1,calendar.get(Calendar.YEAR)+2,calendar.get(Calendar.YEAR)+3,calendar.get(Calendar.YEAR)+4};
		
		Integer tabMois[] = {1,2,3,4,5,6,7,8,9,10,12};
		annee = (Integer)JOptionPane.showInputDialog(null, "choisir l'annee :", 
                "annee", JOptionPane.QUESTION_MESSAGE, null, tabAnnee, tabAnnee[0]);
		if(annee==null){
			JOptionPane.showMessageDialog(null,"vous avez annulé ");
			new test(membre);
			dispose();
		}
		else {
			mois = (Integer)JOptionPane.showInputDialog(null, "choisir le mois :", 
	                "mois", JOptionPane.QUESTION_MESSAGE, null, tabMois, tabMois[0]);
			if(mois==null){
				JOptionPane.showMessageDialog(null,"vous avez annulé ");
				new test(membre);
				dispose();
			}
			else {
				if(mois==1 || mois==3||mois==5 || mois==7 ||mois==8||mois==10 ||mois==12 ) {
					Integer tabJour[] = new Integer[31];
					for(int i=1;i<=31;i++)
						tabJour[i-1]=i;
					 jour = (Integer)JOptionPane.showInputDialog(null, "choisir le jour :", 
			                "jour", JOptionPane.QUESTION_MESSAGE, null, tabJour, tabJour[0]);
				}
				else if(mois==4 || mois==6 || mois==9 || mois==11) {
					Integer tabJour[] = new Integer[30];
					for(int i=0;i<30;i++)
						tabJour[i]=i+1;
					 jour = (Integer)JOptionPane.showInputDialog(null, "choisir le jour :", 
			                "jour", JOptionPane.QUESTION_MESSAGE, null, tabJour, tabJour[0]);	
				}
				else {
					DateUser dateUser = new DateUser();
					if(dateUser.bissextile(annee)) {
						Integer tabJour[] = new Integer[29];
						for(int i=1;i<=29;i++)
							tabJour[i-1]=i;
						 jour = (Integer)JOptionPane.showInputDialog(null, "choisir le jour :", 
				                "jour", JOptionPane.QUESTION_MESSAGE, null, tabJour, tabJour[0]);
					}
					else {
						Integer tabJour[] = new Integer[28];
						for(int i=1;i<=28;i++)
							tabJour[i-1]=i;
						 jour = (Integer)JOptionPane.showInputDialog(null, "choisir le jour :", 
				                "jour", JOptionPane.QUESTION_MESSAGE, null, tabJour, tabJour[0]);
					}
				}
				if(jour==null){
					JOptionPane.showMessageDialog(null,"vous avez annulé ");
					new test(membre);
					dispose();
				}
				else {
					
					Float tabPrixUnitaire[] = new Float[30];
					Float j=(float) 0.0f;
					for(int i=0;i<=29;i++) {
						j=(float) (j+0.2f);
						tabPrixUnitaire[i]=j;
						}
					prixUnitaire = (Float)JOptionPane.showInputDialog(null, "choisir le prix d'un Km :", 
			                "PrixUnitaire Km", JOptionPane.QUESTION_MESSAGE, null, tabPrixUnitaire, tabPrixUnitaire[0]);
					
					if(prixUnitaire==null) {
						JOptionPane.showMessageDialog(null,"vous avez annulé ");
						new test(membre);
						dispose();	
					}
					else {
						
						Integer tabKm[] = new Integer[150];
						for(int i=5;i<=145;i++)
							tabKm[i]=i;
						 nbreKm = (Integer)JOptionPane.showInputDialog(null, "choisir le nombre de Km :", 
				                " nombre de Km", JOptionPane.QUESTION_MESSAGE, null, tabKm, tabKm[0]);
						if(nbreKm==null) {
							JOptionPane.showMessageDialog(null,"vous avez annulé ");
							new test(membre);
							dispose();		
						}
						else {
							DateUser dateUser = new DateUser(jour,mois,annee);
							if(dateUser.dateCorrecte()) {
								DateUser dateUser1 = new DateUser();
								//Calendar calendar0 = Calendar.getInstance();
								Calendar calendar1 = Calendar.getInstance();
								Calendar calendar2 = Calendar.getInstance();
								calendar1.set(dateUser.getAnnee(),dateUser.getMois()-1,dateUser.getJour());
							    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
								dateUser1 =new DateUser(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR));
								dateUser1=dateUser1.ajoutJours(30);
								calendar2.set(dateUser1.getAnnee(),dateUser1.getMois()-1,dateUser1.getJour());
								if(calendar2.after(calendar1)) {
									JOptionPane.showMessageDialog(null,"la date d'une balade doit etre la date d'aujourdhui plus au moins 30jours ");
									dispose();
									new V_ajoutBalade(membre);
								}
								else {
									 formatted = format1.format(calendar1.getTime());
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"date incorrecte ");
								dispose();
								new V_ajoutBalade(membre);
							}
									lblLieuBalade.setBounds(37, 200, 126, 14);
									contentPane.add(lblLieuBalade);
									
									dateBalade.setText(formatted);
									setVisible(true);
									lieuBalade.setBounds(216, 197, 154, 20);
									contentPane.add(lieuBalade);
									lieuBalade.setColumns(10);
									
									lblForfait.setBounds(40, 153, 46, 14);
									contentPane.add(lblForfait);
									forfait.setFont(new Font("Tahoma", Font.PLAIN, 12));
									forfait.setEditable(false);
									
									
									forfait.setBounds(216, 150, 154, 20);
									contentPane.add(forfait);
									forfait.setColumns(10);
							
							btnRetour.setHorizontalAlignment(SwingConstants.LEFT);
							btnRetour.setBounds(37, 249, 89, 23);
							contentPane.add(btnRetour);
							btnRetour.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									new test(membre);
									dispose();
								}
							});
							
							btnValider.setBounds(312, 249, 89, 23);
							contentPane.add(btnValider);
							lblDateBalade.setBounds(37, 99, 96, 14);
							
							contentPane.add(lblDateBalade);
							
							contentPane.add(dateBalade);
							Balade balade1 =new Balade();
							balade1.setForfait(Balade.calculForfait(prixUnitaire, nbreKm));
							forfait.setText(balade1.getForfait()+"");
							btnValider.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									if(!lieuBalade.getText().isEmpty()) {
										Balade balade =new Balade(lieuBalade.getText(),formatted);
										balade.setForfait(Balade.calculForfait(prixUnitaire, nbreKm));
										forfait.setText(balade.getForfait()+"");
										if(balade.createBalade(membre.getStatut())) //appel de la methode permettant de creer une new balade
											JOptionPane.showMessageDialog(null,"balade creee avec succes ");
										else
											JOptionPane.showMessageDialog(null,"probleme avec la BD");
										new V_responsable(membre);
										dispose();
									}
									else
										JOptionPane.showMessageDialog(null,"le champs lieu Balade est ne doit pas être vide ");
								}
							});
						}
					}
				}
			}
		}
	}
	/**************************************************** getters et setters *************************************************************************/
	public JTextField getLieuBalade() {
		return lieuBalade;
	}

	public void setLieuBalade(JTextField lieuBalade) {
		this.lieuBalade = lieuBalade;
	}
}
