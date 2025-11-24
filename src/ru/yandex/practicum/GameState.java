package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private int gameStep;
    private boolean winGame;
    private final List<String> answers;
    private final List<String> masks;

    public GameState() {
        answers = new ArrayList<>();
        masks = new ArrayList<>();
        gameStep = 6;
        winGame = false;
    }

    public boolean isWinGame() {
        return winGame;
    }

    public void setWin() {
        winGame = true;
    }

    public int actualStep() {
        return gameStep;
    }

    public void decrementStep() {
        if (gameStep > 0) {
            gameStep--;
        }
    }

    public void addWordAndMask(String answer, String mask) {
        answers.add(answer);
        masks.add(mask);
    }

    public List<String> getAnwsers() {
        return new ArrayList<>(answers);
    }

    public List<String> getMasks() {
        return new ArrayList<>(masks);
    }

}
