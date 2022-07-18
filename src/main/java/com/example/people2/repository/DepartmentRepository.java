package com.example.people2.repository;

import com.example.people2.models.Department;
import com.example.people2.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAll();

    Department findDepartmentById(long id);

    @Query(value = "SELECT * FROM Department WHERE lower(concat(name))"
            + "LIKE lower(concat('%', ?1, '%'))",
            nativeQuery = true)
    List<Department> findDepartmentByKeyword(String keyword);
}

