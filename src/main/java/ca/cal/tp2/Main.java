package ca.cal.tp2;

import ca.cal.tp2.Repository.*;
import ca.cal.tp2.Service.*;
import ca.cal.tp2.dto.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialisation des repositories
            DocumentRepository documentRepo = new DocumentRepositoryJPA();
            EmprunteurRepository emprunteurRepo = new EmprunteurRepositoryJPA();
            EmpruntRepository empruntRepo = new EmpruntRepositoryJPA();

            // Initialisation des services
            DocumentService documentService = new DocumentService(documentRepo);
            EmprunteurService emprunteurService = new EmprunteurService(emprunteurRepo);
            EmpruntService empruntService = new EmpruntService(empruntRepo, emprunteurRepo, documentRepo);

            System.out.println("===== Démarrage du test de la bibliothèque =====");

            // 1. Enregistrement d'un nouvel emprunteur
            System.out.println("\n----- Enregistrement d'un nouvel emprunteur -----");
            EmprunteurDTO emprunteur = new EmprunteurDTO();
            emprunteur.setName("Jean Dupont");
            emprunteur.setEmail("jean.dupont@example.com");
            emprunteur.setPhoneNumber("514-123-4567");

            EmprunteurDTO savedEmprunteur = emprunteurService.enregistrerEmprunteur(emprunteur);
            System.out.println("Nouvel emprunteur enregistré: " + savedEmprunteur.getName());
            System.out.println("ID: " + savedEmprunteur.getUserId());
            System.out.println("Email: " + savedEmprunteur.getEmail());

            // 2. Ajout de documents (livres, CD, DVD) avec gestion des exemplaires
            System.out.println("\n----- Ajout de documents à la bibliothèque -----");

            // Ajout d'un livre
            LivreDTO livre1 = new LivreDTO();
            livre1.setTitre("Les parapluies sont disparus");
            livre1.setAuteur("Martin Leclerc");
            livre1.setEditeur("Éditions du Québec");
            livre1.setAnneePublication(2020);
            livre1.setNombrePages(250);
            livre1.setISBN("978-2-12345-678-9");
            livre1.setNombreExemplaires(3); // 3 exemplaires disponibles

            LivreDTO savedLivre1 = documentService.ajouterLivre(livre1);
            System.out.println("Livre ajouté: " + savedLivre1.getTitre());
            System.out.println("Exemplaires disponibles: " + savedLivre1.getNombreDisponibles());

            // Ajout d'un autre livre
            LivreDTO livre2 = new LivreDTO();
            livre2.setTitre("Programmation en Java");
            livre2.setAuteur("Sophie Tremblay");
            livre2.setEditeur("Éditions Techniques");
            livre2.setAnneePublication(2022);
            livre2.setNombrePages(450);
            livre2.setISBN("978-2-98765-432-1");
            livre2.setNombreExemplaires(2); // 2 exemplaires disponibles

            LivreDTO savedLivre2 = documentService.ajouterLivre(livre2);
            System.out.println("Livre ajouté: " + savedLivre2.getTitre());

            // Ajout d'un CD
            CdDTO cd = new CdDTO();
            cd.setTitre("Greatest Hits");
            cd.setArtiste("Michel Lapointe");
            cd.setGenre("Pop");
            cd.setDuree(65);
            cd.setNombreExemplaires(2);

            CdDTO savedCd = documentService.ajouterCd(cd);
            System.out.println("CD ajouté: " + savedCd.getTitre() + " par " + savedCd.getArtiste());

            // Ajout d'un DVD
            DvdDTO dvd = new DvdDTO();
            dvd.setTitre("Le grand voyage");
            dvd.setDirector("Pierre Tremblay");
            dvd.setDuree(120);
            dvd.setRating("Tout public");
            dvd.setNombreExemplaires(1);

            DvdDTO savedDvd = documentService.ajouterDvd(dvd);
            System.out.println("DVD ajouté: " + savedDvd.getTitre() + " réalisé par " + savedDvd.getDirector());

            // 3. Recherche de documents selon différents critères
            System.out.println("\n----- Recherche de documents -----");

            // Recherche par titre partiel
            System.out.println("\nRecherche de livres contenant 'parapluies':");
            List<LivreDTO> livresParTitre = documentService.rechercherLivresParTitre("parapluies");
            for (LivreDTO livre : livresParTitre) {
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur() + " (" + livre.getAnneePublication() + ")");
            }

            // Recherche par auteur
            System.out.println("\nRecherche de livres de l'auteur 'Tremblay':");
            List<LivreDTO> livresParAuteur = documentService.rechercherLivresParAuteur("Tremblay");
            for (LivreDTO livre : livresParAuteur) {
                System.out.println("- " + livre.getTitre() + " (" + livre.getAnneePublication() + ")");
            }

            // Recherche par année
            System.out.println("\nRecherche de livres publiés en 2022:");
            List<LivreDTO> livresParAnnee = documentService.rechercherLivresParAnnee(2022);
            for (LivreDTO livre : livresParAnnee) {
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }

            // Recherche de CD par artiste
            System.out.println("\nRecherche de CDs de l'artiste 'Lapointe':");
            List<CdDTO> cdsParArtiste = documentService.rechercherCdsParArtiste("Lapointe");
            for (CdDTO cdItem : cdsParArtiste) {
                System.out.println("- " + cdItem.getTitre() + " (" + cdItem.getGenre() + ")");
            }

            // 4. Emprunt de documents
            System.out.println("\n----- Emprunt de documents -----");

            // Emprunt d'un livre
            System.out.println("\nEmprunt du livre: " + savedLivre1.getTitre());

            try {
                EmpruntDTO emprunt1 = empruntService.emprunterDocument(savedEmprunteur.getUserId(), savedLivre1.getDocumentId());
                System.out.println("Emprunt réussi. ID: " + emprunt1.getEmpruntId());
                System.out.println("Date d'emprunt: " + emprunt1.getDateEmprunt());
                System.out.println("Documents empruntés:");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                for (EmpruntDetailDTO detail : emprunt1.getDetails()) {
                    System.out.println("- " + detail.getDocumentTitre());
                    System.out.println("  Date de retour prévue: " + detail.getDateRetourPrevue().format(formatter));
                }
            } catch (Exception e) {
                System.out.println("Erreur lors de l'emprunt: " + e.getMessage());
            }

            // Vérifier le nombre d'exemplaires disponibles après l'emprunt
            boolean disponible = documentService.verifierDisponibilite(savedLivre1.getDocumentId());
            System.out.println("\nLe livre '" + savedLivre1.getTitre() + "' est-il encore disponible? " + disponible);

            // Emprunt d'un CD
            System.out.println("\nEmprunt du CD: " + savedCd.getTitre());

            try {
                EmpruntDTO emprunt2 = empruntService.emprunterDocument(savedEmprunteur.getUserId(), savedCd.getDocumentId());
                System.out.println("Emprunt réussi. ID: " + emprunt2.getEmpruntId());
            } catch (Exception e) {
                System.out.println("Erreur lors de l'emprunt: " + e.getMessage());
            }

            // 5. Liste des emprunts d'un client
            System.out.println("\n----- Liste des emprunts du client -----");

            List<EmpruntDTO> emprunts = empruntService.obtenirEmpruntsParEmprunteur(savedEmprunteur.getUserId());

            System.out.println("\nEmprunts de " + savedEmprunteur.getName() + ":");
            for (EmpruntDTO emprunt : emprunts) {
                System.out.println("Emprunt #" + emprunt.getEmpruntId() + " du " + emprunt.getDateEmprunt());
                System.out.println("Documents empruntés:");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                for (EmpruntDetailDTO detail : emprunt.getDetails()) {
                    System.out.println("- " + detail.getDocumentTitre());
                    System.out.println("  Date de retour prévue: " + detail.getDateRetourPrevue().format(formatter));

                    // Afficher la date de retour actuelle si elle existe
                    if (detail.getDateRetourActuelle() != null) {
                        System.out.println("  Date de retour actuelle: " + detail.getDateRetourActuelle().format(formatter));
                    }

                    System.out.println("  Statut: " + detail.getStatus());
                }
                System.out.println();
            }

            System.out.println("===== Fin du test de la bibliothèque =====");

        } catch (Exception e) {
            System.err.println("Une erreur est survenue: " + e.getMessage());
            e.printStackTrace();
        }
    }
}