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
    private Logger logger;
    // добавить логирование
    public WordleDictionaryLoader(Logger logger) {
        this.logger = logger;
    }

    public WordleDictionary getDictionary(String worldsFileName) {

        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(worldsFileName)))) {
            while (reader.ready()) {
                String word = reader.readLine();
                if (word.length() == 5) {
                    words.add(WordleUtil.normalizeWord(word));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // улучшить обработку исключений
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // использовать утилитный класс для обработки слов


        return WordleUtil.getDictionary(words);
    }
}
