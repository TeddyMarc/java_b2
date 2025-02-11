package Liste_etudiant;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
     private ArrayList<Etudiant> ListE = new ArrayList<Etudiant>();

     public void Menu(){

        int menu = 0;
        Scanner sc = new Scanner(System.in);
        do{
        System.out.println("Que voulez vous faire ?");
        System.out.println("1 - Voir la liste ");
        System.out.println("2 - Ajouter un etudiant");
        System.out.println("3 - Supprimer un etudiant");
        System.out.println("4 - Quitter");
        int cmd = sc.nextInt( );
        System.out.println("Vous voulez : " + cmd);
        } while( menu != 0);
        sc.close();
     }

     public static void main(String[] args) {
        System.out.println("Demarrage ecole ");
     }
}
