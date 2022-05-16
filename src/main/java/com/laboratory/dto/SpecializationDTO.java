package com.laboratory.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecializationDTO {
    private Long id;
    private String name;
    private Integer code;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FacultyDTO faculty;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<DepartmentDTO> departments;
}
