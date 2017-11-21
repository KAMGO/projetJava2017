package CLASSES_BEANS;

import java.util.HashSet;
import java.util.Set;

public class Balade {
	private int id = 0;
	private String lieuBalade;
	private String date;
	private int idCat;
	private Set<Voiture> listVoiture = new HashSet<Voiture>();
	public Balade(){}
	public Balade(String lieuBalade, String date,int idCat) {
		this.lieuBalade = lieuBalade;
		this.date = date;
		this.idCat=idCat;
	}
	public Balade(int id ,String lieuBalade, String date,int idCat) {
		this.lieuBalade = lieuBalade;
		this.date = date;
		this.id=id;
		this.idCat=idCat;
	}
	public void addVoiture(Voiture voi) {
	    if(!listVoiture.contains(voi))
	    	listVoiture.add(voi);
	   }
	public void removeVoiture(Voiture voi) {
		    this.listVoiture.remove(voi);
	}
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

	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void afficheListVoiture(){
		System.out.println("numero voiture \t nombre max de place personne \t  nombre max de place velo \t");
		for(Voiture voiture : this.listVoiture){
			System.out.print(voiture.getId()+"\t"+voiture.getNbres_pers_max()+"\t"+voiture.getNbres_velo_max());
			System.out.println();
		}
	}
	public void afficheRecapBal(Set<Membre> listMembreCat){
		int som = 0,nbreVoiture=0;
		for(Voiture voiture : this.listVoiture){
			voiture.afficheRecapVoiture();
			som += voiture.totalPassageVoi();
			System.out.println();
			nbreVoiture++;
		}
		if(som<listMembreCat.size()-nbreVoiture){
			System.out.println("nous avons : "+(listMembreCat.size()-som)+ " qui n'ont pas de voiture donc manque de chauffeur");	
		}
		else if(som==listMembreCat.size()-nbreVoiture){
			System.out.println(" tout le monde a une place dans une voiture ");	
		}
		else
			System.out.println(" quelqu'un a reseve plus d'une place ");
	}
	public void afficheForfaitBalade(int nbrekm,float prixUnitaire){
		for(Voiture voiture : this.listVoiture){
			voiture.affichForfaitCalcul(nbrekm, prixUnitaire);
		}	
	}
}
