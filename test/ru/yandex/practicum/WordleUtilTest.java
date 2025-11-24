package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleUtilTest {

    @Test
    public void testGetDictionaryFromList() {
        List<String> list = List.of("first", "second");
        WordleDictionary dictionary = WordleUtil.getDictionary(list);
        assertEquals(list.getFirst(), dictionary.get(0));
        assertEquals(WordleDictionary.class, dictionary.getClass());
    }

    @Test
    public void testNormalizeList() {
        List<String> list = List.of("first", "second", "Hello");
        List<String> actual = WordleUtil.normalizeListWords(list);
        assertEquals(2, actual.size());
        assertEquals("first", actual.get(0));
        assertEquals("hello", actual.get(1));
    }

    @Test
    public void testNormalizeWorldCheck() {
        assertEquals("", WordleUtil.normalizeWord("  "));
        assertEquals("first", WordleUtil.normalizeWord("first"));
        assertEquals("first", WordleUtil.normalizeWord("  first  "));
        assertEquals("полет", WordleUtil.normalizeWord("полёт"));
        assertEquals("гонец", WordleUtil.normalizeWord("ГоНеЦ"));
    }

    @Test
    public void testGetMask() {
        assertEquals("+++++", WordleUtil.getMask("гонец", "гонец"));
        assertEquals("+^-^-", WordleUtil.getMask("герой", "гонец"));
        assertEquals("-----", WordleUtil.getMask("fffff", "гонец"));
    }

    @Test
    public void testCheckCorrectionWhenCorrectWord() {
        assertDoesNotThrow(() -> WordleUtil.checkCorrection("гонец"));
    }

    @Test
    public void testCheckWhenIncorrectLength() {
        WordIncorrectLength exception = assertThrows(
                WordIncorrectLength.class,
                () -> WordleUtil.checkCorrection("ввв")
        );
        assertEquals("Слово не из 5 букв", exception.getMessage());
    }

    @Test
    public void testCheckWhenWordHaveIncorrectCharacters() {
        WordHaveIncorrectCharacters exception = assertThrows(
                WordHaveIncorrectCharacters.class,
                () -> WordleUtil.checkCorrection("геро1")
        );

        assertEquals("В слове не корректные символы", exception.getMessage());
    }
}