package com.example.bo.bibleverse.enums;

import lombok.Getter;

@Getter
public enum BibleInfoEnum {
    Genesis(1, "창세기", "Genesis", 50),
    Exodus(2, "출애굽기", "Exodus", 40),
    Leviticus(3, "레위기", "Leviticus", 27),
    Numbers(4, "민수기", "Numbers", 36),
    Deuteronomy(5, "신명기", "Deuteronomy", 34),
    Joshua(6, "여호수아", "Joshua", 24),
    Judges(7, "사사기", "Judges", 21),
    Ruth(8, "룻기", "Ruth", 4),
    Samuel1(9, "사무엘상", "1Samuel", 31),
    Samuel2(10, "사무엘하", "2Samuel", 24),
    Kings1(11, "열왕기상", "1Kings", 22),
    Kings2(12, "열왕기하", "2Kings", 25),
    Chronicles1(13, "역대상", "1Chronicles", 29),
    Chronicles2(14, "역대하", "2Chronicles", 36),
    Ezra(15, "에스라", "Ezra", 10),
    Nehemiah(16, "느헤미야", "Nehemiah", 13),
    Esther(17, "에스더", "Esther", 10),
    Job(18, "욥기", "Job", 42),
    Psalms(19, "시편", "Psalms", 150),
    Proverbs(20, "잠언", "Proverbs", 31),
    Ecclesiastes(21, "전도서", "Ecclesiastes", 12),
    SongOfSongs(22, "아가", "Song of Songs", 8),
    Isaiah(23, "이사야", "Isaiah", 66),
    Jeremiah(24, "예레미야", "Jeremiah", 52),
    Lamentations(25, "예레미야애가", "Lamentations", 5),
    Ezekiel(26, "에스겔", "Ezekiel", 48),
    Daniel(27, "다니엘", "Daniel", 12),
    Hosea(28, "호세아", "Hosea", 14),
    Joel(29, "요엘", "Joel", 3),
    Amos(30, "아모스", "Amos", 9),
    Obadiah(31, "오바댜", "Obadiah", 1),
    Jonah(32, "요나", "Jonah", 4),
    Micah(33, "미가", "Micah", 7),
    Nahum(34, "나훔", "Nahum", 3),
    Habakkuk(35, "하박국", "Habakkuk", 3),
    Zephaniah(36, "스바냐", "Zephaniah", 3),
    Haggai(37, "학개", "Haggai", 2),
    Zechariah(38, "스가랴", "Zechariah", 14),
    Malachi(39, "말라기", "Malachi", 4),
    Matthew(40, "마태복음", "Matthew", 28),
    Mark(41, "마가복음", "Mark", 16),
    Luke(42, "누가복음", "Luke", 24),
    John(43, "요한복음", "John", 21),
    Acts(44, "사도행전", "Acts", 28),
    Romans(45, "로마서", "Romans", 16),
    Corinthians1(46, "고린도전서", "1Corinthians", 16),
    Corinthians2(47, "고린도후서", "2Corinthians", 13),
    Galatians(48, "갈라디아서", "Galatians", 6),
    Ephesians(49, "에베소서", "Ephesians", 6),
    Philippians(50, "빌립보서", "Philippians", 4),
    Colossians(51, "골로새서", "Colossians", 4),
    Thessalonians1(52, "데살로니가전서", "1Thessalonians", 5),
    Thessalonians2(53, "데살로니가후서", "2Thessalonians", 3),
    Timothy1(54, "디모데전서", "1Timothy", 6),
    Timothy2(55, "디모데후서", "2Timothy", 4),
    Titus(56, "디도서", "Titus", 3),
    Philemon(57, "빌레몬서", "Philemon", 1),
    Hebrews(58, "히브리서", "Hebrews", 12),
    James(59, "야고보서", "James", 5),
    Peter1(60, "베드로전서", "1Peter", 5),
    Peter2(61, "베드로후서", "2Peter", 3),
    John1(62, "요한1서", "1John", 5),
    John2(63, "요한2서", "2John", 1),
    John3(64, "요한3서", "3John", 1),
    Jude(65, "유다서", "Jude", 1),
    Revelation(66, "요한계시록", "Revelation", 22);

    private final int bookSeq;
    private final String korBookName;
    private final String enBookName;
    private final int chapter;

    BibleInfoEnum(int bookSeq, String korBookName, String enBookName, int chapter) {
        this.bookSeq = bookSeq;
        this.korBookName = korBookName;
        this.enBookName = enBookName;
        this.chapter = chapter;
    }

    // Getter methods...
}
