package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordleDictionaryLoaderTest {

    @Test
    public void testWordDictionaryGetDictionary() throws IOException {
        Logger logger = new Logger("test_logg.txt");
        WordleDictionaryLoader loader = new WordleDictionaryLoader(logger, "words_ru.txt");
        WordleDictionary dictionary = loader.getDictionary();
        assertEquals(WordleDictionary.class, dictionary.getClass());
    }

    @Test
    public void testWordDictionaryThrowException() throws IOException {
        Logger logger = new Logger("test_logg.txt");
        WordleDictionaryLoader loader = new WordleDictionaryLoader(logger, "file.txt");
        assertThrows(
                RuntimeException.class,
                loader::getDictionary);
    }
}