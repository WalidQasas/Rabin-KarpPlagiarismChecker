package com.plagiarism.experiment;

import com.plagiarism.algorithms.PlagiarismDetector;
import com.plagiarism.algorithms.RabinKarpListDetector;
import com.plagiarism.algorithms.RabinKarpHashTableDetector;

import java.util.List;
import java.util.Random;

public class PlagiarismExperiment {

    // compares the two Rabin-Karp implementations on random character-level text
    public void runExperiment(int[] sizes, int k) {
        System.out.println("Experiment 1: Random Character-Level Comparison");
        System.out.println("k-gram size: " + k);
        System.out.println("------------------------------------");
        System.out.println("Size | Naive (ms) | Rabin-Karp (ms)");
        System.out.println("------------------------------------");

        for (int size : sizes) {
            String text1 = generateRandomText(size);
            String text2 = generateRandomText(size);

            long naiveTime = measureTime(new RabinKarpListDetector(), text1, text2, k);
            long rkTime = measureTime(new RabinKarpHashTableDetector(), text1, text2, k);

            System.out.printf("%4d | %10d | %15d\n", size, naiveTime, rkTime);
        }
    }

    private long measureTime(PlagiarismDetector detector, String text1, String text2, int k) {
        long start = System.currentTimeMillis();
        detector.findMatches(text1, text2, k);
        return System.currentTimeMillis() - start;
    }

    private String generateRandomText(int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }

}
