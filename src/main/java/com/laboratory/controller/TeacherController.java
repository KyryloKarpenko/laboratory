package com.laboratory.controller;

import com.laboratory.dto.DepartmentDTO;
import com.laboratory.dto.TeacherDTO;
import com.laboratory.enumeration.Position;
import com.laboratory.model.Teacher;
import com.laboratory.service.DepartmentService;
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
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final DepartmentService departmentService;

    @Autowired
    public TeacherController(TeacherService teacherService, DepartmentService departmentService) {
        this.teacherService = teacherService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAllTeachers(Model model) {
        List<Teacher> teachers = teacherService.findAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    @GetMapping("/create")
    public String getCreateTeacherPage(Model model) {
        model.addAttribute("teacherDTO", new TeacherDTO());
        populateTeacher(model);
        return "create_teacher";
    }

    @PostMapping("/create")
    public String createTeacher(@ModelAttribute("teacherDTO") @Valid TeacherDTO teacherDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
        teacherService.saveTeacher(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teacher";
    }

    @GetMapping("/update/{id}")
    public String getUpdateTeacherPage(@PathVariable Long id, Model model) {
        Teacher teacher = teacherService.findTeacherById(id);
        ModelMapper modelMapper = new ModelMapper();
        TeacherDTO teacherDTO = modelMapper.map(teacher, TeacherDTO.class);
        model.addAttribute("teacherDTO", teacherDTO);
        populateTeacher(model);
        return "create_teacher";
    }

    private void populateTeacher(Model model) {
        ModelMapper modelMapper = new ModelMapper();
        List<DepartmentDTO> departments = new ArrayList<>();
        departmentService.findAllDepartments().forEach(
                department -> departments.add(modelMapper.map(department, DepartmentDTO.class)));
        model.addAttribute("departments", departments);
        model.addAttribute("positions", List.of(Position.values()));
    }

}
