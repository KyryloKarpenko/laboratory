package com.laboratory.converter;

import com.laboratory.dto.SpecializationDTO;
import com.laboratory.repository.SpecializationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpecializationConverter implements Converter<String, SpecializationDTO> {

    private final SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationConverter(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public SpecializationDTO convert(String id) {
        if (!id.isEmpty()) {
            return new ModelMapper().map(specializationRepository.getById(Long.valueOf(id)), SpecializationDTO.class);
        }

        return null;
    }

}
