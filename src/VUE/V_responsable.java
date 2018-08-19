package VUE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CLASSES_BEANS.Membre;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V_responsable extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public V_responsable(Membre membre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 357);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("que voulez faire ?");
		lblNewLabel.setBounds(166, 11, 327, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnAjouterUneBalde = new JButton("Ajouter une balde");
		btnAjouterUneBalde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new test(membre);
				dispose();
			}
		});
		btnAjouterUneBalde.setBounds(177, 66, 156, 23);
		contentPane.add(btnAjouterUneBalde);
		
		JButton btnNewButton = new JButton("Recapitulatif des disponibilites");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new V_recapDispoBalade(membre);
				dispose();
			}
		});
		btnNewButton.setBounds(95, 137, 309, 23);
		contentPane.add(btnNewButton);
		
		//JButton btnNewButton_1 = new JButton("Calculer forfait ");
		//btnNewButton_1.setBounds(177, 199, 156, 23);
		//contentPane.add(btnNewButton_1);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(207, 262, 89, 23);
		contentPane.add(btnQuitter);
	}

}
