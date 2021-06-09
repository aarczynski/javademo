package pl.adamarczynski.demo.springdemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class SpringdemoApplicationSpec extends Specification {

    @Autowired
    private ApplicationContext context

    def "spring context loads"() {
        expect:
        context
    }
}
