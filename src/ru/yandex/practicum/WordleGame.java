package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.List;

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

    private final Logger logger;
    private final WordleDictionary dictionary;
    private final WordleAuto auto;
    private int step;
    private boolean winGame;
    private final List<String> answers;
    private final List<String> masks;

    public WordleGame(WordleDictionary dictionary, Logger logger) {
        this.dictionary = dictionary;
        this.logger = logger;
        answers = new ArrayList<>();
        masks = new ArrayList<>();
        step = 6;
        winGame = false;
        auto = new WordleAuto(dictionary.getDictionaryList());
        this.logger.info("WordleGame - game created");
    }

    public boolean status() {
        return !winGame && step > 0;
    }

    public String generateAnswer() {
        return auto.getAnswerWord();
    }

    public String getMask(String secret, String answer) throws WordNotFoundInDictionary,
            WordHaveIncorrectCharacters, WordIncorrectLength {
        WordleUtil.checkCorrection(answer);
        if (!dictionary.contains(answer)) {
            throw new WordNotFoundInDictionary("Слова нет в словаре");
        }
        String mask = WordleUtil.getMask(secret, answer);
        auto.setUserWordAndMask(answer, mask);
        answers.add(answer);
        masks.add(mask);
        if (mask.equals("+++++")) {
            setWin();
        }
        return mask;
    }

    public boolean isWin() {
        return winGame;
    }

    public void setWin() {
        winGame = true;
    }
}
