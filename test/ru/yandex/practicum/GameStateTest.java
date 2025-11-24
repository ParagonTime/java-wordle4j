package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

    @Test
    public void testIsWinGameAndSetWin() {
        assertFalse(gameState.isWinGame());
        gameState.setWin();
        assertTrue(gameState.isWinGame());
    }

    @Test
    public void testActualStepWhenDefault() {
        assertEquals(6, gameState.actualStep());
    }

    @Test
    public void testActualStepWhenUseDecrement() {
        assertEquals(6, gameState.actualStep());
        gameState.decrementStep();
        assertEquals(5, gameState.actualStep());
        gameState.decrementStep();
        assertEquals(4, gameState.actualStep());
        gameState.decrementStep();
        assertEquals(3, gameState.actualStep());
        gameState.decrementStep();
        assertEquals(2, gameState.actualStep());
        gameState.decrementStep();
        assertEquals(1, gameState.actualStep());
        gameState.decrementStep();
        assertEquals(0, gameState.actualStep());
        gameState.decrementStep();
        assertEquals(0, gameState.actualStep());
    }

    @Test
    public void testAddWordAndMaskWhenAddElement() {
        String word = "гонец";
        String mask = "++^--";
        gameState.addWordAndMask(word, mask);
        List<String> answers = gameState.getAnwsers();
        List<String> masks = gameState.getMasks();
        assertEquals(1, answers.size());
        assertEquals(word, answers.getFirst());
        assertEquals(1, masks.size());
        assertEquals(mask, masks.getFirst());
        gameState.addWordAndMask(word, mask);
        answers = gameState.getAnwsers();
        masks = gameState.getMasks();
        assertEquals(2, answers.size());
        assertEquals(word, answers.getFirst());
        assertEquals(word, answers.getLast());
        assertEquals(2, masks.size());
        assertEquals(mask, masks.getFirst());
        assertEquals(mask, masks.getLast());
    }

    @Test
    public void testGetAnswersWhenNoAddedWords() {
        assertEquals(0, gameState.getAnwsers().size());
        assertEquals(0, gameState.getMasks().size());
    }

}