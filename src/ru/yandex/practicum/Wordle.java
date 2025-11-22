package ru.yandex.practicum;

import java.io.IOException;
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

    static Logger logger;
    static WordleDictionaryLoader dictionaryLoader;
    static WordleDictionary dictionary;
    static WordleGame game;

    public static void main(String[] args) {
        try {
            logger = new Logger("game_log.txt");
            dictionaryLoader = new WordleDictionaryLoader(logger);
            dictionary = dictionaryLoader.getDictionary("words_ru.txt");
            game = new WordleGame(dictionary, new Scanner(System.in), logger);

            game.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
