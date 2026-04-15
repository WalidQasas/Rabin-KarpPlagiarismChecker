package com.plagiarism.algorithms;

import com.plagiarism.ds.HashTable;
import com.plagiarism.ds.LinearProbingHashTable;
import java.util.ArrayList;
import java.util.List;

public class RabinKarpHashTableDetector implements PlagiarismDetector{
     

    @Override
    public List<String> findMatches(String text1, String text2, int k) {
        List<String> matches = new ArrayList<>();
        // setup parameters for the rolling hash 
        Long base = 31L, mod = 1_000_000_007L, h = 0L;
        Long power = calculateBase(base, mod, k);
        HashTable<Long, String> index = new LinearProbingHashTable<>();


        // Compute the rolling hash 
        for (int i = 0; i < k; i++) h = (h * base + text1.charAt(i)) % mod;
        index.put(h, text1.substring(0, k)); 
        // add the rolling hash to the index table
        for (int i = 1; i <= text1.length() - k; i++) {
            h = (h - text1.charAt(i - 1) * power % mod + mod) % mod;
            h = (h * base + text1.charAt(i + k - 1)) % mod;
            index.put(h, text1.substring(i, i + k));
        }

        // roll through query and check for matches 
        h = 0L; 
        // compute the initial rolling hash for the text2
        for (int i = 0; i < k; i++) h = (h * base + text2.charAt(i)) % mod;
        if (index.get(h) != null) matches.add(text2.substring(0, k));

        // roll through text2 and check for matches
        for (int i = 1; i <= text2.length() - k; i++) {
            h = (h - text2.charAt(i - 1) * power % mod + mod) % mod;
            h = (h * base + text2.charAt(i + k - 1)) % mod;
            if (index.get(h) != null) matches.add(text2.substring(i, i + k));
        }

        return matches;
    }


    private long calculateBase(long base, Long mod, int k) {
        // calculate the base for the rolling hash
        long p = 1;
        for (int i = 0; i < k - 1; i++) p = (p * base) % mod;
        return p;
    }

}
