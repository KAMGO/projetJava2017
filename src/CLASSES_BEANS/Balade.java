package CLASSES_BEANS;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import DAO.dao;
import DAOFACTORY.AbstractDAOFactory;

public class Balade {
	// ATTRIBUTS
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private int id = 0;
	private String lieuBalade;
	private String date;
	private Calendrier calendrier=null;
	private Set<Voiture> listVoiture = new HashSet<Voiture>();
	private int  forfait;

	// CONSTRUCTEURS
	public Balade(){}
	public Balade(String lieuBalade, String date,Calendrier calendrier) {
		this.lieuBalade = lieuBalade;
		this.date = date;
		this.calendrier=calendrier;
	}
	public Balade(int id ,String lieuBalade, String date,Calendrier calendrier) {
		this.lieuBalade = lieuBalade;
		this.date = date;
		this.id=id;
		this.calendrier=calendrier;
	}
	public Balade(int id ,String lieuBalade, String date,int forfait) {
		this.lieuBalade = lieuBalade;
		this.date = date;
		this.id=id;
		this.forfait=forfait;
	}
	public Balade(String lieuBalade, String date) {
		this.lieuBalade = lieuBalade;
		this.date = date;
	}
	// GETTERS SETTERS
	public Set<Voiture> getListVoiture() {
		return listVoiture;
	}
	public void setListVoiture(Set<Voiture> listVoiture) {
		this.listVoiture = listVoiture;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLieuBalade() {
		return lieuBalade;
	}

	public void setLieuBalade(String lieuBalade) {
		this.lieuBalade = lieuBalade;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public Calendrier getCalendrier() {
		return calendrier;
	}
	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}
	// METHODES
	public void addVoiture(Voiture voi) {
	    if(!listVoiture.contains(voi))
	    	listVoiture.add(voi);
	   }
	public void removeVoiture(Voiture voi) {
		    this.listVoiture.remove(voi);
	}
	public int getForfait() {
		return forfait;
	}
	public void setForfait(int nbrKm,float prixUnitaire) {
		this.forfait = (int)prixUnitaire*nbrKm;
	}
	//permet de verifier si le membre est deja chauffeur dans dans cette balade
	public boolean estDejaChauffeur(int id_pers) {
		Balade balade =new Balade();
		Voiture voiture =new Voiture();
		dao<Balade> baladeDao = adf.getBaladeDAO();
		balade=baladeDao.find(id);
		voiture = balade.listVoiture.stream()
				.filter(x->id_pers==(x.getMembre().getId()))
				.findAny()
				.orElse(null);
		JOptionPane.showMessageDialog(null," dans je suis chauffeur "+voiture!=null);
		return voiture!=null;
	}
	//permet de verifier si le membre a deja fait une reservation pour cette balade 
	public int aDejaReserve(int id_passage) {
		int deja=3;
		for(Voiture voiture : this.listVoiture){
			for(Passage passage : voiture.getListPassage()){
				if(passage.getId()==id_passage) {
					if(passage.getNbrPlaceresa()>0&&passage.getNbrveloresa()>0)
						deja=0;//plus de reservation possible
					else if(passage.getNbrPlaceresa()>0)
						deja=1;//place assise reserve
					else if(passage.getNbrveloresa()>0)
						deja=2;//place velo reserve
					else
						deja=3;
				}
			}
		}
		return deja;
	}
	public boolean createBalade(String nomcat) {
		Set<Categorie> listCategorie = new HashSet<Categorie>();
		dao<Categorie> categorieDao = adf.getCategorieDAO();
		Categorie trouve = null;
		listCategorie = categorieDao.getList();
		for(Categorie cat : listCategorie ) {
			if(cat.getNomCategorie().compareTo(nomcat)==0)
				trouve=cat;
		}
		setCalendrier(new Calendrier(trouve));
		dao<Balade> baladeDAO = adf.getBaladeDAO();
		return baladeDAO.create(this);
	}
	/*public void afficheListVoiture(){
		System.out.println("numero voiture \t nombre max de place personne \t  nombre max de place velo \t");
		for(Voiture voiture : this.listVoiture){
			System.out.print(voiture.getId()+"\t\t\t\t"+voiture.getNbres_pers_max()+"\t\t\t\t"+voiture.getNbres_velo_max());
			System.out.println();
		}
	}*/
	/*public void afficheRecapBal(){
		int som = 0,nbreVoiture=0;
		for(Voiture voiture : this.listVoiture){
			voiture.afficheRecapVoiture();
			som += voiture.totalPassageVoi();
			System.out.println();
		}
		if(som<listMembreCat.size()-nbreVoiture){
			System.out.println("nous avons encore : "+(listMembreCat.size()-som)+ " qui n'ont/n'a pas de place donc manque de chauffeur");	
		}
		else if(som==listMembreCat.size()-nbreVoiture){
			System.out.println(" tout le monde a une place dans une voiture ");	
		}
		else
			System.out.println(" quelqu'un a reseve plus d'une place ");
	}*/
	//recupere la liste des chauffeurs superflus
	public Set<Voiture> chauffeurSuperflus(){
		 Set<Voiture> listVoiture1=new HashSet<Voiture>();
		 for(Voiture voiture : listVoiture)
			 if(voiture.getListPassage().size()==0)
				 listVoiture1.add(voiture);
		 return listVoiture1;
	}
	//cette fonction permet de calculer la liste des participant d'une balade sans compte les chauffeurs
	public Set<Passage> listParticipant(){
		Set<Passage> listParticipant=new HashSet<Passage>() ;
		for(Voiture voiture : listVoiture) {
			for(Passage passage : voiture.getListPassage())
				listParticipant.add(passage);
		}
		return listParticipant;
	}
}
