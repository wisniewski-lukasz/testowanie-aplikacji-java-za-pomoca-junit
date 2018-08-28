package pl.rsokol;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookmarkServiceImplTest extends DatabaseTestSkeleton {

    @Before
    public void before() {
        bookmarkService = new BookmarkServiceImpl();
        bookmarkService.entityManager = getEntityManager();
    }

    @After
    public void after() {
        bookmarkService.entityManager.createNativeQuery("Delete From Bookmark").executeUpdate();
        bookmarkService.entityManager.clear();
    }

    @Test(expected = BookmarkNotFoundException.class)
    public void addNullBookmark() throws Exception {
        bookmarkService.add(null);
    }

    @Test
    public void add() throws Exception {
        final Bookmark bookmark = BookmarkEntity.of(PAGE_NAME, PAGE_URI);
        assertThat(bookmarkService.getAll()).isEmpty();
        bookmarkService.add(bookmark);
        assertThat(bookmarkService.getAll()).containsOnly(bookmark);
    }

    @Test(expected = DuplicateBookmarkException.class)
    public void addTwice() throws Exception {
        final Bookmark bookmark = BookmarkEntity.of(PAGE_NAME, PAGE_URI);
        assertThat(bookmarkService.getAll()).isEmpty();
        bookmarkService.add(bookmark);
        bookmarkService.add(bookmark);
    }

    @Test(expected = BookmarkNotFoundException.class)
    public void removeNull() throws Exception {
        bookmarkService.remove(null);
    }

    @Test(expected = BookmarkNotFoundException.class)
    public void removeNonexistent() throws Exception {
        final Bookmark bookmark = BookmarkEntity.of(PAGE_NAME, PAGE_URI);
        bookmarkService.remove(bookmark);
    }

    @Test
    public void remove() throws Exception {
        getEntityManager().createNativeQuery("Insert Into Bookmark (PageName, PageUri) "
                                  + "Values ('Strona testowa', 'http://www.helion.pl/')")
                          .executeUpdate();
        final Bookmark bookmark = BookmarkEntity.of(PAGE_NAME, PAGE_URI);
        assertThat(bookmarkService.getAll()).containsOnly(bookmark);
        bookmarkService.remove(bookmark);
        assertThat(bookmarkService.getAll()).isEmpty();
    }

    @Test
    public void getAll() throws Exception {
        final Bookmark bookmark = BookmarkEntity.of(PAGE_NAME, PAGE_URI);
        final Bookmark otherBookmark = BookmarkEntity.of("Inna strona", PAGE_URI);
        getEntityManager().createNativeQuery("Insert Into Bookmark (PageName, PageUri) Values "
                            + "('Strona testowa', 'http://www.helion.pl/'), "
                            + "('Inna strona', 'http://www.helion.pl/')")
                          .executeUpdate();
        assertThat(bookmarkService.getAll()).containsOnly(bookmark, otherBookmark);
    }

    private static final String PAGE_NAME = "Strona testowa";
    private static final String PAGE_URI = "http://www.helion.pl/";

    private BookmarkServiceImpl bookmarkService;

}
