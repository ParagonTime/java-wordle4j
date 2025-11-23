package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private final List<String> words;
    private final Set<String> setWords;

    public WordleDictionary(List<String> words) {
        this.words = new ArrayList<>(words);
        this.setWords = new HashSet<>(words);

    }

    public String generateWorld() {
        int numOfWorld = (int) (Math.random() * 10000) % words.size();
        return words.get(numOfWorld);
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
