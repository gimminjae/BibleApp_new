package com.example.bo.bibleverse.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BibleVerseDto {
    private int book;
    private String bookName;
    private int chapter;
    private Integer verse;
    private String content;
}
