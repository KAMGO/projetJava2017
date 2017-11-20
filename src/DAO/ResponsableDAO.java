package DAO;

import java.sql.Connection;

import CLASSES_BEANS.Responsable;

public class ResponsableDAO  extends dao<Responsable>{

	public ResponsableDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Responsable obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Responsable obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Responsable obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Responsable find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Responsable connecter(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Responsable EstDejaChauffeur(int id_pers1,int id_balade1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Responsable baladeCategorie(int id_balade) {
		// TODO Auto-generated method stub
		return null;
	}

}
