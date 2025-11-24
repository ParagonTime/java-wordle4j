package ru.yandex.practicum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    private final Logger logger;
    private final String worldsFileName;

    public WordleDictionaryLoader(Logger logger, String worldsFileName) {
        this.logger = logger;
        this.worldsFileName = worldsFileName;
    }

    public WordleDictionary getDictionary() {
        logger.info("dictionary loading start");
        List<String> words = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(worldsFileName)))) {
            while (reader.ready()) {
                String word = reader.readLine();
                if (word.length() == 5) {
                    words.add(WordleUtil.normalizeWord(word));
                }
            }
        } catch (IOException e) {
            logger.excepion("WordleDictionaryLoader - " + e.getMessage());
            throw new RuntimeException(e);
        }

        logger.info("dictionary loading completed");
        logger.info("dictionary has " + words.size() + " words");
        return WordleUtil.getDictionary(words);
    }
}
