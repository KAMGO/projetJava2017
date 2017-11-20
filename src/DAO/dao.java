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
	public abstract T EstDejaChauffeur(int id_pers1,int id_balade1);
	public abstract T baladeCategorie(int id_balade);
}
