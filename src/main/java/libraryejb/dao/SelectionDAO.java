package libraryejb.dao;

import javax.ejb.Local;
import libraryejb.domain.Selection;

/**
 *
 */
@Local
public interface SelectionDAO {

    Selection get();

    void updateAuthor(Long authorId);

    void updateBook(Long bookId);
}
