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

    public static BibleVerseDto fromString(String searchString) {
        String[] searchArr = searchString.split(" ");
        return BibleVerseDto.builder()
                .bookName(searchArr[0])
                .chapter(searchArr.length > 1 ? Integer.parseInt(searchArr[1]) : 1)
                .build();
    }
}
