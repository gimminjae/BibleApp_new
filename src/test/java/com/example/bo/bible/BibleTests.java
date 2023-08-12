package com.example.bo.bible;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BibleTests {
    @Test
    void t1() throws IOException {
        ClassPathResource resource = new ClassPathResource("/genesis/1.json");
        try {
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);
            content.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
