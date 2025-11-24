package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleDictionaryTest {

    WordleDictionary dictionary;
    List<String> words = List.of("один", "два", "три");

    @BeforeEach
    void setUp() {
        dictionary = new WordleDictionary(words);
    }

    @Test
    public void testGenerateWordWhenDictionaryEmpty() {
        WordleDictionary emptyDictionary = new WordleDictionary(List.of());
        assertEquals("гонец", emptyDictionary.generateWorld());
    }

    @Test
    public void testGenerateWordWhenDictionaryNotEmpty() {
        List<String> def = List.of("гонец");
        assertTrue(words.contains(dictionary.generateWorld()));
        assertTrue(words.contains(dictionary.generateWorld()));
        assertTrue(words.contains(dictionary.generateWorld()));
        assertFalse(def.contains(dictionary.generateWorld()));
        assertFalse(def.contains(dictionary.generateWorld()));
        assertFalse(def.contains(dictionary.generateWorld()));
    }

    @Test
    public void testContainsWhenContains() {
        assertTrue(dictionary.contains("один"));
        assertTrue(dictionary.contains("два"));
        assertTrue(dictionary.contains("три"));
    }

    @Test
    public void testContainsWhenNotContain() {
        assertFalse(dictionary.contains("гонец"));
        assertFalse(dictionary.contains("герой"));
    }

    @Test
    public void testGetDictionaryList() {
        assertEquals(words, dictionary.getDictionaryList());
    }
}