package ca.cal.tp2.modele;

abstract class Document  {
    private int documentId;
    private String titre;
    private int nombreExemplaires;

    public Document() {}

    public Document(int documentId, String titre, int nombreExemplaires) {
        this.documentId = documentId;
        this.titre = titre;
        this.nombreExemplaires = nombreExemplaires;
    }

    public boolean verifieDisponibilite() {
        return nombreExemplaires > 0;
    }

    public int getDocumentId() {
        return documentId;
    }
    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public int getNombreExemplaires() {
        return nombreExemplaires;
    }
    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }
}
