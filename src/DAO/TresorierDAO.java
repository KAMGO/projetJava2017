package DAO;

import java.sql.Connection;

import CLASSES_BEANS.Tresorier;

public class TresorierDAO extends dao<Tresorier> {

	public TresorierDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Tresorier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Tresorier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Tresorier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tresorier find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tresorier connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tresorier EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tresorier baladeCategorie(int id_balade) {
		// TODO Auto-generated method stub
		return null;
	}

}
