package pl.rsokol;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkEntityTest {

    @Test
    public void create() {
        final Bookmark bookmark = BookmarkEntity.of(PAGE_NAME, PAGE_URI);
        assertThat(bookmark.getPageName()).isEqualTo(PAGE_NAME);
        assertThat(bookmark.getPageUri()).isEqualTo(PAGE_URI);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createEmptyPageName() {
        BookmarkEntity.of("", PAGE_URI);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullPageName() {
        BookmarkEntity.of(null, PAGE_URI);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createEmptyPageUri() {
        BookmarkEntity.of(PAGE_NAME, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullPageUri() {
        BookmarkEntity.of(PAGE_NAME, null);
    }

    @Test
    public void equality() {
        EqualsVerifier.forClass(BookmarkEntity.class)
                      .verify();
    }

    private static final String PAGE_NAME = "Helion";
    private static final String PAGE_URI = "http://www.helion.pl/";

}
