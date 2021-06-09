package pl.adamarczynski.demo.springdemo.company.department;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class DepartmentCost {
    String departmentName;
    BigDecimal departmentCost;
}
