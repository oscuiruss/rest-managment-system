package com.example.people2.service;

import com.example.people2.models.Section;
import com.example.people2.repository.DepartmentRepository;
import com.example.people2.repository.EmployeeRepository;
import com.example.people2.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public SectionService(SectionRepository sectionRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.sectionRepository = sectionRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    public Section findSectionById(long id) {
        return sectionRepository.findById(id).orElse(null);
    }

    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    public void update(Section section, long id, long directorId, long depId) {
        Section sectionToBeUpdated = sectionRepository.findSectionById(id);
        if (sectionToBeUpdated == null) {
            throw new IllegalArgumentException();
        }
        sectionToBeUpdated.setDirector(section.getDirector());
        sectionToBeUpdated.setEmployees(section.getEmployees());
        sectionToBeUpdated.setName(section.getName());
        sectionToBeUpdated.setDirector(employeeRepository.findEmployeeById(directorId));
        sectionToBeUpdated.setDepartment(departmentRepository.findDepartmentById(depId));
        sectionRepository.save(sectionToBeUpdated);

    }

    public void delete(long id) {
        sectionRepository.deleteById(id);
    }
}
