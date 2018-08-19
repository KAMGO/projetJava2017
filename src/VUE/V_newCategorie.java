package VUE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CLASSES_BEANS.Categorie;
import CLASSES_BEANS.Membre;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V_newCategorie extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JComboBox<String> comboBoxAsous = new JComboBox<>();
	JComboBox<String> comboBox = new JComboBox<String>();
	JButton btnValider = new JButton("Valider new categorie");
	JButton btnRetour = new JButton("Retour");
	JLabel lblListeDeCategories = new JLabel("Liste de Categories \u00E0 souscrir");
	JLabel lblListeDeVos = new JLabel("Liste de vos Categories");


	/**
	 * Create the frame.
	 */
	public V_newCategorie(Membre membre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		setBounds(100, 100, 508, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblListeDeVos.setBounds(37, 54, 144, 14);
		contentPane.add(lblListeDeVos);
		
		
		comboBox.setBounds(246, 51, 136, 20);
		for (Categorie cat : membre.getListCategorie())
			comboBox.addItem(cat.getNomCategorie());
		contentPane.add(comboBox);
		
		btnRetour.setBounds(61, 232, 89, 23);
		contentPane.add(btnRetour);
		
		comboBoxAsous.setBounds(246, 121, 136, 20);
		if(membre.afficheCatAsouscrir().size()!=0) {
			for(Categorie cat : membre.afficheCatAsouscrir())
				comboBoxAsous.addItem(cat.getNomCategorie());
		}
		else {
			JOptionPane.showMessageDialog(null,"vous avez deja souscrit à toute les categories !! ");
			lblListeDeCategories.setVisible(false);
			comboBoxAsous.setVisible(false);
			btnValider.setVisible(false);
		}
		
		
		lblListeDeCategories.setBounds(20, 124, 189, 14);
		contentPane.add(lblListeDeCategories);
		
		contentPane.add(comboBoxAsous);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(membre.newCategorie(comboBoxAsous.getSelectedItem().toString())) {
					//JOptionPane.showMessageDialog(null,"enregistre avec succes ");	
					int paye=membre.getPaye();
					membre.setPaye(paye+5);
					membre.updateMembre();
					if(membre.getPaye()>0)
    					JOptionPane.showMessageDialog(null," reservation reussie !   vous devez  "+membre.getPaye()+"  veuillez a solde votre compte");
    				else
    					JOptionPane.showMessageDialog(null," reservation reussie !  on vous doit :  "+(-1)*membre.getPaye()+"  veuillez a prendre attache evec le tresorier pour etre solde");
				}
				else
					JOptionPane.showMessageDialog(null,"probleme de base de donnee");
				dispose();
				new V_membre(membre);
			}
		});
		
		btnValider.setBounds(262, 232, 175, 23);
		contentPane.add(btnValider);
		
		
		btnRetour.setBounds(61, 232, 89, 23);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_membre(membre);
				dispose();
			}
		});
	}
}
