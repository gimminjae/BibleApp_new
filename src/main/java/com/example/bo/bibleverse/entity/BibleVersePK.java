package com.example.bo.bibleverse.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BibleVersePK implements Serializable {
    private int book;
    private int chapter;
    private int verse;
}
