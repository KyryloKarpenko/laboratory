package com.laboratory.model;

import com.laboratory.enumeration.Position;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Position position;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    private List<Lecture> lectures;

    @ManyToOne
    private Department department;

    @PreRemove
    private void preRemove() {
        lectures.forEach(lecture -> lecture.setTeacher(null));
    }

}
