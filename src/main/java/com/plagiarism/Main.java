package com.plagiarism;

import com.plagiarism.experiment.PlagiarismExperiment;

public class Main {
    public static void main(String[] args) {
        PlagiarismExperiment experiment = new PlagiarismExperiment();
        
        // Defining test sizes and k-gram length
        int[] sizes = {5000, 10000, 20000, 50000};
        int k = 10; 

        System.out.println("Running Plagiarism Detection Experiments with k = " + k);
        System.out.println("Comparing Naive O(n^2) vs Rabin-Karp O(n) average-case");
        
        experiment.runExperiment(sizes, k);
        
        System.out.println("------------------------------------");
        System.out.println("Experiments Completed Successfully.");
    }
}
