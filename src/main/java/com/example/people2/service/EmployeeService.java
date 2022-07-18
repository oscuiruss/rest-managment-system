package com.example.people2.service;

import com.example.people2.models.Employee;
import com.example.people2.repository.DepartmentRepository;
import com.example.people2.repository.EmployeeRepository;
import com.example.people2.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, SectionRepository sectionRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Employee> findAll(){
        List<Employee> res = employeeRepository.findAll();
        for (Employee l : res){
            System.out.println(l.getName());

        }
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public  List<Employee> findEmployeeByKeyword(String keyword){
        return employeeRepository.findEmployeeByKeyword(keyword);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void update(Employee employee, long id){
        Employee employeeToBeUpdated = employeeRepository.findEmployeeById(id);
        if (employeeToBeUpdated == null){
            throw new IllegalArgumentException();
        }
        employeeToBeUpdated.setName(employee.getName());
        employeeToBeUpdated.setSection(employee.getSection());
        employeeRepository.save(employeeToBeUpdated);
    }

    public void delete(long id){
        Employee oldEmployee = employeeRepository.findById(id).get();
        if (oldEmployee.getSection() == null || oldEmployee.getSection().getDirector() == null){
        } else {
            if (oldEmployee.getSection().getDirector().getId() == oldEmployee.getId()){
                oldEmployee.getSection().setDirector(null);
                sectionRepository.save(oldEmployee.getSection());
            }
        }
        if (oldEmployee.getDepartment() == null || oldEmployee.getDepartment().getDirector() == null){
        } else {
            if (oldEmployee.getDepartment().getDirector().getId() == oldEmployee.getId()){
                oldEmployee.getDepartment().setDirector(null);
                departmentRepository.save(oldEmployee.getDepartment());
            }
        }
        employeeRepository.deleteById(id);
    }
}
