package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

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
		Categorie categorie = null;            
		 try {
		   ResultSet result = this.connect.createStatement(
		     ResultSet.TYPE_SCROLL_INSENSITIVE, 
		     ResultSet.CONCUR_READ_ONLY
		    ).executeQuery(
		      "SELECT * FROM Categorie "+
		      " where id_cat= '"+id+"'"
		    );
		    if(result.first()){
		    	categorie = new Categorie(); 
		    	categorie = new Categorie( result.getInt("id_cat"),result.getString("nom_cat"));
		  }
		 }catch (SQLException e) {
			    e.printStackTrace();
			  }
		return categorie;
	}

	@Override
	public Set<Categorie> getList() {
		Categorie cat=null;
		Set<Categorie> listCategorie = new HashSet<Categorie>();
		  try {
			    ResultSet result = this.connect.createStatement(
			      ResultSet.TYPE_SCROLL_INSENSITIVE, 
			      ResultSet.CONCUR_READ_ONLY
			    ).executeQuery(
			      "SELECT * FROM Categorie "
			    );
			    while(result.next()){
			        cat = new Categorie( result.getInt("id_cat"),result.getString("nom_cat"));  
			    	listCategorie.add(cat);					     
			    }
			  } catch (SQLException e) {
			    e.printStackTrace();
			  }
		return listCategorie;
	}
}
