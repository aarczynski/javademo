package pl.adamarczynski.demo.springdemo.company;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.adamarczynski.demo.springdemo.company.department.Department;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentCost;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentNotFoundException;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentRepository;
import pl.adamarczynski.demo.springdemo.company.employee.Employee;
import pl.adamarczynski.demo.springdemo.company.employee.EmployeeRepository;

import java.math.BigDecimal;
import java.util.Map;

import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class Company {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public Company(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Map<String, BigDecimal> findAllCosts() {
        var cost = departmentRepository.findAll()
                .stream()
                .collect(toMap(
                        Department::getName,
                        v -> v.getEmployees()
                                .stream()
                                .map(Employee::getSalary)
                                .reduce(ZERO, BigDecimal::add)
                        )
                );

        log.info("Found cost of {} departments", cost.size());
        return cost;
    }

    public DepartmentCost findDepartmentCost(@PathVariable String departmentName) {
        var employees = departmentRepository.findByNameIgnoreCase(departmentName)
                .map(employeeRepository::findByDepartment)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentName + "not found"));

        var cost = employees.stream()
                .map(Employee::getSalary)
                .reduce(ZERO, BigDecimal::add);

        log.info("Found total cost of {} {} employees equal to {}", employees.size(), departmentName, cost);
        return new DepartmentCost(departmentName, cost);
    }
}
