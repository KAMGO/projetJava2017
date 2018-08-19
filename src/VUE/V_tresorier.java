package VUE;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CLASSES_BEANS.Membre;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V_tresorier extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public V_tresorier(Membre membre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		JButton btnpayeChauffeur = new JButton("Remboursement Chauffeur");
		btnpayeChauffeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_listBaladeTre(membre);
				dispose();
			}
		});
		btnpayeChauffeur.setBounds(167, 64, 191, 23);
		contentPane.add(btnpayeChauffeur);
		
		JButton btnPayePassage = new JButton("payement passage");
		btnPayePassage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new V_listbaladePassage(membre);
			}
		});
		btnPayePassage.setBounds(167, 137, 191, 23);
		contentPane.add(btnPayePassage);
		
		JLabel lblFonctionTresorier = new JLabel("fonction Tresorier");
		lblFonctionTresorier.setBounds(206, 11, 174, 14);
		contentPane.add(lblFonctionTresorier);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(219, 257, 89, 23);
		contentPane.add(btnQuitter);
	}
}
