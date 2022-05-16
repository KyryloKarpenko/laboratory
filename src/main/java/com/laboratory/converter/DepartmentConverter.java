package com.laboratory.converter;

import com.laboratory.dto.DepartmentDTO;
import com.laboratory.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter implements Converter<String, DepartmentDTO> {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentConverter(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDTO convert(String id) {
        if (!id.isEmpty()) {
            return new ModelMapper().map(departmentRepository.getById(Long.valueOf(id)), DepartmentDTO.class);
        }

        return null;
    }

}
