package VUE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CLASSES_BEANS.Balade;
import CLASSES_BEANS.Voiture;

public class V_listChauffTre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnRetour = new JButton("Retour");
	JLabel lblNewLabel = new JLabel("Liste des chauffeurs ");
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;

	/**
	 * Create the frame.
	 */
	public V_listChauffTre(Balade balade) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		scrollPane.setBounds(0, 40, 492, 228);
		
		btnRetour.setBounds(198, 287, 89, 23);
		contentPane.add(btnRetour);
		
		
		lblNewLabel.setBounds(179, 11, 161, 14);
		contentPane.add(lblNewLabel);
		
		
		String[] header = 	new String[] {"id","Nom", "Prenom", "MontantAresevoir"};
		
		Object[][] data =new Object[balade.getListVoiture().size()][4];
		int j=0;
		for(Voiture voiture :balade.getListVoiture()) {
			data[j][0]=voiture.getMembre().getId();
			data[j][1]=voiture.getMembre().getNom() ;
			data[j][2]=voiture.getMembre().getPrenom() ;
			data[j][3]=voiture.montantAResevoir(balade.getForfait()) ;
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
		scrollPane.setSize(476, 200);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int ligne =table.getSelectedRow();
		    	Voiture voiture = new Voiture();
		    	//Balade balade1 = new Balade(Integer.parseInt(table.getValueAt(ligne, 0).toString()),table.getValueAt(ligne, 1).toString(),table.getValueAt(ligne, 2).toString(),Integer.parseInt(table.getValueAt(ligne, 3).toString()));
				voiture=balade.getListVoiture().stream().filter(x->Integer.parseInt(table.getValueAt(ligne, 0).toString())==x.getMembre().getId()).findAny().orElse(null);
				voiture.getMembre().setPaye(voiture.getMembre().getPaye() + Integer.parseInt(table.getValueAt(ligne, 3).toString()));
				if(voiture.getMembre().updateMembre()) {
					JOptionPane.showMessageDialog(null," la somme de : "+Integer.parseInt(table.getValueAt(ligne, 3).toString())+ "  ont été ajoutées au compte de  "+ voiture.getMembre().getNom());
				}
				else
					JOptionPane.showMessageDialog(null," probleme avec la base de donnee ");
		    }
		});
	}

}
