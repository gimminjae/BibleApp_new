package com.example.bo.bibleverse.service;

import com.example.bo.bibleverse.dto.BibleVerseDto;
import com.example.bo.bibleverse.entity.BibleVerseEntity;
import com.example.bo.bibleverse.enums.BibleInfoEnum;
import com.example.bo.bibleverse.repository.BibleRepository;
import com.example.bo.plan.dto.Bible;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BibleService {
    private static final String EMPTY_RESULT = "성경을 찾을 수 없습니다.";
    private final BibleRepository bibleVerseRepository;
    private final ObjectMapper objectMapper;

    public List<BibleVerseDto> getByBookAndChapter(BibleVerseDto bibleVerseDto) {
        return bibleVerseRepository.findByBookAndChapter(bibleVerseDto.getBook(), bibleVerseDto.getChapter()).stream().map(BibleVerseEntity::toDto).toList();
    }

    public List<BibleVerseDto> getByBookNameAndChapter(BibleVerseDto bibleVerseDto) {
//        List<BibleVerseDto> result = bibleVerseRepository.findByBookNameAndChapter(bibleVerseDto.getBookName(), bibleVerseDto.getChapter()).stream().map(BibleVerseEntity::toDto).toList();
        List<BibleVerseDto> result = new LinkedList<>();

        BibleInfoEnum foundBible = null;
        for (BibleInfoEnum bibleInfoEnum : BibleInfoEnum.values()) {
            if (bibleInfoEnum.getKorBookName().equals(bibleVerseDto.getBookName())) {
                foundBible = bibleInfoEnum;
                break;
            }
        }
        if(foundBible == null) {
            throw new NullPointerException(EMPTY_RESULT);
        }
        // read bible content from resources
        ClassPathResource resource = new ClassPathResource("bible/%s/%s.json".formatted(foundBible.getEnBookName().toLowerCase(), bibleVerseDto.getChapter()));
        try {
            Path path = Paths.get(resource.getURI());
            String content = Files.readString(path);
//            for (String strBible : content) {
//                result.add(objectMapper.readValue(strBible, BibleVerseDto.class));
//            }
            result = objectMapper.readValue(content, List.class);
        } catch (IOException e) {
            throw new NullPointerException(EMPTY_RESULT);
        }
        if(result.isEmpty()) {
            throw new NullPointerException(EMPTY_RESULT);
        }
        return result;
    }
}
