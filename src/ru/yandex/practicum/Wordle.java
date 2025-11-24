package ru.yandex.practicum;

import java.util.Arrays;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    // перенести всю логику в этот класс оставить консольные ввод/вывод
    static Logger logger;
    static WordleDictionaryLoader dictionaryLoader;
    static WordleDictionary dictionary;
    static WordleGame game;
    static Scanner scanner;

    public static void main(String[] args) {
        try {
            logger = new Logger("game_log.txt");
            dictionaryLoader = new WordleDictionaryLoader(logger, "words_ru.txt");
            dictionary = dictionaryLoader.getDictionary();
            game = new WordleGame(dictionary, logger);
            scanner = new Scanner(System.in);

            logger.info("=".repeat(20));
            logger.info("WordleGame - START GAME");
            String secret = dictionary.generateWorld();
            logger.info("dictionary generated secret word - " + secret);
            System.out.println("Угадайте слово из 5-ти букв:");
            while (game.status()) {
                try {
                    String answer = scanner.nextLine();
                    if (answer.isBlank()) {
                        answer = game.generateAnswer();
                        System.out.println(answer);
                    } else {
                        answer = WordleUtil.normalizeWord(answer);
                    }
                    if (secret.equals(answer)) {
                        game.setWin();
                        break;
                    }
                    System.out.println(game.getMask(secret, answer));
                } catch (WordNotFoundInDictionary | WordHaveIncorrectCharacters | WordIncorrectLength e) {
                    logger.info("WordleGame - " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            }
            if (game.isWin()) {
                System.out.println("YOU WIN");
            } else {
                System.out.println("YOU LOSE");
            }
            System.out.println("Загаданнео слово: " + secret);
            logger.info("user status win game: " + game.isWin());
            logger.info("WordleGame - END GAME");
            logger.info("=".repeat(20));
        } catch (Exception e) {
            logger.excepion(Arrays.toString(e.getStackTrace()));
        }
    }

}
