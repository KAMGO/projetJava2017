package DAO;
import java.sql.Connection;
import java.util.Set;
public abstract class dao<T> {
	protected Connection connect = null;
	
	public dao(Connection conn){
		this.connect = conn;
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract Set<T> getList();
}
// On ne doit pas avoir la classe passage ni CatPersonne
// eviter de voir 
//revoir les calculs pour une balade    forfait 
//revoir tout avec les membres 
//decrementation losque l'on fait une reservation 
