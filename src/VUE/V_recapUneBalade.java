package VUE;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CLASSES_BEANS.Balade;
import CLASSES_BEANS.Membre;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V_recapUneBalade extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nbreChaufSuperflus;
	private JTextField nbreChauffeurs;
	private JTextField nbreParticipants;
	JButton btnVoirChauffeur = new JButton("afficher");
	JButton btnRetour = new JButton("Retour");
	JButton btnVoirParticipants = new JButton("Afficher");
	JButton btnVoirChaufSu = new JButton("afficher");
	JLabel lblParticipants = new JLabel("Participants");
	JLabel lblChauffeur = new JLabel("Chauffeurs");
	JLabel lblRecapBalade = new JLabel("statistique Balade ");
	JLabel lblChaufSuperflus = new JLabel(" Chauffeurs Superflus");
	
	/**
	 * Create the frame.
	 */
	public V_recapUneBalade(Balade balade, Membre membre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		lblChaufSuperflus.setBounds(20, 102, 151, 14);
		contentPane.add(lblChaufSuperflus);
		
		nbreChaufSuperflus = new JTextField();
		nbreChaufSuperflus.setText(balade.chauffeurSuperflus().size()+"");
		nbreChaufSuperflus.setBounds(227, 99, 86, 20);
		contentPane.add(nbreChaufSuperflus);
		nbreChaufSuperflus.setColumns(10);
		
		lblRecapBalade = new JLabel("statistique Balade du : "+ balade.getDate()+"  à "+ balade.getLieuBalade());
		lblRecapBalade.setBounds(83, 11, 312, 14);
		contentPane.add(lblRecapBalade);
		
		lblChauffeur.setBounds(20, 58, 96, 14);
		contentPane.add(lblChauffeur);
		
		nbreChauffeurs = new JTextField();
		nbreChauffeurs.setText(balade.getListVoiture().size()+"");	//liste des chauffeurs
		nbreChauffeurs.setBounds(227, 55, 86, 20);
		contentPane.add(nbreChauffeurs);
		nbreChauffeurs.setColumns(10);
		
		
		lblParticipants.setBounds(20, 173, 125, 14);
		contentPane.add(lblParticipants);
		
		nbreParticipants = new JTextField();
		nbreParticipants.setText((balade.listParticipant().size()+balade.getListVoiture().size())+""); // liste des participant a la balade 
		nbreParticipants.setBounds(227, 167, 86, 20);
		contentPane.add(nbreParticipants);
		nbreParticipants.setColumns(10);
		if(balade.listParticipant().size()+balade.getListVoiture().size()==0)
			btnVoirParticipants.setEnabled(false);
		else {
			btnVoirParticipants.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new  V_affichParticpantBal( balade) ;
					dispose();
				}
			});	
		}
		btnVoirParticipants.setBounds(363, 169, 89, 23);
		contentPane.add(btnVoirParticipants);
		
		
		
		if(balade.chauffeurSuperflus().size()==0)
			btnVoirChaufSu.setEnabled(false);
		else {
			btnVoirChaufSu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new V_affichChaufSurperflus(balade);
					dispose();
				}
			});	
		}
		btnVoirChaufSu.setBounds(363, 98, 89, 23);
		contentPane.add(btnVoirChaufSu);
		

		btnRetour.setBounds(199, 267, 89, 23);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_recapDispoBalade(membre);
				dispose();
			}
		});
		
		
		if(balade.getListVoiture().size()==0)
			btnVoirChauffeur.setEnabled(false);
		else {
			btnVoirChauffeur.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new V_ListChauffeurBal(balade);
					dispose();
				}
			});	
		}

		btnVoirChauffeur.setBounds(363, 54, 89, 23);
		contentPane.add(btnVoirChauffeur);
	}
}
