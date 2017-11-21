package CLASSES_BEANS;

import java.util.HashSet;
import java.util.Set;

public class Voiture {
	private int id;
	private int nbres_pers_max;
	private int nbres_velo_max;
	private int id_pers1;
	private int id_balade;
	private Set<Passage> listPassage = new HashSet<Passage>();
	
	public Voiture(){}
	public Voiture(int nbres_pers_max, int nbres_velo_max, int id_pers1, int id_balade) {
		this.nbres_pers_max = nbres_pers_max;
		this.nbres_velo_max = nbres_velo_max;
		this.id_pers1 = id_pers1;
		this.id_balade = id_balade;
	}
	public Voiture(int id,int nbres_pers_max, int nbres_velo_max, int id_pers1, int id_balade) {
		this.nbres_pers_max = nbres_pers_max;
		this.nbres_velo_max = nbres_velo_max;
		this.id_pers1 = id_pers1;
		this.id_balade = id_balade;
		this.id=id;
	}
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
	public int getId_pers1() {
		return id_pers1;
	}
	public void setId_pers1(int id_pers1) {
		this.id_pers1 = id_pers1;
	}
	public int getId_balade() {
		return id_balade;
	}
	public void setId_balade(int id_balade) {
		this.id_balade = id_balade;
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
	public int totalVeloVoi(){
		int somVelo=0;
		for (Passage passage : this.getListPassage()){
			somVelo +=passage.getNbrveloresa();
		}
		return somVelo;
	}
	public int totalPassageVoi(){
		int somPassage=0;
		for (Passage passage : this.getListPassage()){
			somPassage +=passage.getNbrPlaceresa();
		}
		return somPassage;
	}
	public float affichForfaitCalcul(int nbrekm,float prixUnitaire){
		 float som =0, somTotal=nbrekm*prixUnitaire;
		if(this.totalPassageVoi()==0){
			System.out.println(" imposible de calcul un forfait car il n'y a pas de covoitureur dans la voiture  "+this.id);
		}
		else{
			som =somTotal/(this.totalPassageVoi()+1);
			System.out.println("tout les covoitureurs de la voiture  "+ this.id + "  doivent payer : "+som);
		}
		return som;
	}
	
}
