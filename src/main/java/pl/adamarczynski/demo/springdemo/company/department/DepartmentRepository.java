package pl.adamarczynski.demo.springdemo.company.department;

import org.springframework.stereotype.Component;
import pl.adamarczynski.demo.springdemo.company.employee.Employee;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DepartmentRepository {

    List<Department> departments;

    public DepartmentRepository() {
        Employee janKowalski = new Employee(UUID.randomUUID(), "Jan", "Kowalski", new BigDecimal("10500.00"), null);
        Employee janNowak = new Employee(UUID.randomUUID(), "Jan", "Nowak", new BigDecimal("10500.00"), null);
        Employee adamAniol = new Employee(UUID.randomUUID(), "Adam", "Anioł", new BigDecimal("15000.00"), null);
        Employee maciejWesoly = new Employee(UUID.randomUUID(), "Maciej", "Wesoły", new BigDecimal("15500.00"), null);
        Employee tomaszWrobel = new Employee(UUID.randomUUID(), "Tomasz", "Wrobel", new BigDecimal("12000.00"), null);

        Department finances = new Department(UUID.randomUUID(), "Finances", List.of(janKowalski, janNowak, adamAniol, maciejWesoly));
        Department it = new Department(UUID.randomUUID(), "IT", List.of(tomaszWrobel));
        Department delivery = new Department(UUID.randomUUID(), "Delivery", Collections.emptyList());

        janKowalski.setDepartment(finances);
        janNowak.setDepartment(finances);
        adamAniol.setDepartment(finances);
        maciejWesoly.setDepartment(finances);
        tomaszWrobel.setDepartment(it);

        this.departments = List.of(finances, it, delivery);
    }

    public List<Department> findAll() {
        return departments;
    }

    public Optional<Department> findByNameIgnoreCase(String name) {
        return departments.stream()
                .filter(d -> name.equalsIgnoreCase(d.getName()))
                .findFirst();
    }
}
