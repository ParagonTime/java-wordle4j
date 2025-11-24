package ru.yandex.practicum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static String getMask(String answer, String userWord) {
        StringBuilder builder = new StringBuilder();
        Set<Character> charSet = new HashSet<>();
        for (char charr : answer.toCharArray()) {
            charSet.add(charr);
        }
        for (int i = 0; i < userWord.length(); i++) {
            char charr = userWord.charAt(i);
            if (charr == answer.charAt(i)) {
                builder.append("+");
            } else if (charSet.contains(charr)) {
                builder.append("^");
            } else {
                builder.append("-");
            }
        }
        return builder.toString();
    }

    public static void checkCorrection(String word) throws WordHaveIncorrectCharacters, WordIncorrectLength {
        if (!word.matches("^[а-я]+$")) {
            throw new WordHaveIncorrectCharacters("В слове не корректные символы");
        }
        if (word.length() != 5) {
            throw new WordIncorrectLength("Слово не из 5 букв");
        }
    }
}
