package ca.cal.tp2.modele;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class Document  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    private String titre;
    private int nombreExemplaires;
    private int nombreDisponibles;

    @OneToMany(mappedBy = "document")
    private List<EmpruntDetail> empruntsDetails = new ArrayList<>();

    public boolean verifieDisponibilite() {
        return nombreDisponibles > 0;
    }

    public void emprunter() {
        if (verifieDisponibilite()) {
            nombreDisponibles--;
        }
    }

    public void retourner() {
        if (nombreDisponibles < nombreExemplaires) {
            nombreDisponibles++;
        }
    }

}
