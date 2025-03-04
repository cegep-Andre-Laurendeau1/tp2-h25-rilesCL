package ca.cal.tp2.Service;
import ca.cal.tp2.Repository.PreposeRepository;
import ca.cal.tp2.modele.Prepose;

public class PreposeService {
    private final PreposeRepository preposeRepository;

    // Injection de dépendance via le constructeur
    public PreposeService(PreposeRepository preposeRepository) {
        this.preposeRepository = preposeRepository;
    }

    // CREATE
    public Prepose creerPrepose(int userID, String name, String email, String phoneNumber) {
        Prepose nouveauPrepose = new Prepose(userID, name, email, phoneNumber);
        return preposeRepository.save(nouveauPrepose);
    }

    // READ
    public Prepose obtenirPrepose(int id) {
        return preposeRepository.getPrepose(id);
    }
}
