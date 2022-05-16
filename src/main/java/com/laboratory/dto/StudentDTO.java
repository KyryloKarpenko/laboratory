package com.laboratory.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private GroupDTO group;
}
