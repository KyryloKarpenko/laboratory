package com.laboratory.controller;

import com.laboratory.dto.GroupDTO;
import com.laboratory.dto.StudentDTO;
import com.laboratory.model.Student;
import com.laboratory.service.GroupService;
import com.laboratory.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/create")
    public String getCreateStudentPage(Model model) {
        model.addAttribute("studentDTO", new StudentDTO());
        ModelMapper modelMapper = new ModelMapper();
        List<GroupDTO> groups = new ArrayList<>();
        groupService.findAllGroups().forEach(group -> groups.add(modelMapper.map(group, GroupDTO.class)));
        model.addAttribute("groups", groups);
        return "create_student";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("studentDTO") @Valid StudentDTO studentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Student student = modelMapper.map(studentDTO, Student.class);
        studentService.saveStudent(student);
        return "redirect:/student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
    public String getUpdateStudentPage(@PathVariable Long id, Model model) {
        Student student = studentService.findStudentById(id);
        ModelMapper modelMapper = new ModelMapper();
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        model.addAttribute("studentDTO", studentDTO);
        List<GroupDTO> groups = new ArrayList<>();
        groupService.findAllGroups().forEach(group -> groups.add(modelMapper.map(group, GroupDTO.class)));
        model.addAttribute("groups", groups);
        return "create_student";
    }

}
