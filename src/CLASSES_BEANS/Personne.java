package CLASSES_BEANS;

public class Personne {
	// ATTRIBUTS
	private int id = 0;
	private String nom;
	private String prenom;
	private String statut;
	private String email;
	private String password;
	private int paye=0;
	// CONSTRUCTEURS
	public Personne(){}
	public Personne(int id , String nom,String prenom,String email,String password,String statut){
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.statut=statut;
		this.email=email;
		this.password=password;
	}
	public Personne(String nom,String prenom,String email,String password,String statut){
		this.nom=nom;
		this.prenom=prenom;
		this.statut=statut;
		this.email=email;
		this.password=password;
	}
	public Personne(int id,String nom,String prenom,String email,String password,String statut,int paye){
		this.nom=nom;
		this.prenom=prenom;
		this.statut=statut;
		this.email=email;
		this.password=password;
		this.paye=paye;
		this.id=id;
	}
	// GETTERS ET SETTERS
	public String getNom(){
		return nom;
	}
	public String getPrenom(){
		return prenom;
	}
	public String getStatut(){
		return statut;
	}
	public void setStatut(String statut){
		this.statut = statut;
	 }
	public String getEmail(){
		return email;
	}
	public int getId() {
	  return id;
	}
	public String getPassword(){
		return password;
	}
	public void setNom(String nom){
		this.nom=nom;
	}
	public void setPrenom(String prenom){
		this.prenom= prenom;
	}
	public  void setEmail(String email){
		this.email=email;
	}
	public void setId(int id) {
	   this.id = id;
	}
	public  void setPassword(String password){
		this.password=password;
	}
	public int getPaye() {
		return paye;
	}
	public void setPaye(int paye) {
		this.paye = paye;
	}
	
}

