package ru.yandex.practicum;

import java.io.Serial;

public class WordHaveIncorrectCharacters extends Exception {

    @Serial
    private static final long serialVersionUID = -3375973362250286180L;

    public WordHaveIncorrectCharacters(String message) {
        super(message);
    }
}
