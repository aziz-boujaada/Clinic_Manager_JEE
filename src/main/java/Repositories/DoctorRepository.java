package Repositories;

import Models.Doctor;

import java.util.Optional;

public interface DoctorRepository {

    Optional<Doctor> findById(long id);
}
