package ca.cal.tp2.modele;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Livre extends Document {
    private String ISBN;
    private String editeur;
    private String auteur;
    private int nbPages;
    private int anneePublication;
}
