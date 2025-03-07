package ca.cal.tp2.modele;

import jakarta.persistence.*;
import jdk.vm.ci.meta.Local;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
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
    @JoinColumn(name = "emprunteurId")
    private Emprunteur emprunteur;

    @OneToMany(mappedBy = "emprunt")
    private List<EmpruntDetail> empruntDetails;

    private LocalDate dateEmprunt;
    private String status;

    public List<EmpruntDetail> getItems() {
        return empruntDetails;
    }
}
