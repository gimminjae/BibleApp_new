package com.example.bo.plan.dto;

import com.example.bo.bibleverse.enums.BibleInfoEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Bible {
    //true -> old / false -> new
    private boolean testament;
    private String bookName;
    private int book;
    private int chapter;
    private List<Integer> verseStatus;

    public static Bible makeBibleByEnum(BibleInfoEnum bibleEnum) {
        List<Integer> verseList = new ArrayList<>();
        for(int i = 1; i <= bibleEnum.getChapter(); i++) verseList.add(0);
        return Bible.builder()
                .testament(bibleEnum.getBookSeq() <= 39)
                .bookName(bibleEnum.getKorBookName())
                .book(bibleEnum.getBookSeq())
                .chapter(bibleEnum.getChapter())
                .verseStatus(verseList)
                .build();
    }
    public static List<Bible> createAllList() {
        List<BibleInfoEnum> bibleInfoEnumList = Arrays.stream(BibleInfoEnum.values()).toList();
        List<Bible> bibleList = new ArrayList<>();
        for (BibleInfoEnum bibleInfoEnum : bibleInfoEnumList) {
            bibleList.add(Bible.makeBibleByEnum(bibleInfoEnum));
        }
        return bibleList;
    }
}
