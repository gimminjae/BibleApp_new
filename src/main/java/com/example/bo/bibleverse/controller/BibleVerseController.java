package com.example.bo.bibleverse.controller;

import com.example.bo.bibleverse.dto.BibleVerseDto;
import com.example.bo.bibleverse.service.BibleVerseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bibleverse")
public class BibleVerseController {
    private final BibleVerseService bibleVerseService;
    @GetMapping("")
    public ResponseEntity<List<BibleVerseDto>> getBibleVerse(@RequestParam String bookName,
                                                             @RequestParam int chapterSeq,
                                                             @RequestParam int verseSeq) {
        int bookSeq = bibleVerseService.getBookSeqByName(bookName).getBibleSeq();
        return new ResponseEntity<>(bibleVerseService.getByBookAndChapterSeq(bookSeq, chapterSeq), HttpStatus.OK);
    }
}
