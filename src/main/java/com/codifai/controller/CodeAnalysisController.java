// #CodeAnalysisController.java
package com.codifai.controller;

import com.codifai.model.CodeAnalysis;
import com.codifai.service.CodeAnalysisService;
import com.codifai.util.ZipUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/analysis")
public class CodeAnalysisController {

    private final CodeAnalysisService service;

    public CodeAnalysisController(CodeAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCode(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("Étape 1 : Fichier reçu -> " + file.getOriginalFilename());

            if (file.isEmpty()) {
                System.out.println("Fichier vide !");
                return ResponseEntity.badRequest().body("Le fichier est vide !");
            }

            String fileName = file.getOriginalFilename();
            if (fileName == null || !fileName.endsWith(".zip")) {
                System.out.println("Ce n’est pas un ZIP !");
                return ResponseEntity.badRequest().body("Veuillez uploader un fichier ZIP !");
            }

            // Étape 2 : Sauvegarde temporaire
            String zipPath = System.getProperty("java.io.tmpdir") + fileName;
            Files.write(Paths.get(zipPath), file.getBytes());
            System.out.println("Étape 2 : ZIP sauvegardé à " + zipPath);

            // Étape 3 : Extraction
            List<String> extractedFiles = ZipUtils.extractZip(zipPath);
            System.out.println("Étape 3 : Fichiers extraits -> " + extractedFiles);

            // Étape 4 : Lecture des fichiers
            StringBuilder codeContent = new StringBuilder();
            for (String path : extractedFiles) {
                System.out.println("Lecture de " + path);
                if (path.endsWith(".java") || path.endsWith(".py")) {
                    String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
                    codeContent.append(content).append("\n\n");
                }
            }

            if (codeContent.isEmpty()) {
                System.out.println("Aucun contenu lisible");
                return ResponseEntity.badRequest().body("Aucun fichier .java ou .py valide trouvé dans l’archive.");
            }

            System.out.println("Étape 5 : Contenu prêt pour analyse");

            // Étape 6 : Analyse
            CodeAnalysis analysis = service.analyzeCode(codeContent.toString());
            System.out.println("Étape 6 : Analyse terminée");

            return ResponseEntity.ok(analysis);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue : " + e.getMessage());
        }
    }
}
