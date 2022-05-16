package com.laboratory.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeacherDTO head;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SpecializationDTO specialization;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<GroupDTO> groups;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeacherDTO> teachers;
}
