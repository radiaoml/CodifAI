package com.codifai;

import com.codifai.model.CodeAnalysis;

import java.time.LocalDateTime;

public class TestBuilder {
    public static void main(String[] args) {
        CodeAnalysis ca = CodeAnalysis.builder()
                .fileName("test.java")
                .codeContent("public class Test {}")
                .analysisResult("It works!")
                .analysisDate(LocalDateTime.now())
                .build();

        System.out.println(ca);
    }
}

