package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    private String file;
    private Path path;

    @BeforeEach
    void setUp() {
        file = "test_logg.txt";
        path = Path.of(file);
    }

    @Test
    public void testCreateLogger() throws IOException {
        Logger logger = new Logger(file);
        assertTrue(Files.exists(path));
        logger = new Logger(file);
        assertTrue(Files.exists(path));
    }

    @Test
    public void testWhenLoggingInfoThenLogAddInfoIntoFile() throws IOException {
        String infoStr = "info";
        Logger logger = new Logger(file);
        logger.info(infoStr);
        String actual =  Files.readAllLines(path).getLast();
        assertEquals("INFO: " + infoStr, actual);
    }

    @Test
    public void testWhenLoggingExceptionThenLogAddExceptionIntoFile() throws IOException {
        String infoStr = "exception";
        Logger logger = new Logger(file);
        logger.excepion(infoStr);
        String actual =  Files.readAllLines(path).getLast();
        assertEquals("EXCEPTION: "+ infoStr, actual);
    }

    @Test
    public void testWhenLoggingFirstInfoSecondExceptionThenLogFileHaveTwoLines() throws IOException {
        Logger logger = new Logger(file);
        int startSize = Files.readAllLines(path).size();
        logger.info("info");
        logger.excepion("exception");
        int actualSize = Files.readAllLines(path).size();
        assertEquals(2, actualSize - startSize);
    }

}