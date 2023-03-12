package com.nk.pagination.service;

import com.nk.pagination.model.Employee;
import com.nk.pagination.model.PageResponse;
import com.nk.pagination.model.PaginationRequest;

public interface EmployeeService {
    PageResponse<Employee> getEmployees(PaginationRequest request);
    void saveEmployee(Employee employee);
}
