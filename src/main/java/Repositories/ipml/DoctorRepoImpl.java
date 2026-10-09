package Repositories.ipml;

import Config.DatabaseConfig;
import Models.Doctor;
import Models.User;
import Repositories.DoctorRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class DoctorRepoImpl implements DoctorRepository {
    private final DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    @Override
    public Optional<Doctor> findById(long id) {

        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT u FROM Doctor u WHERE u.id = :id ",
                            Doctor.class
                    )
                    .setParameter("id", id)
                    .getResultStream()
                    .findFirst();
        }

    }
}
