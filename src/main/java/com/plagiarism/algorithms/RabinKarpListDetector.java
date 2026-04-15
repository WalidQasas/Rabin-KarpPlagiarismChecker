package com.plagiarism.algorithms;

import java.util.ArrayList;
import java.util.List;


public class RabinKarpListDetector implements PlagiarismDetector {
    // since it's a list, entry is how we store the hash and window altogether.
    private static class Entry {
        Long hash;
        String window;

        Entry(Long hash, String window) {
            this.hash = hash;
            this.window = window;
        }
    }

    @Override
    public List<String> findMatches(String text1, String text2, int k) {
        List<String> matches = new ArrayList<>();
        if (text1.length() < k || text2.length() < k) return matches;

        Long base = 31L, mod = 1_000_000_007L, h = 0L;
        Long power = calculateBase(base, mod, k);

        List<Entry> index = new ArrayList<>();

        // Compute the rolling hash for text1 and add to list
        for (int i = 0; i < k; i++) h = (h * base + text1.charAt(i)) % mod;
        index.add(new Entry(h, text1.substring(0, k)));

        for (int i = 1; i <= text1.length() - k; i++) {
            h = (h - text1.charAt(i - 1) * power % mod + mod) % mod;
            h = (h * base + text1.charAt(i + k - 1)) % mod;
            index.add(new Entry(h, text1.substring(i, i + k)));
        }

        // Roll through text2 and search the list
        h = 0L;
        for (int i = 0; i < k; i++) h = (h * base + text2.charAt(i)) % mod;

        if (searchList(index, h) != null) {
            matches.add(text2.substring(0, k));
        }

        for (int i = 1; i <= text2.length() - k; i++) {
            h = (h - text2.charAt(i - 1) * power % mod + mod) % mod;
            h = (h * base + text2.charAt(i + k - 1)) % mod;

            if (searchList(index, h) != null) {
                matches.add(text2.substring(i, i + k));
            }
        }

        return matches;
    }

    private String searchList(List<Entry> index, Long h) {
        for (Entry e : index) {
            if (e.hash.equals(h)) {
                return e.window;
            }
        }
        return null;
    }

    private long calculateBase(long base, Long mod, int k) {
        long p = 1;
        for (int i = 0; i < k - 1; i++) p = (p * base) % mod;
        return p;
    }
}