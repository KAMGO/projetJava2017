package VUE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CLASSES_BEANS.Balade;
import CLASSES_BEANS.Passage;
import CLASSES_BEANS.Voiture;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V_affichParticpantBal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnRetour = new JButton("Retour");
	JLabel lblNewLabel = new JLabel("Liste des participants ");
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	
	/**
	 * Create the frame.
	 */
	public V_affichParticpantBal(Balade balade) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		scrollPane.setBounds(0, 40, 492, 228);
		//contentPane.add(scrollPane);

		
		
		btnRetour.setBounds(198, 287, 89, 23);
		contentPane.add(btnRetour);
		
		
		lblNewLabel.setBounds(179, 11, 161, 14);
		contentPane.add(lblNewLabel);
		
		
		String[] header = 	new String[] {"Nom", "Prenom", "fonction"};
		
		Object[][] data =new Object[balade.getListVoiture().size()+balade.listParticipant().size()][3];
		int j=0;
		for(Passage participant :balade.listParticipant()) {
			
				data[j][0]=participant.getNom();
				data[j][1]=participant.getPrenom();
				data[j][2]="passage";
				j++;
		}
		for(Voiture voiture :balade.getListVoiture()) {
			
			data[j][0]=voiture.getMembre().getNom() ;
			data[j][1]=voiture.getMembre().getPrenom() ;
			data[j][2]="chauffeur" ;
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
		table.setVisible(true);
		//scrollPane.setVisible(true);
		scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setLocation(6, 46);
		scrollPane.setSize(432, 200);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
