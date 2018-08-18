package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import CLASSES_BEANS.*;
import DAOFACTORY.AbstractDAOFactory;

public class MembreDAO  extends dao<Membre>{
	AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	public MembreDAO(Connection conn) {
		super(conn);
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
		dao<Categorie> cat = adf.getCategorieDAO();
	  	boolean etat = true;
	  	Set<Categorie> listCat = new HashSet<Categorie>();
	    try{	  		  
	  	      PreparedStatement stmtMembre = this.connect.prepareStatement("UPDATE Personne SET nom_pers = ?, prenom_pers = ?, email_pers = ?, password_pers = ?, statut_pers= ?, paye = ? "+"WHERE id_pers = "+membre.getId());
	  	      PreparedStatement stmtCategorie = this.connect.prepareStatement("SELECT Categorie.id_cat FROM PersonneCategorie JOIN Categorie ON PersonneCategorie.id_cat1 = Categorie.id_cat WHERE PersonneCategorie.id_pers3 = ?");
	  	      stmtMembre.setString(1,membre.getNom());
	  	      stmtMembre.setString(2,membre.getPrenom());
	  	      stmtMembre.setString(3,membre.getEmail());
	  	      stmtMembre.setString(4,membre.getPassword());
	  	      stmtMembre.setString(5,membre.getStatut());
	  	      stmtMembre.setInt(6,membre.getPaye());
	  	      stmtCategorie.setInt(1,membre.getId());
	  	      stmtMembre.executeUpdate();
	  	      ResultSet rs = stmtCategorie.executeQuery();
	  	      
			  while (rs.next()) {
				int  idcat = rs.getInt("id_cat");
				if(cat.find(idcat).getId() != 0)
					listCat.add(cat.find(idcat));
			 }
			  Categorie cat1 = new Categorie();
			  Set<Categorie> listTampon = new HashSet<Categorie>(membre.getListCategorie());
				for(Categorie cate : listCat){
					cat1=listTampon.stream().filter(x->cate.getId()==x.getId()).findAny().orElse(null);
					if(cat1!=null)
						listTampon.remove(cat1);
				}
			  for(Categorie categ : listTampon) {
				  Statement st = this.connect.createStatement();
				try {
			  		  String req = "INSERT INTO PersonneCategorie (id_cat1,id_pers3) VALUES ('"+categ.getId()+"','"+membre.getId()+"')";
			  		  st.executeUpdate(req);
						
					} catch (SQLException ex) {
						etat = false;
						ex.printStackTrace();
					}
			}
	  	} 
	    catch(SQLException e) {
	    	etat = false;
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
					  
				    while(result.next()) {
				          membre.addCategorie(new Categorie(result.getInt("id_cat"),result.getString("nom_cat")));
				    }
				     
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return membre;
	}

		@Override
		public Set<Membre> getList() {
			Membre membre = null; 
			Set<Membre> listMembre = new HashSet<Membre>();
			  try {
				    ResultSet result = this.connect.createStatement(
				      ResultSet.TYPE_SCROLL_INSENSITIVE, 
				      ResultSet.CONCUR_READ_ONLY
				    ).executeQuery(
				      "SELECT * FROM Personne "
				    );
				    while(result.next()){
				    	membre = new Membre( result.getInt("id_pers"),result.getString("nom_pers"),result.getString("prenom_pers"),result.getString("email_pers"),result.getString("password_pers"),result.getString("statut_pers"),result.getInt("paye"));  
				    	ResultSet  result1 = this.connect.createStatement().executeQuery("SELECT * FROM PersonneCategorie inner join Categorie on id_cat1=id_cat WHERE id_pers3 = " + result.getInt("id_pers"));
							  
						    while(result1.next())
						          membre.addCategorie(new Categorie(result1.getInt("id_cat"),result1.getString("nom_cat")));
						    listMembre.add(membre);
						     
				    }
				  } catch (SQLException e) {
				    e.printStackTrace();
				  }
			return listMembre;
		}
	}
