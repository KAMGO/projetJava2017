package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CLASSES_BEANS.*;

public class VoitureDAO extends dao<Voiture>{

	public VoitureDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Voiture voiture) {
	  	boolean etat = false;
	    try{
	  	  System.out.println("bonjour :"+voiture.getNbres_pers_max());
	  	   Statement st = this.connect.createStatement();
	  		  String req = "INSERT INTO Voiture (nbres_pers_max,nbres_velo_max,id_pers1,id_balade1) VALUES ('"+voiture.getNbres_pers_max()+"','"+voiture.getNbres_velo_max()+"','"+voiture.getId_pers1()+"','"+voiture.getId_balade()+"')";
	  		  st.executeUpdate(req);
	  		      etat = true; 
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
	public boolean update(Voiture obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Voiture find(int id) {
		Voiture voiture1=null;
	    Voiture voiture = new Voiture();          
	    try {
	      ResultSet result = this.connect.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Voiture WHERE id_voiture = " + id);
	      if(result.first()){
	    	  voiture = new Voiture(result.getInt("id_voiture"),result.getInt("nbres_pers_max"),result.getInt("nbres_velo_max"),result.getInt("id_pers1"),result.getInt("id_balade1"));
		       voiture1=voiture;
		       result = this.connect.createStatement().executeQuery("SELECT * FROM Personne inner join PassageVoiture on id_pers=id_pers2 WHERE id_voiture1 = " + result.getInt("id_voiture"));
				  
			      while(result.next())
			            voiture.addPassage(new Passage(result.getString("nom_pers"),result.getString("prenom_pers"),result.getInt("nbre_pers_resa"),result.getInt("nbre_velo_resa")));
			       voiture1=voiture;
	      }
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return voiture1;
	} 
	public Voiture connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
 public Voiture EstDejaChauffeur(int id_pers1,int id_balade1){
		Voiture voiture1=null;
	    Voiture voiture = new Voiture();          
	    try {
	      ResultSet result = this.connect.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Voiture WHERE id_pers1 = " +id_pers1 +" and id_balade1 = " + id_balade1);
	      if(result.first()){
	    	  voiture = new Voiture(result.getInt("id_voiture"),result.getInt("nbres_pers_max"),result.getInt("nbres_velo_max"),result.getInt("id_pers1"),result.getInt("id_balade1"));
		       voiture1=voiture;
		       result = this.connect.createStatement().executeQuery("SELECT * FROM Personne inner join PassageVoiture on id_pers=id_pers2 WHERE id_voiture1 = " + result.getInt("id_voiture"));
				  
			      while(result.next())
			            voiture.addPassage(new Passage(result.getString("nom_pers"),result.getString("prenom_pers"),result.getInt("nbre_pers_resa"),result.getInt("nbre_velo_resa")));
			       voiture1=voiture;
	      }
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return voiture1;	 
 }

@Override
public Voiture baladeCategorie(int id_balade) {
	// TODO Auto-generated method stub
	return null;
}

}
