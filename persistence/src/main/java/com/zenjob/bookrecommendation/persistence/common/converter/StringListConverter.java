package com.zenjob.bookrecommendation.persistence.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(final List<String> list) {
        return String.join(",", list);
    }

    @Override
    public List<String> convertToEntityAttribute(final String joined) {
        return new ArrayList<>(Arrays.asList(joined.split(",")));
    }

}
