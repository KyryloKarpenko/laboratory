package com.laboratory.converter;

import com.laboratory.enumeration.Day;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DayConverter implements Converter<String, Day> {

    @Override
    public Day convert(String day) {
        return Day.valueOf(day);
    }

}
