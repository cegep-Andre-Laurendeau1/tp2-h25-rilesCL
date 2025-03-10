package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmpruntDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lineItemID;

    @ManyToOne
    @JoinColumn(name = "documentId")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "empruntId")
    private Emprunt emprunt;

    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourActuelle;
    private String status;

    public boolean isEnRetard() {
        if (dateRetourActuelle == null) {
            return LocalDate.now().isAfter(dateRetourPrevue);
        }
        return dateRetourActuelle.isAfter(dateRetourPrevue);
    }

    public double calculAmende() {
        if (!isEnRetard()) {
            return 0.0;
        }
        LocalDate dateComparaison = (dateRetourActuelle != null) ? dateRetourActuelle : LocalDate.now();
        long joursRetard = java.time.temporal.ChronoUnit.DAYS.between(dateRetourPrevue, dateComparaison);

        return joursRetard * 0.25;
    }

    public void updateStatus() {
        if (dateRetourActuelle != null) {
            this.status = "Retourné";
        } else if (isEnRetard()) {
            this.status = "En retard";
        } else {
            this.status = "En cours";
        }
    }
}
