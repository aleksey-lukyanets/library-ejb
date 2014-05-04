package libraryejb.dao;

import java.util.List;
import javax.ejb.Local;
import libraryejb.domain.Author;

/**
 * ДАО автора.
 */
@Local
public interface AuthorDAO {

    List<Author> getAll();

    Author getById(long authorId);
    
    Author getByName(String name);

    Author insert(Author author);
    
    Author remove(long authorId);
}
