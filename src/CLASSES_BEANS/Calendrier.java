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
	public Set<Balade> chargeAllCalendrier() {
		Set<Balade> listAllBal=new HashSet<Balade>();
		Set<Categorie> listCategorie = new HashSet<Categorie>();
		dao<Categorie> categorieDao = adf.getCategorieDAO();
		listCategorie = categorieDao.getList();
		for(Categorie cat : listCategorie ) {
			dao<Calendrier> calendrierDao = adf.getCalendrierDAO();	
			listBalade=calendrierDao.find(cat.getId()).getListBalade();
			for(Balade bal : listBalade ) {
				listAllBal.add(bal);
			}
		}	
		return listAllBal;
	}
	
}
