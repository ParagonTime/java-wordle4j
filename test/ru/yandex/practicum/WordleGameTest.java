package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleGameTest {

    private WordleDictionary dictionary;
    private Logger logger;

    @BeforeEach
    void setUp() throws IOException {
        dictionary = new WordleDictionary(List.of("гонец", "герой", "мечта", "слово", "яблоко"));
        logger = new Logger("test_logg.txt");
    }

    @Test
    void testGameCreation() {
        WordleGame game = new WordleGame(dictionary, logger);
        assertNotNull(game);
    }

    @Test
    void testGameStatus() {
        WordleGame game = new WordleGame(dictionary, logger);
        assertTrue(game.status());
    }

    @Test
    void testGenerateAnswer() {
        WordleGame game = new WordleGame(dictionary, logger);
        String answer = game.generateAnswer();
        assertNotNull(answer);
        assertFalse(answer.isEmpty());
    }

    @Test
    void testGetMaskWithCorrectWord() throws Exception {
        WordleGame game = new WordleGame(dictionary, logger);
        String secret = "гонец";
        String answer = "гонец";

        String mask = game.getMask(secret, answer);
        assertEquals("+++++", mask);
        assertTrue(game.isWin());
    }

    @Test
    void testGetMaskWithIncorrectWord() throws Exception {
        WordleGame game = new WordleGame(dictionary, logger);
        String secret = "гонец";
        String answer = "герой";

        String mask = game.getMask(secret, answer);
        assertNotNull(mask);
        assertEquals(5, mask.length());
        assertFalse(game.isWin());
    }

    @Test
    void testGetMaskWithInvalidWord() {
        WordleGame game = new WordleGame(dictionary, logger);
        String secret = "гонец";
        String answer = "ге0ой";

        assertThrows(WordHaveIncorrectCharacters.class, () -> game.getMask(secret, answer));
    }

    @Test
    void testGetMaskWithWordNotInDictionary() {
        WordleGame game = new WordleGame(dictionary, logger);
        String secret = "гонец";
        String answer = "типаж";

        assertThrows(WordNotFoundInDictionary.class, () -> game.getMask(secret, answer));
    }

    @Test
    void testSetWin() {
        WordleGame game = new WordleGame(dictionary, logger);
        assertFalse(game.isWin());
        game.setWin();
        assertTrue(game.isWin());
    }
}