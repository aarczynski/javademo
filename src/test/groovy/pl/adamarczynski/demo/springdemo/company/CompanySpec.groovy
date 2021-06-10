package pl.adamarczynski.demo.springdemo.company

import pl.adamarczynski.demo.springdemo.company.department.Department
import pl.adamarczynski.demo.springdemo.company.department.DepartmentCost
import pl.adamarczynski.demo.springdemo.company.department.DepartmentRepository
import pl.adamarczynski.demo.springdemo.company.employee.Employee
import pl.adamarczynski.demo.springdemo.company.employee.EmployeeRepository
import spock.lang.Specification
import spock.lang.Subject

class CompanySpec extends Specification {

    private DepartmentRepository departmentRepository = Mock()

    @Subject
    private company = new Company(departmentRepository)

    void setup() {
        var adam = new Employee(firstName: 'Adam', salary: new BigDecimal('1000.00'))
        var maciej = new Employee(firstName: 'Maciej', salary: new BigDecimal('2000.00'))
        var anna = new Employee(firstName: 'Anna', salary: new BigDecimal('5000.00'))

        var it = new Department(id: UUID.randomUUID(), name: "IT", employees: [adam, maciej])
        var finance = new Department(id: UUID.randomUUID(), name: "Finance", employees: [anna])
        var delivery = new Department(id: UUID.randomUUID(), name: "Delivery", employees: [])

        departmentRepository.findByNameIgnoreCase('it') >> Optional.of(it)
        departmentRepository.findByNameIgnoreCase('finance') >> Optional.of(finance)
        departmentRepository.findByNameIgnoreCase('delivery') >> Optional.of(delivery)
        departmentRepository.findAll() >> [it, finance, delivery]
    }

    def "should find all departments costs"() {
        when:
        def costs = company.findAllCosts()

        then:
        costs as Set == [
                new DepartmentCost('IT', new BigDecimal("3000.00")),
                new DepartmentCost('Finance', new BigDecimal("5000.00")),
                new DepartmentCost('Delivery', new BigDecimal("0.00"))
        ] as Set
    }

    def "#expectedDisplayName should cost #expectedCost"() {
        when:
        def actual = company.findDepartmentCost(departmentName)

        then:
        actual.departmentName == expectedDisplayName
        actual.departmentCost == expectedCost

        where:
        departmentName | expectedCost | expectedDisplayName
        'it'           | 3000         | 'IT'
        'finance'      | 5000         | 'Finance'
        'delivery'     | 0            | 'Delivery'
    }
}
