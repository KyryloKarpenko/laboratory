package com.laboratory.converter;

import com.laboratory.dto.TeacherDTO;
import com.laboratory.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter implements Converter<String, TeacherDTO> {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherConverter(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherDTO convert(String id) {
        if (!id.isEmpty()) {
            return new ModelMapper().map(teacherRepository.getById(Long.valueOf(id)), TeacherDTO.class);
        }

        return null;
    }

}
