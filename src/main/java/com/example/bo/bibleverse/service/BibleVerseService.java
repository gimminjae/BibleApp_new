package com.example.bo.bibleverse.service;

import com.example.bo.bibleverse.dto.BibleInfoDto;
import com.example.bo.bibleverse.dto.BibleVerseDto;
import com.example.bo.bibleverse.mapper.BibleVerseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibleVerseService {
    private final BibleVerseMapper bibleVerseMapper;

    public List<BibleVerseDto> getByBookAndChapterSeq(int bookSeq, int chapterSeq) {
        return bibleVerseMapper.findByBookAndChapterSeq(bookSeq, chapterSeq);
    }

    public BibleInfoDto getBookSeqByName(String bookName) {
        return bibleVerseMapper.findBookSeqByBookName(bookName);
    }
}
