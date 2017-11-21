package UTILITAIRE;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import CLASSES_BEANS.*;

import DAO.dao;
import DAOFACTORY.AbstractDAOFactory;

public class FonctionUtile {
	
	public static Boolean testValeur(int[] tab,int nbre){
		Boolean etat =true;
		int i=0;
		while(i<tab.length && etat==true){
			if(tab[i]==nbre){
				etat=false;
			}
			i++;
		}
		return etat;
	}
	public static void ISMmenbre(Membre membre){

		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	 	int numBalade;
		int numVoiture;
		int numPers=membre.getId();
		int totalresaV=0;
		int totalresaP=0;
		int n=0;
		int nbrplace=0;
		int nbrevelo=0;
		int numCategorie=0; 
		Calendrier calendrier = new Calendrier() ;
		dao<Calendrier> calendrierDao = adf.getCalendrierDAO();
		calendrier=calendrierDao.find(membre.getId());
		if(calendrier==null)
			System.out.println("vous n'avez aucune balade programme");
		else{
			int k1=0;
			int[] tabData2  = new int[calendrier.getListBalade().size()];
			System.out.println("\n********************************************************************************************************");
			System.out.println("\n******************         liste des balades auxquelles vous pouvez participer       ********************");
			System.out.println("\n********************************************************************************************************");
			for(Balade balade : calendrier.getListBalade()){
				tabData2[k1]=balade.getId();
				k1++;
			}
			 calendrier.afficheCalandrier();
			 System.out.println("\n********************************************************************************************************");
			 System.out.println();
			 System.out.println("\n Choisir le numero d'une balade ");numBalade = Clavier.lireInt();

			while(FonctionUtile.testValeur(tabData2, numBalade)){
				System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE CORRESPONDANT A UN NOMBRE CI-DESSUS");
				numBalade = Clavier.lireInt();
			}
			System.out.println("****************************  Memu  ************************************************ ");
			System.out.println(" que voulez vous faire ? ");
			System.out.println(" 1\t faire une reservation");
			System.out.println(" 2\t poste des disponibilites");
			System.out.println(" 3\t souscrire a une autre categorie");
			System.out.println("votre choix :");n = Clavier.lireInt();
			while(n<1 || n>3){
				System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 3");
				n = Clavier.lireInt();
			}
			if(n==1){
				dao<Voiture> voitureDao2 = adf.getVoitureDAO();
				if(voitureDao2.EstDejaChauffeur(numPers,numBalade)==null){
					int k=0;
					Balade balade = new Balade();
					dao<Balade> baladeDAO = adf.getBaladeDAO();
					balade=baladeDAO.find(numBalade);
					int[] tabData1  = new int[balade.getListVoiture().size()];
					for(Voiture voiture : balade.getListVoiture())
						tabData1[k++]=voiture.getId_balade();
					balade.afficheListVoiture();
					 System.out.println(); System.out.println();
					 System.out.print("choisir un numero correspondant a votre voiture : ");numVoiture = Clavier.lireInt();
					 System.out.println();
					while(FonctionUtile.testValeur(tabData1, numVoiture)){
						System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE CORRESPONDANT A UN NOMBRE CI-DESSUS");
						numVoiture = Clavier.lireInt();
					}
					Voiture voiture = new Voiture();
					dao<Voiture> voitureDao = adf.getVoitureDAO();
					voiture=voitureDao.find(numVoiture);
					for (Passage passage :voiture.getListPassage()){
						totalresaP+= passage.getNbrPlaceresa();
						totalresaV+= passage.getNbrveloresa();
					}
					if(totalresaP<voiture.getNbres_pers_max()){
						 System.out.println("combien de place reservez vous sachant que dans cette voiture il ne reste plus que : "+(voiture.getNbres_pers_max()-totalresaP)+
								 "place (s) personne(s)");nbrplace= Clavier.lireInt();
							while(nbrplace<0 || nbrplace>voiture.getNbres_pers_max()-totalresaP){
								System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET  "+(voiture.getNbres_pers_max()-totalresaP));
								nbrplace = Clavier.lireInt();
							}
						 
					 }
					
						
						if(totalresaV<voiture.getNbres_velo_max()){
							 System.out.println("combien de place reservez vous sachant que dans cette voiture il ne reste plus que : "+(voiture.getNbres_velo_max()-totalresaV)+
									 "place (s) velo (s)"); nbrevelo=Clavier.lireInt();
								while(nbrevelo<0 || nbrevelo>voiture.getNbres_velo_max()-totalresaV){
									System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET  "+(voiture.getNbres_velo_max()-totalresaV));
									nbrevelo = Clavier.lireInt();
								}
						 }
						else
							 System.out.println("desole dans cette voiture ya plus de place velo       ");
						if(nbrplace==0 && nbrevelo==0){
							System.out.println("desole aucune reservation n'a ete faite ");
							System.exit(0);
						}
						else{
							int[] tabData  = {numPers,numVoiture,nbrplace,nbrevelo};
							Passage passage = new Passage();
							passage.setTabData(tabData);
							dao<Passage> passageDao = adf.getPassageDAO();
							passageDao.create(passage);
						}
					}
					else{
						System.out.println("desole vous etez deja chauffeur pour cette balade ");
					}
			}
			else if(n==2){
				dao<Voiture> voitureDao = adf.getVoitureDAO();
				if(voitureDao.EstDejaChauffeur(numPers,numBalade)==null){
					 System.out.println("Combien de places personnes vous mettez a dispisition des autres membres : ");nbrevelo = Clavier.lireInt();
					 System.out.println("Combien de places velos vous mettez a dispisition des autres membres : ");nbrplace = Clavier.lireInt();
					Voiture voiture = new Voiture(nbrplace,nbrevelo,numPers,numBalade);
					dao<Voiture> voitureDao1 = adf.getVoitureDAO();
					voitureDao1.create(voiture);
				}
				else{
					System.out.println("desole vous etez deja chauffeur pour cette balade ");
					
				}
			}
			else{
				
				int[] tabData  = new int[4-membre.getListCategorie().size()];
				if(membre.getListCategorie().size()==4){
					System.out.println(" vous avez deja souscrit a toute les categories ");
				}
				else{
					System.out.println(" Voici la liste des Categories auxquelles vous avez deja souscrit ");
					membre.afficheListCategorie();
					System.out.println();
					System.out.println(" voici celle dont vous n'avez pas souscrit : ");
					tabData=membre.afficheCatAsouscrir();
					System.out.println(tabData[0]);
					System.out.println(" entre le numero de votre choix : ");numCategorie = Clavier.lireInt();
					while(FonctionUtile.testValeur(tabData,numCategorie)){
						System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE CORRESPONDANT A UN NUMERO CI_DESSUS ");
						nbrevelo = Clavier.lireInt();
					}
					CatPersonne catP = new CatPersonne(numCategorie,numPers);
					dao<CatPersonne> catPDao = adf.getCatPersonneDAO();
					catPDao.create(catP);
				}
			}
		 }	
	}
	
	public static void ISResponsable(Membre membre,int id_cat){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		int choix=0,mois=0, annee=0,jour=0;
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
		System.out.println("2\t responsable de la categorie ");
		System.out.print("votre choix :");choix = Clavier.lireInt();
		while(choix<1 || choix>2){
			System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 2");
			choix = Clavier.lireInt();
		}
		if(choix==1){
			FonctionUtile.ISMmenbre(membre);
		}
		else{
			System.out.println(" que voulez-faire  : ");
			System.out.println(" 1\t ajouter une balade : ");
			System.out.println(" 2\t afficher le recapitulatif des disponibilites ");
			System.out.println(" 3\t affiche le montant a payer par covoitureur   ");
			System.out.print("votre choix : ");choix= Clavier.lireInt();
			while(choix<1 || choix>3){
				System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 3");
				choix = Clavier.lireInt();
			}
			if(choix==1){
				System.out.println(" ***********************************  AJOUT D'UNE BALADE  **************************************************");
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
				Balade balade = new Balade(lieuBalade,formatted,id_cat);
				dao<Balade> baladeDAO = adf.getBaladeDAO();
				baladeDAO.create(balade);
			}
			else if(choix==2){
				System.out.println(" ***********************************  RECAPITULATIF   **************************************************");
				Calendrier calendrier = new Calendrier() ;
				dao<Calendrier> calendrierDao = adf.getCalendrierDAO();
				CatPersonne catPers = new CatPersonne();
				dao<CatPersonne> catPersrDao = adf.getCatPersonneDAO();
				
				calendrier=calendrierDao.baladeCategorie(id_cat);
				//calendrier.afficheCalandrier();
				calendrier.getListBalade();
				int k1=0;
				Balade[] tabData2  = new Balade[calendrier.getListBalade().size()];
				for (Balade balade : calendrier.getListBalade()){
					tabData2[k1++]=balade;
					System.out.println(" numero balade : "+k1+"  le lieu de la balade est  : "+balade.getLieuBalade());
				}
				System.out.println(" entre le numero d'une balade  "); choix=Clavier.lireInt();
				while(choix<1 || choix>k1){
					System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET "+k1);
					choix = Clavier.lireInt();
				}
				System.out.println();
				System.out.println();
				if(k1!=0)
					tabData2[choix-1].afficheRecapBal(catPersrDao.find(id_cat).getListMembreCat());
				else
					System.out.println("desole y a pas de balade ");
			}
			else{
				System.out.println(" ***********************************  CALCUL ET AFFICHARGE FORFAIT  **************************************************");
				int nbrekm=0;float prixunit=0;
				Calendrier calendrier = new Calendrier() ;
				dao<Calendrier> calendrierDao = adf.getCalendrierDAO();
				calendrier=calendrierDao.baladeCategorie(id_cat);
				int k1=0;
				Balade[] tabData2  = new Balade[calendrier.getListBalade().size()];
				for (Balade balade : calendrier.getListBalade()){
					tabData2[k1++]=balade;
					System.out.print(" numero balade :  "+k1+"  le lieu de la balade est  : "+balade.getLieuBalade());
				}
				if(k1!=0){
					System.out.println();
					System.out.print(" entre le numero d'une balade  "); choix=Clavier.lireInt();
					while(choix<1 || choix>k1){
						System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET "+k1);
						choix = Clavier.lireInt();
					}
					System.out.println();
					System.out.println();
					System.out.print("entre le nombre de kilometre pour arriver au lieu de depart de cette balade"); nbrekm=Clavier.lireInt();
					System.out.println();
					System.out.print(" entrer le prix unitaire pour un km  "); prixunit=Clavier.lireFloat();
					System.out.println();
					tabData2[choix-1].afficheForfaitBalade(nbrekm, prixunit);
				}
				else
					System.out.println("desole y a pas de balade ");
			}
		}
	}
}
