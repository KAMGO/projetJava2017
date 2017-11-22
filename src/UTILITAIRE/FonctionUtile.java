package UTILITAIRE;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import CLASSES_BEANS.*;

import DAO.dao;
import DAOFACTORY.AbstractDAOFactory;

public class FonctionUtile {
	
	public static Boolean testValeur(int[] tab,int nbre){
		Boolean etat =true;
		int i=0;
		while(i<tab.length && etat){
			if(tab[i]==nbre){
				etat=false;
			}
			i++;
		}
		return etat;
	}
	public static void ISMmenbre(Membre membre){

		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	 	int numBalade, numVoiture, numPers=membre.getId(), totalresaV=0, totalresaP=0, n=0;
		int nbrplace=0, nbrevelo=0, numCategorie=0; 
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
			 System.out.print("\n Choisir le numero d'une balade ");numBalade = Clavier.lireInt();System.out.println();

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
					if(balade.getListVoiture().size()==0){
						System.out.println("Cette balade n'a pas encore de voiture ");
					}
					else{
						int[] tabData1  = new int[balade.getListVoiture().size()];
						for(Voiture voiture : balade.getListVoiture())
							tabData1[k++]=voiture.getId();
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
	    
		System.out.println("voulez-vous continuez comme  membre ou comme responsable de votre categorie  ");
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
				dao<CatPersonne> catPersrDao = adf.getCatPersonneDAO();
				
				calendrier=calendrierDao.baladeCategorie(id_cat);
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
				System.out.println(" ***********************************  CALCUL ET AFFICHARGE FORFAIT  ******************************************");
				int nbrekm=0;float prixunit=0;
				Calendrier calendrier = new Calendrier() ;
				dao<Calendrier> calendrierDao = adf.getCalendrierDAO();
				calendrier=calendrierDao.baladeCategorie(id_cat);
				int k1=0;
				Balade[] tabData2  = new Balade[calendrier.getListBalade().size()];
				for (Balade balade : calendrier.getListBalade()){
					tabData2[k1++]=balade;
					System.out.println(" numero balade :  "+k1+"  le lieu de la balade est  : "+balade.getLieuBalade());
				}
				if(k1!=0){
					System.out.println();
					System.out.print(" entre le numero d'une balade : "); choix=Clavier.lireInt();
					System.out.println();
					while(choix<1 || choix>k1){
						System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET "+k1);
						choix = Clavier.lireInt();
					}
					System.out.println();
					System.out.println();
					System.out.print("entre le nombre de kilometre pour arriver au lieu de depart de cette balade  : "); nbrekm=Clavier.lireInt();
					System.out.println();
					System.out.print(" entrer le prix au km  : "); prixunit=Clavier.lireFloat();
					System.out.println();
					tabData2[choix-1].afficheForfaitBalade(nbrekm, prixunit);
				}
				else
					System.out.println("desole y a pas de balade ");
			}
		}
	}
	public static void inscription(){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		String nom=null,prenom=null,email=null,password=null;
		int confirme=0;int choix3=0;
		do{
		System.out.println("*********************************************** s'inscrire *****************************************************************");
		System.out.println();
		System.out.print(" entrer votre nom      :  "); nom=Clavier.lireString();System.out.println();
		System.out.print(" entrer votre prenom   :  "); prenom=Clavier.lireString();System.out.println();
		System.out.print(" entrer votre email    :  "); email =Clavier.lireString();System.out.println();
		System.out.print(" entrer votre password :  "); password=Clavier.lireString();System.out.println(); 
		System.out.println();
		System.out.println(" dans quelle categorie voulez-vous inscrire :  ");
		System.out.println();
		System.out.println(" 1\t  categorie Cyclo  ");
		System.out.println(" 2\t  categorie VTT Randonneurs ");
		System.out.println(" 3\t  categorie VTT Trialistes  ");
		System.out.println(" 4\t  categorie VTT Descendeurs ");
		System.out.println(" Votre choix : ");choix3=Clavier.lireInt();
		while(choix3<1 || choix3>4){
			System.out.print("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 4 ");
			choix3 = Clavier.lireInt();System.out.println();
		}
		System.out.println();
		System.out.println("**************      infos saisie        **************************************************");
		System.out.println();
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
    	Membre membre1 =new Membre(nom,prenom,email,password,"membre");
		dao<Membre> membreDao1 = adf.getMembreDAO();
		membre1=membreDao1.connecter(email,password);
		if(membre1==null){
			System.out.println(" pour confirmer entrer 0 et autre nombre pour modifier ");confirme=Clavier.lireInt();
		}
		else{
			System.out.println();
			System.out.println(" il existe deja un membre avec cette email et ce mot de passe veuillez entrer de nouvelle infos ");confirme=1;
			System.out.println();
		}
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
	public static void seConnecter(){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
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
		/***************************************************************************************************************************************/
		/**                                                    se connecter comme tresorier                                                   **/
		/***************************************************************************************************************************************/
		if(membre.getStatut().compareTo("membre")!=0){
			if(membre.getStatut().compareTo("tresorier")==0){
				int sortir=0,choix=0;
				do{
					System.out.println("voulez-vous continuez comme  membre ou tresorier  ");
					System.out.println("1\t membre");
					System.out.println("2\t tresorier ");
					System.out.print("votre choix :");choix = Clavier.lireInt();
					while(choix<1 || choix>2){
						System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 2");
						choix = Clavier.lireInt();
					}
					if(choix==1){
						FonctionUtile.ISMmenbre(membre);
					}
					else{
						FonctionUtile.ISTreserier();
					}
					System.out.println();
					System.out.println(" entrer 0 pour continuer ou un autre nombre pour sortir "); sortir=Clavier.lireInt();
				}while(sortir==0);
			}
			/***************************************************************************************************************************************/
			/**                                                    se connecter comme responsable categorie                                       **/
			/***************************************************************************************************************************************/
			else if((membre.getStatut().compareTo("cat_cyclo"))==0){
				int sortir=0;
				do{
					FonctionUtile.ISResponsable(membre, 1);
					System.out.println();
					System.out.print(" entrer 0 pour continuer ou un autre nombre pour sortir   "); sortir=Clavier.lireInt();System.out.println();
				}while(sortir==0);
			}
			else if((membre.getStatut().compareTo("cat_vtt_rand"))==0){
				int sortir=0;
				do{
					FonctionUtile.ISResponsable(membre, 2);
					System.out.println();
					System.out.print(" entrer 0 pour continuer  ou un autre nombre pour sortir "); sortir=Clavier.lireInt();System.out.println();
			}while(sortir==0);
			}
			else if((membre.getStatut().compareTo("cat_vtt_trial"))==0){
				int sortir=0;
				do{
					FonctionUtile.ISResponsable(membre, 3);
					System.out.println();
					System.out.print(" entrer 0 pour continuer ou un autre nombre pour sortir  "); sortir=Clavier.lireInt();System.out.println();
			}while(sortir==0);
			}
			else if((membre.getStatut().compareTo("cat_vtt_desc"))==0){
				int sortir=0;
				do{
					FonctionUtile.ISResponsable(membre, 4);
					System.out.println();
					System.out.print(" entrer 0 pour continuer ou un autre nombre pour sortir  "); sortir=Clavier.lireInt();System.out.println();
			}while(sortir==0);
			}
			else
			{System.out.println("ya un probleme avec les donnees provenant de DB ");System.exit(0);}
		}
		else{
			/***************************************************************************************************************************************/
			/**                                                    se connecter comme membre                                                      **/
			/***************************************************************************************************************************************/
			int sortir=0;
			do{
				FonctionUtile.ISMmenbre(membre);
				System.out.println();
				System.out.print(" entrer 0 pour continuer  ou un autre nombre pour sortir  "); sortir=Clavier.lireInt();System.out.println();
			}while(sortir==0);
		}
		System.out.println(" aurevoir ");
	}
	public static void ISTreserier(){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		Set<Membre> listAllMembreP = new HashSet<Membre>();
		Set<Membre> listAllMembreN = new HashSet<Membre>();
		Set<Membre> listAllMembrePN = new HashSet<Membre>();
		int count=0,montantApaye=0,choix=0 ,numMembre=0 ,i=0,valeur=0;
    	Tresorier tresorier =new Tresorier();
		dao<Tresorier> TresorierDao = adf.getTresorierDAO();
		tresorier=TresorierDao.find(0);
		int[] tabData1  = new int[tresorier.getListAllMembre().size()];
		for(Membre membre : tresorier.getListAllMembre()){
			count=membre.getListCategorie().size();
			montantApaye=20+(count-1)*5;
			if(membre.getPaye()==montantApaye){
				listAllMembreP.add(membre);
			}
			else if(membre.getPaye()<montantApaye){
				listAllMembreN.add(membre);
			}
			else{
				listAllMembrePN.add(membre);
			}
		}
		System.out.println(" ********************************  Liste des personnes qui ont tout payes **************************************************");
		for(Membre membre : listAllMembreP){
			System.out.println("numero    : "+ membre.getId());
			System.out.println("nom       : "+ membre.getNom());
			System.out.println("prenom    : "+membre.getPrenom());
			count=0;
			System.out.println("categorie :");
			for( Categorie cat : membre.getListCategorie()){
				System.out.println("\t  - "+cat.getNomCategorie());
				count++;
			}
			System.out.println("---------------------------------------------------------------");
		}
		System.out.println(" ********************************  Liste des personnes qui n'ont pas tout payes **************************************************");
	
		for(Membre membre : listAllMembreN){
			tabData1[i]=membre.getId();
			i++;
			System.out.println("numero    : "+ membre.getId());
			System.out.println("nom       : "+ membre.getNom());
			System.out.println("prenom    : "+membre.getPrenom());
			count=0;
			System.out.println("categorie :");
			for( Categorie cat : membre.getListCategorie()){
				System.out.println("\t  - "+cat.getNomCategorie());
				count++;
			}
			montantApaye=20+(count-1)*5;
			System.out.println("reste a payer : "+(montantApaye-membre.getPaye()) + " €");
			System.out.println("---------------------------------------------------------------");
		}
		System.out.println(" ********************************  Liste des personnes qui on un probleme  **************************************************");
		for(Membre membre : listAllMembrePN){
			System.out.println("numero    : "+ membre.getId());
			System.out.println("nom       : "+ membre.getNom());
			System.out.println("prenom    : "+membre.getPrenom());
		
			System.out.println("categorie :");
			for( Categorie cat : membre.getListCategorie()){
				System.out.println("\t  - "+cat.getNomCategorie());
	
			}
			System.out.println("---------------------------------------------------------------");
		}
		
		
		System.out.println();
		System.out.println("**************************************** que voulez faire :***************************************************************");
		System.out.println("1\t faire une mise a jour ");
		System.out.println("2\t quitter  ");
		System.out.print(" votre choix :  ");choix=Clavier.lireInt();System.out.println();
		while(choix<1 || choix>2){
			System.out.print("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 2 ");
			choix = Clavier.lireInt();System.out.println();
		}
		if(choix==1){
			System.out.print(" entre le numero du membre :"); numMembre= Clavier.lireInt();System.out.println();
			while(FonctionUtile.testValeur(tabData1,numMembre)){
				System.out.print(" entre le numero de membre  n'a pas tout paye  :"); numMembre = Clavier.lireInt(); System.out.println();
			}
			System.out.print("entrer la valeur a ajouter : ");valeur = Clavier.lireInt(); System.out.println();
			Membre membre1= new Membre();
			dao<Membre> membreDao = adf.getMembreDAO();
			membre1=membreDao.find(numMembre);
			membreDao.update(new Membre(membre1.getId(),membre1.getNom(),membre1.getPrenom(),membre1.getEmail(),membre1.getPassword(),membre1.getStatut(),membre1.getPaye()+valeur));
		}
		else
			System.exit(0);	
	}
}
