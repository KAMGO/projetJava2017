package VUE;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V_disponibilite extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public V_disponibilite() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre place");
		lblNewLabel.setBounds(25, 46, 103, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre velo");
		lblNewLabel_1.setBounds(25, 107, 86, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCaracteristiqueVoiture = new JLabel("Caracteristique Voiture");
		lblCaracteristiqueVoiture.setBounds(146, 11, 170, 14);
		contentPane.add(lblCaracteristiqueVoiture);
		
		JComboBox<Integer> comboBoxPlace = new JComboBox<Integer>();
		comboBoxPlace.setBounds(229, 49, 103, 20);
		contentPane.add(comboBoxPlace);
		comboBoxPlace.addItem(1);
		comboBoxPlace.addItem(2);
		comboBoxPlace.addItem(3);
		comboBoxPlace.addItem(4);
		
		JComboBox<Integer> comboBoxVelo = new JComboBox<Integer>();
		comboBoxVelo.setBounds(229, 106, 103, 20);
		contentPane.add(comboBoxVelo);
		comboBoxVelo.addItem(1);
		comboBoxVelo.addItem(2);
		comboBoxVelo.addItem(3);
		comboBoxVelo.addItem(4);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setBounds(296, 217, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRetour.setBounds(61, 217, 89, 23);
		contentPane.add(btnRetour);
	}
}
