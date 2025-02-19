package com.monprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
       System.out.println("Hello word");
       connexion link = new connexion();
       
       
       Scanner sc = new Scanner(System.in);
       int choix = 0;

       do{
        System.out.println("Que voulez vous faire");
        System.out.println("1. Ajouter un utilisateur");
        System.out.println("2. Afficher les utilisateurs");
        System.out.println("3. Mofifier un utilisateurs");
        System.out.println("4. Supprimer un utilisateur");
        System.out.println("5. Quitter");
        System.out.println("Choix :");
        choix = sc.nextInt();
       } while(choix != 0);
       
    
       
       sc.close();
    }
 }

