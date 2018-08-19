package VUE;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CLASSES_BEANS.Balade;
import CLASSES_BEANS.Membre;
import CLASSES_BEANS.Passage;
import CLASSES_BEANS.Voiture;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ListVoitureBalade extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblListVoiture ;
	JScrollPane scrollPane = new JScrollPane();
	JButton btnRetour = new JButton("retour");
	private JTable table= new JTable();;
	Balade balade = new Balade();

	/**
	 * Create the frame.
	 */
	public ListVoitureBalade(Membre membre,Set<Balade> listBalade ,int idBaladeSelect) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		scrollPane.setBounds(10, 40, 471, 263);
		
		
		
		btnRetour.setBounds(217, 314, 89, 23);
		
		

		
		
		/********************************* afficher la liste des voitures ****************************************/
		//recupere la balade correspondant a  id selectionne
		
		balade = listBalade.stream()
				.filter(x->idBaladeSelect==(x.getId()))
				.findAny()
				.orElse(null);

		lblListVoiture = new JLabel("Liste des voitures de la balade  "+ balade.getLieuBalade());
		lblListVoiture.setBounds(78, 11, 375, 22);
		
		
		String[] header = 	new String[] {"Id_Voiture", "PlaceDispo", "PlaceVeloDispo","chauffeur"};
		Object[][] data = new Object[balade.getListVoiture().size()][4];
		//verifie s'il existe deja des voiture pour cette balade 
		if(balade.getListVoiture().size()==0) {
			JOptionPane.showMessageDialog(null,"desole pas encore de voiture disponible pour cette balade");
			//new V_membre(membre);
			//dispose();
		}
		else {
			int j=0;
			// charge les voitures de la balade dans une matrice 
			for(Voiture voiture : balade.getListVoiture()) {
					data[j][0]=voiture.getId();
					data[j][1]=voiture.getRestPlacePers();
					data[j][2]=voiture.getRestPlaceVelo();
					data[j][3]=voiture.getMembre().getNom();
					JOptionPane.showMessageDialog(null,voiture.getMembre().getNom());
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
			setVisible(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowSelectionAllowed(true);
			table.setBounds(10, 46, 495, 252);
			scrollPane = new JScrollPane(table);
			contentPane.add(btnRetour);
			contentPane.add(scrollPane);
			contentPane.add(lblListVoiture);
			scrollPane.setVisible(true);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setLocation(6, 46);
			scrollPane.setSize(432, 200);

		}
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_membre(membre);
				dispose();
			}
		});
		//ce qui doit etre excecute lorsqu'on click sur un enregistrement dans le tableau
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int ligne =table.getSelectedRow();
		    	Voiture voiture = new Voiture();
		    	// permet de recuperer la voiture selectionne
				voiture = balade.getListVoiture().stream()
						.filter(x->Integer.parseInt(table.getValueAt(ligne, 0).toString())==(x.getId()))
						.findAny()
						.orElse(null);
				// si le membre na encore rien fait comme reservation
				if(balade.aDejaReserve(membre.getId())==3) {
			    	int replyPlace = JOptionPane.showConfirmDialog(null," voulez vous reserver  une place assise ?");
			    	int replyVelo = JOptionPane.showConfirmDialog(null," voulez vous reserver  aussi une place velo ?");
			    	if(replyPlace==JOptionPane.YES_OPTION&&replyVelo==JOptionPane.YES_OPTION) {//enregistre une place assise et une place Velo
			    		if(Integer.parseInt(table.getValueAt(ligne, 1).toString())>0&&Integer.parseInt(table.getValueAt(ligne, 2).toString())>0){
			    			voiture.setBalade(balade);
			    			voiture.addPassage(new Passage(membre.getId(),membre.getNom(),membre.getPrenom(),membre.getEmail(),membre.getPassword(),membre.getStatut(),membre.getPaye(),1,1));
			    			if(voiture.savePassage()) {
			    				//JOptionPane.showMessageDialog(null,"reservation reusie");
			    				membre.setPaye(membre.getPaye()+(int)balade.getForfait());
			    				membre.updateMembre();
			    				if(membre.getPaye()>0)
			    					JOptionPane.showMessageDialog(null,"reservation reusie ! vous devez  : "+membre.getPaye()+"  veuillez a le solde via le tresorier merci !");
			    				else
			    					JOptionPane.showMessageDialog(null,"reservation reusie ! on vous doit  : "+(-1)*membre.getPaye()+"  veuillez contacter le tresorier merci !");
			    			}
			    			else
			    				JOptionPane.showMessageDialog(null,"probleme avec la BD");
			    		}
			    		else if(Integer.parseInt(table.getValueAt(ligne, 1).toString())>0)
			    			JOptionPane.showMessageDialog(null,"desole ya plus de place Velo dans cette voiture.Veuillez faire un autre choix de voiture ");
			    		else if(Integer.parseInt(table.getValueAt(ligne, 2).toString())>0)
			    			JOptionPane.showMessageDialog(null,"desole ya plus de place assise dans cette voiture.Veuillez faire un autre choix de voiture ");
			    		else
			    			JOptionPane.showMessageDialog(null,"desole cette voiture est deja pleine .Veuillez faire un autre choix de voiture ");
			    	}
			    	else if(replyPlace==JOptionPane.YES_OPTION) {//enregistre uniquement une place assise
			    		if(Integer.parseInt(table.getValueAt(ligne, 1).toString())>0) {
			    			voiture.setBalade(balade);
			    			voiture.addPassage(new Passage(membre.getId(),membre.getNom(),membre.getPrenom(),membre.getEmail(),membre.getPassword(),membre.getStatut(),membre.getPaye(),1,0));
			    			if(voiture.savePassage()) {
			    				//JOptionPane.showMessageDialog(null,"reservation reusie");
			    				membre.setPaye(membre.getPaye()+(int)balade.getForfait());
			    				membre.updateMembre();
			    				if(membre.getPaye()>0)
			    					JOptionPane.showMessageDialog(null,"reservation reusie ! vous devez  : "+membre.getPaye()+"  veuillez a le solde via le tresorier merci !");
			    				else
			    					JOptionPane.showMessageDialog(null,"reservation reusie ! on vous doit  : "+(-1)*membre.getPaye()+"  veuillez contacter le tresorier merci !");
			    			}
			    			else
			    				JOptionPane.showMessageDialog(null,"probleme avec la BD");
			    		}
			    		else
			    			JOptionPane.showMessageDialog(null,"desole ya plus de place assise dans cette voiture.Veuillez faire un autre choix de voiture ");	
			    	}
			    	else if(replyVelo==JOptionPane.YES_OPTION) {//enregistre uniquement le velo
			    		if(Integer.parseInt(table.getValueAt(ligne, 2).toString())>0) {
			    			voiture.setBalade(balade);
			    			voiture.addPassage(new Passage(membre.getId(),membre.getNom(),membre.getPrenom(),membre.getEmail(),membre.getPassword(),membre.getStatut(),membre.getPaye(),0,1));
			    			if(voiture.savePassage()) {
			    				membre.setPaye(membre.getPaye()+(int)balade.getForfait());
			    				membre.updateMembre();
			    				if(membre.getPaye()>0)
			    					JOptionPane.showMessageDialog(null," reservation reussie !   vous devez deja a  "+membre.getPaye()+"  veuillez a solde votre compte");
			    				else
			    					JOptionPane.showMessageDialog(null," reservation reussie !  on vous doit :  "+(-1)*membre.getPaye()+"  veuillez a prendre attache evec le tresorier pour etre solde");
			    			}
			    			else
			    				JOptionPane.showMessageDialog(null,"probleme avec la BD");
			    		}
			    		else
			    			JOptionPane.showMessageDialog(null,"desole ya plus de place velo dans cette voiture.Veuillez faire un autre choix de voiture ");	
			    	}
				}
				else if(balade.aDejaReserve(membre.getId())==2) 
					JOptionPane.showMessageDialog(null,"une place assise a deja ete reservé plus de reservation  posible a votre nom");
				else if(balade.aDejaReserve(membre.getId())==1) {
					JOptionPane.showMessageDialog(null,"une place velo a deja ete reservé plus de reservation  posible a votre nom");	
				}
				else
					JOptionPane.showMessageDialog(null,"vous avez deja fait une reservation plus possible de faire une autre a votre nom");
		    	
		    	//Balade balade = new Balade(Integer.parseInt(table.getValueAt(ligne, 0).toString()),table.getValueAt(ligne, 1).toString(),table.getValueAt(ligne, 2).toString());
				//permet de verifier si le membre n est pas deja chauffeur pour cette categorie
		    	//Balade balade = new Balade();
		
			}
		});
	}

}
