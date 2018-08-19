package VUE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CLASSES_BEANS.Membre;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class V_connexion extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JPanel panelInscription = new JPanel();
	JButton btnInscription = new JButton(" creer un compte membre ");
	JRadioButton rdbtnMembre = new JRadioButton("Membre",true);
	JRadioButton rdbtnResponsable = new JRadioButton("Responsable");
	JRadioButton rdbtnTresorier = new JRadioButton("Tresorier");
	//private ButtonGroup buttonGroupUtilisateur = new ButtonGroup();
	private JPanel contentPane;
	private JTextField email= new JTextField();;
	private JPasswordField password1=new JPasswordField();;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V_connexion frame = new V_connexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public V_connexion() {
		setTitle("projet java ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		email.setBounds(157, 11, 169, 20);
		panel.add(email);
		email.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(74, 63, 83, 14);
		panel.add(lblMotDePasse);
		
		
		password1.setBounds(157, 60, 169, 20);
		panel.add(password1);
		
		
		JButton btnConnexion = new JButton("connexion");
		btnConnexion.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(!email.getText().isEmpty()&&!password1.getText().isEmpty()){
					Membre membre = new Membre();
					membre=membre.connexion(email.getText(), password1.getText());
					if(membre!=null) {
						if(membre.getStatut().compareTo("tresorier")==0) {
							String conn;
							String tabConn[] = {"Membre","Tresorier"};
							conn = (String)JOptionPane.showInputDialog(null, "Comment compte vous vous connecter ? ", 
					                "Connexion", JOptionPane.QUESTION_MESSAGE, null, tabConn, tabConn[0]);
							if(conn!=null)
								if(conn.compareTo("Membre")==0)
									new V_membre(membre);
								else if(conn.compareTo("Tresorier")==0)
									new V_tresorier(membre);
						}
						else if(membre.getStatut().compareTo("membre")==0)
							new V_membre(membre);
						else {
							String conn;
							String tabConn[] = {"Membre","Responsable"};
							conn = (String)JOptionPane.showInputDialog(null, "Comment compte vous vous connecter ? ", 
					                "Connexion", JOptionPane.QUESTION_MESSAGE, null, tabConn, tabConn[0]);
							if(conn!=null)
								if(conn.compareTo("Membre")==0)
									new V_membre(membre);
								else if(conn.compareTo("Responsable")==0)
									new V_responsable(membre);
						//dispose();
						}
					}
					else
						JOptionPane.showMessageDialog(null,"vous n ete pas membre");
				}
				else
					JOptionPane.showMessageDialog(null, "Champs manquants");
			}
		});
		btnConnexion.setBounds(174, 142, 96, 23);
		panel.add(btnConnexion);
		
		
		rdbtnMembre.setBounds(34, 94, 86, 23);
		//panel.add(rdbtnMembre);
		
		
		rdbtnResponsable.setBounds(160, 94, 99, 23);
		//panel.add(rdbtnResponsable);
		
		
		rdbtnTresorier.setBounds(292, 94, 109, 23);
		//panel.add(rdbtnTresorier);
		
		//buttonGroupUtilisateur.add(rdbtnMembre);
		//buttonGroupUtilisateur.add(rdbtnResponsable);
		//buttonGroupUtilisateur.add(rdbtnTresorier);
		
		btnInscription.setForeground(Color.BLUE);
		btnInscription.setBorderPainted(false);
		btnInscription.setContentAreaFilled(false);
		btnInscription.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Map<TextAttribute, Integer> attributes = new HashMap<TextAttribute, Integer>();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		btnInscription.setFont(btnInscription.getFont().deriveFont(attributes));
		// action listerner inscription 
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				V_inscription inscription = new V_inscription(V_connexion.this);
				inscription.setVisible(true);
			}
		});
		btnInscription.setBounds(132, 214, 206, 23);
		panel.add(btnInscription);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(74, 14, 31, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		
	
	}
	public String getRadioUtilisateur() {
		if (rdbtnMembre.isSelected())
			return rdbtnMembre.getText();
		else if (rdbtnResponsable.isSelected())
			return rdbtnResponsable.getText();
		else
			return rdbtnTresorier.getText();
	}
}
