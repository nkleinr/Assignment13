import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String[] args) {
        // Input and output file paths
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Map to store word counts
        Map<String, Integer> wordCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Convert word to lowercase and count it
                String word = line.trim().toLowerCase();
                if (!word.isEmpty()) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
            return;
        }

        // Write the word counts to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            wordCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort alphabetically
                .forEach(entry -> {
                    try {
                        writer.write(entry.getKey() + " " + entry.getValue());
                        writer.newLine();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
        } catch (IOException e) {
            System.err.println("Error writing the output file: " + e.getMessage());
        }

        System.out.println("Word counts written to " + outputFile);
    }
}