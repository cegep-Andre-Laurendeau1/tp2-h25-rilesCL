package ca.cal.tp2.modele;

public class Cd extends Document{
    private String artiste;
    private int duree;
    private String genre;

    public Cd(){
        super();
    }

    public Cd(int documentID, String titre, int nombreExemplaires, String artiste, int duree, String genre) {
        super(documentID, titre, nombreExemplaires);
        this.artiste = artiste;
        this.duree = duree;
        this.genre = genre;
    }
    public String getArtiste() {
        return artiste;
    }
    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
