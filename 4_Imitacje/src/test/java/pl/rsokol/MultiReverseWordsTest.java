package pl.rsokol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MultiReverseWordsTest {

    @Before
    public void before() {
        reversePhrase = mock(ReverseLetters.class);
        reverseWords = ReverseWords.with(reversePhrase);
    }

    @Test
    public void testReversePhrase() {
        when(reversePhrase.apply("Ala")).thenReturn("alA");
        when(reversePhrase.apply("ma")).thenReturn("am");
        when(reversePhrase.apply("kota")).thenReturn("atok");
        assertEquals(reverseWords.apply("Ala ma kota"), "alA am atok");
        verify(reversePhrase, times(3)).apply(any(String.class));
    }

    @Test
    public void testReverseUppercase() {
        when(reversePhrase.apply("Ala")).thenReturn("ALA");
        when(reversePhrase.apply("ma")).thenReturn("AM");
        when(reversePhrase.apply("kota")).thenReturn("ATOK");
        assertEquals(reverseWords.apply("Ala ma kota"), "ALA AM ATOK");
    }

    @Test
    public void testReverseForceUppercase() {
        when(reversePhrase.apply("ALA")).thenReturn("ALA");
        when(reversePhrase.apply("MA")).thenReturn("AM");
        when(reversePhrase.apply("KOTA")).thenReturn("ATOK");
        assertEquals(reverseWords.apply("ALA MA KOTA"), "ALA AM ATOK");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReverseForceUppercase_invalidData() {
        doAnswer(new Answer<String>() {
            @Override
            public String answer(final InvocationOnMock invocation) throws Throwable {
                final String phrase = (String) invocation.getArgument(0);
                final String uppercase = phrase.toUpperCase();
                if (!phrase.equals(uppercase)) {
                    throw new IllegalArgumentException();
                }
                throw new UnsupportedOperationException("Brak implementacji");
            }
        }).when(reversePhrase).apply(any(String.class));
        reverseWords.apply("Ala ma kota");
    }

    @Test
    public void testReversePhrase_wordReverserCalled() {
        when(reversePhrase.apply(any(String.class))).thenReturn("?");
        reverseWords.apply("Ala ma kota");
        verify(reversePhrase, atLeastOnce()).apply(any(String.class));
    }

    private ReverseLetters reversePhrase;
    private ReverseLetters reverseWords;

}
