package libraryejb.service;

import javax.ejb.Local;
import libraryejb.domain.Selection;

/**
 *
 */
@Local
public interface SelectionService {

    Selection get();

    void updateAuthor(Long authorId);

    void updateBook(Long bookId);
}
