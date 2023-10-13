package com.example.bo.bibleverse.controller;

import com.example.bo.bibleverse.dto.BibleVerseDto;
import com.example.bo.bibleverse.service.BibleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bibleverse")
@Slf4j
public class BibleController {
    private final BibleService bibleVerseService;

    @GetMapping("/input")
    public ResponseEntity<List<BibleVerseDto>> getBibleVerseType1(@ModelAttribute BibleVerseDto bibleVerseDto) {
        List<BibleVerseDto> result = bibleVerseService.getByBookNameAndChapter(bibleVerseDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<BibleVerseDto>> getBibleVerse(@ModelAttribute BibleVerseDto bibleVerseDto) {
        System.out.println("receive request");
        List<BibleVerseDto> result = bibleVerseService.getByBookAndChapter(bibleVerseDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
