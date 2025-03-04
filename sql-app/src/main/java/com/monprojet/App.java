package com.monprojet;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Connexion link = new Connexion();
        GestionUtilisateurs gu = new GestionUtilisateurs(link);
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1 - Lister les utilisateurs");
            System.out.println("2 - Ajouter un utilisateur");
            System.out.println("3 - Supprimer un utilisateur");
            System.out.println("4 - Modifier un utilisateur");
            System.out.println("5 - Rechercher un utilisateur");
            System.out.println("0 - Quitter");
            choice = sc.nextInt();
            sc.nextLine(); // Nettoyage buffer

            switch (choice) {
                case 1:
                    gu.listUtilisateurs();
                    break;
                case 2:
                    System.out.print("Nom: ");
                    String nom = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    Utilisateur utilisateur = new Utilisateur(nom, email);
                    gu.addUtilisateurs(utilisateur);
                    break;
                case 3:
                    System.out.print("ID de l'utilisateur à supprimer: ");
                    int idSuppr = sc.nextInt();
                    gu.deleteUtilisateur(idSuppr);
                    break;
                case 4:
                    System.out.print("ID de l'utilisateur à modifier: ");
                    int idEdit = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nouveau nom: ");
                    String newNom = sc.nextLine();
                    System.out.print("Nouvel email: ");
                    String newEmail = sc.nextLine();
                    gu.editUtilisateur(idEdit, newNom, newEmail);
                    break;
                case 5:
                    System.out.print("Nom ou email à rechercher: ");
                    String searchTerm = sc.nextLine();
                    gu.searchUtilisateur(searchTerm);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        } while (choice != 0);

        link.close();
        sc.close();
    }
}
