package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.boot.model.source.spi.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String nom;
    private String email;
    private String phoneNumber;

        public void login() {
            if (userId > 0 && nom != null && email != null) {
                notifyLogin();
            }
        }

    private void notifyLogin() {
        String loginInfo = String.format("ID: %d, Name: %s", userId, nom);

    }
}

