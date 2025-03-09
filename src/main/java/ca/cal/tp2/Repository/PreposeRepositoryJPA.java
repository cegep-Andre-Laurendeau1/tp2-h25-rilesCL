package ca.cal.tp2.Repository;

import ca.cal.tp2.modele.Prepose;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class PreposeRepositoryJPA implements PreposeRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2-h25-rilesCL.pu");


    @Override
    public Prepose save(Prepose prepose) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(prepose);
            em.getTransaction().commit();
            return prepose;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Prepose> findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Prepose prepose = em.find(Prepose.class, id);
            em.getTransaction().commit();
            return Optional.ofNullable(prepose);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prepose> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Prepose> query = em.createQuery("FROM Prepose", Prepose.class);
            List<Prepose> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prepose update(Prepose prepose) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Prepose updated = em.merge(prepose);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Prepose prepose) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (!em.contains(prepose)) {
                prepose = em.merge(prepose);
            }
            em.remove(prepose);
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