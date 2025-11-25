package ru.yandex.practicum;

import java.io.Serial;

public class WordIncorrectLength extends Exception {
    @Serial
    private static final long serialVersionUID = 4203185621551953726L;

    public WordIncorrectLength(String message) {
        super(message);
    }
}
