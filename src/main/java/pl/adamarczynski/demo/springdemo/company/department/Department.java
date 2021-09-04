package pl.adamarczynski.demo.springdemo.company.department;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.adamarczynski.demo.springdemo.company.employee.Employee;

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
    @ToString.Exclude
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Employee> employees;

}



