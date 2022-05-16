package com.laboratory.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "subject")
    @ToString.Exclude
    private List<Lecture> lectures;

    @PreRemove
    private void preRemove() {
        lectures.forEach(lecture -> lecture.setSubject(null));
    }

}
