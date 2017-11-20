package DAOFACTORY;

import java.sql.Connection;

import DAO.*;
import CLASSES_BEANS.*;

public class DAOFactory extends AbstractDAOFactory{
protected static final Connection conn = ConnectAccess.getInstance();
	
	public dao<Balade> getBaladeDAO(){
		return new BaladeDAO(conn);
	}
	public dao<Calendrier> getCalendrierDAO(){
		return new CalendrierDAO(conn);
	}
	public dao<Categorie> getCategorieDAO(){
		return new CategorieDAO(conn);
	}
	public dao<Membre> getMembreDAO(){
		return new MembreDAO(conn);
	}
	public dao<Personne> getPersonneDAO(){
		return new PersonneDAO(conn);
	}
	public dao<Responsable> getResponsableDAO(){
		return new ResponsableDAO(conn);
	}
	public dao<Tresorier> getTresorierDAO(){
		return new TresorierDAO(conn);
	}
	public dao<Voiture> getVoitureDAO(){
		return new VoitureDAO(conn);
	}
	@Override
	public dao<Passage> getPassageDAO() {
		return new PassageDAO(conn);
	}

	public dao<CatPersonne> getCatPersonneDAO() {
		return new CatPersonneDAO(conn);
	}
}
