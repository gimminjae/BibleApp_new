package com.example.bo.bibleverse.service;

import com.example.bo.bibleverse.dto.BibleVerseDto;
import com.example.bo.bibleverse.entity.BibleVerseEntity;
import com.example.bo.bibleverse.repository.BibleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibleService {
    private final BibleRepository bibleVerseRepository;

    public List<BibleVerseDto> getByBookAndChapter(BibleVerseDto bibleVerseDto) {
        return bibleVerseRepository.findByBookAndChapter(bibleVerseDto.getBook(), bibleVerseDto.getChapter()).stream().map(BibleVerseEntity::toDto).toList();
    }
}
