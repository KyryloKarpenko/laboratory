package com.laboratory.model;

import com.laboratory.enumeration.Day;
import com.laboratory.enumeration.LectureType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "lecture_teacher_week_day_number_index", columnList = "teacher_id,week,day,number", unique = true),
        @Index(name = "lecture_group_week_day_number_index", columnList = "group_id,week,day,number", unique = true),
        @Index(name = "lecture_room_week_day_number_index", columnList = "room,week,day,number", unique = true)
})
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer week;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Day day;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LectureType type;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private String room;

    @ManyToOne
    private Group group;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Subject subject;

}
