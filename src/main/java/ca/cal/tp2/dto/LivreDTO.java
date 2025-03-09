package ca.cal.tp2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivreDTO {
    private int documentId;
    private String titre;
    private int nombreExemplaires;
    private int nombreDisponibles;
    private String ISBN;
    private String auteur;
    private String editeur;
    private int anneePublication;
    private int nombrePages;

}
