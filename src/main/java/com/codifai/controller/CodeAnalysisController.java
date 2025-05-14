// #CodeAnalysisController.java
package com.codifai.controller;

import com.codifai.model.CodeAnalysis;
import com.codifai.service.CodeAnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/analysis")
public class CodeAnalysisController {

    private final CodeAnalysisService service;

    public CodeAnalysisController(CodeAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCode(@RequestParam("file") MultipartFile file) {
        try {
            // Validation du fichier
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Le fichier est vide !");
            }

            String fileName = file.getOriginalFilename();
            String codeContent = new String(file.getBytes(), StandardCharsets.UTF_8);

            // Logging simple
            System.out.println("Fichier reçu : " + fileName);

            // Analyse via le service
            CodeAnalysis analysisResult = service.analyzeCode(fileName, codeContent);

            return ResponseEntity.ok(analysisResult);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue : " + e.getMessage());
        }
    }
}
