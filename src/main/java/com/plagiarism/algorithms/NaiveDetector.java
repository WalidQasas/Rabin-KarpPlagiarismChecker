package com.plagiarism.algorithms;

import java.util.ArrayList;
import java.util.List;

public class NaiveDetector implements PlagiarismDetector {
    @Override
    public List<String> findMatches(String text1, String text2, int k) {
        List<String> matches = new ArrayList<>();
        if (text1.length() < k || text2.length() < k) return matches;

        // compare every substring that is of k length of text1 against every text2 that is also of k length
        for (int i = 0; i <= text1.length() - k; i++) {
            String kGram1 = text1.substring(i, i + k);
            for (int j = 0; j <= text2.length() - k; j++) {
                String kGram2 = text2.substring(j, j + k);
                if (kGram1.equals(kGram2)) {
                    matches.add(kGram1);
                    break; // move to the next k-gram in text1 once a match is found
                }
            }
        }
        return matches;
    }
}
