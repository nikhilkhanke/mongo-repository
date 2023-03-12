package com.nk.pagination.controller;

import com.nk.pagination.model.Employee;
import com.nk.pagination.model.PaginationRequest;
import com.nk.pagination.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static com.nk.pagination.utils.Constants.Errors.*;
import static com.nk.pagination.utils.Constants.General.*;

@RestController
@RequestMapping(value = "/v1")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/employee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<?> getSearchEmployeeCriteriaPage(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE)
            @Min(value = MIN_OFFSET, message = PAGE_OUT_OF_BOUND) Integer page,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_LIMIT)
            @Min(value = MIN_PAGE_LIMIT, message = INVALID_PAGE_LIMIT)
            @Max(value = MAX_PAGE_LIMIT, message = INVALID_PAGE_LIMIT) Integer limit,
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "orders", required = false) String orders) {

        return new ResponseEntity<>(employeeService.getEmployees(PaginationRequest.builder()
                .page(page)
                .limit(limit)
                .filterAnd(filterAnd)
                .filterOr(filterOr)
                .order(orders)
                .build()), HttpStatus.OK);
    }
}
