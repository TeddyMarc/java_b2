package com.monprojet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GestionUtilisateurs {

    ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    Connexion link = null;

    public GestionUtilisateurs(Connexion plink) {
        this.link = plink;
    }

    // Lister les utilisateurs
    public void listUtilisateurs() {
        try {
            Statement stmt = this.link.connexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nom, email FROM utilisateurs");

            System.out.println("Liste des utilisateurs:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Nom: " + nom + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    // Ajouter un utilisateur
    public void addUtilisateurs(Utilisateur utilisateur) {
        try {
            if (utilisateur.isValidNom()) {
                String sqlInsert = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
                PreparedStatement pstmt = this.link.connexion.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, utilisateur.getNom());
                pstmt.setString(2, utilisateur.getEmail());
                pstmt.executeUpdate();

                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    utilisateur.setId(generatedKeys.getInt(1)); // Récupérer l'ID généré
                }

                this.utilisateurs.add(utilisateur);
                System.out.println("Utilisateur ajouté avec succès.");
            } else {
                System.out.println("Nom invalide !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    // Supprimer un utilisateur par ID
    public void deleteUtilisateur(int id) {
        try {
            String sqlDelete = "DELETE FROM utilisateurs WHERE id = ?";
            PreparedStatement pstmt = this.link.connexion.prepareStatement(sqlDelete);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Utilisateur supprimé avec succès.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    // Modifier un utilisateur par ID
    public void editUtilisateur(int id, String nom, String email) {
        try {
            String sqlUpdate = "UPDATE utilisateurs SET nom = ?, email = ? WHERE id = ?";
            PreparedStatement pstmt = this.link.connexion.prepareStatement(sqlUpdate);
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Utilisateur mis à jour avec succès.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    // Rechercher un utilisateur par nom ou email
    public void searchUtilisateur(String terme) {
        try {
            String sqlSearch = "SELECT * FROM utilisateurs WHERE nom LIKE ? OR email LIKE ?";
            PreparedStatement pstmt = this.link.connexion.prepareStatement(sqlSearch);
            pstmt.setString(1, "%" + terme + "%");
            pstmt.setString(2, "%" + terme + "%");
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Résultats de la recherche:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Nom: " + nom + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

            
            

            
    }

