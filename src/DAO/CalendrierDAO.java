package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.swing.JOptionPane;

import CLASSES_BEANS.Calendrier;

public class CalendrierDAO extends dao<Calendrier>{

	public CalendrierDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Calendrier obj) {
		return false;
	}

	@Override
	public boolean delete(Calendrier obj) {
		return false;
	}

	@Override
	public boolean update(Calendrier obj) {
		return false;
	}

	@Override
	public Calendrier find(int id) {
		Calendrier calendrier1=null;
		BaladeDAO baladeDAO = new BaladeDAO(this.connect);
		Calendrier calendrier= new Calendrier();
		try {
			ResultSet result = this.connect.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade  where id_cat2="+id);
		  // if(result.first()){
			  // result.beforeFirst();
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
						e.printStackTrace();
					}
				    if(calendar1.after(calendar2)){
				    	calendrier.addBalade(baladeDAO.find(result.getInt("id_balade")));
				    }
		       }
		       calendrier1=calendrier;
		   //}
			       
		  } catch (Exception e) {
		    e.printStackTrace();
		  }     
		
		return  calendrier1;
	}

	@Override
	public Set<Calendrier> getList() {
		// TODO Auto-generated method stub
		return null;
	}
}
