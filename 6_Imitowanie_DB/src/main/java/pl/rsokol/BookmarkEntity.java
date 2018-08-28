package pl.rsokol;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKMARK")
@NamedQueries({
    @NamedQuery(name = BookmarkEntity.GET_ALL,
                query = "Select b From BookmarkEntity b")
})
public class BookmarkEntity implements Serializable, Bookmark {

    public static Bookmark of(final String pageName, final String pageUri) {
        return new BookmarkEntity(pageName, pageUri);
    }

    @Override
    public String getPageName() {
        return Strings.nullToEmpty(pageName);
    }

    @Override
    public String getPageUri() {
        return Strings.nullToEmpty(pageUri);
    }

    @Override
    public boolean equals(final Object rightSide) {
        if (!(rightSide instanceof Bookmark)) return false;
        final Bookmark that = (Bookmark) rightSide;
        return (this == that) || (
            getPageName().equals(that.getPageName()) &&
            getPageUri().equals(that.getPageUri())
        );
    }

    @Override
    public int hashCode() {
        return getPageName().hashCode() + getPageUri().hashCode();
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("pageName", pageName)
                .add("pageUri", pageUri)
                .toString();
    }

    /** Required by JPA. */
    protected BookmarkEntity() {
    }

    private BookmarkEntity(final String pageName, final String pageUri) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(pageName));
        Preconditions.checkArgument(!Strings.isNullOrEmpty(pageUri));
        this.pageName = pageName;
        this.pageUri = pageUri;
    }

    static final String GET_ALL = "BookmarkEntity.getAll";

    private @Id String pageName;
    private @Column String pageUri;

}
