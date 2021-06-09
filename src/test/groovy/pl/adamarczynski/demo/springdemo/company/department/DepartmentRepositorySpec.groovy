package pl.adamarczynski.demo.springdemo.company.department

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class DepartmentRepositorySpec extends Specification {

    @Autowired
    @Subject
    private DepartmentRepository repository

    def "should load test records"() {
        when:
        def departments = repository.findAll()

        then:
        departments*.name as Set == ['Finance', 'IT'] as Set
    }
}
