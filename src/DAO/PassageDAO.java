package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import CLASSES_BEANS.Passage;

public class PassageDAO extends dao<Passage>{

	public PassageDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Passage passage) {
	  	boolean etat = false;
	  return etat;
		
	}

	@Override
	public boolean delete(Passage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Passage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Passage find(int id) {
		Passage passage = new Passage();            
		  try {
		    ResultSet result = this.connect.createStatement(
		      ResultSet.TYPE_SCROLL_INSENSITIVE, 
		      ResultSet.CONCUR_READ_ONLY
		    ).executeQuery(
		      "SELECT * FROM Personne inner join passageVoiture on id_pers2=id_pers"+
		      " where id_pers2 = '"+id+"'"
		    );
		    if(result.first()){
		    	passage= new Passage(); 
		    	passage = new Passage(result.getInt("id_pers"),result.getString("nom_pers"),result.getString("prenom_pers"),result.getString("email_pers"),result.getString("password_pers"),result.getString("statut_pers"),result.getInt("paye"),result.getInt("nbre_pers_resa"),result.getInt("nbre_velo_resa"));	     
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return passage;
	}

	@Override
	public Set<Passage> getList() {
		// TODO Auto-generated method stub
		return null;
	}
}
