package com.nk.pagination.repository.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection ="employee")
public class EmployeeDocument {

    @Id
    private String id;
    @Indexed(unique = true)
    private Integer employeeNo;
    private String firstName;
    private String lastName;
    private String department;
    private String profile;
    private Double salary;
    
}