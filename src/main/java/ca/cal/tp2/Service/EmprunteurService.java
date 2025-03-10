package ca.cal.tp2.Service;

import ca.cal.tp2.Repository.EmprunteurRepository;
import ca.cal.tp2.dto.EmprunteurDTO;
import ca.cal.tp2.modele.Emprunteur;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmprunteurService {
    private final EmprunteurRepository emprunteurRepository;

    public EmprunteurService(EmprunteurRepository emprunteurRepository) {
        this.emprunteurRepository = emprunteurRepository;
    }

    public EmprunteurDTO enregistrerEmprunteur(EmprunteurDTO emprunteurDTO) {
        Emprunteur emprunteur = new Emprunteur();
        emprunteur.setName(emprunteurDTO.getName());
        emprunteur.setEmail(emprunteurDTO.getEmail());
        emprunteur.setPhoneNumber(emprunteurDTO.getPhoneNumber());

        Emprunteur savedEmprunteur = emprunteurRepository.save(emprunteur);

        emprunteurDTO.setUserId(savedEmprunteur.getUserId());
        return emprunteurDTO;
    }

    public Optional<EmprunteurDTO> obtenirEmprunteur(int id) {
        Optional<Emprunteur> emprunteur = emprunteurRepository.findById(id);
        return emprunteur.map(this::convertToDTO);
    }

    public List<EmprunteurDTO> obtenirTousLesEmprunteurs() {
        List<Emprunteur> emprunteurs = emprunteurRepository.findAll();
        return emprunteurs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmprunteurDTO convertToDTO(Emprunteur emprunteur) {
        EmprunteurDTO dto = new EmprunteurDTO();
        dto.setUserId(emprunteur.getUserId());
        dto.setName(emprunteur.getName());
        dto.setEmail(emprunteur.getEmail());
        dto.setPhoneNumber(emprunteur.getPhoneNumber());
        return dto;
    }
}