package ca.cal.tp2.Repository;

import ca.cal.tp2.modele.Emprunt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class EmpruntRepositoryJPA implements EmpruntRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2-h25-ghilas.pu");

    @Override
    public Emprunt save(Emprunt emprunt) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(emprunt);
            em.getTransaction().commit();
            return emprunt;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Emprunt> findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Emprunt emprunt = em.find(Emprunt.class, id);
            em.getTransaction().commit();
            return Optional.ofNullable(emprunt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Emprunt> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Emprunt> query = em.createQuery("FROM Emprunt", Emprunt.class);
            List<Emprunt> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Emprunt update(Emprunt emprunt) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Emprunt updated = em.merge(emprunt);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Emprunt emprunt) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (!em.contains(emprunt)) {
                emprunt = em.merge(emprunt);
            }
            em.remove(emprunt);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public List<Emprunt> findByEmprunteurId(int emprunteurId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Emprunt> query = em.createQuery(
                    "FROM Emprunt e WHERE e.emprunteur.userId = :emprunteurId",
                    Emprunt.class);
            query.setParameter("emprunteurId", emprunteurId);
            List<Emprunt> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}