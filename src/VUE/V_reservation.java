package VUE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CLASSES_BEANS.Balade;
import CLASSES_BEANS.Calendrier;
import CLASSES_BEANS.Membre;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class V_reservation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblListeBalade ;
	JLabel lblListeVoiture ;
	JButton btnRetour = new JButton("Retour");
	JButton btnRetour1 = new JButton("Retour");
	JScrollPane scrollPane = new JScrollPane();
	JScrollPane scrollPaneV = new JScrollPane();
	private JTable table;
	Calendrier calendrier = new Calendrier();
	Set<Balade> listBalade ;
	/**
	 * Create the frame.
	 */
	public V_reservation(Membre membre,String nomCategorie) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		scrollPane.setBounds(10, 46, 495, 252);
		btnRetour.setBounds(223, 309, 89, 23);
		lblListeBalade = new JLabel("Liste des balades de la categorie "+ nomCategorie);
		lblListeBalade.setBounds(105, 21, 338, 14);
		
		
		
		 listBalade =calendrier.chargeCalendrier(nomCategorie);
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
		table.setBounds(10, 46, 495, 252);
		scrollPane = new JScrollPane(table);
		
		if(calendrier.getListBalade().size()!=0) {
			setVisible(true);
			contentPane.add(scrollPane);
			contentPane.add(lblListeBalade);
			contentPane.add(btnRetour);
			
			scrollPane.setVisible(true);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setLocation(6, 46);
			scrollPane.setSize(432, 200);
		}
		else {
			JOptionPane.showMessageDialog(null,"aucune balade pour cette categorie ");
			new V_membre(membre);
			}
		
		
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
				//permet de verifier si le membre n est pas deja chauffeur pour cette categorie
		    	if(!balade.estDejaChauffeur(membre.getId())) {	
					new ListVoitureBalade(membre,listBalade,Integer.parseInt(table.getValueAt(ligne, 0).toString()));
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"vous ete deja chauffeur pour cette balade");
				}
		
			}
		});
	}
}
