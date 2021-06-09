package pl.adamarczynski.demo.springdemo.company.department

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification
import spock.lang.Subject

@DataJpaTest
class DepartmentRepositorySpec extends Specification {

    @Autowired
    @Subject
    private DepartmentRepository repository

    def "should load test records"() {
        when:
        def departments = repository.findAll()

        then:
        departments*.name as Set == ['Finance', 'IT', 'Delivery'] as Set
    }
}
