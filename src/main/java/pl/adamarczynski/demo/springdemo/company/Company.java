package pl.adamarczynski.demo.springdemo.company;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentCost;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentNotFoundException;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentRepository;
import pl.adamarczynski.demo.springdemo.company.employee.Employee;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class Company {

    private final DepartmentRepository departmentRepository;

    public Company(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentCost> findAllCosts() {
        List<DepartmentCost> costs = departmentRepository.findAllBy()
                .stream()
                .map(department -> new DepartmentCost(department.getName(), department.calculateCost()))
                .collect(toList());

        log.info("Found costs of {} departments", costs.size());
        return costs;
    }

    public DepartmentCost findDepartmentCost(String departmentName) {
        var department = departmentRepository.findByNameIgnoreCase(departmentName)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentName + " department not found"));

        var employees = department.getEmployees();
        var cost = employees
                .stream()
                .map(Employee::getSalary)
                .reduce(ZERO, BigDecimal::add);

        log.info("Found total cost of {} {} employees equal to {}", employees.size(), department.getName(), cost);
        return new DepartmentCost(department.getName(), cost);
    }
}
