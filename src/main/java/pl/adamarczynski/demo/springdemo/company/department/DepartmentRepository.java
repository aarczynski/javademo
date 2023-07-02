package pl.adamarczynski.demo.springdemo.company.department;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    @EntityGraph(value = "Department.employees")
    Optional<Department> findByNameIgnoreCase(String name);

    @EntityGraph(value = "Department.employees")
    List<Department> findAllBy();
}
