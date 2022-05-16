package com.laboratory.converter;

import com.laboratory.enumeration.LectureType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LectureTypeConverter implements Converter<String, LectureType> {

    @Override
    public LectureType convert(String lectureType) {
        return LectureType.valueOf(lectureType);
    }

}
