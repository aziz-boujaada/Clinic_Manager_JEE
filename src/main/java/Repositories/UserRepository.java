package Repositories;

import Models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
   List<User> findAll();
   Optional<User> findByEmail(String email);
   Optional<User> findById(long id);
   User save (User user);
}
