package Repositories.ipml;

import Config.DatabaseConfig;
import Models.Appointment;
import Repositories.AppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class AppointmentRepoImpl implements AppointmentRepository {

    private final DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

    @Override
    public List<Appointment> findAll() {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery("SELECT a FROM Appointment a ORDER BY a.startDateTime", Appointment.class)
                    .getResultList();
        }
    }

    @Override
    public List<Appointment> findByDoctorId(long doctorId) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId ORDER BY a.startDateTime",
                            Appointment.class
                    )
                    .setParameter("doctorId", doctorId)
                    .getResultList();
        }
    }

    @Override
    public List<Appointment> findByPatientId(long patientId) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT a FROM Appointment a WHERE a.patient.id = :patientId ORDER BY a.startDateTime DESC",
                            Appointment.class
                    )
                    .setParameter("patientId", patientId)
                    .getResultList();
        }
    }

    @Override
    public Appointment save(Appointment appointment) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(appointment);
                transaction.commit();
                return appointment;
            } catch (RuntimeException e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }
}
