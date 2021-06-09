package pl.adamarczynski.demo.springdemo.company

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import pl.adamarczynski.demo.springdemo.company.department.DepartmentCost
import pl.adamarczynski.demo.springdemo.company.department.DepartmentNotFoundException
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CompanyController)
class CompanyControllerSpec extends Specification {

    @SpringBean
    private Company company = Mock()

    @Autowired
    @Subject
    private MockMvc mockMvc

    def "should return json response for all departments"() {
        given:
        company.findAllCosts() >> [
                "IT": new BigDecimal('1000.0'),
                "Finance": new BigDecimal('2000.0')
        ]

        when:
        def response = mockMvc.perform(get('/company/departments/cost'))

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.IT').value('1000.0'))
                .andExpect(jsonPath('$.Finance').value('2000.0'))
    }

    def "should return json response for given department"() {
        given:
        company.findDepartmentCost('it') >> new DepartmentCost('it', new BigDecimal('500.0'))

        when:
        def response = mockMvc.perform(get('/company/departments/it/cost'))

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.departmentName').value('it'))
                .andExpect(jsonPath('$.departmentCost').value('500.0'))
    }

    def "should return 404 when department does not exist"() {
        given:
        company.findDepartmentCost('it') >> { throw new DepartmentNotFoundException("test err msg") }

        when:
        def response = mockMvc.perform(get('/company/departments/it/cost'))

        then:
        response.andExpect(status().isNotFound())
                .andExpect(jsonPath('$.message').value('test err msg'))
    }
}
