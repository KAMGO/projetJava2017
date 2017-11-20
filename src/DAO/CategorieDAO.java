package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import CLASSES_BEANS.*;

public class CategorieDAO extends dao<Categorie> {

	public CategorieDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Categorie cat) {
	  	boolean etat = false;
	    try{
	  	   Statement st = this.connect.createStatement();
	  		  String req = "INSERT INTO Categorie (nom_cat) VALUES ('"+cat.getNomCategorie()+"')";
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
	public boolean delete(Categorie obj) {
		return false;
	}

	@Override
	public boolean update(Categorie obj) {
		return false;
	}

	@Override
	public Categorie find(int id) {
		return null;
	}

	@Override
	public Categorie connecter(String string, String string2) {
		return null;
	}

	@Override
	public Categorie EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie baladeCategorie(int id_balade) {
		// TODO Auto-generated method stub
		return null;
	}

}
