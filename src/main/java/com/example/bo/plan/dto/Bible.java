package com.example.bo.plan.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bible {
    private boolean testament;
    private String bookName;
    private int book;
    private int chapter;
    private int verse;
    private List<Integer> bibleStatus;
}
