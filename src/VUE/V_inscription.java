package VUE;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CLASSES_BEANS.Membre;
import javax.swing.SwingConstants;

public class V_inscription extends JFrame {
	private static final long serialVersionUID = 1L;
	/**************************************************** attributs ******************************************************************************/
	private JTextField nom = new JTextField();
	private JTextField prenom = new JTextField();
	private JTextField email = new JTextField();
	private JTextField password = new JTextField();
	private JRadioButton rdbtnCyclo = new JRadioButton("Cyclo",true);
	private JRadioButton rdbtnRandonneurs = new JRadioButton("VTT Randonneurs");
	private JRadioButton rdbtnTrialistes = new JRadioButton("VTT Trialistes");
	private JRadioButton rdbtnDescendeurs = new JRadioButton("VTT Descendeurs");
	private ButtonGroup buttonGroupCategorie = new ButtonGroup();
	private JButton btnSoumettre = new JButton("Soumettre");
	private JPanel contentPane=new JPanel();
	private JPanel contentPane_1;;
	
	/**************************************************** getters et setters *************************************************************************/
	public JTextField getNom() {
		return nom;
	}
	public void setNom(JTextField nom) {
		this.nom = nom;
	}
	public JTextField getPrenom() {
		return prenom;
	}
	public void setPrenom(JTextField prenom) {
		this.prenom = prenom;
	}
	public JTextField getEmail() {
		return email;
	}
	public void setEmail(JTextField email) {
		this.email = email;
	}
	public JTextField getPassword() {
		return password;
	}
	public void setPassword(JTextField password) {
		this.password = password;
	}

	public V_inscription(V_connexion conn) {
		setResizable(false);
		conn.setEnabled(false);
		setTitle("Inscription");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 348);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		this.panelInscription(conn);
	}
	/************************************************* Create the panel._constructeur ***************************************************************** */
	public void panelInscription(V_connexion conn) {
		
		getContentPane().setLayout(null);
		contentPane_1.setLayout(null);
		JLabel lblNewLabel = new JLabel("donnees personnelles");
		lblNewLabel.setBounds(166, 11, 127, 14);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(34, 39, 46, 14);
		contentPane_1.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(34, 65, 46, 14);
		contentPane_1.add(lblPrenom);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(34, 90, 46, 14);
		contentPane_1.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(34, 117, 66, 14);
		contentPane_1.add(lblPassword);
		
		
		nom.setBounds(197, 36, 134, 20);
		contentPane_1.add(nom);
		nom.setColumns(10);
		
		
		prenom.setBounds(197, 62, 134, 20);
		contentPane_1.add(prenom);
		prenom.setColumns(10);
		
		
		email.setBounds(197, 87, 134, 20);
		contentPane_1.add(email);
		email.setColumns(10);
		
		
		password.setBounds(197, 114, 134, 20);
		contentPane_1.add(password);
		password.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(" dans quelle categorie voulez-vous vous inscrire  ?");
		lblNewLabel_1.setBounds(85, 161, 286, 14);
		contentPane_1.add(lblNewLabel_1);
		rdbtnCyclo.setBounds(24, 200, 59, 31);
		buttonGroupCategorie.add(rdbtnCyclo);
		contentPane_1.add(rdbtnCyclo);
	
		rdbtnRandonneurs.setBounds(85, 204, 143, 23);
		buttonGroupCategorie.add(rdbtnRandonneurs);
		contentPane_1.add(rdbtnRandonneurs);
		
		rdbtnTrialistes.setBounds(210, 204, 109, 23);
		buttonGroupCategorie.add(rdbtnTrialistes);
		contentPane_1.add(rdbtnTrialistes);
		
		rdbtnDescendeurs.setBounds(321, 204, 127, 23);
		buttonGroupCategorie.add(rdbtnDescendeurs);
		contentPane_1.add(rdbtnDescendeurs);
		btnSoumettre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Membre membre = new Membre();
					if(!nom.getText().isEmpty()&& !prenom.getText().isEmpty()&& !email.getText().isEmpty()&& !password.getText().isEmpty()) {
					int etat =membre.inscription(nom.getText(), prenom.getText(), email.getText(), password.getText(),getRadioCategorie());
					if(etat==0) {
						conn.setEnabled(true);
						JOptionPane.showMessageDialog(null,"inscription reusie !! ");
							membre=membre.connexion(email.getText(), password.getText());
		    				membre.setPaye(membre.getPaye()+20);
		    				membre.updateMembre();
		    				V_inscription.this.dispose();
					}
					else if(etat==1)
						JOptionPane.showMessageDialog(null," probleme avec l access a la BD!! ");
					else
						JOptionPane.showMessageDialog(null,"Email et mot de passe donnee existe deja !! Veuillez les changer ");
				}
				else
					JOptionPane.showMessageDialog(null, "Champs manquants");
			}
		});
		btnSoumettre.setBounds(194, 264, 99, 23);
		
		getContentPane().add(btnSoumettre);
		//setVisible(true);
	}
	/********************************************************* return Categorie *******************************************************************/
	public String getRadioCategorie() {
		if (rdbtnCyclo.isSelected())
			return "cat_cyclo";
		else if (rdbtnRandonneurs.isSelected())
			return "cat_vtt_rand";
		else if(rdbtnTrialistes.isSelected())
			return "cat_vtt_trial";
		else 
			return "cat_vtt_desc";
	}
}
