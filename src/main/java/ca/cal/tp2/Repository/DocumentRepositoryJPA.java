package ca.cal.tp2.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import ca.cal.tp2.modele.Document;
import java.util.List;
import java.util.Optional;

public class DocumentRepositoryJPA implements DocumentRepository{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2-h25-rilesCL.pu");

    @Override
    public Document save(Document document) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(document);
            em.getTransaction().commit();
            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Document> findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Document document = em.find(Document.class, id);
            em.getTransaction().commit();
            return Optional.ofNullable(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Document> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Document> query = em.createQuery("FROM Document", Document.class);
            List<Document> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }    }

    @Override
    public Document update(Document document) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Document updated = em.merge(document);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }    }

    @Override
    public void delete(Document document) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (!em.contains(document)) {
                document = em.merge(document);
            }
            em.remove(document);
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
    public List<Document> findbyTitreContaining(String titrePartiel) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Document> query = em.createQuery(
                    "FROM Document d WHERE LOWER(d.titre) LIKE LOWER(:titre)",
                    Document.class);
            query.setParameter("titre", "%" + titrePartiel + "%");
            List<Document> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Document> findByTitreContaining(String titrePartiel) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Document> query = em.createQuery(
                    "FROM Document d WHERE LOWER(d.titre) LIKE LOWER(:titre)",
                    Document.class);
            query.setParameter("titre", "%" + titrePartiel + "%");
            List<Document> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

