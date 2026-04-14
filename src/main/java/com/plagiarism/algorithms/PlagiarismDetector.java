package com.plagiarism.algorithms;

import java.util.List;

public interface PlagiarismDetector {
    /**
     * finds the matching substrings of length k between two texts.
     */
    List<String> findMatches(String text1, String text2, int k);
}
