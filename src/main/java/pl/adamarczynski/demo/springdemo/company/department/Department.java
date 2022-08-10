package pl.adamarczynski.demo.springdemo.company.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.adamarczynski.demo.springdemo.company.employee.Employee;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department {

    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    @ToString.Exclude
    private List<Employee> employees;

}



