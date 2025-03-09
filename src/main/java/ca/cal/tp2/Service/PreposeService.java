package ca.cal.tp2.Service;

import ca.cal.tp2.Repository.PreposeRepository;
import ca.cal.tp2.modele.Prepose;

import java.util.List;
import java.util.Optional;

public class PreposeService {
    private final PreposeRepository preposeRepository;

    // Injection de dépendance via le constructeur
    public PreposeService(PreposeRepository preposeRepository) {
        this.preposeRepository = preposeRepository;
    }

    // CREATE
    public Prepose creerPrepose(String name, String email, String phoneNumber) {
        Prepose nouveauPrepose = new Prepose();
        nouveauPrepose.setName(name);
        nouveauPrepose.setEmail(email);
        nouveauPrepose.setPhoneNumber(phoneNumber);
        return preposeRepository.save(nouveauPrepose);
    }

    // READ
    public Optional<Prepose> obtenirPrepose(int id) {
        return preposeRepository.findById(id);
    }

    // Récupérer tous les préposés
    public List<Prepose> obtenirTousLesPreposés() {
        return preposeRepository.findAll();
    }

    // UPDATE
    public Prepose mettreAJourPrepose(Prepose prepose) {
        return preposeRepository.update(prepose);
    }

    // DELETE
    public void supprimerPrepose(int id) {
        preposeRepository.deleteById(id);
    }
}