package ru.yandex.practicum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {

    private Path pathFile;

    public Logger(String logFile) throws IOException {
        Path path = Paths.get(logFile);
        if (Files.exists(path)) {
            this.pathFile = path;
        } else {
            this.pathFile = Files.createFile(path);
        }
    }

    public void info(String message) {
        try {
            write("INFO: " + message + "\n");
        } catch (IOException ignored) {
        }
    }

    public void excepion(String message) {
        try {
            write("EXCEPTION: " + message + "\n");
        } catch (IOException ignored) {
        }
    }

    private void write(String message) throws IOException {
        Files.write(pathFile, message.getBytes(), StandardOpenOption.APPEND);
    }
}
