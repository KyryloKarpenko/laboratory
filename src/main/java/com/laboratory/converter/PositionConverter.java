package com.laboratory.converter;

import com.laboratory.enumeration.Position;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PositionConverter implements Converter<String, Position> {

    @Override
    public Position convert(String position) {
        return Position.valueOf(position);
    }

}
