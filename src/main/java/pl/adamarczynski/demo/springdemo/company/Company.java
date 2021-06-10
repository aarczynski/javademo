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
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class Company {

    private final DepartmentRepository departmentRepository;

    public Company(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentCost> findAllCosts() {
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

        log.info("Found costs of {} departments", cost.size());
        return cost.entrySet().stream()
                .map(e -> new DepartmentCost(e.getKey(), e.getValue()))
                .collect(toList());
    }

    public DepartmentCost findDepartmentCost(@PathVariable String departmentName) {
        var department = departmentRepository.findByNameIgnoreCase(departmentName)
                .orElseThrow(() -> new DepartmentNotFoundException("not found"));

        var employees = department.getEmployees();
        var cost = employees
                .stream()
                .map(Employee::getSalary)
                .reduce(ZERO, BigDecimal::add);

        log.info("Found total cost of {} {} employees equal to {}", employees.size(), departmentName, cost);
        return new DepartmentCost(department.getName(), cost);
    }
}
