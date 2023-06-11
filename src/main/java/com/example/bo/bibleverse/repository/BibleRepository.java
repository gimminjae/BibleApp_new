package com.example.bo.bibleverse.repository;

import com.example.bo.bibleverse.entity.BibleVerseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibleRepository extends JpaRepository<BibleVerseEntity, Integer> {
    List<BibleVerseEntity> findByBookAndChapter(int book, int chapter);
    List<BibleVerseEntity> findByBookNameAndChapter(String bookName, int chapter);
}
