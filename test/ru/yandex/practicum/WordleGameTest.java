package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordleGameTest {

    private WordleDictionary dictionary;
    private ByteArrayOutputStream outputStream;
    private Logger logger;

    @BeforeEach
    void setUp() throws IOException {
        dictionary = new WordleDictionary(List.of("слово", "гонец", "герой", "мечта"));
        outputStream = new ByteArrayOutputStream();
        logger = new Logger("test_logg.txt");
    }

    @Test
    void testGameCreation() {
        InputStream input = new ByteArrayInputStream("".getBytes());
        WordleGame game = new WordleGame(dictionary, input, outputStream, logger);
        assertNotNull(game);
    }

    @Test
    void testGameWithCorrectWord() throws IOException {
        String inputWords = "слово\n\n\n\n\n\n";
        InputStream input = new ByteArrayInputStream(inputWords.getBytes());
        WordleGame game = new WordleGame(dictionary, input, outputStream, logger);

        game.run();

        String output = outputStream.toString();
        assertTrue(output.contains("YOU WIN"));
    }

    @Test
    void testGameWithIncorrectThenCorrectWord() throws IOException {
        String inputWords = "wrong\napple\n\n\n\n\n";
        InputStream input = new ByteArrayInputStream(inputWords.getBytes());
        WordleGame game = new WordleGame(dictionary, input, outputStream, logger);

        game.run();

        String output = outputStream.toString();
        assertTrue(output.contains("YOU WIN"));
    }

    @Test
    void testGameWithInvalidWord() throws IOException {
        String inputWords = "ге0ой\nгыыss\n\n\n\n\n";
        InputStream input = new ByteArrayInputStream(inputWords.getBytes());
        WordleGame game = new WordleGame(dictionary, input, outputStream, logger);

        game.run();

        String output = outputStream.toString();
        assertTrue(output.contains("В слове не корректные символы"));
        assertTrue(output.contains("YOU WIN"));
    }

    @Test
    void testGameWithWordNotInDictionary() throws IOException {
        String inputWords = "типаж\nгонец\n\n\n\n\n";
        InputStream input = new ByteArrayInputStream(inputWords.getBytes());
        WordleGame game = new WordleGame(dictionary, input, outputStream, logger);

        game.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Слова нет в словаре"));
        assertTrue(output.contains("YOU WIN"));
    }

    @Test
    void testGameLoseScenario() throws IOException {
        String inputWords = "слово\nслово\nслово\nслово\nслово\nслово\n";
        InputStream input = new ByteArrayInputStream(inputWords.getBytes());
        WordleGame game = new WordleGame(dictionary, input, outputStream, logger);

        game.run();

        String output = outputStream.toString();
        assertTrue(output.contains("YOU LOSE"));
        assertTrue(output.contains("Загаданнео слово:"));
    }
}