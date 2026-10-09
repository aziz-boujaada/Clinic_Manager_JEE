package Repositories;

import Models.Availability;


import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository {

    List<Availability> findAll();
    Optional<Availability> findById(long id);
    Availability save(Availability department);
    Availability update(Availability department);
    boolean deleteById(long id);
}
