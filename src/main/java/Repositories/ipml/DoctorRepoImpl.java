package Repositories.ipml;

import Config.DatabaseConfig;
import Models.Doctor;
import Repositories.DoctorRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class DoctorRepoImpl implements DoctorRepository {
    private final DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    @Override
    public Optional<Doctor> findById(long id) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return Optional.ofNullable(em.find(Doctor.class, id));
        }
    }

    @Override
    public List<Doctor> findAllActive() {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT d FROM Doctor d WHERE d.active = true ORDER BY d.lastName, d.firstName",
                            Doctor.class
                    )
                    .getResultList();
        }
    }
}
