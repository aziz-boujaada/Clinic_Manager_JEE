package Repositories.ipml;

import Config.DatabaseConfig;
import Models.Specialty;
import Repositories.SpecialtyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class SpecialtyRepoImplement implements SpecialtyRepository {
    private final DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

    @Override
    public List<Specialty> findAll() {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT s FROM Specialty s JOIN FETCH s.department ORDER BY s.name", Specialty.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<Specialty> findById(long id) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT s FROM Specialty s JOIN FETCH s.department WHERE s.id = :id", Specialty.class)
                    .setParameter("id", id)
                    .getResultStream()
                    .findFirst();
        }
    }

    @Override
    public Specialty save(Specialty specialty) {
        return inTransaction(em -> {
            em.persist(specialty);
            return specialty;
        });
    }

    @Override
    public Specialty update(Specialty specialty) {
        return inTransaction(em -> em.merge(specialty));
    }

    @Override
    public boolean deleteById(long id) {
        return inTransaction(em -> {
            Specialty specialty = em.find(Specialty.class, id);
            if (specialty == null) return false;
            em.remove(specialty);
            return true;
        });
    }

    private <T> T inTransaction(EntityManagerWork<T> work) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                T result = work.execute(em);
                transaction.commit();
                return result;
            } catch (RuntimeException e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @FunctionalInterface
    private interface EntityManagerWork<T> {
        T execute(EntityManager em);
    }
}
