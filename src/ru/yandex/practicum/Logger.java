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

    public void info(String message) throws IOException {
        write("INFO: " + message + "\n");
    }

    public void excepion(String message) throws IOException {
        write("EXCEPTION: " + message + "\n");
    }

    private void write(String message) throws IOException {
        Files.write(pathFile, message.getBytes(), StandardOpenOption.APPEND);
    }
}
