package Repositories.ipml;

import Config.DatabaseConfig;
import Models.Department;
import Repositories.DepartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class DepartmentRepoImplement implements DepartmentRepository {
    private final DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

    @Override
    public List<Department> findAll() {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return em.createQuery("SELECT d FROM Department d ORDER BY d.name", Department.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<Department> findById(long id) {
        try (EntityManager em = databaseConfig.createEntityManager()) {
            return Optional.ofNullable(em.find(Department.class, id));
        }
    }

    @Override
    public Department save(Department department) {
         try (EntityManager em = databaseConfig.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                 em.persist(department);
                
                transaction.commit();
                return  department;
                
            } catch (RuntimeException e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Department update(Department department) {
          try (EntityManager em = databaseConfig.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                  department =  em.merge(department);
                 
                transaction.commit();
                return  department;
                
            } catch (RuntimeException e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public boolean deleteById(long id) {
          try (EntityManager em = databaseConfig.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                Department department = em.find(Department.class, id);
                if (department == null) {
                    transaction.commit();
                    return false;
                }
                em.remove(department);
                transaction.commit();
                return true;
                
                
            } catch (RuntimeException e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
        
    }


}
