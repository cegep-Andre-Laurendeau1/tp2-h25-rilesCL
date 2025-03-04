package ca.cal.tp2;

import ca.cal.tp2.Repository.PreposeRepository;
import ca.cal.tp2.Service.PreposeService;
import ca.cal.tp2.TcpServer;
import ca.cal.tp2.modele.Prepose;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {

        TcpServer.startTcpServer();
        PreposeRepository repository = new PreposeRepository();
        PreposeService service = new PreposeService(repository);
        repository.createDatabase();
        try {
            // Test CREATE
            System.out.println("Création d'un nouveau préposé...");
            Prepose nouveauPrepose = service.creerPrepose(
                    1,
                    "John Doe",
                    "john.doe@bibliotheque.com",
                    "514-555-0123"
            );

            // Test READ
            System.out.println("Lecture du préposé créé...");
            Prepose preposeRecupere = service.obtenirPrepose(1);

            if (preposeRecupere != null) {
                System.out.println("Préposé trouvé :");
                System.out.println("ID: " + preposeRecupere.getUserId());
                System.out.println("Nom: " + preposeRecupere.getNom());
                System.out.println("Email: " + preposeRecupere.getEmail());
                System.out.println("Téléphone: " + preposeRecupere.getPhoneNumber());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.currentThread().join();
    }
}
