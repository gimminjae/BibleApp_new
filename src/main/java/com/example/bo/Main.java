package com.example.bo;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//파일에서 성경 가져오기 테스트
public class Main {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("bible/genesis/1.json");
        try {
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);
            content.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
