package pl.rsokol;

import com.google.common.annotations.VisibleForTesting;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
@PermitAll
public class BookmarkServiceImpl implements BookmarkService {

    @Override
    public void add(final Bookmark bookmark) throws BookmarkNotFoundException, DuplicateBookmarkException {
        if (bookmark == null) throw new BookmarkNotFoundException();
        BookmarkEntity entity = entityManager.find(BookmarkEntity.class, bookmark.getPageName());
        if (entity != null) throw new DuplicateBookmarkException();
        entity = (BookmarkEntity) BookmarkEntity.of(bookmark.getPageName(), bookmark.getPageUri());
        entityManager.persist(entity);
    }

    @Override
    public void remove(final Bookmark bookmark) throws BookmarkNotFoundException {
        if (bookmark != null) {
            final BookmarkEntity entity = entityManager.find(BookmarkEntity.class, bookmark.getPageName());
            if (entity != null) {
                entityManager.remove(entity);
                return;
            }
        }
        throw new BookmarkNotFoundException();
    }

    @Override
    public List<Bookmark> getAll() {
        return (List<Bookmark>) (List<?>)
                    entityManager.createNamedQuery(BookmarkEntity.GET_ALL, BookmarkEntity.class)
                                 .getResultList();
    }

    @Inject @VisibleForTesting EntityManager entityManager;

}
