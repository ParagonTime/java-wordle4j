package ru.yandex.practicum;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    private final Scanner scanner;
    private final OutputStream output;
    private final Logger logger;
    private final WordleDictionary dictionary;
    private final GameState gameState;
    private final WordleAuto auto;

    public WordleGame(WordleDictionary dictionary, InputStream input, OutputStream output, Logger logger) {
        this.scanner = new Scanner(input);
        this.output = output;
        this.dictionary = dictionary;
        this.logger = logger;
        this.gameState = new GameState();
        auto = new WordleAuto(dictionary.getDictionaryList());
        logger.info("WordleGame - game created");
    }

    public void run() throws IOException {
        logger.info("=".repeat(20));
        logger.info("WordleGame - START GAME");
        String answer = dictionary.generateWorld();
        logger.info("dictionary generated secret word - " + answer);
        output.write("Угадайте слово из 5-ти букв:\n".getBytes());
        while (!gameState.isWinGame() && gameState.actualStep() != 0) {
            try {
                String userWord = scanner.nextLine();
                logger.info("step " + (7 - gameState.actualStep()) + " : user input " + userWord);
                userWord = WordleUtil.normalizeWord(userWord);
                if (userWord.isEmpty()) {
                    userWord = auto.getAnswerWord();
                    output.write((userWord + "\n").getBytes());
                } else {
                    WordleUtil.checkCorrection(userWord);
                    if (!dictionary.contains(userWord)) {
                        throw new WordNotFoundInDictionary("Слова нет в словаре");
                    }
                }
                if (answer.equals(userWord)) {
                    output.write("YOU WIN \n".getBytes());
                    gameState.setWin();
                } else {
                    String mask = WordleUtil.getMask(answer, userWord);
                    gameState.addWordAndMask(userWord, mask);
                    auto.setUserWordAndMask(userWord, mask);
                    logger.info("mask for userWord: " + mask);
                    output.write((mask + "\n").getBytes());
                    gameState.decrementStep();
                }

            } catch (WordNotFoundInDictionary | WordHaveIncorrectCharacters | WordIncorrectLength e) {
                logger.info("WordleGame - " + e.getMessage());
                output.write((e.getMessage() + "\n").getBytes());
            }
        }
        if (!gameState.isWinGame()) {
            output.write("YOU LOSE \n".getBytes());
        }
        output.write(("Загаданнео слово: " + answer).getBytes());
        logger.info("user status win game: " + gameState.isWinGame());
        logger.info("WordleGame - END GAME");
        logger.info("=".repeat(20));
    }

}
