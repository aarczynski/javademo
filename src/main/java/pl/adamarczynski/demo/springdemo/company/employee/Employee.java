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
@Entity
@Table(name = "EMPLOYEE", schema = "COMPANY")
public class Employee {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
