package DAO;
import java.sql.Connection;

public abstract class dao<T> {
	protected Connection connect = null;
	
	public dao(Connection conn){
		this.connect = conn;
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);

	public abstract T connecter(String string, String string2);
	public abstract T EstDejaChauffeur(int id_pers1,int id_balade1); //doit etre dans une classe appelle une liste 
	public abstract T baladeCategorie(int id_balade);
}
// On ne doit pas avoir la classe passage ni CatPersonne
// eviter de voir 
//revoir les calculs pour une balade    forfait 
//revoir tout avec les membres 
//decrementation losque l'on fait une reservation 
