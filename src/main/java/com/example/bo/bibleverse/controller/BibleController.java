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
    @GetMapping("")
    public ResponseEntity<List<BibleVerseDto>> getBibleVerse(@ModelAttribute BibleVerseDto bibleVerseDto) {

        List<BibleVerseDto> result = bibleVerseService.getByBookAndChapter(bibleVerseDto);
        log.debug(result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/input")
    public ResponseEntity<List<BibleVerseDto>> getBibleVerseType1(@ModelAttribute BibleVerseDto bibleVerseDto) {

        List<BibleVerseDto> result = bibleVerseService.getByBookNameAndChapter(bibleVerseDto);
        log.debug(result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/inputString")
    public ResponseEntity<List<BibleVerseDto>> getBibleVerseType3(@RequestParam String bibleSearchString) {
        BibleVerseDto bibleVerseDto = BibleVerseDto.fromString(bibleSearchString);

        List<BibleVerseDto> result = bibleVerseService.getByBookNameAndChapter(bibleVerseDto);
        log.debug(result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
