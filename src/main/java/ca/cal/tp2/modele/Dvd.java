package ca.cal.tp2.modele;

public class Dvd extends Document{
    private String director;
    private int duree;
    private String rating;

    public Dvd(){
        super();
    }
    public Dvd(int documentId,String titre, int nombreExemplaires, String director, int duree, String rating ){
        super(documentId,titre,nombreExemplaires);
        this.director = director;
        this.duree = duree;
        this.rating = rating;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
}
