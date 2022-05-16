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
@Table(indexes = {
        @Index(name = "department_name_specialization_index", columnList = "name,specialization_id", unique = true)
})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Teacher head;

    @ManyToOne(optional = false)
    private Specialization specialization;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Group> groups;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Teacher> teachers;

    @PreRemove
    private void preRemove() {
        teachers.forEach(teacher -> teacher.setDepartment(null));
        groups.forEach(group -> group.setDepartment(null));
    }

}
