package com.laboratory.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String name;
    private Integer year;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DepartmentDTO department;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<LectureDTO> lectures;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<StudentDTO> students;
}
