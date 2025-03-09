package ca.cal.tp2.modele;

import java.time.LocalDate;
import java.util.Date;

public class Amende {
    private int fineID;
    private double montant;
    private Date dateCreation;
    private boolean status;
    private Emprunteur emprunteur;

    public Amende() {
    }

    public Amende(int fineID, double montant, Date dateCreation, boolean status) {
        this.fineID = fineID;
        this.montant = montant;
        this.dateCreation = dateCreation;
        this.status = status;
    }

    public void calculMontant() {
        if (montant <= 0) {
            this.montant = 0.25; // Montant minimal d'une amende
        }
    }

    public void updateStatus() {
        this.status = montant <= 0;
    }

    // Getters et Setters
    public int getFineID() {
        return fineID;
    }

    public void setFineID(int fineID) {
        this.fineID = fineID;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }
    }
}
