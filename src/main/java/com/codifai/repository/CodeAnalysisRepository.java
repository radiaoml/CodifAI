// #CodeAnalysisRepository.java
package com.codifai.repository;

import com.codifai.model.CodeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeAnalysisRepository extends JpaRepository<CodeAnalysis, Long> {
}
