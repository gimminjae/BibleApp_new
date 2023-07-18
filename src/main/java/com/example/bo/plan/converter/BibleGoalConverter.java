package com.example.bo.plan.converter;

import com.example.bo.plan.dto.Bible;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class BibleGoalConverter implements AttributeConverter<List<Bible>, String> {
    private static final String SPLIT_CHAR = ";";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Bible> bibles) {
        if (bibles == null) return "";
        return String.join(SPLIT_CHAR, bibles.stream().map(data -> {
            try {
                return objectMapper.writeValueAsString(data);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).toList());
    }

    @Override
    public List<Bible> convertToEntityAttribute(String bibles) {
        if (!StringUtils.hasText(bibles)) return new ArrayList<>();
        List<String> bibleStringList = Arrays.stream(bibles.split(SPLIT_CHAR)).toList();
        List<Bible> result = new ArrayList<>();
        try {
            for (String s : bibleStringList) {
                result.add(objectMapper.readValue(s, Bible.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
