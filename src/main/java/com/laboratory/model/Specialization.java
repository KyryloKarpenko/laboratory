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
        @Index(name = "specialization_name_code_faculty_index", columnList = "name,code,faculty_id", unique = true)
})
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer code;

    @ManyToOne(optional = false)
    private Faculty faculty;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Department> departments;

}
