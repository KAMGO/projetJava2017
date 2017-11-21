package CLASSES_BEANS;
import UTILITAIRE.*;

import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DAO.MembreDAO;
import DAO.dao;
import DAOFACTORY.*;

public class Main {
	public static void main(String[] args) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		
 /******************************************insertion d'un membre*******************************************/
		//Membre membre =new Membre("teuguis","joel","tmjmartin@yahoo","pass","membre");
		//dao<Membre> membreDao = adf.getMembreDAO();
		//membreDao.create(membre);
/******************************************insertion d'une balade*******************************************/
		//Balade balade = new Balade("charleroi","06/08/2018 09:00:00");
		//dao<Balade> baladeDao = adf.getBaladeDAO();
		//baladeDao.create(balade);
		
/******************************************poster un vehicule*******************************************/
		//Voiture voiture = new Voiture(2,3,2,1);
		//dao<Voiture> voitureDao = adf.getVoitureDAO();
		//voitureDao.create(voiture);
/********************************************se connecter avec son email et son mot de ^passe ************/
		//Membre membre= new Membre();
		//dao<Membre> membreDao = adf.getMembreDAO();
		//membre=membreDao.connecter("martin@yahoo.fr","pass");
		//System.out.println("statut : "+ membre.getStatut());
/***************************************************affiche la liste des balades***************************/
		//Calendrier calendar = new Calendrier() ;
		//dao<Calendrier> calendrierDao = adf.getCalendrierDAO();
		//calendrierDao.find(1);
		//}
/***************************************************apres avoir choisi une balade*************************/
	
	
/******************************************connexion*******************************************/
		int choix2=0;
		System.out.println("Bienvenue");
		System.out.println("1\t se connecter ");
		System.out.println("2\t s'inscrire ");
		System.out.print(" votre choix : ");Clavier.lireInt();
		while(choix2<1 || choix2>2){
			System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 2 ");
			choix2 = Clavier.lireInt();
		}
		if(choix2==1){
			System.out.println("*********************************************** connexion *****************************************************************");
			int count=0; 
			Membre membre = new Membre();
			do{
					System.out.print(" email "); String email = Clavier.lireString();System.out.println(); 
					System.out.print(" password "); String pass=Clavier.lireString();System.out.println();
					count++;
					dao<Membre> membreDao = adf.getMembreDAO();
					membre=membreDao.connecter(email,pass);
					if(membre==null)
						System.out.println("desole votre mot de passe ou votre email n est pas correct il vous reste : "+(3-count)+" tentative(s)");
						System.out.println();
					if((3-count)==0){
						System.out.println("desole votre nombre de tentative est epuise \n aurevoir ");	
						System.exit(0);
					}
					
			}while(membre==null);
			if(membre.getStatut().compareTo("membre")!=0){
				if(membre.getStatut()=="tresorier"){
					
				}
				/***************************************************************************************************************************************/
				/**                                                    se connecter comme responsable categorie                                       **/
				/***************************************************************************************************************************************/
				else if((membre.getStatut().compareTo("cat_cyclo"))==0){
					int sortir=0;
					do{
						FonctionUtile.ISResponsable(membre, 1);
						System.out.println();
						System.out.println(" entrer 0 pour continuer  "); sortir=Clavier.lireInt();
					}while(sortir==0);
				}
				else if((membre.getStatut().compareTo("cat_vtt_rand"))==0){
					int sortir=0;
					do{
						FonctionUtile.ISResponsable(membre, 2);
						System.out.println();
						System.out.println(" entrer 0 pour continuer  "); sortir=Clavier.lireInt();
				}while(sortir==0);
				}
				else if((membre.getStatut().compareTo("cat_vtt_trial"))==0){
					int sortir=0;
					do{
						FonctionUtile.ISResponsable(membre, 3);
						System.out.println();
						System.out.println(" entrer 0 pour continuer  "); sortir=Clavier.lireInt();
				}while(sortir==0);
				}
				else if((membre.getStatut().compareTo("cat_vtt_desc"))==0){
					int sortir=0;
					do{
						FonctionUtile.ISResponsable(membre, 4);
						System.out.println();
						System.out.println(" entrer 0 pour continuer  "); sortir=Clavier.lireInt();
				}while(sortir==0);
				}
				else
				{System.out.println(membre.getStatut());System.exit(0);}
			}
			else{
				/***************************************************************************************************************************************/
				/**                                                    se connecter comme membre                                                      **/
				/***************************************************************************************************************************************/
				int sortir=0;
				do{
					FonctionUtile.ISMmenbre(membre);
					System.out.println();
					System.out.println(" entrer 0 pour continuer  "); sortir=Clavier.lireInt();
				}while(sortir==0);
			}
			System.out.println(" aurevoir ");
		}
		else{
			String nom=null,prenom=null,email=null,password=null;
			int confirme=0;int choix3=0;
			do{
			System.out.println("*********************************************** s'inscrire *****************************************************************");
			System.out.println(" entrer votre nom      :  "); nom=Clavier.lireString();
			System.out.println(" entrer votre prenom   :  "); prenom=Clavier.lireString();
			System.out.println(" entrer votre email    :  "); email =Clavier.lireString();
			System.out.println(" entrer votre password :  "); password=Clavier.lireString(); 
			System.out.println();
			System.out.println(" dans quelle categorie voulez-vous inscrire :  ");
			System.out.println(" 1\t  categorie Cyclo  ");
			System.out.println(" 2\t  categorie VTT Randonneurs ");
			System.out.println(" 3\t  categorie VTT Trialistes  ");
			System.out.println(" 4\t  categorie VTT Descendeurs ");
			System.out.println(" Votre choix : ");choix2=Clavier.lireInt();
			while(choix3<1 || choix3>4){
				System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 4 ");
				choix3 = Clavier.lireInt();
			}
			System.out.println("************** infos saisie **************************************************");
			System.out.println(" nom         :  "+nom);
			System.out.println(" prenom      :  "+prenom);
			System.out.println(" email       :  "+email);
			System.out.println(" password    :  "+password);
			System.out.println(" statut      :   membre");
	    	if(choix3==1){
		    	System.out.print(" Categorie : Cyclo(Velo sur route )");
		    	System.out.println();
	    	}
	    	if(choix3==2){
		    	System.out.print(" Categorie :  VTT descendeurs");
		    	System.out.println();
	    	}
	    	if(choix3==3){
		    	System.out.print(" Categorie : VTT Randonneurs");
		    	System.out.println();
	    	}
	    	if(choix3==4){
		    	System.out.print(" Categorie :  VTT Trialistes");
		    	System.out.println();
	    	}
			
			System.out.println(" pour confirme entrer 0 et autre nombre pour modifier ");confirme=Clavier.lireInt();
			}while(confirme!=0);
			Membre membre =new Membre(nom,prenom,email,password,"membre");
			dao<Membre> membreDao = adf.getMembreDAO();
			membreDao.create(membre);
		
			dao<Membre> membreDao1 = adf.getMembreDAO();
			membre=membreDao1.connecter(email,password);
			CatPersonne catP = new CatPersonne(choix3,membre.getId());
			dao<CatPersonne> catPDao = adf.getCatPersonneDAO();
			catPDao.create(catP);
		}
	}
}
