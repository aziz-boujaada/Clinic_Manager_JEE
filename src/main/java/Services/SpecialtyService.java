package Services;

import Models.Department;
import Models.Specialty;
import Repositories.ipml.SpecialtyRepoImplement;

import java.util.List;

public class SpecialtyService {
    private final SpecialtyRepoImplement specialtyRepo = new SpecialtyRepoImplement();
    private final DepartmentService departmentService = new DepartmentService();

    public List<Specialty> findAll() {
        return specialtyRepo.findAll();
    }

    public Specialty findById(long id) {
        return specialtyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Specialty not found."));
    }

    public Specialty create(String name, String description, long departmentId) {
        Department department = departmentService.findByID(departmentId);
        Specialty specialty = new Specialty(name, description, department);
        return specialtyRepo.save(specialty);
    }

    public Specialty update(long id, String name, String description, long departmentId) {
        Specialty specialty = findById(id);
        specialty.setName(name);
        specialty.setDescription(description);
        specialty.setDepartment(departmentService.findByID(departmentId));
        return specialtyRepo.update(specialty);
    }

    public boolean delete(long id) {
        return specialtyRepo.deleteById(id);
    }


}
