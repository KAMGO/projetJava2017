package CLASSES_BEANS;

import java.util.HashSet;
import java.util.Set;

import DAO.dao;
import DAOFACTORY.AbstractDAOFactory;

public class Membre extends Personne{
	//ATTRIBUTS
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private Set<Categorie> listCategorie = new HashSet<Categorie>();
	DAO_CLASS_BEANS daoClass = new DAO_CLASS_BEANS();
	//CONSTRUCTEURS
	public Membre(){}
	public Membre(String nom,String prenom,String email,String password,String statut_pers){
		super(nom, prenom,email,password,statut_pers);
	}
	public Membre(int id,String nom,String prenom,String email,String password,String statut_pers){
		super(id,nom, prenom,email,password,statut_pers);
	}
	public Membre(int id,String nom,String prenom,String email,String password,String statut_pers,int paye){
		super(id,nom, prenom,email,password,statut_pers,paye);
	}
	// GETTERS ET SETTERS
	public Set<Categorie> getListCategorie() {
		return listCategorie;
	}
	public void setListCategorie(Set<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
	
	/************************************************************** METHODES **********************************************************************/
	
	public void addCategorie(Categorie cat) {
	    if(!listCategorie.contains(cat))
	    	listCategorie.add(cat);
	   }
	public void removeCategorie(Categorie cat) {
		    this.listCategorie.remove(cat);
	}
	/************************************************************** METHODES liste des categories a souscrir **********************************************************************/
	public Set<Categorie> afficheCatAsouscrir(){
		Set<Categorie> tab1 = new HashSet<Categorie>();
		dao<Categorie> categorieDao = adf.getCategorieDAO();
		Categorie cat1 = new Categorie();
		tab1 = categorieDao.getList();
		for(Categorie cat : this.listCategorie){
			cat1=tab1.stream().filter(x->cat.getId()==x.getId()).findAny().orElse(null);
			if(cat1!=null)
				tab1.remove(cat1);
		}	
		return tab1;
	}
	/********************************************* methode de connexion  **********************************************************************************************/
	public Membre connexion(String email , String password){
		Set<Membre> listMembre = new HashSet<Membre>();
		dao<Membre> membreDao = adf.getMembreDAO();
		listMembre=membreDao.getList();
		Membre trouve = null;
		for(Membre membre1 : listMembre ) {
			if((membre1.getEmail()).compareTo(email)==0&&(membre1.getPassword()).compareTo(password)==0)
				trouve=membre1;
		}
		/*/JOptionPane.showMessageDialog(null, trouve.getEmail());
		if(trouve!=null) {
			if(statut.compareTo("membre")==0&&"membre".compareTo(trouve.getStatut())==0)
				etat=1;
			if(statut.compareTo("tresorier")==0&&"tresorier".compareTo(trouve.getStatut())==0)
				etat=2;
			else
				etat=3;
		}
		else
			etat=0;*/
		return trouve;
	}
	/********************************************* methode inscription membre *****************************************************************************************/
	public int inscription(String nom,String prenom,String email,String password,String categorie){
		int etat = 0;
		Set<Membre> listMembre = new HashSet<Membre>();
		Set<Categorie> listCategorie = new HashSet<Categorie>();
		Membre membre =new Membre(nom,prenom,email,password,"membre");
		dao<Membre> membreDao = adf.getMembreDAO();
		dao<Membre> membreDao1 = adf.getMembreDAO();
		dao<Membre> membreDao2 = adf.getMembreDAO();
		dao<Categorie> categorieDao = adf.getCategorieDAO();
		dao<Membre> membreDao3 = adf.getMembreDAO();
		listMembre=membreDao.getList();
		Membre trouve= new Membre();
		Membre trouve1= null;
		for(Membre membre1 : listMembre ) {
			if((membre1.getEmail()).compareTo(email)==0&&(membre1.getPassword()).compareTo(password)==0)
				trouve1=membre1;
		}
		if(trouve1==null) {
			membreDao1.create(membre);
			listCategorie = categorieDao.getList();
			listMembre=membreDao2.getList();
			for(Membre membre1 : listMembre ) {
				if((membre1.getEmail()).compareTo(email)==0&&(membre1.getPassword()).compareTo(password)==0)
					trouve=membre1;
			}
			Categorie cat_trouve = new Categorie();
			for(Categorie cat : listCategorie ) {
				if(cat.getNomCategorie().compareTo(categorie)==0)
					cat_trouve=cat;
			}
			trouve.addCategorie(cat_trouve);
			if(membreDao3.update(trouve))
				etat=0;
			else
				etat=1;
		}
		else
			etat=2;
		return etat;
	}
	/********************************************* methode ajout categorie  *****************************************************************************************/
	public boolean newCategorie(String categ) {
		dao<Membre> membreDao = adf.getMembreDAO();
		dao<Categorie> categorieDao = adf.getCategorieDAO();
		/*for(Categorie cat :categorieDao.getList() ) {
			if(cat.getNomCategorie().compareTo(categ)==0)
				this.addCategorie(cat);
		}*/
		this.addCategorie(categorieDao.getList().stream().filter(x->categ.equals(x.getNomCategorie())).findAny().orElse(null));
		return membreDao.update(this);
	}
	
	/********************************************* methode poster disponibilte *****************************************************************************************/
	
	public boolean updateMembre() {
		dao<Membre> membreDao = adf.getMembreDAO();	
		return membreDao.update(this);
	}
}

