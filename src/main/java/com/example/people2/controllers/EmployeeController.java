package com.example.people2.controllers;

//import com.example.people2.dto.EmployeeConverter;

import com.example.people2.models.Employee;
import com.example.people2.utils.Utility;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.people2.dto.EmployeeDTO;
import com.example.people2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    @ResponseBody
    public List<EmployeeDTO> findByKeyword(@RequestParam(required = false) String keyword) {
        return employeeService.findAll().stream().map(Utility::employeeConvertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public EmployeeDTO findById(@PathVariable("id") long id) {
        return Utility.employeeConvertToDto(employeeService.findEmployeeById(id));
    }


    @PostMapping("/create")
    @ResponseBody
    public EmployeeDTO create(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = Utility.dtoConverterToEmployee(employeeDTO);
        return  Utility.employeeConvertToDto(employeeService.save(employee));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{id}")
    public void update(@RequestBody EmployeeDTO employeeDTO, @PathVariable("id") long id) {
        Employee employee = Utility.dtoConverterToEmployee(employeeDTO);
        employeeService.update(employee, id);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        employeeService.delete(id);
    }

}
