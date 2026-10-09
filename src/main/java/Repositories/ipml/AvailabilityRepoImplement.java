package Repositories.ipml;

import Config.DatabaseConfig;
import Models.Availability;
import Models.Availability;
import Repositories.AvailabilityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class AvailabilityRepoImplement implements AvailabilityRepository {

    private final DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

    @Override
public List<Availability> findAll() {
    try (EntityManager em = databaseConfig.createEntityManager()) {
        return em.createQuery("SELECT d FROM Availability d ORDER BY d.startTime", Availability.class)
                .getResultList();
    }
}

    @Override
    public Optional<Availability> findById(long id) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return Optional.ofNullable(em.find(Availability.class, id));
        }
    }

    @Override
    public Availability save(Availability department) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(department);

                transaction.commit();
                return  department;

            } catch (RuntimeException e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Availability update(Availability department) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                department =  em.merge(department);

                transaction.commit();
                return  department;

            } catch (RuntimeException e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public boolean deleteById(long id) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                Availability department = em.find(Availability.class, id);
                if (department == null) {
                    transaction.commit();
                    return false;
                }
                em.remove(department);
                transaction.commit();
                return true;


            } catch (RuntimeException e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }

    }
}
