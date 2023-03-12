package com.nk.pagination.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private Integer employeeNo;
    private String firstName;
    private String lastName;
    private String department;
    private String profile;
    private Double salary;
}
