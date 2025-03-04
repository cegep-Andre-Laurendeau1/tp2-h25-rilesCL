package ca.cal.tp2.modele;

import java.util.Date;

public class EmpruntDetail {
    private int lineItemID;
    private Date dateRetourPrevue;
    private Date dateRetourActuelle;
    private String status;
    private Document document;

    public EmpruntDetail() {
    }

    public EmpruntDetail(int lineItemID, Date dateRetourPrevue, Date dateRetourActuelle, String status) {
        this.lineItemID = lineItemID;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourActuelle = dateRetourActuelle;
        this.status = status;
    }

    public boolean isEnRetard() {
        if (dateRetourActuelle == null) {
            return new Date().after(dateRetourPrevue);
        }
        return dateRetourActuelle.after(dateRetourPrevue);
    }

    public double calculAmende() {
        if (!isEnRetard()) {
            return 0.0;
        }
        long diffInMillies = dateRetourActuelle.getTime() - dateRetourPrevue.getTime();
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
        return diffInDays * 0.25;
    }

    public void updateStatus() {
        status = isEnRetard() ? "EN_RETARD" : "EN_COURS";
    }

        // Getters et Setters
    public int getLineItemID() {
        return lineItemID;
    }

    public void setLineItemID(int lineItemID) {
        this.lineItemID = lineItemID;
    }

    public Date getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(Date dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public Date getDateRetourActuelle() {
        return dateRetourActuelle;
    }

    public void setDateRetourActuelle(Date dateRetourActuelle) {
        this.dateRetourActuelle = dateRetourActuelle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
