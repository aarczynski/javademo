package pl.adamarczynski.demo.springdemo.company.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.adamarczynski.demo.springdemo.company.department.Department;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    @EqualsAndHashCode.Include
    private UUID id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    @ToString.Exclude
    private Department department;
}
