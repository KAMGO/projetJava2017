package CLASSES_BEANS;
import UTILITAIRE.*;

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

	 	int numBalade;
		int numVoiture;
		int numPers=0;
		int totalresaV=0;
		int totalresaP=0;
		int n,count=0;
		int nbrplace=0;
		int nbrevelo=0;
		int numCategorie=0; 
		System.out.println("*************************connexion******************************");
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
		numPers = membre.getId();
		if(membre.getStatut().compareTo("membre")!=0){
			if(membre.getStatut()=="tresorier"){}
			/***************************************************************************************************************************************/
			/**                                                    se connecter comme responsable categorie Cyclo                                 **/
			/***************************************************************************************************************************************/
			else if((membre.getStatut().compareTo("cat_cyclo"))==0){
				int choix=0;
				int mois=0;
				int annee=0;
				int jour=0;
				String lieuBalade=null;
				Date date = new Date();
				DateUser dateUser = new DateUser();
				DateUser dateUser1 = new DateUser();
				Calendar calendar = Calendar.getInstance();
				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance();
			    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			    
				System.out.println("voulez-vous continuez comme  membre ou comme responsable de la categorie Cyclo ");
				System.out.println("1\t membre");
				System.out.println("2\t responsable de la categorie Cyclo ");
				System.out.print("votre choix :");choix = Clavier.lireInt();
				while(choix<1 || choix>2){
					System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 2");
					choix = Clavier.lireInt();
				}
				if(choix==1){
					FonctionUtile.ISMmenbre(membre);
				}
				else{
					System.out.print(" que voulez-faire  : ");
					System.out.print(" 1\t ajouter une balade : ");
					System.out.print(" 2\t afficher le recapitulatif des disponibilites ");
					System.out.print("votre choix : ");choix= Clavier.lireInt();
					while(choix<1 || choix>2){
						System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 2");
						choix = Clavier.lireInt();
					}
					if(choix==1){
						System.out.println(" ***********************************  Ajout d'une balade dans la categorie Cyclo **************************************************");
						System.out.print("entre l'annee : ");annee= Clavier.lireInt();
						System.out.print("entre le mois : ");mois= Clavier.lireInt();
						System.out.print("entre le jour : ");jour= Clavier.lireInt(); 
						dateUser = new DateUser(jour,mois,annee);
						
						while(!dateUser.dateCorrecte()){
							System.out.println(" date incorrecte entrer la date   ");
							System.out.print("entre l'annee : ");annee= Clavier.lireInt();
							System.out.print("entre le mois : ");mois= Clavier.lireInt();
							System.out.print("entre le jour : ");jour= Clavier.lireInt(); 
							dateUser=new DateUser(jour,mois,annee);
						}
						calendar1.set(dateUser.getAnnee(),dateUser.getMois()-1,dateUser.getJour());
						calendar.setTime(date);
						dateUser1 =new DateUser(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR));
						dateUser1=dateUser1.ajoutJours(30);
						calendar2.set(dateUser1.getAnnee(),dateUser1.getMois()-1,dateUser1.getJour());
						while(calendar2.after(calendar1)){
							System.out.println(" la date d'une balade doit etre la date d'aujourdhui plus au moins 30jours  ");
							System.out.print("entre l'annee : ");annee= Clavier.lireInt();
							System.out.print("entre le mois : ");mois= Clavier.lireInt();
							System.out.print("entre le jour : ");jour= Clavier.lireInt(); 
							dateUser=new DateUser(jour,mois,annee);
							calendar1.set(dateUser.getAnnee(),dateUser.getMois()-1,dateUser.getJour());
						}
						String formatted = format1.format(calendar1.getTime());
						System.out.println();
						System.out.print("entre le lieu de la balade  "); lieuBalade=Clavier.lireString();
						Balade balade = new Balade(lieuBalade,formatted,1);
						dao<Balade> baladeDAO = adf.getBaladeDAO();
						baladeDAO.create(balade);
					}
					else{

						Calendrier calendrier = new Calendrier() ;
						dao<Calendrier> calendrierDao = adf.getCalendrierDAO();
						CatPersonne catPers = new CatPersonne() ;
						dao<CatPersonne> catPersrDao = adf.getCatPersonneDAO();
						
						calendrier=calendrierDao.baladeCategorie(1);
						calendrier.afficheCalandrier();
						calendrier.getListBalade();
						int k1=0;
						Balade[] tabData2  = new Balade[calendrier.getListBalade().size()];
						for (Balade balade : calendrier.getListBalade()){
							tabData2[k1++]=balade;
							balade.afficheRecapBal(catPersrDao.find(1).getListMembreCat());
						}
						
						
					}
				}
			}
			else if((membre.getStatut().compareTo("cat_vtt_rand"))==0){}
			else if((membre.getStatut().compareTo("cat_vtt_trial"))==0){}
			else if((membre.getStatut().compareTo("cat_vtt_desc"))==0){}
			else
			{System.out.println(membre.getStatut());System.exit(0);}
		}
		else{
			FonctionUtile.ISMmenbre(membre);
		}
		/*System.out.println("CONNECTE VOUS COMME  : ");
		//System.out.println("1 . Membre");
		//System.out.println("2 . Responsable");
		//System.out.println("3 . Tresorier");
		//System.out.print("entrez votre choix : ");
		//n = Clavier.lireInt();
		//System.out.print("votre choix : " + n );
		//System.out.println("");
		while(n<1 || n>3){
			System.out.print("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 3 : ");
			n = Clavier.lireInt();
		}
		if(n==1){
			//affiche le calendrier et choisir sa balade et reserve une place dans une voiture disponible
		}
		else if (n==2){
			// choix responsabilite de la categorie,
		}
		else{
			// tache du tresorier
		}*/
	}

	/**********************************************  si c'est un membre qui est connecte ********************************************************/
}
