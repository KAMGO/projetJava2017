package CLASSES_BEANS;
import java.util.HashSet;
import java.util.Set;

import DAO.dao;
import DAOFACTORY.AbstractDAOFactory;
import UTILITAIRE.Clavier;

public class DAO_CLASS_BEANS {
	// ATTRIBUT
	 AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	 dao<Membre> membreDao;
	 dao<Balade> baladeDao;
	 dao<Calendrier> calendrierDao;
	 dao<Categorie> categorieDao;
	 dao<Passage> passageDao;
	 dao<Voiture> voitureDao;
	 // CONSTRUCTEUR
	 public DAO_CLASS_BEANS(){
		 membreDao		 = adf.getMembreDAO();
		 baladeDao 		 = adf.getBaladeDAO();
		 calendrierDao	 = adf.getCalendrierDAO();
		 categorieDao	 = adf.getCategorieDAO();
		 passageDao	     = adf.getPassageDAO();
		 voitureDao	     = adf.getVoitureDAO();
	}
	 // GETTERS ET SETTERS
	 public dao<Balade> getBaladeDao() {
		return baladeDao;
	}
	public void setBaladeDao(dao<Balade> baladeDao) {
		this.baladeDao = baladeDao;
	}
	public dao<Calendrier> getCalendrierDao() {
		return calendrierDao;
	}
	public void setCalendrierDao(dao<Calendrier> calendrierDao) {
		this.calendrierDao = calendrierDao;
	}
	public dao<Categorie> getCategorieDao() {
		return categorieDao;
	}
	public void setCategorieDao(dao<Categorie> categorieDao) {
		this.categorieDao = categorieDao;
	}
	public dao<Passage> getPassageDao() {
		return passageDao;
	}
	public void setPassageDao(dao<Passage> passageDao) {
		this.passageDao = passageDao;
	}
	public dao<Voiture> getVoitureDao() {
		return voitureDao;
	}
	public void setVoitureDao(dao<Voiture> voitureDao) {
		this.voitureDao = voitureDao;
	}
	public dao<Membre> getMembreDao() {
		return membreDao;
	}
	public void setMembreDao(dao<Membre> membreDao) {
		this.membreDao = membreDao;
	}
	//METHODES
	public Membre connexion() {
		System.out.println("*********************************************** connexion *****************************************************************");
		int count=0; 
		boolean trouve=false;
		Set<Membre> listMembre = new HashSet<Membre>();
		listMembre=this.membreDao.getList();
		Membre membre = null;
		do{
				System.out.print(" email "); String email = Clavier.lireString();System.out.println(); 
				System.out.print(" password "); String password=Clavier.lireString();System.out.println();
				count++;
				for (Membre membre1 : listMembre){
					if(membre1.getEmail().compareTo(email)==0&&membre1.getPassword().compareTo(password)==0) {
						membre=membre1;
						trouve=true;
						}
					}
				if(membre==null)
					System.out.println("desole votre mot de passe ou votre email n est pas correct il vous reste : "+(3-count)+" tentative(s)");
					System.out.println();
				if((3-count)==0){
					System.out.println("desole votre nombre de tentative est epuise \n aurevoir ");
					System.exit(0);
				}
				
		}while(!trouve);
		return membre;
	}
	public boolean estDejaChauffeur(int numPers,int numBalade) {
		boolean trouve=false;
		for(Voiture voiture : this.voitureDao.getList()){
			if(voiture.getMembre().getId()==numPers&&voiture.getBalade().getId()==0)
				trouve=true;
		}
		return trouve;
	}
}

