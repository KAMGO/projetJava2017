package DAOFACTORY;
import CLASSES_BEANS.*;
import DAO.*;

public abstract class AbstractDAOFactory {
	public static final int DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;
	public abstract dao<Balade> getBaladeDAO();
	public abstract dao<Calendrier> getCalendrierDAO();
	public abstract dao<Membre> getMembreDAO();
	//public abstract dao<Tresorier> getTresorierDAO();
	//public abstract dao<Responsable> getResponsableDAO();
	public abstract dao<Categorie> getCategorieDAO();
	public abstract dao<Voiture> getVoitureDAO();
	public abstract dao<Passage> getPassageDAO();
	public static AbstractDAOFactory getFactory(int type){
		switch(type){
		case DAO_FACTORY:
			return new DAOFactory();
			default:
				return null;
		}
	}
}
