package com.example.bo.bibleverse.enums;

import lombok.Getter;

@Getter
public enum BibleInfoEnum {
    Genesis(1, "창세기", 50),
    Exodus(2, "출애굽기", 40),
    Leviticus(3, "레위기", 27),
    Numbers(4, "민수기", 36),
    Deuteronomy(5, "신명기", 34),
    Joshua(6, "여호수아", 24),
    Judges(7, "사사기", 21),
    Ruth(8, "룻기", 4),
    Samuel1(9, "사무엘상", 31),
    Samuel2(10, "사무엘하", 24),
    Kings1(11, "열왕기상", 22),
    Kings2(12, "열왕기하", 25),
    Chronicles1(13, "역대상", 29),
    Chronicles2(14, "역대하", 36),
    Ezra(15, "에스라", 10),
    Nehemiah(16, "느헤미야", 13),
    Esther(17, "에스더", 10),
    Job(18, "욥기", 42),
    Psalms(19, "시편", 150),
    Proverbs(20, "잠언", 31),
    Ecclesiastes(21, "전도서", 12),
    SongOfSongs(22, "아가", 8),
    Isaiah(23, "이사야", 66),
    Jeremiah(24, "예레미야", 52),
    Lamentations(25, "예레미야애가", 5),
    Ezekiel(26, "에스겔", 48),
    Daniel(27, "다니엘", 12),
    Hosea(28, "호세아", 14),
    Joel(29, "요엘", 3),
    Amos(30, "아모스", 9),
    Obadiah(31, "오바댜", 1),
    Jonah(32, "요나", 4),
    Micah(33, "미가", 7),
    Nahum(34, "나훔", 3),
    Habakkuk(35, "하박국", 3),
    Zephaniah(36, "스바냐", 3),
    Haggai(37, "학개", 2),
    Zechariah(38, "스가랴", 14),
    Malachi(39, "말라기", 4),
    Matthew(40, "마태복음", 28),
    Mark(41, "마가복음", 16),
    Luke(42, "누가복음", 24),
    John(43, "요한복음", 21),
    Acts(44, "사도행전", 28),
    Romans(45, "로마서", 16),
    Corinthians1(46, "고린도전서", 16),
    Corinthians2(47, "고린도후서", 13),
    Galatians(48, "갈라디아서", 6),
    Ephesians(49, "에베소서", 6),
    Philippians(50, "빌립보서", 4),
    Colossians(51, "골로새서", 4),
    Thessalonians1(52, "데살로니가전서", 5),
    Thessalonians2(53, "데살로니가후서", 3),
    Timothy1(54, "디모데전서", 6),
    Timothy2(55, "디모데후서", 4),
    Titus(56, "디도서", 3),
    Philemon(57, "빌레몬서", 1),
    Hebrews(58, "히브리서", 12),
    James(59, "야고보서", 5),
    Peter1(60, "베드로전서", 5),
    Peter2(61, "베드로후서", 3),
    John1(62, "요한1서", 5),
    John2(63, "요한2서", 1),
    John3(64, "요한3서", 1),
    Jude(65, "유다서", 1),
    Revelation(66, "요한계시록", 22);
    BibleInfoEnum(int bookSeq, String bookName, int chapter) {
        this.bookSeq = bookSeq;
        this.bookName = bookName;
        this.chapter = chapter;
    }
    private final String bookName;
    private final int bookSeq;
    private final int chapter;

    public String getBookName() {
        return bookName;
    }

    public int getBookSeq() {
        return bookSeq;
    }

    public int getChapter() {
        return chapter;
    }

    public BibleInfoEnum getByBookSeq(int bookSeq) {
        return null;
    }
}
