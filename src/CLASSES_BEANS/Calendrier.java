package CLASSES_BEANS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import DAO.dao;
import DAOFACTORY.AbstractDAOFactory;

public class Calendrier {
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private Categorie categorie;
	private Set<Balade> listBalade = new HashSet<Balade>();
	public Calendrier() {}
	
	public Calendrier(Categorie categorie) {
		this.categorie=categorie;
		/*Set<Categorie> listCategorie = new HashSet<Categorie>();
		dao<Categorie> categorieDao = adf.getCategorieDAO();
		Categorie trouve = null;
		listCategorie = categorieDao.getList();
		for(Categorie cat : listCategorie ) {
			if(cat.getNomCategorie().compareTo(categorie.getNomCategorie())==0)
				trouve=cat;
		}
		if(trouve!=null) {
			this.categorie=trouve;
			dao<Calendrier> calendrierDao = adf.getCalendrierDAO();	
			listBalade=calendrierDao.find(trouve.getId()).getListBalade();
		}
		else
			JOptionPane.showMessageDialog(null,"probleme avec la BD");*/
	}

	public Set<Balade> getListBalade() {
		return listBalade;
	}
	public void addBalade(Balade bal) {
	    if(!listBalade.contains(bal))
	    	listBalade.add(bal);
	   }
	public void removeBalade(Balade bal) {
		    this.listBalade.remove(bal);
	}
	public void setListBalade(Set<Balade> listBalade) {
		this.listBalade = listBalade;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	

	//permet d'obtenir la liste des balades a partir du nom d'une gategorie
	public Set<Balade> chargeCalendrier(String nomcat) {
		Set<Categorie> listCategorie = new HashSet<Categorie>();
		dao<Categorie> categorieDao = adf.getCategorieDAO();
		Categorie trouve = null;
		listCategorie = categorieDao.getList();
		for(Categorie cat : listCategorie ) {
			if(cat.getNomCategorie().compareTo(nomcat)==0)
				trouve=cat;
		}
		if(trouve!=null) {
			this.categorie=trouve;
			dao<Calendrier> calendrierDao = adf.getCalendrierDAO();	
			listBalade=calendrierDao.find(trouve.getId()).getListBalade();
		}
		else
			JOptionPane.showMessageDialog(null,"probleme avec la BD");	
		return listBalade;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void afficheCalandrier(){
		System.out.println(" NUMERO BALADE \t LIEU DE LA BALADE \t  DATE DE LA BALADE  \t CATEGORIE ");
		System.out.println();
		for(Balade balade : this.listBalade){
			Date date1 = new Date();
			Date date2 = new Date();
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
		    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		    try {
				date1=format1.parse(balade.getDate());
				calendar1.setTime(date1);
				calendar2.setTime(date2);
				
			} catch (ParseException e) {
				System.out.println(" probleme avec la date "+e);
			}
		    if(calendar1.after(calendar2)){
		    	if(categorie.getId()==1){
			    	System.out.print(balade.getId()+"\t\t"+balade.getLieuBalade()+"\t\t"+balade.getDate()+"\t\t Cyclo(Velo sur route )");
			    	System.out.println();
		    	}
		    	if(categorie.getId()==2){
			    	System.out.print(balade.getId()+"\t\t"+balade.getLieuBalade()+"\t\t"+balade.getDate()+"\t\t VTT descendeurs");
			    	System.out.println();
		    	}
		    	if(categorie.getId()==3){
			    	System.out.print(balade.getId()+"\t\t"+balade.getLieuBalade()+"\t\t"+balade.getDate()+"\t\t VTT Randonneurs");
			    	System.out.println();
		    	}
		    	if(categorie.getId()==4){
			    	System.out.print(balade.getId()+"\t\t"+balade.getLieuBalade()+"\t\t"+balade.getDate()+"\t\t VTT Trialistes");
			    	System.out.println();
		    	}
		    }
		}
	}
	
}
