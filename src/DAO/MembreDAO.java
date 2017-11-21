package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import CLASSES_BEANS.*;

public class MembreDAO  extends dao<Membre>{

	public MembreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Membre membre) {
	  	boolean etat = false;
	    try{
	  	   Statement st = this.connect.createStatement();
	  		  String req = "INSERT INTO Personne (nom_pers,prenom_pers,email_pers,password_pers,statut_pers) VALUES ('"+membre.getNom()+"','"+membre.getPrenom()+"','"+membre.getEmail()+"','"+membre.getPassword()+"','"+membre.getStatut()+"')";
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
	public boolean delete(Membre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Membre membre) {
	  	boolean etat = false;
	    try{	  		  
	  	      Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	  	      PreparedStatement prepare = this.connect.prepareStatement("UPDATE Personne set paye = ? "+"WHERE id_pers = "+membre.getId());
	  		  prepare.setInt(1,membre.getPaye());
	  		  prepare.executeUpdate();
	  		      etat = true;
	  		  } 
	    catch(SQLException e) {
	  	   e.getStackTrace();
	  	}
	  return etat;
	}

	public Membre find(int id) {
		Membre membre = null;            
		  try {
		    ResultSet result = this.connect.createStatement(
		      ResultSet.TYPE_SCROLL_INSENSITIVE, 
		      ResultSet.CONCUR_READ_ONLY
		    ).executeQuery(
		      "SELECT * FROM Personne "+
		      " where id_pers = '"+id+"'"
		    );
		    if(result.first()){
		    	membre= new Membre(); 
		    	membre = new Membre( result.getInt("id_pers"),result.getString("nom_pers"),result.getString("prenom_pers"),result.getString("email_pers"),result.getString("password_pers"),result.getString("statut_pers"),result.getInt("paye"));  
			    result = this.connect.createStatement().executeQuery("SELECT * FROM PersonneCategorie inner join Categorie on id_cat1=id_cat WHERE id_pers3 = " + result.getInt("id_pers"));
					  
				    while(result.next())
				          membre.addCategorie(new Categorie(result.getInt("id_cat"),result.getString("nom_cat")));
				     
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return membre;
	}
		  public Membre connecter(String email,String password){
				Membre membre = null;            
				  try {
				    ResultSet result = this.connect.createStatement(
				      ResultSet.TYPE_SCROLL_INSENSITIVE, 
				      ResultSet.CONCUR_READ_ONLY
				    ).executeQuery(
				      "SELECT * FROM Personne "+
				      " where email_pers = '"+email+"' and password_pers = '"+password+"'"
				    );
				    if(result.first()){
				    	membre= new Membre(); 
				    	membre = new Membre( result.getInt("id_pers"),result.getString("nom_pers"),result.getString("prenom_pers"),result.getString("email_pers"),result.getString("password_pers"),result.getString("statut_pers"));  
					    result = this.connect.createStatement().executeQuery("SELECT * FROM PersonneCategorie inner join Categorie on id_cat1=id_cat WHERE id_pers3 = " + result.getInt("id_pers"));
							  
						    while(result.next())
						          membre.addCategorie(new Categorie(result.getInt("id_cat"),result.getString("nom_cat")));
						     
				    }
				  } catch (SQLException e) {
				    e.printStackTrace();
				  }
				  return membre;	
			}

		@Override
		public Membre EstDejaChauffeur(int id_pers1,int id_balade1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Membre baladeCategorie(int id_balade) {
			// TODO Auto-generated method stub
			return null;
		}
	}
