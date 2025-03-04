package ca.cal.tp2.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Emprunteur extends Utilisateur {
    private List<Emprunt> emprunts = new ArrayList<>();
    private List<Amende> amendes;

    public Emprunteur() {
        super();
        this.emprunts = new ArrayList<>();
        this.amendes = new ArrayList<>();
    }

    public Emprunteur(int userID, String name, String email, String phoneNumber) {
        super(userID, name, email, phoneNumber);
        this.emprunts = new ArrayList<>();
        this.amendes = new ArrayList<>();
    }

    public void emprunte(Document document) {
        if (document != null && document.verifieDisponibilite()) {
            Emprunt nouvelEmprunt = new Emprunt();
            nouvelEmprunt.setDateEmprunt(new Date());
            nouvelEmprunt.setStatus("EN_COURS");
            emprunts.add(nouvelEmprunt);
        }
    }

    public void retourneDocument(Document document) {
        if (document != null) {
            for (Emprunt emprunt : emprunts) {
                if (emprunt.getStatus().equals("EN_COURS")) {
                    emprunt.setStatus("RETOURNE");
                    break;
                }
            }
        }

    }

    public void payeAmende(double montant) {
        if (montant > 0) {
            Amende nouvelleAmende = new Amende();
            nouvelleAmende.setMontant(montant);
            amendes.add(nouvelleAmende);
        }
    }

    public ArrayList<Emprunt> rapportHistoriqueEmprunt() {
        return new ArrayList<>(emprunts);
    }

    public List<Emprunt> getEmprunts() {
        return new ArrayList<>(emprunts);
    }

    public List<Amende> getAmendes() {
        return new ArrayList<>(amendes);
    }

    // Setters pour CREATE/JDBC
    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public void setAmendes(List<Amende> amendes) {
        this.amendes = amendes;
    }
}
