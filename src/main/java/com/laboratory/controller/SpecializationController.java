package com.laboratory.controller;

import com.laboratory.dto.FacultyDTO;
import com.laboratory.dto.SpecializationDTO;
import com.laboratory.model.Specialization;
import com.laboratory.service.FacultyService;
import com.laboratory.service.SpecializationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/specialization")
public class SpecializationController {

    private final SpecializationService specializationService;
    private final FacultyService facultyService;

    @Autowired
    public SpecializationController(SpecializationService specializationService, FacultyService facultyService) {
        this.specializationService = specializationService;
        this.facultyService = facultyService;
    }

    @GetMapping
    public String getAllSpecializations(Model model) {
        List<Specialization> specializations = specializationService.findAllSpecializations();
        model.addAttribute("specializations", specializations);
        return "specializations";
    }

    @GetMapping("/create")
    public String getCreateSpecializationPage(Model model) {
        model.addAttribute("specializationDTO", new SpecializationDTO());
        ModelMapper modelMapper = new ModelMapper();
        List<FacultyDTO> faculties = new ArrayList<>();
        facultyService.findAllFaculties().forEach(faculty -> faculties.add(modelMapper.map(faculty, FacultyDTO.class)));
        model.addAttribute("faculties", faculties);
        return "create_specialization";
    }

    @PostMapping("/create")
    public String createSpecialization(@ModelAttribute("specializationDTO") @Valid SpecializationDTO specializationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Specialization specialization = modelMapper.map(specializationDTO, Specialization.class);
        specializationService.saveSpecialization(specialization);
        return "redirect:/specialization";
    }

    @GetMapping("/delete/{id}")
    public String deleteSpecialization(@PathVariable Long id) {
        specializationService.deleteSpecializationById(id);
        return "redirect:/specialization";
    }

    @GetMapping("/update/{id}")
    public String getUpdateSpecializationPage(@PathVariable Long id, Model model) {
        Specialization specialization = specializationService.findSpecializationById(id);
        ModelMapper modelMapper = new ModelMapper();
        SpecializationDTO specializationDTO = modelMapper.map(specialization, SpecializationDTO.class);
        model.addAttribute("specializationDTO", specializationDTO);
        List<FacultyDTO> faculties = new ArrayList<>();
        facultyService.findAllFaculties().forEach(faculty -> faculties.add(modelMapper.map(faculty, FacultyDTO.class)));
        model.addAttribute("faculties", faculties);
        return "create_specialization";
    }

}
