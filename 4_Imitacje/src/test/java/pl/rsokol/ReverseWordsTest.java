package pl.rsokol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReverseWordsTest {

    @Before
    public void before() {
        final ReverseLetters reversePhrase = mock(ReverseLetters.class);
        when(reversePhrase.apply("Zamieniona")).thenReturn("anoineimaZ");
        when(reversePhrase.apply("kolejność")).thenReturn("ćśonjelok");
        when(reversePhrase.apply("liter")).thenReturn("retil");
        when(reversePhrase.apply("")).thenReturn("");
        reverseWords = ReverseWords.with(reversePhrase);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_withNullReversePhrase() {
        ReverseWords.with(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void apply_null() {
        reverseWords.apply(null);
    }

    @Test
    public void apply_empty() {
        assertEquals("", reverseWords.apply(""));
    }

    @Test
    public void apply_plainText() {
        assertEquals("anoineimaZ ćśonjelok retil", reverseWords.apply("Zamieniona kolejność liter"));
    }

    @Test
    public void apply_textWithSpaces() {
        assertEquals(" anoineimaZ  ćśonjelok  retil  ", reverseWords.apply(" Zamieniona  kolejność  liter  "));
    }

    private ReverseLetters reverseWords;

}
