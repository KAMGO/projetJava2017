package VUE;

import java.awt.Color;
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
import CLASSES_BEANS.Membre;
import CLASSES_BEANS.Passage;
import CLASSES_BEANS.Voiture;

public class V_listPassageBalade extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnRetour = new JButton("Retour");
	JLabel lblNewLabel = new JLabel("Liste des participants ");
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;

	/**
	 * Create the frame.
	 */
	public V_listPassageBalade(Balade balade,Membre membre) {
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
		
		String[] header = 	new String[] {"id","Nom", "Prenom"};
		Object[][] data =new Object[balade.listParticipant().size()][3];
		int j=0;
		for(Passage passage :balade.listParticipant()) {
				data[j][0]=passage.getId();
				data[j][1]=passage.getNom() ;
				data[j][2]=passage.getPrenom() ;
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
				new V_listbaladePassage(membre);
				dispose();
			}
		});
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int ligne =table.getSelectedRow();
		    	Integer montant;
		    	Membre membre = new Membre();
		    	Passage passage1 = new Passage();
				for(Voiture voiture :balade.getListVoiture()) {
					for(Passage passage : voiture.getListPassage()) {
						if(passage.getId()==Integer.parseInt(table.getValueAt(ligne, 0).toString()))
							passage1=passage;
					}
				}
				membre=membre.connexion(passage1.getEmail(),passage1.getPassword());
				Integer tabJour[] = new Integer[100];
				for(int i=0;i<100;i++)
					tabJour[i]=i+1;
				 montant = (Integer)JOptionPane.showInputDialog(null, " Montant :", 
		                "Montant payer par le user ", JOptionPane.QUESTION_MESSAGE, null, tabJour, tabJour[0]);
				membre.setPaye(membre.getPaye()-montant);
				if(montant!=null) {
					if(membre.updateMembre())
						JOptionPane.showMessageDialog(null," la somme de : "+montant+ "  été  debitée du compte de : "+ membre.getNom());
				}
				else
					JOptionPane.showMessageDialog(null," processus annulé");
		    }
		});
	}

}
