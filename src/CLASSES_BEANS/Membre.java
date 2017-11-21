package CLASSES_BEANS;

import java.util.HashSet;
import java.util.Set;

public class Membre extends Personne{
	private int id = 0;
	//private String statut = "membre";
	private Set<Categorie> listCategorie = new HashSet<Categorie>();
	public Membre(){}
	public Membre(String nom,String prenom,String email,String password,String statut_pers){
		super(nom, prenom,email,password,statut_pers);
	}
	public Membre(int id,String nom,String prenom,String email,String password,String statut_pers){
		super(nom, prenom,email,password,statut_pers);
		this.id=id;
	}
	public Membre(int id,String nom,String prenom,String email,String password,String statut_pers,int paye){
		super(nom, prenom,email,password,statut_pers,paye);
		this.id=id;
	}
	public int getId(){
		return id;
	}
	 public void setId(int id){
		 this.id = id;
	}
	public Set<Categorie> getListCategorie() {
		return listCategorie;
	}
	public void setListCategorie(Set<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
	public void addCategorie(Categorie cat) {
	    if(!listCategorie.contains(cat))
	    	listCategorie.add(cat);
	   }
	public void removeVoiture(Categorie cat) {
		    this.listCategorie.remove(cat);
	}
	public void afficheListCategorie(){
		for(Categorie cat : this.listCategorie){
			System.out.print(cat.getId()+"\t"+cat.getNomCategorie());
			System.out.println();
		}
	}
	public int [] afficheCatAsouscrir(){
		int[] tab = {1,2,3,4};
		int j=0;
		int[] tab1 = new int[4-this.listCategorie.size()];
		for(Categorie cat : this.listCategorie){
			if(tab[0]==cat.getId())
				tab[0]=0;
			if(tab[1]==cat.getId())
				tab[1]=0;
			if(tab[2]==cat.getId())
				tab[2]=0;
			if(tab[3]==cat.getId())
				tab[3]=0;
		}
		if(tab[0]!=0)
			System.out.println(tab[0]+"\t"+"cat_cyclo");
		if(tab[1]!=0)
			System.out.println(tab[1]+"\t"+"cat_vtt_rand");
		if(tab[2]!=0)
			System.out.println(tab[2]+"\t"+"cat_vtt_trial");
		if(tab[3]!=0)
			System.out.println(tab[3]+"\t"+"cat_vtt_desc");
		for(int i=0;i<4;i++){
			if(tab[i]!=0){
				tab1[j]=tab[i];
				j++;
				}
		}
			
		return tab1;
	}
}
