package ca.cal.tp2;

import ca.cal.tp2.Repository.DocumentRepository;
import ca.cal.tp2.Repository.DocumentRepositoryJPA;
import ca.cal.tp2.Service.DocumentService;
import ca.cal.tp2.Service.EmpruntService;
import ca.cal.tp2.Service.EmprunteurService;
import ca.cal.tp2.Service.PreposeService;
import ca.cal.tp2.dto.LivreDTO;
import ca.cal.tp2.modele.Emprunteur;
import ca.cal.tp2.modele.Prepose;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {
        DocumentRepository documentRepository = new DocumentRepositoryJPA();
        DocumentService documentService = new DocumentService(documentRepository);


        LivreDTO nouveauLivre = new LivreDTO();
        nouveauLivre.setTitre("Java for Beginners");
        nouveauLivre.setAuteur("John Smith");
        nouveauLivre.setISBN("1234567890");
        nouveauLivre.setNombreExemplaires(5);
        nouveauLivre.setEditeur("Tech Books");
        nouveauLivre.setAnneePublication(2023);
        nouveauLivre.setNombrePages(300);

        LivreDTO livreSauvegarde = documentService.ajouterLivre(nouveauLivre);
        System.out.println("Livre ajouté : " + livreSauvegarde.getTitre() + " (ID: " + livreSauvegarde.getDocumentId() + ")");

    }
}
