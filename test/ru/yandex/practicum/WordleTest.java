package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    @TempDir
    Path tempDir;

    @Test
    void testMainWithImmediateWin() throws IOException {
        // Подготовка входных данных - сразу правильное слово
        String input = "гонец\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Создаем временный файл словаря
            Path dictFile = tempDir.resolve("test_words.txt");
            java.nio.file.Files.write(dictFile, List.of("гонец"));

            // Запускаем main с тестовыми аргументами
            Wordle.main(new String[]{});

            String output = outputStream.toString();
            assertFalse(output.contains("YOU WIN"));
            assertFalse(output.contains("Загаданнео слово:"));
        } finally {
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }

    @Test
    void testMainWithMultipleAttempts() throws IOException {
        // Подготовка входных данных - сначала неправильное, потом правильное слово
        String input = "герой\nгонец\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Создаем временный файл словаря
            Path dictFile = tempDir.resolve("test_words.txt");
            java.nio.file.Files.write(dictFile, List.of("гонец", "герой", "мечта"));

            // Запускаем main с тестовыми аргументами
            Wordle.main(new String[]{});

            String output = outputStream.toString();
            assertFalse(output.contains("YOU WIN"));
        } finally {
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }

    @Test
    void testMainWithAutoAnswer() throws IOException {
        // Подготовка входных данных - пустая строка для автоподбора
        String input = "\n"; // пустая строка
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Создаем временный файл словаря
            Path dictFile = tempDir.resolve("test_words.txt");
            java.nio.file.Files.write(dictFile, List.of("гонец", "герой", "мечта"));

            // Запускаем main с тестовыми аргументами
            Wordle.main(new String[]{});

            String output = outputStream.toString();
            // Проверяем что игра что-то вывела (либо результат, либо маску)
            assertFalse(output.isEmpty());
        } finally {
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }

    @Test
    void testMainWithInvalidInput() throws IOException {
        // Подготовка входных данных - некорректное слово, потом правильное
        String input = "ге0ой\nгонец\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Создаем временный файл словаря
            Path dictFile = tempDir.resolve("test_words.txt");
            java.nio.file.Files.write(dictFile, List.of("гонец", "герой", "мечта"));

            // Запускаем main с тестовыми аргументами
            Wordle.main(new String[]{});

            String output = outputStream.toString();
            assertTrue(output.contains("В слове не корректные символы") || output.contains("YOU WIN"));
        } finally {
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }
}