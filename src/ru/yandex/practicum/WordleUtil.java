package ru.yandex.practicum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordleUtil {

    public static WordleDictionary getDictionary(final List<String> listWords) {
        List<String> normalWords = normalizeListWords(listWords);
        return new WordleDictionary(normalWords);
    }

    public static List<String> normalizeListWords(final List<String> dictionary) {
        return dictionary.stream()
                .filter(word -> word.length() == 5)
                .map(WordleUtil::normalizeWord).toList();
    }

    public static String normalizeWord(String word) {
        return word.trim().toLowerCase().replaceAll("ё", "е");
    }

    public static String getMask(String secret, String answer) {
        StringBuilder builder = new StringBuilder();
        Map<Character, Integer> charMap = new HashMap<>();
        for (char charr : secret.toCharArray()) {
            charMap.put(charr, charMap.getOrDefault(charr, 0) + 1);
        }
        for (int i = 0; i < answer.length(); i++) {
            char charr = answer.charAt(i);
            if (charr == secret.charAt(i)) {
                builder.append("+");
                charMap.put(charr, charMap.get(charr) - 1);
            } else {
                builder.append("-");
            }
        }

        for (int i = 0; i < answer.length(); i++) {
            char charr = answer.charAt(i);
            char charrMask = builder.charAt(i);
            if (charrMask == '-') {
                if (charMap.containsKey(charr) && charMap.get(charr) > 0) {
                    builder.setCharAt(i, '^');
                    charMap.put(charr, charMap.get(charr) - 1);
                }
            }
        }
        return builder.toString();
    }

    public static void checkCorrection(String word) throws WordHaveIncorrectCharacters, WordIncorrectLength {
        if (word == null || word.isEmpty() || !word.matches("^[а-я]+$")) {
            throw new WordHaveIncorrectCharacters("В слове не корректные символы");
        }
        if (word.length() != 5) {
            throw new WordIncorrectLength("Слово не из 5 букв");
        }
    }
}
