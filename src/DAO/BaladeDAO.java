package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import javax.swing.JOptionPane;

import CLASSES_BEANS.*;
import DAOFACTORY.AbstractDAOFactory;

public class BaladeDAO extends dao<Balade>{
	AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	public BaladeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Balade balade) {
	  	boolean etat = false;
	    try{
	  	  System.out.println("bonjour :"+balade.getLieuBalade());
	  	   Statement st = this.connect.createStatement();
	  		  String req = "INSERT INTO Balade (lieu_depart_balade,date_balade,id_cat2,forfaitBalade) VALUES ('"+balade.getLieuBalade()+"','"+balade.getDate()+"','"+balade.getCalendrier().getCategorie().getId()+"','"+balade.getForfait()+"')";
	  		  st.executeUpdate(req);
	  		      etat = true; 
	  		    System.out.println("aurevoir");
	  		  } 
	    catch(SQLException e) {
	  	   e.getStackTrace();
	  	}
	    JOptionPane.showMessageDialog(null,"sortie"); 
	  return etat;
	}

	@Override
	public boolean delete(Balade obj) {

		return false;
	}

	@Override
	public boolean update(Balade obj) {
		return false;
	}

	@Override
	public Balade find(int id) {
		//dao<Categorie> cat = adf.getCategorieDAO();
		Balade balade1=null;
	    Balade balade = new Balade();  
	    VoitureDAO voitureDAO = new VoitureDAO(this.connect);
	    try {
	      ResultSet result = this.connect.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade WHERE id_balade = " + id);
	      if(result.first()){
	    	  balade = new Balade(result.getInt("id_balade"),result.getString("lieu_depart_balade"),result.getString("date_balade"),result.getInt("forfaitBalade"));
	    	  // balade = new Balade(result.getInt("id_balade"),result.getString("lieu_depart_balade"),result.getString("date_balade"),new Calendrier(cat.find(result.getInt("id_cat2"))));
	    	  result = this.connect.createStatement().executeQuery("SELECT * FROM Voiture WHERE id_balade1 = " + id);
			  //int j=0;
		      while(result.next()){
		            balade.addVoiture(voitureDAO.find(result.getInt("id_voiture")));
		      }
		       balade1=balade;
	      }
	      	
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return balade1;
	}

	@Override
	public Set<Balade> getList() {
		// TODO Auto-generated method stub
		return null;
	}
}
