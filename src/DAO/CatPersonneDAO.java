package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CLASSES_BEANS.Balade;
import CLASSES_BEANS.CatPersonne;

public class CatPersonneDAO extends dao<CatPersonne>{

	public CatPersonneDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public boolean create(CatPersonne catP) {
	  	boolean etat = false;
	    try{
	  	   Statement st = this.connect.createStatement();
	  		  String req = "INSERT INTO PersonneCategorie (id_cat1,id_pers3) VALUES ('"+catP.getId_cat1()+"','"+catP.getId_pers3()+"')";
	  		  st.executeUpdate(req);
	  		      etat = true; 
	  		  } 
	    catch(SQLException e) {
	  	   e.getStackTrace();
	  	}
	    System.out.println("aurevoir");
	  return etat;
	}

	@Override
	public boolean delete(CatPersonne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CatPersonne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CatPersonne find(int id) {
		CatPersonne catPersonne1=null;
		CatPersonne catPersonne = new CatPersonne();          
	    try {
	      ResultSet result = this.connect.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM PersonneCategorie  WHERE id_cat1 = " + id);
	      if(result.first()){
	    	  MembreDAO membreDAO = new MembreDAO(this.connect);
	    	  catPersonne = new CatPersonne(result.getInt("id_cat1"),result.getInt("id_pers3"));
	    	  catPersonne.addMembre(membreDAO.find(result.getInt("id_pers3")));
		      while(result.next())
		    	  catPersonne.addMembre(membreDAO.find(result.getInt("id_pers3")));
		      catPersonne1=catPersonne;
	      }
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return catPersonne1;
	}	

	@Override
	public CatPersonne connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatPersonne EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatPersonne baladeCategorie(int id_balade) {
		// TODO Auto-generated method stub
		return null;
	}

}
