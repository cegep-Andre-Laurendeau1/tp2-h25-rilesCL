package ca.cal.tp2.modele;

public class Livre extends Document {
    private String ISBN;
    private String editeur;
    private String Auteur;
    private int nbPages;

    public Livre() {
        super();
    }
    public Livre(int documentID, String titre, int nombreExemplaires, String ISBN, String editeur, String Auteur, int nbPages) {
        super(documentID, titre, nombreExemplaires);
        this.ISBN = ISBN;
        this.editeur = editeur;
        this.Auteur = Auteur;
        this.nbPages = nbPages;
    }

    public String getISBN() {
        return ISBN;
    }
    public String getEditeur() {
        return editeur;
    }
    public String getAuteur() {
        return Auteur;
    }
    public int getNbPages() {
        return nbPages;
    }
}
