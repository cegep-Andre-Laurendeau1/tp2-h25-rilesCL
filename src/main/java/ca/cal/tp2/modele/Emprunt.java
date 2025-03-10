package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Emprunt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowID;

    @ManyToOne
    private Emprunteur emprunteur;

    @OneToMany(mappedBy = "emprunt")
    private List<EmpruntDetail> empruntDetails = new ArrayList<>();

    private LocalDate dateEmprunt;
    private String status;

    public List<EmpruntDetail> getItems() {
        return empruntDetails;
    }
}
