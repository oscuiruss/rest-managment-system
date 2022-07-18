package com.example.people2.controllers;


import com.example.people2.dto.SectionDTO;
import com.example.people2.models.Section;
import com.example.people2.service.EmployeeService;
import com.example.people2.service.SectionService;
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
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;
    private final EmployeeService employeeService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SectionController(SectionService sectionService, EmployeeService employeeService) {
        this.sectionService = sectionService;
        this.employeeService = employeeService;
    }

    @GetMapping()
    @ResponseBody
    public List<SectionDTO> findByKeyword(@RequestParam(required = false) String keyword) {
        return sectionService.findAll().stream().map(Utility::sectionConvertToDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    @ResponseBody
    public SectionDTO findById(@PathVariable("id") long id) {
        return Utility.sectionConvertToDto(sectionService.findSectionById(id));
    }

    @PostMapping("/create")
    @ResponseBody
    public SectionDTO create(@Valid @RequestBody SectionDTO sectionDTO) {
        Section section = Utility.dtoConverterToSection(sectionDTO);
        section.setDirector(employeeService.findEmployeeById(sectionDTO.getId()));
        return Utility.sectionConvertToDto(sectionService.save(section));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{id}")
    public void update(@RequestBody SectionDTO sectionDTO, @PathVariable("id") long id) {
        Section section = Utility.dtoConverterToSection(sectionDTO);
        sectionService.update(section, id, sectionDTO.getDirectorId(), sectionDTO.getDepartmentId());
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        sectionService.delete(id);
    }
}
