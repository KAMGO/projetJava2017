package CLASSES_BEANS;

import java.util.HashSet;
import java.util.Set;

public class Tresorier {
	private int id;
	private Set<Membre> listAllMembre = new HashSet<Membre>();
	public Tresorier() {}
	public Tresorier(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Membre> getListAllMembre() {
		return listAllMembre;
	}
	public void setListAllMembre(Set<Membre> listAllMembre) {
		this.listAllMembre = listAllMembre;
	}
	public void addMembre(Membre membre) {
	    if(!this.listAllMembre.contains(membre))
	    	this.listAllMembre.add(membre);
	   }
	public void removeMemmbre(Membre membre) {
		    this.listAllMembre.remove(membre);
	}
	
}
