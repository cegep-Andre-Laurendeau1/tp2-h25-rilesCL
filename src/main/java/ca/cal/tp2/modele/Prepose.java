package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prepose extends Utilisateur {

    @OneToMany
    @JoinColumn(name = "prepose_id")
    private List<Document> documents = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "prepose_id")
    private List<Emprunt> emprunts = new ArrayList<>();

    public Prepose(String name, String email, String phoneNumber) {
        super();
        this.setName(name);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }

    public void setName(String name) {
    }


    public void entreNouveauDocument(Document document) {
        if (document != null) {
            documents.add(document);
        }
    }

    public List<Emprunt> rapportEmprunts() {
        return new ArrayList<>(emprunts);
    }
}