// #ZipUtils.java
package com.codifai.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.nio.file.*;

public class ZipUtils {

    public static List<String> extractZip(String zipFilePath) throws IOException {
        List<String> extractedFiles = new ArrayList<>();
        Path destDir = Paths.get(System.getProperty("java.io.tmpdir"), "extracted_files");

        // Créer le répertoire de destination si nécessaire
        Files.createDirectories(destDir);

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Paths.get(zipFilePath)))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }

                Path filePath = destDir.resolve(entry.getName());
                Files.createDirectories(filePath.getParent());

                try {
                    Files.copy(zipInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    extractedFiles.add(filePath.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return extractedFiles;
    }
}
