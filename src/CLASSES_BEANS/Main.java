package CLASSES_BEANS;

import UTILITAIRE.*;


public class Main {
	public static void main(String[] args) {
		int choix2=0;
		System.out.println("Bienvenue");
		System.out.println("1\t se connecter ");
		System.out.println("2\t s'inscrire ");
		System.out.print(" votre choix : ");choix2=Clavier.lireInt();
		while(choix2<1 || choix2>2){
			System.out.println("VOTRE NOMBRE EST INCORRECTE VEUILLEZ ENTRE UN NOMBRE COMPRIS ENTRE 1 ET 2 ");
			choix2 = Clavier.lireInt();
		}
		if(choix2==1){
			FonctionUtile.seConnecter();
		}
		else{
			FonctionUtile.inscription();
		}
	}
}
