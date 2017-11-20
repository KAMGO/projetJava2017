package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import CLASSES_BEANS.Passage;

public class PassageDAO extends dao<Passage>{

	public PassageDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Passage passage) {
	  	boolean etat = false;
	    try{
	  	   Statement st = this.connect.createStatement();
	  		  String req = "INSERT INTO PassageVoiture (id_pers2,id_voiture1,nbre_pers_resa,nbre_velo_resa) VALUES ('"+passage.getTabData()[0]+"','"+passage.getTabData()[1]+"','"+passage.getTabData()[2]+"','"+passage.getTabData()[3]+"')";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Passage connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Passage EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Passage baladeCategorie(int id_balade) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
