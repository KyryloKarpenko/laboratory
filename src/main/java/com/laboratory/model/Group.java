package com.laboratory.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity(name = "univ_group")
@Table(indexes = {
        @Index(name = "group_name_year_department_index", columnList = "name,year,department_id", unique = true)
})
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Student> students;

}
