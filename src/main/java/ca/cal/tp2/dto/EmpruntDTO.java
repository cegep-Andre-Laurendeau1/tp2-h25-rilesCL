package ca.cal.tp2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpruntDTO {
    private int empruntId;
    private int emprunteurId;
    private String emprunteurNom;
    private LocalDate dateEmprunt;
    private String status;
    private List<EmpruntDetailDTO> details;
}