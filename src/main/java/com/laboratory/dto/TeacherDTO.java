package com.laboratory.dto;

import com.laboratory.enumeration.Position;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Position position;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<LectureDTO> lectures;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DepartmentDTO department;
}
