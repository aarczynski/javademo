package pl.adamarczynski.demo.springdemo.company

import pl.adamarczynski.demo.springdemo.company.department.Department
import pl.adamarczynski.demo.springdemo.company.department.DepartmentRepository
import pl.adamarczynski.demo.springdemo.company.employee.Employee
import pl.adamarczynski.demo.springdemo.company.employee.EmployeeRepository
import spock.lang.Specification
import spock.lang.Subject

class CompanySpec extends Specification {

    private EmployeeRepository employeeRepository = Mock()
    private DepartmentRepository departmentRepository = Mock()

    @Subject
    private company = new Company(employeeRepository, departmentRepository)

    void setup() {
        var adam = new Employee(firstName: 'Adam', salary: new BigDecimal('1000'))
        var maciej = new Employee(firstName: 'Maciej', salary: new BigDecimal('2000'))
        var anna = new Employee(firstName: 'Anna', salary: new BigDecimal('5000'))

        var it = new Department(id: UUID.randomUUID(), name: "IT", employees: [adam, maciej])
        var finance = new Department(id: UUID.randomUUID(), name: "Finance", employees: [anna])
        var delivery = new Department(id: UUID.randomUUID(), name: "Delivery", employees: [])

        departmentRepository.findByNameIgnoreCase('IT') >> Optional.of(it)
        departmentRepository.findByNameIgnoreCase('Finance') >> Optional.of(finance)
        departmentRepository.findByNameIgnoreCase('Delivery') >> Optional.of(delivery)
        departmentRepository.findAll() >> [it, finance, delivery]

        employeeRepository.findByDepartment(it) >> [adam, maciej]
        employeeRepository.findByDepartment(finance) >> [anna]
        employeeRepository.findByDepartment(delivery) >> []
        employeeRepository.findAll() >> [adam, maciej, anna]
    }

    def "should find all departments costs"() {
        when:
        def costs = company.findAllCosts()

        then:
        costs['IT'] == 3000
        costs['Finance'] == 5000
        costs['Delivery'] == 0
    }

    def "#departmentName should cost #expectedCost"() {
        when:
        def actual = company.findDepartmentCost(departmentName)

        then:
        actual.departmentName == departmentName
        actual.departmentCost == expectedCost

        where:
        departmentName | expectedCost
        'IT'           | 3000
        'Finance'      | 5000
        'Delivery'     | 0
    }
}
