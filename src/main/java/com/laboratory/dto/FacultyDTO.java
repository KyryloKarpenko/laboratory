package com.laboratory.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDTO {
    private Long id;
    private String name;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeacherDTO dean;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<SpecializationDTO> specializations;
}
