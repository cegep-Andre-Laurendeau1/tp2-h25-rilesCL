package ca.cal.tp2.Service;

import ca.cal.tp2.Repository.PreposeRepository;
import ca.cal.tp2.modele.Prepose;

import java.util.List;
import java.util.Optional;

public class PreposeService {
    private final PreposeRepository preposeRepository;

    public PreposeService(PreposeRepository preposeRepository) {
        this.preposeRepository = preposeRepository;
    }

    public Prepose creerPrepose(String name, String email, String phoneNumber) {
        Prepose nouveauPrepose = new Prepose();
        nouveauPrepose.setName(name);
        nouveauPrepose.setEmail(email);
        nouveauPrepose.setPhoneNumber(phoneNumber);
        return preposeRepository.save(nouveauPrepose);
    }

    public Optional<Prepose> obtenirPrepose(int id) {
        return preposeRepository.findById(id);
    }

    public List<Prepose> obtenirTousLesPreposés() {
        return preposeRepository.findAll();
    }

    public Prepose mettreAJourPrepose(Prepose prepose) {
        return preposeRepository.update(prepose);
    }

    public void supprimerPrepose(int id) {
        preposeRepository.deleteById(id);
    }
}