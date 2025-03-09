package ca.cal.tp2.Repository;

import ca.cal.tp2.modele.Document;
import java.util.List;

public interface DocumentRepository extends Repository<Document>{
    List<Document> findbyTitreContaining(String titrePartiel);

    List<Document> findByTitreContaining(String titrePartiel);
}
