// #CodeAnalysisService.java
package com.codifai.service;

import com.codifai.model.CodeAnalysis;
import com.codifai.repository.CodeAnalysisRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CodeAnalysisService {

    private final CodeAnalysisRepository repository;
    private final OllamaService ollamaService;

    public CodeAnalysisService(CodeAnalysisRepository repository, OllamaService ollamaService) {
        this.repository = repository;
        this.ollamaService = ollamaService;
    }

    public CodeAnalysis analyzeCode(String filename, String codeContent) {
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

        // Appel à Ollama pour obtenir l'analyse
        String analysisResult = ollamaService.analyze(prompt);

        CodeAnalysis analysis = CodeAnalysis.builder()
                .fileName(filename)
                .codeContent(codeContent)
                .analysisResult(analysisResult)
                .analysisDate(LocalDateTime.now())
                .build();

        return repository.save(analysis);
    }
}
