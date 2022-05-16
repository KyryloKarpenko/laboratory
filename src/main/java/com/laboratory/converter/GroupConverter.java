package com.laboratory.converter;

import com.laboratory.dto.GroupDTO;
import com.laboratory.repository.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter implements Converter<String, GroupDTO> {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupConverter(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public GroupDTO convert(String id) {
        if (!id.isEmpty()) {
            return new ModelMapper().map(groupRepository.getById(Long.valueOf(id)), GroupDTO.class);
        }

        return null;
    }

}
