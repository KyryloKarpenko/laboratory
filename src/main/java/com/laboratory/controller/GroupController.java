package com.laboratory.controller;

import com.laboratory.dto.DepartmentDTO;
import com.laboratory.dto.GroupDTO;
import com.laboratory.model.Group;
import com.laboratory.service.DepartmentService;
import com.laboratory.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;
    private final DepartmentService departmentService;

    @Autowired
    public GroupController(GroupService groupService, DepartmentService departmentService) {
        this.groupService = groupService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAllGroups(Model model) {
        List<Group> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("/create")
    public String getCreateGroupPage(Model model) {
        model.addAttribute("groupDTO", new GroupDTO());
        ModelMapper modelMapper = new ModelMapper();
        List<DepartmentDTO> departments = new ArrayList<>();
        departmentService.findAllDepartments().forEach(
                department -> departments.add(modelMapper.map(department, DepartmentDTO.class)));
        model.addAttribute("departments", departments);
        return "create_group";
    }

    @PostMapping("/create")
    public String createGroup(@ModelAttribute("groupDTO") @Valid GroupDTO groupDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Group group = modelMapper.map(groupDTO, Group.class);
        groupService.saveGroup(group);
        return "redirect:/group";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.deleteGroupById(id);
        return "redirect:/group";
    }

    @GetMapping("/update/{id}")
    public String getUpdateGroupPage(@PathVariable Long id, Model model) {
        Group group = groupService.findGroupById(id);
        ModelMapper modelMapper = new ModelMapper();
        GroupDTO groupDTO = modelMapper.map(group, GroupDTO.class);
        model.addAttribute("groupDTO", groupDTO);
        List<DepartmentDTO> departments = new ArrayList<>();
        departmentService.findAllDepartments().forEach(
                department -> departments.add(modelMapper.map(department, DepartmentDTO.class)));
        model.addAttribute("departments", departments);
        return "create_group";
    }

}
