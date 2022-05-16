package com.laboratory.controller;

import com.laboratory.dto.DepartmentDTO;
import com.laboratory.dto.SpecializationDTO;
import com.laboratory.dto.TeacherDTO;
import com.laboratory.model.Department;
import com.laboratory.service.DepartmentService;
import com.laboratory.service.SpecializationService;
import com.laboratory.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final SpecializationService specializationService;
    private final TeacherService teacherService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, SpecializationService specializationService,
                                TeacherService teacherService) {
        this.departmentService = departmentService;
        this.specializationService = specializationService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/create")
    public String getCreateDepartmentPage(Model model) {
        model.addAttribute("departmentDTO", new DepartmentDTO());
        populateDepartment(model);
        return "create_department";
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute("departmentDTO") @Valid DepartmentDTO departmentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Department department = modelMapper.map(departmentDTO, Department.class);
        departmentService.saveDepartment(department);
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/department";
    }

    @GetMapping("/update/{id}")
    public String getUpdateDepartmentPage(@PathVariable Long id, Model model) {
        Department department = departmentService.findDepartmentById(id);
        ModelMapper modelMapper = new ModelMapper();
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
        model.addAttribute("departmentDTO", departmentDTO);
        populateDepartment(model);
        return "create_department";
    }

    private void populateDepartment(Model model) {
        ModelMapper modelMapper = new ModelMapper();
        List<SpecializationDTO> specializations = new ArrayList<>();
        specializationService.findAllSpecializations().forEach(
                specialization -> specializations.add(modelMapper.map(specialization, SpecializationDTO.class)));
        model.addAttribute("specializations", specializations);
        List<TeacherDTO> teachers = new ArrayList<>();
        teacherService.findAllTeachers().forEach(teacher -> teachers.add(modelMapper.map(teacher, TeacherDTO.class)));
        model.addAttribute("teachers", teachers);
    }

}
