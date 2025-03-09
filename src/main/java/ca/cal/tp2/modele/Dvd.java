package ca.cal.tp2.modele;

import jakarta.persistence.Access;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dvd extends Document{
    private String director;
    private int duree;
    private String rating;
}
