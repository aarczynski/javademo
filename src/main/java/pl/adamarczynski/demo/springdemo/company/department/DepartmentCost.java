package pl.adamarczynski.demo.springdemo.company.department;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class DepartmentCost {
    String departmentName;
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    BigDecimal departmentCost;

    public DepartmentCost(String departmentName, BigDecimal departmentCost) {
        this.departmentName = departmentName;
        this.departmentCost = departmentCost.setScale(2, RoundingMode.HALF_UP);
    }
}
