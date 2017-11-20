package UTILITAIRE;

public class DateUser implements Cloneable {
	private int jour;
	private int mois;
	private int annee;
	
	//definition des getters et des setters
	
	public int getAnnee(){
		return annee;
	}
	public void setAnnee(int a){
		annee = a;
	}
	
	public int getMois(){
		return mois;
	}
	public void setMois(int m){		
		if (verifMois(m)){
			mois = m;
		}
		else {
			mois = 0;
		}
	}
	
	public int getJour(){
		return jour;
	}
	public void setJour(int j){		
		if (verifJour(j)){
			jour = j;
		}
		else {
			jour = 0;
		}
	}
	
	//constructeur
	
	public DateUser(){}
	public DateUser(int j, int m, int a){
		this.setAnnee(a);
		this.setMois(m);
		this.setJour(j);
	}
	
	public DateUser copie() {
		 DateUser date = new DateUser(jour,mois,annee);
		 return date; 
	}
	
	public String toString(){
		return this.getJour() + "/" + this.getMois() + "/" + this.getAnnee();
	}
	
	public boolean equals(Object obj){
		DateUser d;
		if (obj == null || obj.getClass() != this.getClass()){
			return false;
		}
		else {
			d = (DateUser)obj;
			if(this.getAnnee() == d.getAnnee() &&
					this.getMois() == d.getMois() &&
					this.getJour() == d.getJour()){
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public boolean bissextile(int a)
    {
        return (((a % 4 == 0) && (a % 100 != 0)) || (a % 400 == 0));
    }
	
	public boolean verifMois(int m){
		return (m >= 1 && m <= 12) ? true:false;
	}
	
	public boolean verifJour(int j){
		if (j > 0 && j < 32){
			if (this.getMois() == 2){
				if (this.bissextile(this.getAnnee())){
					if (j < 30){
						return true;
					}
					else{
						return false;
					}
				}
				else{
					if (j < 29){
						return true;
					}
					else{
						return false;
					}
				}
			}
			else if (this.getMois() == 4 || this.getMois() == 6 || this.getMois() == 9 || this.getMois() == 11){
				if (j < 31){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if (j < 32){
					return true;
				}
				else{
					return false;
				}
			}
		}
		else {
			return false;
		}
	}

	public boolean dateCorrecte(){
		return this.verifMois(this.getMois()) && this.verifJour(this.getJour());
	}

	public DateUser ajoutJours(int nbrJ){
		DateUser dateTmp = new DateUser(this.getJour(), this.getMois(), this.getAnnee());
		if (dateTmp.dateCorrecte()){
			dateTmp.jour = dateTmp.getJour() + nbrJ;
			do {
				if (dateTmp.jour > 28){
					if (dateTmp.getMois() == 2){
						if (dateTmp.bissextile(dateTmp.getAnnee())){
							if (dateTmp.jour > 29){
								dateTmp.mois += 1;
								dateTmp.jour -= 29;
							}
						}
						else{
							dateTmp.mois += 1;
							dateTmp.jour -= 28;
						}
					}
					else if (dateTmp.getMois() == 4 || dateTmp.getMois() == 6 || dateTmp.getMois() == 9 || dateTmp.getMois() == 11){
						if (dateTmp.jour > 30){
							dateTmp.mois += 1;
							dateTmp.jour -= 30;
						}
					}
					else {
						if (dateTmp.jour > 31){
							dateTmp.mois += 1;
							dateTmp.jour -= 31;
						}
					}
				}
				if (dateTmp.mois > 12){
					dateTmp.setMois(1);
					dateTmp.setAnnee(dateTmp.getAnnee() + 1);
				}
			}while (!dateTmp.verifMois(dateTmp.getMois()) || !dateTmp.verifJour(dateTmp.getJour()));
		}
		else {
			System.out.println("ajout de jour impossible car dateEntrée incorrecte");
		}
		
		return dateTmp;
	}
	
	public int nbrJourEntre(DateUser d) throws CloneNotSupportedException{
		int nbrJ = 0;
	    Object dateTmp1 = new DateUser();
		DateUser dateTmp2 = new DateUser();
		if (this.dateCorrecte() && d.dateCorrecte()){
			if (this.getAnnee() > d.getAnnee()){
				dateTmp1 = this.clone();
				dateTmp2 = d;
			}
			else if (this.getAnnee() < d.getAnnee()){
				dateTmp1 = d.clone();
				dateTmp2 = this;
			}
			else{
				if (this.getMois() > d.getMois()){
					dateTmp1 = this.clone();
					dateTmp2 = d;
				}
				else if (this.getMois() < d.getMois()){
					dateTmp1 = d.clone();
					dateTmp2 = this;
				}
				else {
					if (this.getJour() > d.getJour()){
						dateTmp1 = this.clone();
						dateTmp2 = d;
					}
					else if (this.getJour() < d.getJour()){
						dateTmp1 = d.clone();
						dateTmp2 = this;
					}
				}
			}
			while(!dateTmp2.equals(dateTmp1)){
				dateTmp2=dateTmp2.ajoutJours(1);
				nbrJ++;
			}
		}
		else {
			System.out.println("impossible de vérifier le nombre de jour car une des 2 dates est incorretce");
		}
		return nbrJ;
	}
}

