package com.example.bo.bibleverse.service;

import com.example.bo.bibleverse.mapper.BibleVerseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BibleVerseService {
    private final BibleVerseMapper bibleVerseMapper;
}
