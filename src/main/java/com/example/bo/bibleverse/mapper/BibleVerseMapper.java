package com.example.bo.bibleverse.mapper;

import com.example.bo.bibleverse.dto.BibleInfoDto;
import com.example.bo.bibleverse.dto.BibleVerseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BibleVerseMapper {
    @Select("""
            select book_seq, chapter_seq, verse_seq, content
            from bible_verse
            where book_seq = #{bookSeq} and chapter_seq = #{chapterSeq}
            """)
    List<BibleVerseDto> findByBookAndChapterSeq(@Param("bookSeq") int bookSeq, @Param("chapterSeq") int chapterSeq);

    @Select("""
            select bible_seq, name
            from bible_info
            where name = #{bookName}
            """)
    BibleInfoDto findBookSeqByBookName(@Param("bookName") String bookName);
}
