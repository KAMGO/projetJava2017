package CLASSES_BEANS;

import java.util.HashSet;
import java.util.Set;

public class CatPersonne {
	private int id=0;
	private int id_cat1;
	private int id_pers3;
	private int[] tabData  = new int[4];
	private Set<Membre> listMembreCat = new HashSet<Membre>();
	public CatPersonne() {}
	public CatPersonne(int id_cat1, int id_pers3) {
		this.id_cat1 = id_cat1;
		this.id_pers3 = id_pers3;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_cat1() {
		return id_cat1;
	}
	public void setId_cat1(int id_cat1) {
		this.id_cat1 = id_cat1;
	}
	public int getId_pers3() {
		return id_pers3;
	}
	public void setId_pers3(int id_pers3) {
		this.id_pers3 = id_pers3;
	}
	public int[] getTabData() {
		return tabData;
	}
	public void setTabData(int[] tabData) {
		this.tabData = tabData;
	}
	public Set<Membre> getListMembreCat() {
		return listMembreCat;
	}
	public void setListMembreCat(Set<Membre> listMembreCat) {
		this.listMembreCat = listMembreCat;
	}
	public void addMembre(Membre membre) {
	    if(!this.listMembreCat.contains(membre))
	    	this.listMembreCat.add(membre);
	   }
	public void removeMemmbre(Membre membre) {
		    this.listMembreCat.remove(membre);
	}
}
