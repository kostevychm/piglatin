package me.kostevych;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatinTranslator {

    private static Character[] vowels = {'a', 'e', 'i', 'o', 'u'};

    static String convert(String sentence) {
        StringBuilder result = new StringBuilder();

        String[] words = sentence.trim().replaceAll("( )+", " ")
                .split(String.format("((?<=%1$s)|(?=%1$s))", "[\\s -]+")); //split with saved delimiters
        for(String word : words) {
            //Saves position of uppercase letters
            ArrayList<Integer> upperCasePositions = new ArrayList<>();
            //Save the delimiter or unchangeable words to the result (Necessary for correct treating of hyphens)
            if(word.equals(" ") || word.equals("-") || word.endsWith("way")) { //case #1 ends with way
                result.append(word);
                continue;
            }

            //Check upperCases
            for (int j = 0; j<word.length(); j++) {
                if(Character.isUpperCase(word.charAt(j)))
                    upperCasePositions.add(j);
            }

            if (isVowel(word.charAt(0))) { //case #2 starts with vowel
                result.append(alignsPunctuation(keepUpperCases(word + "way", upperCasePositions)));
                continue;
            }

            // case #3, starts with consonant
            result.append(alignsPunctuation(keepUpperCases(word.substring(1) + word.substring(0, 1) + "ay",upperCasePositions)));
        }
        return result.toString();
    }

    private static String alignsPunctuation(String word) {
        Map<Integer, Character> punctuationPositions = new HashMap<>();
        int length = word.length();
        String pattern = "\\p{Punct}";
        Pattern r = Pattern.compile(pattern);    // Create a Pattern object
        Matcher m = r.matcher(word);            // Now create matcher object.
        while (m.find()) {
            punctuationPositions.put(length-m.start()-4, m.group().charAt(0));
        }
        //Remove punctuation from the old positions
        StringBuilder res = new StringBuilder(word.replaceAll(pattern, ""));

        //Add to correct positions
        for(int pos : punctuationPositions.keySet()) {
            res.insert(res.length()-pos, punctuationPositions.get(pos));
        }

        return res.toString();
    }

    private static String keepUpperCases(String word, ArrayList<Integer> positions) {
        if(positions.size() == 0)
            return word;

        StringBuilder res = new StringBuilder(word.toLowerCase());

        //Add to correct positions
        for (int i = 0; i<positions.size(); i++) {
            res.setCharAt(positions.get(i), Character.toUpperCase(word.charAt(positions.get(i))));
        }

        return res.toString();
    }

    private static boolean isVowel(char charAt) {
        return Arrays.asList(vowels).contains(charAt);
    }
}