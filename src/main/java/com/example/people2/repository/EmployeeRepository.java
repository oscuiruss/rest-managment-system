package com.example.people2.repository;

import com.example.people2.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAll();

    Employee findEmployeeById(long id);

    @Query(value = "SELECT * FROM Employee WHERE lower(concat(name))"
            + "LIKE lower(concat('%', ?1, '%'))",
            nativeQuery = true)
    List<Employee> findEmployeeByKeyword(String keyword);
}
