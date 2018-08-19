package VUE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import CLASSES_BEANS.Balade;
import CLASSES_BEANS.Calendrier;
import CLASSES_BEANS.Categorie;
import CLASSES_BEANS.Membre;
import CLASSES_BEANS.Voiture;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class V_membre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblQueVoulezVous = new JLabel("     que voulez vous faire avec la categorie selectionne ? ");
	private JLabel lblV = new JLabel("  Vos categories");
	private JButton btnFaireUneReservation = new JButton("Faire une reservation");
	private JButton btnPosteDisponibilite = new JButton("Poster Disponibilite");
	private JButton btnNewCategorie = new JButton("New categorie");
	JScrollPane scrollPane = new JScrollPane();
	JButton btnRetour = new JButton("retour");
	JLabel lblListeDesBalades ;
	JComboBox<Integer> comboBoxPlace = new JComboBox<Integer>();
	JComboBox<Integer> comboBoxVelo = new JComboBox<Integer>();
	JButton btnValider = new JButton("Valider");
	JLabel lblCaracteristiqueVoiture = new JLabel("Caracteristique Voiture");
	JLabel lblNewLabel_1 = new JLabel("Nombre velo");
	JLabel lblNewLabel = new JLabel("Nombre place");
	JButton btnRetour1 = new JButton("Retour");
	JComboBox<String> comboBox = new JComboBox<String>();
	private final JButton btnQuitter = new JButton("Quitter");


	/**
	 * Create the frame.
	 */
	public V_membre(Membre membre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		comboBox.setBounds(157, 11, 189, 20);
		contentPane.add(comboBox);
		for(Categorie cat : membre.getListCategorie())
			comboBox.addItem(cat.getNomCategorie());
		
		//permet de poster une disponibilite 
		btnPosteDisponibilite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Calendrier calendrier = new Calendrier();
				lblListeDesBalades = new JLabel("liste des balades de la "+comboBox.getSelectedItem().toString());
				Set<Balade> listBalade =calendrier.chargeCalendrier(comboBox.getSelectedItem().toString());
				String[] header = 	new String[] {"Id_balade", "lieu_balade", "date_balade", "forfait"};
				
				Object[][] data =new Object[calendrier.getListBalade().size()][4];
				int j=0;
				for(Balade balade :listBalade) {
					
						data[j][0]=balade.getId();
						data[j][1]=balade.getLieuBalade();
						data[j][2]=balade.getDate();
						data[j][3]=balade.getForfait();
						j++;
				}
				table = new JTable(data, header);
				table.setModel(new DefaultTableModel(data, header){
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int iRowIndex, int iColumnIndex)
					{
						return false;
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setRowSelectionAllowed(true);
				table.setBounds(10, 42, 460, 245);
				scrollPane = new JScrollPane(table);
				if(calendrier.getListBalade().size()!=0) {
					lblListeDesBalades.setBounds(184, 11, 191, 14);
					contentPane.add(lblListeDesBalades);
					contentPane.add(scrollPane);
					btnRetour.setBounds(213, 291, 85, 23);
					contentPane.add(btnRetour);
					
					scrollPane.setVisible(true);
					scrollPane.setBackground(Color.WHITE);
					scrollPane.setLocation(6, 46);
					scrollPane.setSize(432, 200);
					//scrollPane.setBounds(488, 279, -478, -235);
					
					comboBox.setVisible(false);
					btnPosteDisponibilite.setVisible(false);
					btnFaireUneReservation.setVisible(false);
					btnNewCategorie.setVisible(false);
					lblQueVoulezVous.setVisible(false);
					lblV.setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null,"aucune balade pour cette categorie ");
				
				// permet de retourner a la page precedante
				btnRetour.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new V_membre(membre);
						dispose();
						
					}
				});
				
				
				table.addMouseListener(new java.awt.event.MouseAdapter() {
				    @Override
				    public void mouseClicked(java.awt.event.MouseEvent evt) {
				    	int ligne =table.getSelectedRow();
				    	Balade balade = new Balade(Integer.parseInt(table.getValueAt(ligne, 0).toString()),table.getValueAt(ligne, 1).toString(),table.getValueAt(ligne, 2).toString(),Integer.parseInt(table.getValueAt(ligne, 3).toString()));
						if(balade.estDejaChauffeur(membre.getId())) {
							JOptionPane.showMessageDialog(null,"vous etes deja chauffeur pour cette balade");	
						}
						else
						{
							lblNewLabel.setBounds(25, 46, 103, 27);
							contentPane.add(lblNewLabel);
							
							lblNewLabel_1.setBounds(25, 107, 86, 19);
							contentPane.add(lblNewLabel_1);
							
							lblCaracteristiqueVoiture.setBounds(146, 11, 170, 14);
							contentPane.add(lblCaracteristiqueVoiture);
							
							comboBoxPlace.setBounds(179, 49, 103, 20);
							contentPane.add(comboBoxPlace);
							comboBoxPlace.addItem(1);
							comboBoxPlace.addItem(2);
							comboBoxPlace.addItem(3);
							comboBoxPlace.addItem(4);
							
							comboBoxVelo.setBounds(179, 106, 103, 20);
							contentPane.add(comboBoxVelo);
							comboBoxVelo.addItem(1);
							comboBoxVelo.addItem(2);
							comboBoxVelo.addItem(3);
							comboBoxVelo.addItem(4);
							
							btnValider.setBounds(179, 208, 89, 23);
							contentPane.add(btnValider);
							
							scrollPane.setVisible(false);
							lblListeDesBalades.setVisible(false);
							btnRetour.setVisible(false);
							btnQuitter.setVisible(false);
							
							btnRetour1.setBounds(179, 240, 89, 23);
							contentPane.add(btnRetour1);
							
							btnRetour1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									new V_membre(membre);
									dispose();
								}
							});
							
							btnValider.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
								Voiture voiture = new Voiture(Integer.parseInt(comboBoxPlace.getSelectedItem().toString()),Integer.parseInt(comboBoxVelo.getSelectedItem().toString()),membre,balade);	
								voiture.createVoiture();
								JOptionPane.showMessageDialog(null,"disponibilite enregistre ");
								new V_membre(membre);
								dispose();
								}
							});
						}
						//ligne=Integer.parseInt(table.getValueAt(ligne, 0).toString());
						//JOptionPane.showMessageDialog(null,ligne);
				    }
				});
			}
				
		});
		btnPosteDisponibilite.setBounds(157, 100, 189, 41);
		contentPane.add(btnPosteDisponibilite);
		
		
		btnFaireUneReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new V_reservation(membre,comboBox.getSelectedItem().toString());
				dispose();
			}
		});
		
		
		btnFaireUneReservation.setBounds(157, 208, 189, 41);
		contentPane.add(btnFaireUneReservation);
		
		
		btnNewCategorie.setBounds(157, 158, 189, 41);
		contentPane.add(btnNewCategorie);
		
		btnNewCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new V_newCategorie(membre);
				dispose();
			}
		});
		lblV.setBounds(10, 14, 95, 14);
		contentPane.add(lblV);
		
		
		lblQueVoulezVous.setBounds(103, 75, 310, 14);
		contentPane.add(lblQueVoulezVous);
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(284, 315, 89, 23);
		
		contentPane.add(btnQuitter);
	
	}
}
