package pl.rsokol;

import java.util.List;

public interface BookmarkService {

    void add(Bookmark bookmark)
            throws BookmarkNotFoundException,
                   DuplicateBookmarkException;

    void remove(Bookmark bookmark)
            throws BookmarkNotFoundException;

    List<Bookmark> getAll();

}
