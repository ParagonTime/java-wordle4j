package ru.yandex.practicum;

import java.util.*;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {
    private final Random randomizer;
    private final List<String> words;
    private final Set<String> setWords;

    public WordleDictionary(List<String> words) {
        this.words = new ArrayList<>(words);
        this.setWords = new HashSet<>(words);
        randomizer = new Random();
    }

    public String generateWorld() {
        if (!words.isEmpty()) {
            return words.get(randomizer.nextInt(0, 123456) % words.size());
        } else {
            return "гонец";
        }
    }

    public boolean contains(String word) {
        return setWords.contains(word);
    }

    public String get(int numWord) {
        return words.get(numWord);
    }

    public List<String> getDictionaryList() {
        return new ArrayList<>(words);
    }
}
