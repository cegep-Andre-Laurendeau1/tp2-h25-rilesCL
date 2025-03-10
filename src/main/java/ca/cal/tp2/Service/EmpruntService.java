package ca.cal.tp2.Service;

import ca.cal.tp2.Repository.DocumentRepository;
import ca.cal.tp2.Repository.EmpruntRepository;
import ca.cal.tp2.Repository.EmprunteurRepository;
import ca.cal.tp2.dto.EmpruntDTO;
import ca.cal.tp2.dto.EmpruntDetailDTO;
import ca.cal.tp2.modele.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2-h25-ghilas.pu");



        public EmpruntDTO emprunterDocument(int emprunteurId, int documentId) {
            Optional<Emprunteur> emprunteurOpt = emprunteurRepository.findById(emprunteurId);
            if (emprunteurOpt.isEmpty()) {
                throw new IllegalArgumentException("Emprunteur non trouvé");
            }

            Optional<Document> documentOpt = documentRepository.findById(documentId);
            if (documentOpt.isEmpty()) {
                throw new IllegalArgumentException("Document non trouvé");
            }

            Emprunteur emprunteur = emprunteurOpt.get();
            Document document = documentOpt.get();

            if (!document.verifieDisponibilite()) {
                throw new IllegalStateException("Document non disponible");
            }

            Emprunt emprunt = new Emprunt();
            emprunt.setEmprunteur(emprunteur);
            emprunt.setDateEmprunt(LocalDate.now());
            emprunt.setStatus("EN_COURS");

            Emprunt savedEmprunt = empruntRepository.save(emprunt);

            EmpruntDetail detail = new EmpruntDetail();
            detail.setEmprunt(savedEmprunt);
            detail.setDocument(document);
            detail.setStatus("EN_COURS");

            LocalDate dateRetourPrevue;
            if (document instanceof Livre) {
                dateRetourPrevue = LocalDate.now().plusWeeks(3);
            } else if (document instanceof Cd) {
                dateRetourPrevue = LocalDate.now().plusWeeks(2);
            } else {
                dateRetourPrevue = LocalDate.now().plusWeeks(1);
            }
            detail.setDateRetourPrevue(dateRetourPrevue);

            savedEmprunt.getEmpruntDetails().add(detail);

            document.emprunter();
            documentRepository.update(document);

            //  Sauvegarde explicite le détail d'emprunt
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(detail);  // Persister explicitement l'EmpruntDetail
                em.getTransaction().commit();
            } finally {
                em.close();
            }

            empruntRepository.update(savedEmprunt);

            return convertToDTO(savedEmprunt);
        }


    public List<EmpruntDTO> obtenirEmpruntsParEmprunteur(int emprunteurId) {
        List<Emprunt> emprunts = empruntRepository.findByEmprunteurIdWithDetails(emprunteurId);
        return emprunts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


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