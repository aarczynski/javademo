package pl.adamarczynski.demo.springdemo.company;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentCost;
import pl.adamarczynski.demo.springdemo.company.department.DepartmentNotFoundException;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/company/departments")
public class CompanyController {

    private final Company company;

    public CompanyController(Company company) {
        this.company = company;
    }

    @GetMapping("/cost")
    public List<DepartmentCost> allCosts() {
        log.info("Received request for cost of all departments");
        return company.findAllCosts();
    }

    @GetMapping("/{departmentName}/cost")
    public DepartmentCost allCosts(@PathVariable String departmentName) {
        log.info("Received request for cost of {} department", departmentName);
        return company.findDepartmentCost(departmentName);
    }

    @RestControllerAdvice
    public class ExceptionResolver {
        @ExceptionHandler(DepartmentNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public HashMap<String, String> handleDepartmentNotFound(DepartmentNotFoundException e, WebRequest r) {
            log.error("Requested department not found - returning 404", e);
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
