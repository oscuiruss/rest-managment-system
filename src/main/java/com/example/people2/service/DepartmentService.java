package com.example.people2.service;

import com.example.people2.models.Department;
import com.example.people2.repository.DepartmentRepository;
import com.example.people2.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this. departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findDepartmentById(long id) {
        return  departmentRepository.findById(id).orElse(null);
    }

    public Department save(Department department) {
        return  departmentRepository.save(department);
    }

    public void update(Department department, long id, long directorId) {
        Department departmentToBeUpdated =  departmentRepository.findDepartmentById(id);
        if (departmentToBeUpdated == null) {
            throw new IllegalArgumentException();
        }
        departmentToBeUpdated.setDirector(department.getDirector());
        departmentToBeUpdated.setSections(department.getSections());
        departmentToBeUpdated.setName(department.getName());
        departmentToBeUpdated.setDirector(employeeRepository.findEmployeeById(directorId));
        departmentRepository.save(departmentToBeUpdated);
    }

    public void delete(long id) {
        departmentRepository.deleteById(id);
    }
}
