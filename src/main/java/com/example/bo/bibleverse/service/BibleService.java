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
        BibleInfoEnum foundBible = getBibleInfoEnumByBookSeq(bibleVerseDto.getBook());
        System.out.println("getByBookAndChapter");
        return getBibleFromResourcesByBibleInfo(foundBible, bibleVerseDto.getChapter());
    }

    public List<BibleVerseDto> getByBookNameAndChapter(BibleVerseDto bibleVerseDto) {
        BibleInfoEnum foundBible = getBibleInfoEnumByKorBookName(bibleVerseDto.getBookName());
        List<BibleVerseDto> result = getBibleFromResourcesByBibleInfo(foundBible, bibleVerseDto.getChapter());
        if(result.isEmpty()) {
            throw new NullPointerException(EMPTY_RESULT);
        }
        return result;
    }

    private List<BibleVerseDto> getBibleFromResourcesByBibleInfo(BibleInfoEnum foundBible, int chapter) {
        System.out.println("getBibleFormResourcesByBibleInfo");
        ClassPathResource resource = new ClassPathResource("bible/%s/%s.json".formatted(foundBible.getEnBookName().toLowerCase(), chapter));
        System.out.println("bible/%s/%s.json".formatted(foundBible.getEnBookName().toLowerCase(), chapter));
        try {
            System.out.println(resource.getURI());
            System.out.println(Paths.get(resource.getURI()));
            String content = Files.readString(Paths.get(resource.getURI()));
            System.out.println(content);
            return objectMapper.readValue(content, List.class);
        } catch (IOException e) {
            throw new NullPointerException(EMPTY_RESULT);
        }
    }

    private BibleInfoEnum getBibleInfoEnumByKorBookName(String bookName) {
        for (BibleInfoEnum bibleInfoEnum : BibleInfoEnum.values()) {
            if (bibleInfoEnum.getKorBookName().equals(bookName)) {
                return bibleInfoEnum;
            }
        }
        throw new NullPointerException(EMPTY_RESULT);
    }
    private BibleInfoEnum getBibleInfoEnumByBookSeq(int bookSeq) {
        for (BibleInfoEnum bibleInfoEnum : BibleInfoEnum.values()) {
            if (bibleInfoEnum.getBookSeq() == bookSeq) {
                return bibleInfoEnum;
            }
        }
        throw new NullPointerException(EMPTY_RESULT);
    }
}
