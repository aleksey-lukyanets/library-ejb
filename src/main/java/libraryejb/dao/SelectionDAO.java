package libraryejb.dao;

import javax.ejb.Local;
import libraryejb.domain.Selection;

/**
 *
 */
public interface SelectionDAO {

    Selection get();

    void updateAuthor(Long authorId);

    void updateBook(Long bookId);
}
