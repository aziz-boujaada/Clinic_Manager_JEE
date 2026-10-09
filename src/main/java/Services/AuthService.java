package Services;

import Models.User;
import Repositories.ipml.UserRepoImplement;

import java.util.List;
import java.util.Optional;

public class AuthService {

    private final UserRepoImplement userRepo = new UserRepoImplement();

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> login(String email, String password) {
        return userRepo.findByEmail(email);
    }

    public User register(User user){
        return  userRepo.save(user) ;
    }
}
