package ru.yandex.practicum;

public class GameState {
    private int gameStep;
    private boolean winGame;

    public GameState() {
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
        gameStep--;
    }

}
