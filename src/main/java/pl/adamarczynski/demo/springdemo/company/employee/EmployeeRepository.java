package pl.adamarczynski.demo.springdemo.company.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.adamarczynski.demo.springdemo.company.department.Department;

import java.util.List;
import java.util.UUID;

public class EmployeeRepository {
    public List<Employee> findByDepartment(Department department) {

    }
}
