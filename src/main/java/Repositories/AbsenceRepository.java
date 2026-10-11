package Repositories;

import Models.Absence;

import java.util.List;
import java.util.Optional;

public interface AbsenceRepository {
    List<Absence> findAll();
    Optional<Absence> findById(long id);
    Absence save(Absence absence);
    Absence update(Absence absence);
    boolean deleteById(long id);
}
