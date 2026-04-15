package com.plagiarism;

import com.plagiarism.experiment.FileSentencePlagiarismExperiment;
import com.plagiarism.experiment.PlagiarismExperiment;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PlagiarismExperiment experiment = new PlagiarismExperiment();
        
        // Defining test sizes and k-gram length
        int[] sizes = {5000, 10000, 20000, 50000};
        int k = 10; 

        System.out.println("Running Plagiarism Detection Experiments with k = " + k);
        
        experiment.runExperiment(sizes, k);
        System.out.println("------------------------------------");

        FileSentencePlagiarismExperiment fileExperiment = new FileSentencePlagiarismExperiment();
        String suspectFileE1 = "src/main/resources/documents/suspect-e1.txt";
        String originalFileE1 = "src/main/resources/documents/original-e1.txt";
        String suspectFileE2 = "src/main/resources/documents/suspect-e2.txt";
        String originalFileE2 = "src/main/resources/documents/original-e2.txt";

        try {
            k = 20;
            System.out.println("File-based Experiment E1 (short document pair)");
            fileExperiment.run(suspectFileE1, originalFileE1, k);
            System.out.println("------------------------------------");
            k = 30;
            System.out.println("File-based Experiment E2 (larger document pair)");
            fileExperiment.run(suspectFileE2, originalFileE2, k);
            System.out.println("------------------------------------");
            System.out.println("Experiments Completed Successfully.");
        } catch (IOException e) {
            System.err.println("Failed to run file-based experiment: " + e.getMessage());
        }
    }
}
