package ru.yandex.practicum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordleAuto {
    private List<String> words;
    private final Set<Character> suitable;
    private final Set<Character> unsuitable;
    private final char[] unionMask;

    public WordleAuto(List<String> words) {
        this.words = words;
        suitable = new HashSet<>();
        unsuitable = new HashSet<>();
        unionMask = new char[]{'0', '0', '0', '0', '0'};
    }

    public String getAnswerWord() {
        boolean isSuitable = false;
        String autoAnswer = "гонец";
        if (words.isEmpty()) {
            return autoAnswer;
        }
        while (!isSuitable) {
            autoAnswer = words.get((int) (Math.random() * 112342) % words.size());
            isSuitable = checkSuitable(autoAnswer);
        }
        return autoAnswer;
    }

    private boolean checkSuitable(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (unionMask[i] != '0') {
                if (word.charAt(i) != unionMask[i]) {
                    return false;
                }
            }
        }
        return true;
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
                    unionMask[i] = userWord.charAt(i);
                    break;
                case '^':
                    suitable.add(userWord.charAt(i));
                    break;
            }
        }
        words = words.stream()
                .filter(this::checkCharactersAtUnsuitable)
                .filter(this::checkCharactersAtSuitable)
                .filter(word -> !word.equals(userWord))
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
