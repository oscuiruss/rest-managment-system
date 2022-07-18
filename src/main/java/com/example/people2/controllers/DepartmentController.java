package com.example.people2.controllers;

import com.example.people2.dto.DepartmentDTO;
import com.example.people2.dto.EmployeeDTO;
import com.example.people2.models.Department;
import com.example.people2.models.Employee;
import com.example.people2.service.DepartmentService;
import com.example.people2.service.EmployeeService;
import com.example.people2.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    @ResponseBody
    public List<DepartmentDTO> findByKeyword(@RequestParam(required = false) String keyword) {
        return departmentService.findAll().stream().map(Utility::departmentConvertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public DepartmentDTO findById(@PathVariable("id") long id) {
        return Utility.departmentConvertToDto(departmentService.findDepartmentById(id));
    }


    @PostMapping("/create")
    @ResponseBody
    public DepartmentDTO create(@Valid @RequestBody DepartmentDTO departmentDTO) {
        Department department = Utility.dtoConverterToDepartment(departmentDTO);
        return  Utility.departmentConvertToDto(departmentService.save(department));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{id}")
    public void update(@RequestBody DepartmentDTO departmentDTO, @PathVariable("id") long id) {
        Department department = Utility.dtoConverterToDepartment(departmentDTO);
        departmentService.update(department, id, departmentDTO.getDirectorId());
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        departmentService.delete(id);
    }

}

