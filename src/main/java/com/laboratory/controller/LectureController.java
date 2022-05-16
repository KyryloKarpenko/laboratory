package com.laboratory.controller;

import com.laboratory.dto.GroupDTO;
import com.laboratory.dto.LectureDTO;
import com.laboratory.dto.SubjectDTO;
import com.laboratory.dto.TeacherDTO;
import com.laboratory.enumeration.Day;
import com.laboratory.enumeration.LectureType;
import com.laboratory.model.Lecture;
import com.laboratory.service.GroupService;
import com.laboratory.service.LectureService;
import com.laboratory.service.SubjectService;
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
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final SubjectService subjectService;

    @Autowired
    public LectureController(LectureService lectureService, TeacherService teacherService, GroupService groupService,
                             SubjectService subjectService) {
        this.lectureService = lectureService;
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String getAllLectures(Model model) {
        List<Lecture> lectures = lectureService.findAllLectures();
        model.addAttribute("lectures", lectures);
        return "lectures";
    }

    @GetMapping("/create")
    public String getCreateLecturePage(Model model) {
        model.addAttribute("lectureDTO", new LectureDTO());
        model.addAttribute("days", List.of(Day.values()));
        model.addAttribute("lectureTypes", List.of(LectureType.values()));
        populateLecture(model);
        return "create_lecture";
    }

    @PostMapping("/create")
    public String createLecture(@ModelAttribute("lectureDTO") @Valid LectureDTO lectureDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Lecture lecture = modelMapper.map(lectureDTO, Lecture.class);
        lectureService.saveLecture(lecture);
        return "redirect:/lecture";
    }

    @GetMapping("/delete/{id}")
    public String deleteLecture(@PathVariable Long id) {
        lectureService.deleteLectureById(id);
        return "redirect:/lecture";
    }

    @GetMapping("/update/{id}")
    public String getUpdateLecturePage(@PathVariable Long id, Model model) {
        Lecture lecture = lectureService.findLectureById(id);
        ModelMapper modelMapper = new ModelMapper();
        LectureDTO lectureDTO = modelMapper.map(lecture, LectureDTO.class);
        model.addAttribute("lectureDTO", lectureDTO);
        model.addAttribute("days", List.of(Day.values()));
        model.addAttribute("lectureTypes", List.of(LectureType.values()));
        populateLecture(model);
        return "create_lecture";
    }

    private void populateLecture(Model model) {
        ModelMapper modelMapper = new ModelMapper();
        List<TeacherDTO> teachers = new ArrayList<>();
        teacherService.findAllTeachers().forEach(teacher -> teachers.add(modelMapper.map(teacher, TeacherDTO.class)));
        model.addAttribute("teachers", teachers);
        List<GroupDTO> groups = new ArrayList<>();
        groupService.findAllGroups().forEach(group -> groups.add(modelMapper.map(group, GroupDTO.class)));
        model.addAttribute("groups", groups);
        List<SubjectDTO> subjects = new ArrayList<>();
        subjectService.findAllSubjects().forEach(subject -> subjects.add(modelMapper.map(subject, SubjectDTO.class)));
        model.addAttribute("subjects", subjects);
    }

}
