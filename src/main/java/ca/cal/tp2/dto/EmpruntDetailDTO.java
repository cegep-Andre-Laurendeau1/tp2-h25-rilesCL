package ca.cal.tp2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpruntDetailDTO {
    private int detailId;
    private int empruntId;
    private int documentId;
    private String documentTitre;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourActuelle;
    private String status;
}