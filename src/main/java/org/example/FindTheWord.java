package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindTheWord {
    private static Set<String> readWordsFromURL(String urlString) throws IOException {
        Set<String> words = new HashSet<>();
        URL url = new URL(urlString);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }
        return words;
    }

    public static void main(String[] args) throws IOException {

        Set<String> allWords = readWordsFromURL(
                "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        List<String> result = new LinkedList<>();
        for (String word : allWords) {
            if (word.length() == 9 && isValid(word, allWords)) {
                result.add(word);
            }
        }

        System.out.println(result);
    }

    private static boolean isValid(String word, Set<String> allWords) {

        if (word.length() == 1) {
            return word.equals("A") || word.equals("I");
        }
        if(word.length() == 2) {
            return true;
        }
        for (int i = 0; i < word.length(); i++) {
            String lesser = removeCharAt(word, i);
            if (allWords.contains(lesser) && isValid(lesser, allWords)) {
                return true;
            }
        }

        return false;
    }

    private static String removeCharAt(String word, int index) {
        return word.substring(0, index) + word.substring(index + 1);
    }
}

