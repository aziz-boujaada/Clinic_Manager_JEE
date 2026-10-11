package Repositories;

import Models.Appointment;

import java.util.List;

public interface AppointmentRepository {
    List<Appointment> findAll();
    List<Appointment> findByDoctorId(long doctorId);
    List<Appointment> findByPatientId(long patientId);
    Appointment save(Appointment appointment);
}
