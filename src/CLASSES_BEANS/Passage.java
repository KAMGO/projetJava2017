package CLASSES_BEANS;

public class Passage {
	private int id=0;
	private String nomPassage;
	private String prenomPassage;
	private int nbrPlaceresa;
	private int nbrveloresa;
	private int[] tabData  = new int[4];
	public Passage(){} 
	public Passage(String nomPassage, String prenomPassage, int nbrPlaceresa, int nbrveloresa) {
		this.nomPassage = nomPassage;
		this.prenomPassage = prenomPassage;
		this.nbrPlaceresa = nbrPlaceresa;
		this.nbrveloresa = nbrveloresa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomPassage() {
		return nomPassage;
	}
	public void setNomPassage(String nomPassage) {
		this.nomPassage = nomPassage;
	}
	public String getPrenomPassage() {
		return prenomPassage;
	}
	public void setPrenomPassage(String prenomPassage) {
		this.prenomPassage = prenomPassage;
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
	public int[] getTabData() {
		return tabData;
	}
	public void setTabData(int[] tabData) {
		this.tabData = tabData;
	}
	
	
}
