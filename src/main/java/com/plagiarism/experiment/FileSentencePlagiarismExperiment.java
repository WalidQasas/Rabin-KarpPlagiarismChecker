package com.plagiarism.experiment;

import com.plagiarism.algorithms.RabinKarpListDetector;
import com.plagiarism.algorithms.PlagiarismDetector;
import com.plagiarism.algorithms.RabinKarpHashTableDetector;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FileSentencePlagiarismExperiment {
    public void run(String suspectPath, String originalPath, int k) throws IOException {
        String suspectText = Files.readString(Path.of(suspectPath), StandardCharsets.UTF_8);
        String originalText = Files.readString(Path.of(originalPath), StandardCharsets.UTF_8);

        PlagiarismDetector naiveDetector = new RabinKarpListDetector();
        PlagiarismDetector rabinKarpDetector = new RabinKarpHashTableDetector();

        // I want to look at the matches - no point using measureTime here
        long naiveStart = System.currentTimeMillis();
        List<String> naiveMatchesRaw = naiveDetector.findMatches(suspectText, originalText, k);
        long naiveTime = System.currentTimeMillis() - naiveStart;

        long rkStart = System.currentTimeMillis();
        List<String> rkMatchesRaw = rabinKarpDetector.findMatches(suspectText, originalText, k);
        long rkTime = System.currentTimeMillis() - rkStart;

        System.out.println("File-based Plagiarism Experiment");
        System.out.println("Suspect file : " + suspectPath);
        System.out.println("Original file: " + originalPath);
        System.out.println("k-gram size  : " + k);
        System.out.println("Last match found: " + naiveMatchesRaw.get(naiveMatchesRaw.size() - 1));
        System.out.println();
        System.out.println("Runtime Comparison:");
        System.out.printf("Naive nested comparison   : %d ms%n", naiveTime);
        System.out.printf("Hash table lookup         : %d ms%n", rkTime);
    }
}
