package com.laboratory.converter;

import com.laboratory.dto.SubjectDTO;
import com.laboratory.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter implements Converter<String, SubjectDTO> {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectConverter(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectDTO convert(String id) {
        if (!id.isEmpty()) {
            return new ModelMapper().map(subjectRepository.getById(Long.valueOf(id)), SubjectDTO.class);
        }

        return null;
    }

}
