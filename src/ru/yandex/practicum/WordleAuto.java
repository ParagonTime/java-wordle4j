package ru.yandex.practicum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordleAuto {
    private List<String> words;
    private final Set<Character> suitable;
    private final Set<Character> unsuitable;
    //private char[] unionPlusMask;

    public WordleAuto(WordleDictionary dictionary) {
        this.words = dictionary.getDictionaryList();
        suitable = new HashSet<>();
        unsuitable = new HashSet<>();
        //unionPlusMask = new char[5];
    }

    public String getAnswerWord() {
        return words.get((int) (Math.random() * 10000) % words.size());
    }

    public void setUserWordAndMask(String userWord, String masks) {
        for (int i = 0; i < userWord.length(); i++) {
            char comm = masks.charAt(i);
            switch (comm) {
                case '-':
                    unsuitable.add(userWord.charAt(i));
                    break;
                case '+':
                    suitable.add(userWord.charAt(i));
                    //unionPlusMask[i] = '+';
                    break;
                case '^':
                    suitable.add(userWord.charAt(i));
                    break;
            }
        }
        words = words.stream()
                .filter(this::checkCharactersAtUnsuitable)
                .filter(this::checkCharactersAtSuitable)
                .toList();
    }

    private boolean checkCharactersAtUnsuitable(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (unsuitable.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCharactersAtSuitable(String word) {
        Set<Character> suitableWord = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            suitableWord.add(word.charAt(i));
        }
        for (Character cr : suitable) {
            if (!suitableWord.contains(cr)) {
                return false;
            }
        }
        return true;
    }
}
