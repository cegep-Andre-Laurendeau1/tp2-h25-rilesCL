package ca.cal.tp2.modele;

import java.util.Date;

public class Emprunt{
    private int borrowID;
    private Date dateEmprunt;
    private String status;
    private EmpruntDetail empruntDetail;

    public Emprunt() {
    }

    public Emprunt(int borrowID, Date dateEmprunt, String status) {
        this.borrowID = borrowID;
        this.dateEmprunt = dateEmprunt;
        this.status = status;
    }

    public EmpruntDetail getItems() {
        return empruntDetail;
    }

    // Getters et Setters
    public int getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
