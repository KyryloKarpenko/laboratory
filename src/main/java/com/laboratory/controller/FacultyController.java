package com.laboratory.controller;

import com.laboratory.dto.FacultyDTO;
import com.laboratory.dto.TeacherDTO;
import com.laboratory.model.Faculty;
import com.laboratory.service.FacultyService;
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
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;
    private final TeacherService teacherService;

    @Autowired
    public FacultyController(FacultyService facultyService, TeacherService teacherService) {
        this.facultyService = facultyService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getAllFaculties(Model model) {
        List<Faculty> faculties = facultyService.findAllFaculties();
        model.addAttribute("faculties", faculties);
        return "faculties";
    }

    @GetMapping("/create")
    public String getCreateFacultyPage(Model model) {
        model.addAttribute("facultyDTO", new FacultyDTO());
        ModelMapper modelMapper = new ModelMapper();
        List<TeacherDTO> teachers = new ArrayList<>();
        teacherService.findAllTeachers().forEach(teacher -> teachers.add(modelMapper.map(teacher, TeacherDTO.class)));
        model.addAttribute("teachers", teachers);
        return "create_faculty";
    }

    @PostMapping("/create")
    public String createFaculty(@ModelAttribute("facultyDTO") @Valid FacultyDTO facultyDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
        facultyService.saveFaculty(faculty);
        return "redirect:/faculty";
    }

    @GetMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFacultyById(id);
        return "redirect:/faculty";
    }

    @GetMapping("/update/{id}")
    public String getUpdateFacultyPage(@PathVariable Long id, Model model) {
        Faculty faculty = facultyService.findFacultyById(id);
        ModelMapper modelMapper = new ModelMapper();
        FacultyDTO facultyDTO = modelMapper.map(faculty, FacultyDTO.class);
        model.addAttribute("facultyDTO", facultyDTO);
        List<TeacherDTO> teachers = new ArrayList<>();
        teacherService.findAllTeachers().forEach(teacher -> teachers.add(modelMapper.map(teacher, TeacherDTO.class)));
        model.addAttribute("teachers", teachers);
        return "create_faculty";
    }

}
