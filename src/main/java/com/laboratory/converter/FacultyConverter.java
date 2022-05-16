package com.laboratory.converter;

import com.laboratory.dto.FacultyDTO;
import com.laboratory.repository.FacultyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacultyConverter implements Converter<String, FacultyDTO> {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyConverter(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public FacultyDTO convert(String id) {
        if (!id.isEmpty()) {
            return new ModelMapper().map(facultyRepository.getById(Long.valueOf(id)), FacultyDTO.class);
        }

        return null;
    }

}
