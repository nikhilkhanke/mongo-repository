package com.nk.pagination.repository;

import com.nk.mongo.repository.MongoResourceRepository;
import com.nk.pagination.repository.document.EmployeeDocument;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends MongoResourceRepository<EmployeeDocument, String> {

}
