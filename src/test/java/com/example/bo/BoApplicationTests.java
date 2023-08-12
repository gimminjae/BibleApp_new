package com.example.bo;

import com.example.bo.bibleverse.enums.BibleInfoEnum;
import com.example.bo.bibleverse.repository.BibleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 성경 내용 json 파일 만들 때만 사용하는 코드
 */
@SpringBootTest
class BoApplicationTests {
    @Autowired
    BibleRepository bibleRepository;

//    @Test
//    void contextLoads() {
//        try
//        {
//            ObjectMapper objectMapper = new ObjectMapper();
//            BibleInfoEnum[] arr = BibleInfoEnum.values();
//            for (BibleInfoEnum a : arr) {
//                BibleInfoEnum bibleInfoEnum = a;
//                for (int i = 1; i <= bibleInfoEnum.getTotalChapter(); i++) {
//                    String file = "/Users/gimminjae/Desktop/dev/bibleapp/bible-service/src/main/resources/bible/%s/%s.json".formatted(bibleInfoEnum.getEnBookName().toLowerCase(), i);
//                    String lineToAppend = objectMapper.writeValueAsString(bibleRepository.findByBookAndChapter(bibleInfoEnum.getBookSeq(), i));
//                    byte[] byteArr = lineToAppend.getBytes();
//                    Files.write(Paths.get(file), byteArr, StandardOpenOption.CREATE);
//                }
//            }
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//    }
//    @Test
//    void t1() {
//        BibleInfoEnum[] arr = BibleInfoEnum.values();
//        for (BibleInfoEnum bibleInfoEnum : arr) {
//            String path = "/Users/gimminjae/Desktop/dev/bibleapp/bible-service/src/main/resources/bible/%s".formatted(bibleInfoEnum.getEnBookName().toLowerCase()); //폴더 경로
//            File Folder = new File(path);
//
//            // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
//            if (!Folder.exists()) {
//                try{
//                    Folder.mkdir(); //폴더 생성합니다.
//                    System.out.println("폴더가 생성되었습니다.");
//                }
//                catch(Exception e){
//                    e.getStackTrace();
//                }
//            }else {
//                System.out.println("이미 폴더가 생성되어 있습니다.");
//            }
//        }
//    }

}
