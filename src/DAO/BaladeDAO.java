package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CLASSES_BEANS.*;

public class BaladeDAO extends dao<Balade>{

	public BaladeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Balade balade) {
	  	boolean etat = false;
	    try{
	  	  System.out.println("bonjour :"+balade.getLieuBalade());
	  	   Statement st = this.connect.createStatement();
	  		  String req = "INSERT INTO Balade (lieu_depart_balade,date_balade,id_cat2) VALUES ('"+balade.getLieuBalade()+"','"+balade.getDate()+"','"+balade.getIdCat()+"')";
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
	public boolean delete(Balade obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Balade obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Balade find(int id) {
		Balade balade1=null;
	    Balade balade = new Balade();          
	    try {
	      ResultSet result = this.connect.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade WHERE id_balade = " + id);
	      if(result.first()){
	    	  balade = new Balade(result.getInt("id_balade"),result.getString("lieu_depart_balade"),result.getString("date_balade"),result.getInt("id_cat2"));
	    	  result = this.connect.createStatement().executeQuery("SELECT * FROM Voiture WHERE id_balade1 = " + id);
			  VoitureDAO voitureDAO = new VoitureDAO(this.connect);
		      while(result.next())
		            balade.addVoiture(voitureDAO.find(result.getInt("id_voiture")));
		       balade1=balade;
	      }
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return balade1;
	}

	@Override
	public Balade connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Balade EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Balade baladeCategorie(int id_balade) {
		// TODO Auto-generated method stub
		return null;
	}

}
