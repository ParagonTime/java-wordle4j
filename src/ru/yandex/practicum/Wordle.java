package ru.yandex.practicum;

import java.util.Arrays;

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

    static Logger logger;
    static WordleDictionaryLoader dictionaryLoader;
    static WordleDictionary dictionary;
    static WordleGame game;

    public static void main(String[] args) {
        try {
            logger = new Logger("game_log.txt");
            dictionaryLoader = new WordleDictionaryLoader(logger, "words_ru.txt");
            dictionary = dictionaryLoader.getDictionary();
            game = new WordleGame(dictionary, System.in, System.out, logger);

            game.run();
        } catch (Exception e) {
            logger.excepion(Arrays.toString(e.getStackTrace()));
        }
    }

}
