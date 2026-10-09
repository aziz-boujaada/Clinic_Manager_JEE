package Repositories;

import Models.Specialty;

import java.util.List;
import java.util.Optional;

public interface SpecialtyRepository {
    List<Specialty> findAll();
    Optional<Specialty> findById(long id);
    Specialty save(Specialty specialty);
    Specialty update(Specialty specialty);
    boolean deleteById(long id);
}
