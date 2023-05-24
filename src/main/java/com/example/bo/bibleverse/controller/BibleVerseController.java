package com.example.bo.bibleverse.controller;

import com.example.bo.bibleverse.dto.BibleVerseDto;
import com.example.bo.bibleverse.service.BibleVerseService;
import lombok.Getter;
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
public class BibleVerseController {
    private final BibleVerseService bibleVerseService;
    @GetMapping("")
    public ResponseEntity<List<BibleVerseDto>> getBibleVerse(@ModelAttribute BibleVerseDto bibleVerseDto) {

        List<BibleVerseDto> result = bibleVerseService.getByBookAndChapter(bibleVerseDto);
        log.debug(result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
