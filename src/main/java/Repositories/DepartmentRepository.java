package Repositories;

import Models.Department;

import java.util.Optional;
import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll();
    Optional<Department> findById(long id);
    Department save(Department department);
    Department update(Department department);
    boolean deleteById(long id);
}
