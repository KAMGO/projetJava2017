package CLASSES_BEANS;

public class Categorie {
	private int id=0;
	private String nomCategorie;
	public Categorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public Categorie(int id , String nomCategorie) {
		this.nomCategorie = nomCategorie;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
}
