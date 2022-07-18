package com.example.people2.utils;

import com.example.people2.dto.DepartmentDTO;
import com.example.people2.dto.EmployeeDTO;
import com.example.people2.dto.SectionDTO;
import com.example.people2.models.Department;
import com.example.people2.models.Employee;
import com.example.people2.models.Section;
import com.example.people2.repository.EmployeeRepository;
import com.example.people2.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class Utility {

    public static EmployeeDTO employeeConvertToDto(Employee employee){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public static Employee dtoConverterToEmployee(EmployeeDTO employeeDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeDTO, Employee.class);
    }

    public static Section dtoConverterToSection(SectionDTO sectionDTO){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(sectionDTO, Section.class);
    }

    public static SectionDTO sectionConvertToDto(Section section) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(section, SectionDTO.class);
    }

    public static DepartmentDTO departmentConvertToDto(Department department) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public static Department dtoConverterToDepartment(DepartmentDTO departmentDTO){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(departmentDTO, Department.class);
    }
}
