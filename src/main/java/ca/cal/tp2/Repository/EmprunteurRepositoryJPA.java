package ca.cal.tp2.Repository;

import ca.cal.tp2.modele.Emprunteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class EmprunteurRepositoryJPA implements EmprunteurRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2-h25-ghilas.pu");

    @Override
    public Emprunteur save(Emprunteur emprunteur) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(emprunteur);
            em.getTransaction().commit();
            return emprunteur;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Emprunteur> findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Emprunteur emprunteur = em.find(Emprunteur.class, id);
            em.getTransaction().commit();
            return Optional.ofNullable(emprunteur);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Emprunteur> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Emprunteur> query = em.createQuery("FROM Emprunteur", Emprunteur.class);
            List<Emprunteur> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Emprunteur update(Emprunteur emprunteur) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Emprunteur updated = em.merge(emprunteur);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Emprunteur emprunteur) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (!em.contains(emprunteur)) {
                emprunteur = em.merge(emprunteur);
            }
            em.remove(emprunteur);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        findById(id).ifPresent(this::delete);
    }
}