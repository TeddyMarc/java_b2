package Fichier_Produit;
import java.io.*;
import java.util.*;

public class GestionProduits {
    private static final String FICHIER_PRODUITS = "produits.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Afficher les produits");
            System.out.println("2. Ajouter un produit");
            System.out.println("3. Supprimer un produit");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            
            switch (choix) {
                case 1:
                    afficherProduits();
                    break;
                case 2:
                    System.out.print("Nom du produit : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prix du produit : ");
                    double prix = scanner.nextDouble();
                    ajouterProduit(nom, prix);
                    break;
                case 3:
                    System.out.print("ID du produit à supprimer : ");
                    int id = scanner.nextInt();
                    supprimerProduit(id);
                    break;
                case 4:
                    System.out.println("Fermeture de l'application.");
                    return;
                default:
                    System.out.println("Option invalide.");
            }
        }
    }

    private static void afficherProduits() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER_PRODUITS))) {
            String ligne;
            System.out.println("\nListe des produits :");
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier.");
        }
    }

    private static void ajouterProduit(String nom, double prix) {
        int nouvelId = genererNouvelId();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER_PRODUITS, true))) {
            bw.write(nouvelId + "," + nom + "," + prix + "\n");
            System.out.println("Produit ajouté avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'ajout du produit.");
        }
    }

    private static void supprimerProduit(int id) {
        List<String> lignes = new ArrayList<>();
        boolean trouve = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER_PRODUITS))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parts = ligne.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    lignes.add(ligne);
                } else {
                    trouve = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier.");
            return;
        }

        if (!trouve) {
            System.out.println("Produit introuvable.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER_PRODUITS))) {
            for (String l : lignes) {
                bw.write(l + "\n");
            }
            System.out.println("Produit supprimé avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la suppression du produit.");
        }
    }

    private static int genererNouvelId() {
        int maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER_PRODUITS))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parts = ligne.split(",");
                int id = Integer.parseInt(parts[0]);
                if (id > maxId) {
                    maxId = id;
                }
            }
        } catch (IOException e) {
            System.out.println("Création du fichier produits.");
        }
        return maxId + 1;
    }
}


