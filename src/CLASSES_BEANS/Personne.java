package CLASSES_BEANS;

public class Personne {
	private int id = 0;
	private String nom;
	private String prenom;
	private String statut;
	private String email;
	private String password;
	public Personne(){}
	public Personne(String nom,String prenom,String email,String password,String statut){
		this.nom=nom;
		this.prenom=prenom;
		this.statut=statut;
		this.email=email;
		this.password=password;
	}
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
}
