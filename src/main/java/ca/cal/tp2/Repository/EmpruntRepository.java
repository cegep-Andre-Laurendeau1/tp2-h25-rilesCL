package ca.cal.tp2.Repository;

import ca.cal.tp2.modele.Emprunt;

import java.util.List;

public interface EmpruntRepository extends Repository<Emprunt>{
    List<Emprunt> findByEmprunteurId(int emprunteurId);

    List<Emprunt> findByEmprunteurIdWithDetails(int emprunteurId);
}
