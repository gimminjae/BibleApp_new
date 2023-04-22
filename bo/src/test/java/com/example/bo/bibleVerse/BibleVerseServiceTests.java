package com.example.bo.bibleVerse;

import com.example.bo.bibleverse.dto.BibleVerseDto;
import com.example.bo.bibleverse.mapper.BibleVerseMapper;
import com.example.bo.bibleverse.service.BibleVerseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class BibleVerseServiceTests {
    @Mock
    BibleVerseMapper bibleVerseMapper;
    @InjectMocks
    BibleVerseService bibleVerseService;
    @Test
    @DisplayName("성경 seq, 장 seq로 해당 성경의 장 가져오기")
    void t1() {
        //given
        int bookSeq = 1; //창세기는 1번째 성경
        int chapterSeq = 1;

        //when
        List<BibleVerseDto> bibleVerseDtoList = bibleVerseService.getBySeqs(bookSeq, chapterSeq);

        //then
        assertThat(bibleVerseDtoList.get(0).getContent()).isEqualTo("태초에 하나님이 천지를 창조하시니라.");
    }
}
