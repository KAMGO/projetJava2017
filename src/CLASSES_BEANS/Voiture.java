package CLASSES_BEANS;

import java.util.HashSet;
import java.util.Set;

import DAO.dao;
import DAOFACTORY.AbstractDAOFactory;

public class Voiture {
	// ATTRIBUT
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private int id;
	private int nbres_pers_max;
	private int nbres_velo_max;
	private Membre membre;
	private Balade balade;
	private Set<Passage> listPassage = new HashSet<Passage>();
	// CONSTRUCTEURS
	public Voiture(){}
	public Voiture(int nbres_pers_max, int nbres_velo_max,Membre membre,Balade balade) {
		this.nbres_pers_max = nbres_pers_max;
		this.nbres_velo_max = nbres_velo_max;
		this.membre = membre;
		this.balade = balade;
	}
	public Voiture(int id,int nbres_pers_max, int nbres_velo_max,Membre membre,Balade balade) {
		this.nbres_pers_max = nbres_pers_max;
		this.nbres_velo_max = nbres_velo_max;
		this.membre = membre;
		this.balade = balade;
		this.id=id;
	}
	public Voiture(int id,int nbres_pers_max, int nbres_velo_max,Membre membre) {
		this.nbres_pers_max = nbres_pers_max;
		this.nbres_velo_max = nbres_velo_max;
		this.membre = membre;
		this.id=id;
	}
	// GETTTERS SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNbres_pers_max() {
		return nbres_pers_max;
	}
	public void setNbres_pers_max(int nbres_pers_max) {
		this.nbres_pers_max = nbres_pers_max;
	}
	public int getNbres_velo_max() {
		return nbres_velo_max;
	}
	public void setNbres_velo_max(int nbres_velo_max) {
		this.nbres_velo_max = nbres_velo_max;
	}

	public Set<Passage> getListPassage() {
		return listPassage;
	}
	public void setListPassage(Set<Passage> listPassage) {
		this.listPassage = listPassage;
	}
	public void addPassage(Passage passage) {
	    if(!listPassage.contains(passage))
	    	listPassage.add(passage);
	   }
	public void removeVoiture(Passage passage) {
		    this.listPassage.remove(passage);
	}
	public Membre getMembre() {
		return membre;
	}
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	public Balade getBalade() {
		return balade;
	}
	public void setBalade(Balade balade) {
		this.balade = balade;
	}
	
	//METHODES
    public void createVoiture() {
		dao<Voiture> voitureDao1 = adf.getVoitureDAO();
		voitureDao1.create(this);
    }
	public void afficheRecapVoiture(){
		int k1=0,somVelo=0,somPassage=0;
		Passage[] tabData2  = new Passage[this.getListPassage().size()];
		for (Passage passage : this.getListPassage()){
			tabData2[k1++]=passage;
			somVelo +=passage.getNbrveloresa();
			somPassage +=passage.getNbrPlaceresa();
		}
		if(somVelo<this.nbres_velo_max)
			System.out.println(" dans la voiture "+this.id +" il manque :"+(this.nbres_velo_max-somVelo)+" place(s) pour velo ");
		else
			System.out.println(" dans la voiture  "+this.id +" les places Velo sont complets ");
		if(somPassage<this.nbres_pers_max)
			System.out.println(" dans la voiture  "+this.id +" il manque :"+(this.nbres_pers_max-somPassage)+" place(s) pour personne");
		else
			System.out.println(" dans la voiture  "+this.id +" les places personnes sont completes ");
		if(somVelo==0 && somPassage==0)
			System.out.println("la voiture  "+this.id +" est vide ");
			
	}
	public int getRestPlaceVelo(){
		int somVelo=0;
		for (Passage passage : this.getListPassage()){
			somVelo +=passage.getNbrveloresa();
		}
		return nbres_velo_max-somVelo;
	}
	public int getRestPlacePers(){
		int somPassage=0;
		for (Passage passage : this.getListPassage()){
			somPassage +=passage.getNbrPlaceresa();
		}
		return this.nbres_pers_max-somPassage;
	}
	public boolean savePassage() {
		dao<Voiture> voitureDao1 = adf.getVoitureDAO();
		return voitureDao1.update(this);	
	}
	public int montantAResevoir(int forfait) {
		return forfait*this.getListPassage().size();
	}
	/*public float affichForfaitCalcul(int nbrekm,float prixUnitaire){
		 float som =0, somTotal=nbrekm*prixUnitaire;
		if(this.totalPassageVoi()==0){
			System.out.println( "dans la voiture   "+this.id + " imposible de calcul un forfait car il n'y a pas de covoitureur");
		}
		else{
			som =somTotal/(this.totalPassageVoi()+1);
			System.out.println("tous les covoitureurs de la voiture  "+ this.id + "  doivent payer : "+som);
		}
		return som;
	}*/
	
}
