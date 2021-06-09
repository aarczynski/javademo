package pl.adamarczynski.demo.springdemo.employee

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.adamarczynski.demo.springdemo.company.department.Department
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class EmployeeRepositorySpec extends Specification {

    public static final UUID FINANCE_DEPARTMENT_ID = UUID.fromString('924f79b8-d0a8-401b-a1b9-942993a39259')

    @Autowired
    @Subject
    private EmployeeRepository repository

    def "should load employees"() {
        when:
        def employees = repository.findAll()

        then:
        employees.size() == 5
    }

    def "should find four finance department employees"() {
        given:
        def financeDepartment = new Department(id: FINANCE_DEPARTMENT_ID)

        when:
        def employees = repository.findByDepartment(financeDepartment)

        then:
        employees.size() == 4
    }
}
