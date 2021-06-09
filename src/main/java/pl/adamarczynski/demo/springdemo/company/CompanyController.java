package pl.adamarczynski.demo.springdemo.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentCost;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentNotFoundException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/company/departments")
public class CompanyController {

    private final Company company;

    public CompanyController(Company company) {
        this.company = company;
    }

    @GetMapping("/cost")
    public Map<String, BigDecimal> allCosts() {
        return company.findAllCosts();
    }

    @GetMapping("/{departmentName}/cost")
    public DepartmentCost allCosts(@PathVariable String departmentName) {
            return company.findDepartmentCost(departmentName);
    }

    @RestControllerAdvice
    public class ExceptionResolver {
        @ExceptionHandler(DepartmentNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public HashMap<String, String> handleDepartmentNotFound(DepartmentNotFoundException e, WebRequest r) {
            return buildErrorResponse(e);
        }

        private HashMap<String, String> buildErrorResponse(DepartmentNotFoundException e) {
            HashMap<String, String> response = new HashMap<>();
            response.put("status", HttpStatus.NOT_FOUND.toString());
            response.put("message", e.getLocalizedMessage());
            return response;
        }
    }
}
