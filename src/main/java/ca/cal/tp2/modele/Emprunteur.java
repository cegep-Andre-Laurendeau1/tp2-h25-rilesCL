package ca.cal.tp2.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Emprunteur extends Utilisateur {
    @OneToMany(mappedBy = "emprunteur")
    private List<Emprunt> emprunts = new ArrayList<>();

    public void emprunte(Document document) {
        if (document != null && document.verifieDisponibilite()) {
            Emprunt nouvelEmprunt = new Emprunt();
            nouvelEmprunt.setDateEmprunt(LocalDate.now());
            nouvelEmprunt.setStatus("EN_COURS");
            nouvelEmprunt.setEmprunteur(this);
            emprunts.add(nouvelEmprunt);
            document.emprunter();
        }
    }
}
