package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import CLASSES_BEANS.Calendrier;

public class CalendrierDAO extends dao<Calendrier>{

	public CalendrierDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Calendrier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Calendrier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Calendrier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Calendrier find(int id) {
		Calendrier calendrier1=null;
		
		Calendrier calendrier= new Calendrier();
		try {
			ResultSet result = this.connect.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_balade ,lieu_depart_balade,date_balade,id_cat2 FROM Balade inner join Categorie on Categorie.id_cat=Balade.id_cat2 inner join PersonneCategorie on id_cat=id_cat1 where PersonneCategorie.id_pers3="+id);
		   if(result.first()){
			   result.beforeFirst();
			   BaladeDAO baladeDAO = new BaladeDAO(this.connect);
		       while(result.next())
		            calendrier.addBalade(baladeDAO.find(result.getInt("id_balade")));
		       calendrier1=calendrier;
		   }
			       
		  } catch (Exception e) {
		    e.printStackTrace();
		  }     
		
		return  calendrier1;
	}

	@Override
	public Calendrier connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calendrier EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calendrier baladeCategorie(int id_balade) {
		Calendrier calendrier1=null;
		
		Calendrier calendrier= new Calendrier();
		try {
			ResultSet result = this.connect.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade where id_cat2="+id_balade);
		   if(result.first()){
			   result.beforeFirst();
			   BaladeDAO baladeDAO = new BaladeDAO(this.connect);
		       while(result.next()){
					Date date1 = new Date();
					Date date2 = new Date();
					Calendar calendar1 = Calendar.getInstance();
					Calendar calendar2 = Calendar.getInstance();
				    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
				    try {
						date1=format1.parse(result.getString("date_balade"));
						calendar1.setTime(date1);
						calendar2.setTime(date2);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    if(calendar1.after(calendar2)){
				    	calendrier.addBalade(baladeDAO.find(result.getInt("id_balade")));
				    }
		       }
		       calendrier1=calendrier;
		   }
			       
		  } catch (Exception e) {
		    e.printStackTrace();
		  }     
		
		return  calendrier1;
	}

}
