package DAO;



import java.sql.Connection;


import CLASSES_BEANS.Personne;

public class PersonneDAO extends dao<Personne>{

	public PersonneDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Personne obj) {
	  	boolean etat = false;

	  return etat;
	}

	@Override
	public boolean delete(Personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Personne find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne baladeCategorie(int id_balade) {
		// TODO Auto-generated method stub
		return null;
	}

}
