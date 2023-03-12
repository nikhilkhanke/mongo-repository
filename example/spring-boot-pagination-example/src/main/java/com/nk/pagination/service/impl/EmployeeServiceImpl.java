package com.nk.pagination.service.impl;

import com.nk.mongo.model.MongoFilterCondition;
import com.nk.mongo.service.MongoFilterBuilderService;
import com.nk.mongo.util.GenericFilterCriteriaBuilder;
import com.nk.pagination.model.Employee;
import com.nk.pagination.model.PageResponse;
import com.nk.pagination.model.PaginationRequest;
import com.nk.pagination.repository.EmployeeRepository;
import com.nk.pagination.repository.document.EmployeeDocument;
import com.nk.pagination.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    MongoFilterBuilderService filterBuilderService;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public PageResponse<Employee> getEmployees(PaginationRequest request) {
        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();
        Pageable pageable = filterBuilderService.getPageable(request.getLimit(), request.getPage(), request.getOrder());
        List<MongoFilterCondition> andConditions = filterBuilderService.createFilterCondition(request.getFilterAnd());
        List<MongoFilterCondition> orConditions = filterBuilderService.createFilterCondition(request.getFilterOr());
        Query query = filterCriteriaBuilder.addCondition(andConditions, orConditions);
        Page<EmployeeDocument> employeeDocuments = employeeRepository.findAll(query, pageable);
        PageResponse<Employee> pageResponse = new PageResponse<>();
        pageResponse.setPage(employeeDocuments, map(employeeDocuments.getContent()));
        return pageResponse;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(EmployeeDocument.builder()
                .employeeNo(employee.getEmployeeNo())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .department(employee.getDepartment())
                .profile(employee.getProfile())
                .salary(employee.getSalary())
                .build());
    }

    private List<Employee> map(List<EmployeeDocument> employeeDocuments) {
        return employeeDocuments.stream()
                .map(employeeDocument -> Employee.builder().employeeNo(employeeDocument.getEmployeeNo())
                        .firstName(employeeDocument.getFirstName())
                        .lastName(employeeDocument.getLastName())
                        .department(employeeDocument.getDepartment())
                        .profile(employeeDocument.getProfile())
                        .salary(employeeDocument.getSalary())
                        .build())
                .collect(Collectors.toList());
    }
}