package CLASSES_BEANS;

public class Passage extends Personne{
	// ATTRIBUTS
	private int nbrPlaceresa;
	private int nbrveloresa;
	//CONSTRUCTEURS
	public Passage(){} 
	public Passage(String nom,String prenom,String email,String password,String statut_pers,int nbrPlaceresa,int nbrveloresa) {
		super(nom, prenom,email,password,statut_pers);
		this.nbrPlaceresa = nbrPlaceresa;
		this.nbrveloresa = nbrveloresa;
	}
	public Passage(int id,String nom,String prenom,String email,String password,String statut_pers,int paye,int nbrPlaceresa,int nbrveloresa){
		super(id,nom, prenom,email,password,statut_pers);
		this.nbrPlaceresa = nbrPlaceresa;
		this.nbrveloresa = nbrveloresa;
	}
	public int getNbrPlaceresa() {
		return nbrPlaceresa;
	}
	public void setNbrPlaceresa(int nbrPlaceresa) {
		this.nbrPlaceresa = nbrPlaceresa;
	}
	public int getNbrveloresa() {
		return nbrveloresa;
	}
	public void setNbrveloresa(int nbrveloresa) {
		this.nbrveloresa = nbrveloresa;
	}
}
