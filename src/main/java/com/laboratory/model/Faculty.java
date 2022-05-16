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
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Specialization> specializations;

    @OneToOne
    private Teacher dean;

}
