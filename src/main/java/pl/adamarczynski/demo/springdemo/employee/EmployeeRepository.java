package pl.adamarczynski.demo.springdemo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.adamarczynski.demo.springdemo.company.department.Department;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByDepartment(Department department);
}
