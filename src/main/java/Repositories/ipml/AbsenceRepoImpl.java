package Repositories.ipml;

import Config.DatabaseConfig;
import Models.Absence;
import Repositories.AbsenceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class AbsenceRepoImpl implements AbsenceRepository {

    private final DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

    @Override
    public List<Absence> findAll() {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery("SELECT d FROM Absence d ORDER BY d.id DESC ", Absence.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<Absence> findById(long id) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return Optional.ofNullable(em.find(Absence.class, id));
        }
    }

    @Override
    public Absence save(Absence department) {
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
    public Absence update(Absence department) {
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
                Absence department = em.find(Absence.class, id);
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
