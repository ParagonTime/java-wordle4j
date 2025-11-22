package ru.yandex.practicum;

import java.util.List;
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

    private Scanner scanner;
    private Logger logger;
    private String answer;
    private int steps;
    private WordleDictionary dictionary;
    private List<String> userAnswers;

    public WordleGame(WordleDictionary dictionary, Scanner scanner, Logger logger) {
        this.dictionary = dictionary;
        this.scanner = scanner;
        this.logger = logger;
    }

    public void run() {
        steps = 6;
        answer = dictionary.generateWorld();
        boolean winGame = false;
        System.out.println("Угадайте слово из 5-ти букв:");
        while (!winGame && steps != 0) {
            try {
                String userWord = scanner.nextLine();
                // нормализовать введенное слово
                userWord = WordleUtil.normalizeWord(userWord);
                if (userWord.isEmpty()) {
                    // сгенерировать слово по текущей подсказке автоматически
                    System.out.println(answer);
                    userWord = answer;
                } else {
                    WordleUtil.checkCorrection(userWord);
                    // проверить наличие слова в словаре
                    if (!dictionary.contains(userWord)) {
                        // иначе выкинуть исключение
                        throw new WordNotFoundInDictionary("Слова нет в словаре");
                    }
                }
                String mask = WordleUtil.getMask(answer, userWord);
                if (mask.equals("+++++")) {
                    System.out.println("YOU WIN");
                    winGame = true;
                } else {
                    System.out.println(mask);
                    steps--;
                }
            } catch (WordNotFoundInDictionary e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (!winGame && steps == 0) {
            System.out.println("YOU LOSE");
        }
        System.out.println("Загаданнео слово: " + answer);
    }

}
