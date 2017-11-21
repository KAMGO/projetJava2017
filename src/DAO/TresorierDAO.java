package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import CLASSES_BEANS.Tresorier;

public class TresorierDAO extends dao<Tresorier> {

	public TresorierDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Tresorier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Tresorier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Tresorier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tresorier find(int id) {
		Tresorier tresorier1=null;
		Tresorier tresorier = new Tresorier();          
	    try {
	      ResultSet result = this.connect.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne ");
	      if(result.first()){
	    	  MembreDAO membreDAO = new MembreDAO(this.connect);
	    	  tresorier = new Tresorier(id);
	    	  tresorier.addMembre(membreDAO.find(result.getInt("id_pers")));
		      while(result.next())
		    	  tresorier.addMembre(membreDAO.find(result.getInt("id_pers")));
		      tresorier1=tresorier;
	      }
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return tresorier1;
	}

	@Override
	public Tresorier connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tresorier EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tresorier baladeCategorie(int id_balade) {
		// TODO Auto-generated method stub
		return null;
	}

}
