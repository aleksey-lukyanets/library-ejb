package libraryejb.dao;

import java.util.List;
import javax.ejb.Remote;
import libraryejb.domain.Author;

/**
 * ДАО автора.
 */
@Remote
public interface AuthorDAO {

    List<Author> getAll();

    Author getById(long authorId);
    
    Author getByName(String name);

    Author insert(Author author);
    
    Author remove(long authorId);
}
