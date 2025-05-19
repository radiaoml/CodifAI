package com.codifai.service;

import com.codifai.model.CodeAnalysis;
import com.codifai.repository.CodeAnalysisRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class CodeAnalysisService {

    private final CodeAnalysisRepository repository;
    private final OllamaService ollamaService;

    public CodeAnalysisService(CodeAnalysisRepository repository, OllamaService ollamaService) {
        this.repository = repository;
        this.ollamaService = ollamaService;
    }

    public CodeAnalysis analyzeCode(String filename) {
        String codeContent;
        try {
            codeContent = Files.readString(Path.of(filename));
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }

        if (codeContent == null || codeContent.isBlank()) {
            throw new IllegalArgumentException("Le contenu du code ne peut pas être vide.");
        }

        // Construction du prompt pour une analyse complète
        String prompt = String.format("""
        Tu es un expert en revue de code.
        Voici un extrait de code source :
        ---
        %s
        ---
        1. Donne un résumé clair de ce que fait ce code.
        2. Liste les erreurs potentielles ou les mauvaises pratiques.
        3. Suggère des améliorations.
        4. Évalue sa complexité et sa maintenabilité.
        """, codeContent);

        // Appel à Ollama pour obtenir l’analyse
        String response = ollamaService.analyzeWithLlama(prompt);

        // Création de l'objet CodeAnalysis avec le résultat de l’analyse
        CodeAnalysis analysis = CodeAnalysis.builder()
                .fileName(filename)
                .codeContent(codeContent)
                .analysisResult(response)
                .analysisDate(LocalDateTime.now())
                .build();

        return repository.save(analysis);
    }

}
