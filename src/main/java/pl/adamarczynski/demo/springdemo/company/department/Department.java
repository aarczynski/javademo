package pl.adamarczynski.demo.springdemo.company.department;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.adamarczynski.demo.springdemo.employee.Employee;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "DEPARTMENT", schema = "COMPANY")
public class Department {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<Employee> employees;

}



