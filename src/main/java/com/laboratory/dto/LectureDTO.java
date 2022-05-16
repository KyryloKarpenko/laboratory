package com.laboratory.dto;

import com.laboratory.enumeration.Day;
import com.laboratory.enumeration.LectureType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {
    private Long id;
    private Integer week;
    private Day day;
    private LectureType type;
    private Integer number;
    private String room;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private GroupDTO group;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeacherDTO teacher;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SubjectDTO subject;
}
