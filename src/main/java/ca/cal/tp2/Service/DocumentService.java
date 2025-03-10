package ca.cal.tp2.Service;

import ca.cal.tp2.Repository.DocumentRepository;
import ca.cal.tp2.dto.CdDTO;
import ca.cal.tp2.dto.DvdDTO;
import ca.cal.tp2.dto.LivreDTO;
import ca.cal.tp2.modele.Cd;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Dvd;
import ca.cal.tp2.modele.Livre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    // Méthodes pour ajouter des documents

    public LivreDTO ajouterLivre(LivreDTO livreDTO) {
        Livre livre = new Livre();
        livre.setTitre(livreDTO.getTitre());
        livre.setNombreExemplaires(livreDTO.getNombreExemplaires());
        livre.setNombreDisponibles(livreDTO.getNombreExemplaires()); // Au début, tous les exemplaires sont disponibles
        livre.setISBN(livreDTO.getISBN());
        livre.setAuteur(livreDTO.getAuteur());
        livre.setEditeur(livreDTO.getEditeur());
        livre.setAnneePublication(livreDTO.getAnneePublication());
        livre.setNbPages(livreDTO.getNombrePages());

        Livre savedLivre = (Livre) documentRepository.save(livre);

        // Convertir l'entité sauvegardée en DTO pour la retourner
        livreDTO.setDocumentId(savedLivre.getDocumentId());
        return livreDTO;
    }

    public CdDTO ajouterCd(CdDTO cdDTO) {
        Cd cd = new Cd();
        cd.setTitre(cdDTO.getTitre());
        cd.setNombreExemplaires(cdDTO.getNombreExemplaires());
        cd.setNombreDisponibles(cdDTO.getNombreExemplaires());
        cd.setArtiste(cdDTO.getArtiste());
        cd.setDuree(cdDTO.getDuree());
        cd.setGenre(cdDTO.getGenre());

        Cd savedCd = (Cd) documentRepository.save(cd);

        cdDTO.setDocumentId(savedCd.getDocumentId());
        return cdDTO;
    }

    public DvdDTO ajouterDvd(DvdDTO dvdDTO) {
        Dvd dvd = new Dvd();
        dvd.setTitre(dvdDTO.getTitre());
        dvd.setNombreExemplaires(dvdDTO.getNombreExemplaires());
        dvd.setNombreDisponibles(dvdDTO.getNombreExemplaires());
        dvd.setDirector(dvdDTO.getDirector());
        dvd.setDuree(dvdDTO.getDuree());
        dvd.setRating(dvdDTO.getRating());

        Dvd savedDvd = (Dvd) documentRepository.save(dvd);

        dvdDTO.setDocumentId(savedDvd.getDocumentId());
        return dvdDTO;
    }

    // Méthodes pour rechercher des documents

    public List<LivreDTO> rechercherLivresParTitre(String titrePartiel) {
        List<Document> livres = documentRepository.findAllLivres();

        // Filtrer les livres dont le titre contient la chaîne spécifiée
        return livres.stream()
                .filter(doc -> doc.getTitre().toLowerCase().contains(titrePartiel.toLowerCase()))
                .map(doc -> convertToLivreDTO((Livre) doc))
                .collect(Collectors.toList());
    }

    public List<LivreDTO> rechercherLivresParAuteur(String auteur) {
        List<Document> livres = documentRepository.findLivresByAuteur(auteur);

        return livres.stream()
                .map(doc -> convertToLivreDTO((Livre) doc))
                .collect(Collectors.toList());
    }

    public List<LivreDTO> rechercherLivresParAnnee(int annee) {
        List<Document> livres = documentRepository.findLivresByAnnee(annee);

        return livres.stream()
                .map(doc -> convertToLivreDTO((Livre) doc))
                .collect(Collectors.toList());
    }

    public List<CdDTO> rechercherCdsParArtiste(String artiste) {
        List<Document> cds = documentRepository.findCdsByArtiste(artiste);

        return cds.stream()
                .map(doc -> convertToCdDTO((Cd) doc))
                .collect(Collectors.toList());
    }

    public List<DvdDTO> rechercherDvdsParRealisateur(String realisateur) {
        List<Document> dvds = documentRepository.findDvdsByRealisateur(realisateur);

        return dvds.stream()
                .map(doc -> convertToDvdDTO((Dvd) doc))
                .collect(Collectors.toList());
    }

    // Méthode pour vérifier la disponibilité d'un document

    public boolean verifierDisponibilite(int documentId) {
        Optional<Document> document = documentRepository.findById(documentId);
        return document.map(Document::verifieDisponibilite).orElse(false);
    }

    // Méthodes utilitaires pour convertir les entités en DTOs

    private LivreDTO convertToLivreDTO(Livre livre) {
        LivreDTO dto = new LivreDTO();
        dto.setDocumentId(livre.getDocumentId());
        dto.setTitre(livre.getTitre());
        dto.setNombreExemplaires(livre.getNombreExemplaires());
        dto.setNombreDisponibles(livre.getNombreDisponibles());
        dto.setISBN(livre.getISBN());
        dto.setAuteur(livre.getAuteur());
        dto.setEditeur(livre.getEditeur());
        dto.setAnneePublication(livre.getAnneePublication());
        dto.setNombrePages(livre.getNbPages());
        return dto;
    }

    private CdDTO convertToCdDTO(Cd cd) {
        CdDTO dto = new CdDTO();
        dto.setDocumentId(cd.getDocumentId());
        dto.setTitre(cd.getTitre());
        dto.setNombreExemplaires(cd.getNombreExemplaires());
        dto.setNombreDisponibles(cd.getNombreDisponibles());
        dto.setArtiste(cd.getArtiste());
        dto.setDuree(cd.getDuree());
        dto.setGenre(cd.getGenre());
        return dto;
    }

    private DvdDTO convertToDvdDTO(Dvd dvd) {
        DvdDTO dto = new DvdDTO();
        dto.setDocumentId(dvd.getDocumentId());
        dto.setTitre(dvd.getTitre());
        dto.setNombreExemplaires(dvd.getNombreExemplaires());
        dto.setNombreDisponibles(dvd.getNombreDisponibles());
        dto.setDirector(dvd.getDirector());
        dto.setDuree(dvd.getDuree());
        dto.setRating(dvd.getRating());
        return dto;
    }
}