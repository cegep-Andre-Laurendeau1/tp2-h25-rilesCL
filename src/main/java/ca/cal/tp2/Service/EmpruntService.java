package ca.cal.tp2.Service;

import ca.cal.tp2.Repository.DocumentRepository;
import ca.cal.tp2.Repository.EmpruntRepository;
import ca.cal.tp2.Repository.EmprunteurRepository;
import ca.cal.tp2.dto.EmpruntDTO;
import ca.cal.tp2.dto.EmpruntDetailDTO;
import ca.cal.tp2.modele.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmpruntService {
    private final EmpruntRepository empruntRepository;
    private final EmprunteurRepository emprunteurRepository;
    private final DocumentRepository documentRepository;

    public EmpruntService(EmpruntRepository empruntRepository,
                          EmprunteurRepository emprunteurRepository,
                          DocumentRepository documentRepository) {
        this.empruntRepository = empruntRepository;
        this.emprunteurRepository = emprunteurRepository;
        this.documentRepository = documentRepository;
    }

    // Emprunter un document
    public EmpruntDTO emprunterDocument(int emprunteurId, int documentId) {
        // Vérifier si l'emprunteur existe
        Optional<Emprunteur> emprunteurOpt = emprunteurRepository.findById(emprunteurId);
        if (emprunteurOpt.isEmpty()) {
            throw new IllegalArgumentException("Emprunteur non trouvé");
        }

        // Vérifier si le document existe
        Optional<Document> documentOpt = documentRepository.findById(documentId);
        if (documentOpt.isEmpty()) {
            throw new IllegalArgumentException("Document non trouvé");
        }

        Emprunteur emprunteur = emprunteurOpt.get();
        Document document = documentOpt.get();

        // Vérifier si le document est disponible
        if (!document.verifieDisponibilite()) {
            throw new IllegalStateException("Document non disponible");
        }

        // Créer un nouvel emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setEmprunteur(emprunteur);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setStatus("EN_COURS");

        Emprunt savedEmprunt = empruntRepository.save(emprunt);

        // Créer un détail d'emprunt
        EmpruntDetail detail = new EmpruntDetail();
        detail.setEmprunt(savedEmprunt);
        detail.setDocument(document);
        detail.setStatus("EN_COURS");

        // Calculer la date de retour prévue selon le type de document
        LocalDate dateRetourPrevue;
        if (document instanceof Livre) {
            dateRetourPrevue = LocalDate.now().plusWeeks(3); // 3 semaines pour les livres
        } else if (document instanceof Cd) {
            dateRetourPrevue = LocalDate.now().plusWeeks(2); // 2 semaines pour les CD
        } else {
            dateRetourPrevue = LocalDate.now().plusWeeks(1); // 1 semaine pour les DVD
        }
        detail.setDateRetourPrevue(dateRetourPrevue);

        // Ajouter le détail à l'emprunt
        savedEmprunt.getEmpruntDetails().add(detail);

        // Mettre à jour la disponibilité du document
        document.emprunter();
        documentRepository.update(document);

        // Mettre à jour l'emprunt
        empruntRepository.update(savedEmprunt);

        // Convertir en DTO
        return convertToDTO(savedEmprunt);
    }

    // Obtenir tous les emprunts d'un emprunteur
    public List<EmpruntDTO> obtenirEmpruntsParEmprunteur(int emprunteurId) {
        List<Emprunt> emprunts = empruntRepository.findByEmprunteurId(emprunteurId);
        return emprunts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Méthodes utilitaires pour la conversion entité <-> DTO

    private EmpruntDTO convertToDTO(Emprunt emprunt) {
        EmpruntDTO dto = new EmpruntDTO();
        dto.setEmpruntId(emprunt.getBorrowID());
        dto.setEmprunteurId(emprunt.getEmprunteur().getUserId());
        dto.setEmprunteurNom(emprunt.getEmprunteur().getName());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setStatus(emprunt.getStatus());

        List<EmpruntDetailDTO> detailDTOs = new ArrayList<>();
        for (EmpruntDetail detail : emprunt.getEmpruntDetails()) {
            EmpruntDetailDTO detailDTO = new EmpruntDetailDTO();
            detailDTO.setDetailId(detail.getLineItemID());
            detailDTO.setEmpruntId(emprunt.getBorrowID());
            detailDTO.setDocumentId(detail.getDocument().getDocumentId());
            detailDTO.setDocumentTitre(detail.getDocument().getTitre());
            detailDTO.setDateRetourPrevue(detail.getDateRetourPrevue());
            detailDTO.setDateRetourActuelle(detail.getDateRetourActuelle());
            detailDTO.setStatus(detail.getStatus());

            detailDTOs.add(detailDTO);
        }

        dto.setDetails(detailDTOs);
        return dto;
    }
}