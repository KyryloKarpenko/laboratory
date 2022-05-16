package com.laboratory.controller;

import com.laboratory.dto.SubjectDTO;
import com.laboratory.model.Subject;
import com.laboratory.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String getAllSubjects(Model model) {
        List<Subject> subjects = subjectService.findAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects";
    }

    @GetMapping("/create")
    public String getCreateSubjectPage(Model model) {
        model.addAttribute("subjectDTO", new SubjectDTO());
        return "create_subject";
    }

    @PostMapping("/create")
    public String createSubject(@ModelAttribute("subjectDTO") @Valid SubjectDTO subjectDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        subjectService.saveSubject(subject);
        return "redirect:/subject";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubjectById(id);
        return "redirect:/subject";
    }

    @GetMapping("/update/{id}")
    public String getUpdateSubjectPage(@PathVariable Long id, Model model) {
        Subject subject = subjectService.findSubjectById(id);
        ModelMapper modelMapper = new ModelMapper();
        SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
        model.addAttribute("subjectDTO", subjectDTO);
        return "create_subject";
    }

}
