package wordfreq;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

@FunctionalInterface
interface WordCounter {
    Map<String, Long> count(List<String> words);
}

public class WordFrequency {

    public static void main(String[] args) {
        String filePath = "J:\\Java_Data_Engineering_Training\\Java_Data_Engineering_Tasks\\Core_Java_Tasks\\src\\wordfreq\\demofile.txt"; 
        int topN = 5;

        try {
            List<String> words = Files.lines(Paths.get(filePath))
                    .flatMap(line -> Arrays.stream(line.split("\\W+")))
                    .map(String::toLowerCase)
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.toList());
            
            System.out.println("Words list is " + words);

            WordCounter wordCounter = (wordList) -> {
                Map<String, Long> countMap = new HashMap<>();
                for (String word : wordList) {
                    if (word.length() > 1) { 
                        countMap.put(word, countMap.getOrDefault(word, 0L) + 1);
                    }
                }
                return countMap;
            };

            Map<String, Long> wordCount = wordCounter.count(words);

            List<Map.Entry<String, Long>> topWords = wordCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                    .limit(topN)
                    .collect(Collectors.toList());

            System.out.println("Top " + topN + " frequent words:");
            for (Map.Entry<String, Long> entry : topWords) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
