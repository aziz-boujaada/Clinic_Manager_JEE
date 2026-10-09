package Repositories.ipml;

import Config.DatabaseConfig;
import Models.User;
import Repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transaction;


import java.util.List;
import java.util.Optional;

public class UserRepoImplement implements UserRepository {

    private final DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    @Override
    public List<User> findAll() {

        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT u FROM User u ORDER BY u.id DESC",
                            User.class
                    )
                    .getResultList();
        }

    }

    @Override
    public Optional<User> findByEmail(String email) {

        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT u FROM User u WHERE u.email = :email ",
                            User.class
                    )
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst();
        }

    }

    @Override
    public Optional<User> findById(long id) {

        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery(
                            "SELECT u FROM User u WHERE u.id = :id ",
                            User.class
                    )
                    .setParameter("id", id)
                    .getResultStream()
                    .findFirst();
        }

    }

    @Override
    public User save(User user){

        try(EntityManager em = databaseConfig.createEntityManager()){
            EntityTransaction transaction =  em.getTransaction();

            try{
                transaction.begin();
                em.persist(user);
                transaction.commit();
            } catch (Exception e) {
                if(transaction.isActive()){

                transaction.rollback();
                }
                throw new RuntimeException("Save User Failed " , e);
            }
        }

        return user ;
    }
}
