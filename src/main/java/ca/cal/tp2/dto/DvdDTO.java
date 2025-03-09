package ca.cal.tp2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DvdDTO {
    private int documentId;
    private String titre;
    private int nombreExemplaires;
    private int nombreDisponibles;
    private String director;
    private int duree;
    private String rating;
}