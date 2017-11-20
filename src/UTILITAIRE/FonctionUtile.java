package UTILITAIRE;

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
				tabData2[k1++]=balade.getId();
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
					System.out.println(" merci pour votre visite ");
					System.exit(0);
				}
				System.out.println(" Voici la liste des Categories auxquelles vous avez deja souscrit ");
				membre.afficheListCategorie();
				System.out.println();
				System.out.println(" voici celle dont vous n'avez pas souscrit : ");
				tabData=membre.afficheCatAsouscrir();
				System.out.println();
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
