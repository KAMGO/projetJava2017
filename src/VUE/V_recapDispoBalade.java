package VUE;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

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
import CLASSES_BEANS.Calendrier;
import CLASSES_BEANS.Membre;
import CLASSES_BEANS.Voiture;

public class V_recapDispoBalade extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JLabel lblListeDesBalades ;
	JScrollPane scrollPane = new JScrollPane();
	JButton btnRetour = new JButton("retour");

	/**
	 * Create the frame.
	 */
	public V_recapDispoBalade(Membre membre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 510, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnRetour.setBounds(211, 284, 89, 23);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_responsable(membre);
				dispose();
			}
		});
		
		lblListeDesBalades = new JLabel("liste des balades de la "+membre.getStatut());
		lblListeDesBalades.setBounds(124, 11, 239, 14);
		contentPane.add(lblListeDesBalades);
		
		Calendrier calendrier = new Calendrier();
		Set<Balade> listBalade =calendrier.chargeCalendrier(membre.getStatut());
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
			scrollPane.setBounds(10, 34, 478, 246);
			contentPane.add(scrollPane);

			
			scrollPane.setVisible(true);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setLocation(6, 46);
			scrollPane.setSize(432, 200);
		}
		else
			JOptionPane.showMessageDialog(null,"aucune balade pour cette categorie ");
		
		
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int ligne =table.getSelectedRow();
		    	Balade balade1 = new Balade(Integer.parseInt(table.getValueAt(ligne, 0).toString()),table.getValueAt(ligne, 1).toString(),table.getValueAt(ligne, 2).toString(),Integer.parseInt(table.getValueAt(ligne, 3).toString()));
				balade1=listBalade.stream().filter(x->Integer.parseInt(table.getValueAt(ligne, 0).toString())==x.getId()).findAny().orElse(null);
				new V_recapUneBalade(balade1,membre);
				dispose();
		    }
		});
	}

}
