package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import CLASSES_BEANS.*;
import DAOFACTORY.AbstractDAOFactory;

public class VoitureDAO extends dao<Voiture>{
	AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	public VoitureDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Voiture voiture) {
	  	boolean etat = false;
	    try{
	  	  System.out.println("bonjour :"+voiture.getNbres_pers_max()+" ==== "+voiture.getNbres_velo_max()+"','"+voiture.getMembre().getId()+"'membre'"+voiture.getMembre().getId()+"'balade'"+voiture.getBalade().getId());
	  	  	  Statement st = this.connect.createStatement();
	  		 String req = "INSERT INTO Voiture (nbres_pers_max,nbres_velo_max,id_pers1,id_balade1) VALUES ('"+voiture.getNbres_pers_max()+"','"+voiture.getNbres_velo_max()+"','"+voiture.getMembre().getId()+"','"+voiture.getBalade().getId()+"')";
	  		  st.executeUpdate(req);
	  		      etat = true; 
	  		    JOptionPane.showMessageDialog(null,"disponibilite enregistre ");
	  		    System.out.println("aurevoir");
	  		  } 
	    catch(SQLException e) {
	  	   e.getStackTrace();
	  	}
	    
	  return etat;
	}

	@Override
	public boolean delete(Voiture obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Voiture voiture) {
		dao<Passage> passageDao = adf.getPassageDAO();
	  	boolean etat = false;
	  	Set<Passage> listPassage = new HashSet<Passage>();
	    try{ 		  
	  	      PreparedStatement stmtVoiture = this.connect.prepareStatement("UPDATE Voiture SET nbres_pers_max = ?, nbres_velo_max = ?, id_pers1 = ?, id_balade1 = ? "+"WHERE id_voiture = "+ voiture.getId());
	  	      PreparedStatement stmtPassage = this.connect.prepareStatement("SELECT Personne.id_pers FROM passageVoiture JOIN Personne ON passageVoiture.id_pers2 = Personne.id_pers WHERE passageVoiture.id_voiture1 = ?");
	  	    JOptionPane.showMessageDialog(null,"milieu   update de voitureDao  ");
	  	      stmtVoiture.setInt(1,voiture.getNbres_pers_max());
	  	      stmtVoiture.setInt(2,voiture.getNbres_velo_max());
	  	      stmtVoiture.setInt(3,voiture.getMembre().getId());
	  	      stmtVoiture.setInt(4,voiture.getBalade().getId());
	  	      stmtPassage.setInt(1,voiture.getId());
	  	      stmtVoiture.executeUpdate();
	  	      ResultSet rs = stmtPassage.executeQuery();
	  	      
			  while (rs.next()) {
				int  idPassage = rs.getInt("id_pers");
				if(passageDao.find(idPassage).getId() != 0)
					listPassage.add(passageDao.find(idPassage));
			 }
			  Set<Passage> listTampon = new HashSet<Passage>(voiture.getListPassage());
			 // listTampon.removeAll(listPassage);
			  Passage passage1 = new Passage();
				for(Passage passage: listPassage){
					passage1=listTampon.stream().filter(x->passage.getId()==x.getId()).findAny().orElse(null);
					if(passage1!=null)
						listTampon.remove(passage1);
				}
			  for(Passage itPass : listTampon){
				  etat = true;
				  Statement st = this.connect.createStatement();
				try {
					String req = "INSERT INTO passageVoiture (id_pers2,id_voiture1,nbre_pers_resa,nbre_velo_resa) VALUES ('"+itPass.getId()+"','"+voiture.getId()+"','"+itPass.getNbrPlaceresa()+"','"+itPass.getNbrveloresa()+"')";
					st.executeUpdate(req);
					JOptionPane.showMessageDialog(null,"je suis  a la fin de update de voitureDao  "+voiture.getId()+"  "+itPass.getId()+"  "+itPass.getNbrPlaceresa());	
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
			 }
	  	} 
	    catch(SQLException e) {
	  	   e.getStackTrace();
	  	}
	  return etat;
	}

	@Override
	public Voiture find(int id) {
		Voiture voiture1=null;
	    Voiture voiture = new Voiture();  
	    dao<Membre> membreDao = adf.getMembreDAO();
	    try {
	      ResultSet result = this.connect.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Voiture WHERE id_voiture = " + id);
	      if(result.first()){
	    	  voiture = new Voiture(result.getInt("id_voiture"),result.getInt("nbres_pers_max"),result.getInt("nbres_velo_max"),membreDao.find(result.getInt("id_pers1")));
		      // voiture1=voiture;
		       result = this.connect.createStatement().executeQuery("SELECT * FROM Personne inner join PassageVoiture on id_pers=id_pers2 WHERE id_voiture1 = " + id);
			   while(result.next()) {
			       voiture.addPassage(new Passage(result.getInt("id_pers"),result.getString("nom_pers"),result.getString("prenom_pers"),result.getString("email_pers"),result.getString("password_pers"),result.getString("statut_pers"),result.getInt("paye"),result.getInt("nbre_pers_resa"),result.getInt("nbre_velo_resa")));
			   }
			   
			   voiture1=voiture;
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return voiture1;
	}

	@Override
	public Set<Voiture> getList() {
		Voiture voiture = null; 
	    dao<Membre> membreDao = adf.getMembreDAO();
	    dao<Balade> baladeDao = adf.getBaladeDAO();
		Set<Voiture> listVoiture = new HashSet<Voiture>();
		  try {
			    ResultSet result = this.connect.createStatement(
			      ResultSet.TYPE_SCROLL_INSENSITIVE, 
			      ResultSet.CONCUR_READ_ONLY
			    ).executeQuery(
			      "SELECT * FROM Voiture "
			    );
			    while(result.next()){
			    	voiture = new Voiture(result.getInt("id_voiture"),result.getInt("nbres_pers_max"),result.getInt("nbres_velo_max"),membreDao.find(result.getInt("id_pers1")),baladeDao.find(result.getInt("id_balade1")));
			    	ResultSet result1 = this.connect.createStatement().executeQuery("SELECT * FROM Personne inner join PassageVoiture on id_pers=id_pers2 WHERE id_voiture1 = " + result.getInt("id_voiture"));
					   while(result1.next())
					       voiture.addPassage(new Passage(result1.getInt("id_pers"),result1.getString("nom_pers"),result1.getString("prenom_pers"),result1.getString("email_pers"),result1.getString("password_pers"),result1.getString("statut_pers"),result1.getInt("paye"),result1.getInt("nbre_pers_resa"),result1.getInt("nbre_velo_resa")));
					    listVoiture.add(voiture);
					     
			    }
			  } catch (SQLException e) {
			    e.printStackTrace();
			  }
		return listVoiture;
	} 
}
