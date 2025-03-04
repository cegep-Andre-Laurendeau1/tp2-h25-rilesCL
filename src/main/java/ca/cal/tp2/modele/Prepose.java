package ca.cal.tp2.modele;

import java.util.ArrayList;
import java.util.List;

public class Prepose extends Utilisateur{
    private List<Document> documents;
    private List<Amende> amendes;
    private List<Emprunt> emprunts;


    public Prepose() {
        super();
        this.documents = new ArrayList<>();
        this.amendes = new ArrayList<>();
        this.emprunts = new ArrayList<>();
    }

    public Prepose(int userID, String name, String email, String phoneNumber) {
        super(userID, name, email, phoneNumber);
        this.documents = new ArrayList<>();
        this.amendes = new ArrayList<>();
        this.emprunts = new ArrayList<>();
    }

    public void entreNouveauDocument(Document document) {
        if (document != null) {
            documents.add(document);
        }
    }

    public void collecteAmende(Emprunteur emprunteur, double montant) {
        if (emprunteur != null && montant > 0) {
            Amende nouvelleAmende = new Amende();
            nouvelleAmende.setMontant(montant);
            amendes.add(nouvelleAmende);
        }
    }

    public List<Amende> rapportAmendes() {
        return new ArrayList<>(amendes);
    }

    public List<Emprunt> rapportEmprunts() {
        return new ArrayList<>(emprunts);
    }
    public List<Document> getDocuments() {
        return new ArrayList<>(documents);
    }

    public List<Amende> getAmendes() {
        return new ArrayList<>(amendes);
    }

    public List<Emprunt> getEmprunts() {
        return new ArrayList<>(emprunts);
    }

    // Setters pour CREATE/JDBC
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void setAmendes(List<Amende> amendes) {
        this.amendes = amendes;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
}
