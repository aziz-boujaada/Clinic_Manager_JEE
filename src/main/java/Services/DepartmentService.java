package Services;

import Models.Department;
import Repositories.ipml.DepartmentRepoImplement;

import java.util.List;

public class DepartmentService {

    private final DepartmentRepoImplement departmentRepo = new DepartmentRepoImplement();

    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    public Department findByID(long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    public Department create(String name, String description) {
        return departmentRepo.save(new Department(name, description));
    }

    public Department update(long id, String name, String description) {
        Department department = findByID(id);
        department.setName(name);
        department.setDescription(description);
        return departmentRepo.update(department);
    }

    public boolean delete(long id) {
        return departmentRepo.deleteById(id);
    }


}
